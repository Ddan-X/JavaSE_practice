package ModuleFive.ExamSystemServer.src.com.lagou.server;



import ModuleFive.ExamSystemClient.src.com.lagou.model.Student;
import ModuleFive.ExamSystemClient.src.com.lagou.model.Test;
import ModuleFive.ExamSystemClient.src.com.lagou.model.User;
import ModuleFive.ExamSystemClient.src.com.lagou.model.UserMessage;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 编程实现服务器的主功能
 */
public class ServerView {


    /**
     * 使用合成复用原则
     */

    private ServerInitClose sic;
    private ServerDao sd;
    public List<Student> studentList = new LinkedList<Student>();
    public List<Test> testList = new LinkedList<Test>();
    public List<User> userList = new LinkedList<User>();

    private int grade =0;
    public File students  = new File("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/student.txt");
    public File tests  = new File("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/test.txt");;
    public File users  = new File("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/user.txt");;


    /**
     * 通过构造方法实现成员变量的初始化
     * @param sic
     */
    public ServerView(ServerInitClose sic, ServerDao sd) {
        this.sic = sic;
        this.sd = sd;

    }

    /**
     * 自定义成员方法实现客户端发来消息的接收并处理
     */
    public void serverReceive() throws IOException, ClassNotFoundException {
        System.out.println("服务器运行");
        int a = sic.getOis().readInt();

        System.out.println("serverRec: " +a);

        if(1==a){
            System.out.println("用户登录模式");
            int ci = sic.getOis().readInt();
            System.out.println(ci);
            while (true){
                switch (ci){
                    case 1:
                        studentLogin();
                        break;
                    case 2:
                        startTest();
                        break;
                    default:
                        return;
                }
            }
        }else if (2 == a ){
            System.out.println("管理员");
            UserMessage tum = (UserMessage) sic.getOis().readObject();

            System.out.println("接收到的消息是：" + tum);
            // 调用方法实现管理员账号和密码信息的校验
            if (sd.serverManagerCheck(tum.getUser())) {
                tum.setType("success");

            } else {
                tum.setType("fail");
            }
            // 将校验结果发送给客户端
            sic.getOos().writeObject(tum);
            sic.getOos().flush();
            System.out.println("服务器发送校验结果成功！");

            if("success".equals(tum.getType())){
                int choose = sic.getOis().readInt();
                while (true){
                    switch (choose){
                        case 1:
                            modifyStudentReceive();
                            break;
                        case 2:
                            modifyTestReceive();
                            break;
                        default:
                            return;
                    }

                }
            }
        }else {
            System.out.println("退出");
        }

    }

    public void startTest() throws IOException {
        System.out.println("开始考试模块");

        if(tests.exists()){
            testList = readTest();
        }
        int c = sic.getOis().readInt();
        switch (c){
            case 1:
                System.out.println("题目：");
                sic.getOos().writeInt(testList.size());
                for(Test t : testList){
                    sic.getOos().writeUTF(t.getQuestion());
                    sic.getOos().flush();
                    String an = sic.getOis().readUTF();
                    if(an.equals(t.getCorrectAnswer())){
                        grade++;
                        System.out.println(grade);
                    }
                }

                break;
            case 2:
                sic.getOos().writeInt(grade);
                sic.getOos().flush();
                break;
            default:
                break;
        }
    }

    public void studentLogin() throws IOException, ClassNotFoundException {
        System.out.println("用户登录模块");
        if (users.exists()) {
            userList = readUser();
        }
        int c = sic.getOis().readInt();
        switch (c){
            case 1:
                User u = (User) sic.getOis().readObject();
                boolean check = sd.studentCheck(userList,u);
                if(check){
                    UserMessage um = new UserMessage("success",u);
                    sic.getOos().writeObject(um);
                    sic.getOos().flush();
                    System.out.println("登录");
                }else {
                    sic.getOos().writeObject("登录失败");
                    sic.getOos().flush();
                }
                break;
            case 2:
                String name = sic.getOis().readUTF();
                u = (User) sic.getOis().readObject();
                sd.updatePassword(userList,name,u);

                break;
            case 3:
                u = (User) sic.getOis().readObject();
                sd.addStudentLogin(userList,u);

                break;
            case 4:
                writeUser(userList);
                break;
            case 5:
                return;
        }
    }

    public void modifyStudentReceive() throws IOException, ClassNotFoundException {
        //boolean flag = true;
        System.out.println("管理员学生模块");
        if (students.exists()) {
            studentList = read();
        }

        int choose = sic.getOis().readInt();
       // System.out.println(choose);
        switch (choose) {
            case 1:
                Student st = (Student) sic.getOis().readObject();
                studentList = sd.addStudent(studentList, st);

                //sic.getOos().writeObject(studentList);
                System.out.println("增加学生成功");
                sic.getOos().writeObject("增加学生成功");
                sic.getOos().flush();
                break;
            case 2:
                int id = sic.getOis().readInt();
                sd.delete(studentList, id);
                sic.getOos().writeObject("删除学生成功");
                sic.getOos().flush();
                break;
            case 3:
                id = sic.getOis().readInt();
                Student newS = (Student) sic.getOis().readObject();
                sd.update(studentList, id, newS);
                sic.getOos().writeObject("修改学生成功");
                sic.getOos().flush();
                break;
            case 4:
                id = sic.getOis().readInt();
                System.out.println(id);
                st = sd.search(studentList, id);
                sic.getOos().writeObject(st);
                sic.getOos().flush();

                break;
            case 0:
               // choose = sic.getOis().readInt();
                //System.out.println(choose);
                write(studentList);
                break;
            default:
                break;
        //}

        }
    }


    public void modifyTestReceive() throws IOException, ClassNotFoundException {
        System.out.println("管理员考试模块");
        //boolean flag = true;
        if (tests.exists()) {
            testList = readTest();
        }

        int choose = sic.getOis().readInt();
        System.out.println(choose);
        switch (choose) {
            case 1:
                Test st = (Test) sic.getOis().readObject();
                testList = sd.addTest(testList, st);
                //sic.getOos().writeObject(studentList);
                System.out.println("增加成功");
                sic.getOos().writeObject("增加成功");
                sic.getOos().flush();
                break;
            case 2:
                int id = sic.getOis().readInt();
                sd.deleteTest(testList,id);
                sic.getOos().writeObject("删除成功");
                sic.getOos().flush();
                break;
            case 3:
                id = sic.getOis().readInt();
                Test newT = (Test) sic.getOis().readObject();
                sd.updateTest(testList, id, newT);
                sic.getOos().writeObject("修改成功");
                sic.getOos().flush();
                break;
            case 4:
                id = sic.getOis().readInt();
                System.out.println(id);
                st = sd.searchTest(testList, id);
                sic.getOos().writeObject(st);
                sic.getOos().flush();

                break;
            case 0:
                writeTest(testList);
                System.out.println("保存");
                break;
            default:
                break;


        }
    }
    public void write(List<Student> studentList){
        ObjectOutputStream oos = null;

        try {
            // 1.创建ObjectOutputStream类型的对象与 ModuleFour/studentsIO/student.txt
          // oos = new ObjectOutputStream(new FileOutputStream("./src/ModuleFive/ExamSystemServer/src.com.lagou/test/student.txt"));
            oos = sic.getOos(new FileOutputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/student.txt"));
            // 3.将整个list对象写入输出流

            oos.writeObject(studentList);
            oos.flush();
            System.out.println("写入对象成功！");
            //oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Student> read(){
        ObjectInputStream ois = null;
        List<Student> studentList =null;
        try {
            // 1.创建ObjectInputStream类型的对象与ModuleFour/studentsIO/student.txt
            ois = sic.getOis(new FileInputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/student.txt"));
            // 2.从输入流中读取一个对象并打印
            Object obj = ois.readObject();
            studentList = (List<Student>)obj;
            System.out.println("student information：" + obj);
           // ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void writeTest(List<Test> testList){
        ObjectOutputStream oos = null;

        try {

            oos = sic.getOos(new FileOutputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/test.txt"));
            // 3.将整个list对象写入输出流
            oos.writeObject(testList);
            oos.flush();
            System.out.println("写入对象成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Test> readTest(){
        ObjectInputStream ois = null;
        List<Test> testsList =null;
        try {

            ois = sic.getOis(new FileInputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/test.txt"));
            // 2.从输入流中读取一个对象并打印
            Object obj = ois.readObject();
            testsList = (List<Test>)obj;
            System.out.println("test information：" + obj);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return testsList;
    }

    public void writeUser(List<User> userList){
        ObjectOutputStream oos = null;

        try {
            // 1.创建ObjectOutputStream类型的对象与 ModuleFour/studentsIO/student.txt
            //  oos = new ObjectOutputStream(new FileOutputStream("./src/ModuleFive/ExamSystemServer/src.com.lagou/test/student.txt"));
            oos = sic.getOos(new FileOutputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/user.txt"));
            // 3.将整个list对象写入输出流
            oos.writeObject(userList);
            oos.flush();
            System.out.println("写入对象成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> readUser(){
        ObjectInputStream ois = null;
        List<User> usersList =null;
        try {
            // 1.创建ObjectInputStream类型的对象与ModuleFour/studentsIO/student.txt
            ois = sic.getOis(new FileInputStream("./src/ModuleFive/ExamSystemServer/src/com/lagou/test/user.txt"));
            // 2.从输入流中读取一个对象并打印
            Object obj = ois.readObject();
            usersList = (List<User>)obj;
            System.out.println("user information：" + obj);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usersList;
    }
}

