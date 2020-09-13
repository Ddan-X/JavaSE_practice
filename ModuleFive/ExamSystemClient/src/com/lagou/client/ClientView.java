package ModuleFive.ExamSystemClient.src.com.lagou.client;



import ModuleFive.ExamSystemClient.src.com.lagou.model.Student;
import ModuleFive.ExamSystemClient.src.com.lagou.model.User;
import ModuleFive.ExamSystemClient.src.com.lagou.model.UserMessage;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 编程实现客户端的主界面绘制和相应功能的实现
 */
public class ClientView {


    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */
    public ClientView(ClientInitClose cic) {
        this.cic = cic;
    }

    /**
     * 自定义成员方法实现客户端主界面的绘制
     */
    public void clientMainPage() throws IOException, ClassNotFoundException {
        while(true) {
            System.out.println("  \n\n\t\t   在线考试系统");
            System.out.println("-------------------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("-------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
            //Scanner sc = new Scanner(System.in);
            //int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入学员系统...");
                    break;
                case 2:
                    clientManagerLogin();
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 自定义成员方法实现客户端管理员登录的功能
     */
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 1.准备管理员登录的相关数据
        System.out.println("请输入管理员的账户信息：");
        String userName = ClientScanner.getScanner().next();
        System.out.println("请输入管理员的密码信息：");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("managerCheck", new User(userName, password));
        // 2.将UserMessage类型的对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        cic.getOos().flush();
        System.out.println("客户端发送管理员账户信息成功！");
        // 3.接收服务器的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        boolean flag =true;
        if ("success".equals(tum.getType())) {
            while (flag){
                System.out.println("登录成功，欢迎使用！ 1 进入学生管理模块， 2 进入考题管理模块，0 退出系统： ");
                int choose = ClientScanner.getScanner().nextInt();
                switch (choose){
                    case 1:
                        studentManager();
                        break;
                    case 2:
                        System.out.println();
                        break;
                    case 0:
                        System.out.println("正在退出系统...");
                        flag = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新选择！");

                }
            }

        }
    }

    /**
     * 学员管理模块：增加学员、删除学员、修改学员、查找学员
     * 考题管理模块：增加考题、删除考题、修改考题、查找考题、导入考题（选）
     *
     */
    private void studentManager() throws IOException, ClassNotFoundException {
        boolean flag = true;
        while (flag){
        System.out.println("1 增加学员、2 删除学员、3 修改学员、4 查找学员 ，0返回");
        int choice = ClientScanner.getScanner().nextInt();
        cic.getOos().write(choice);
        switch (choice) {
            case 1:
                System.out.println("增加学生信息！");
                System.out.println("输入学生姓名：");
                String name = ClientScanner.getScanner().next();
                System.out.println("输入学生年龄：");
                int age = ClientScanner.getScanner().nextInt();
                System.out.println("输入学生学号：");
                int id = ClientScanner.getScanner().nextInt();
                Student s = new Student(id, name, age);
                cic.getOos().writeObject(s);
                cic.getOos().flush();
                System.out.println(cic.getOis().readObject());
                break;
            case 2:
                System.out.println("输入学生学号：");
                id = ClientScanner.getScanner().nextInt();
                cic.getOos().write(id);
                cic.getOos().flush();
              //  DeleteStudent.delete(studentList, id);
                System.out.println(cic.getOis().readObject());
                break;
            case 3:
                System.out.println("输入需要修改学生学号：");
                id = ClientScanner.getScanner().nextInt();
                //SearchStudent.search(studentList, id);
                System.out.println("开始修改学生信息！");
                System.out.println("输入学生姓名：");
                name = ClientScanner.getScanner().next();
                System.out.println("输入学生年龄：");
                age = ClientScanner.getScanner().nextInt();
                System.out.println("输入学生学号：");
                int newId = ClientScanner.getScanner().nextInt();

                Student newS = new Student(newId, name, age);
                cic.getOos().writeObject(newS);
                cic.getOos().flush();
               // UpdateStudent.update(studentList, id, newS);
                System.out.println(cic.getOis().readObject());
                break;
            case 4:
                System.out.println("输入查找学生学号：");
                id = ClientScanner.getScanner().nextInt();
               // SearchStudent.search(studentList, id);
                cic.getOos().write(id);
                cic.getOos().flush();

                System.out.println(cic.getOis().readObject());
                break;
            case 5:
               // ShowStudent.showAll(studentList);

                break;
            default:
                //WriteStudents.write(studentList);
                flag=false;
                break;

        }
        }
    }
}
