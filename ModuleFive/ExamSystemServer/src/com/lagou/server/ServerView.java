package ModuleFive.ExamSystemServer.src.com.lagou.server;



import ModuleFive.ExamSystemClient.src.com.lagou.model.Student;
import ModuleFive.ExamSystemClient.src.com.lagou.model.UserMessage;

import java.io.IOException;
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
            while (true){
                modifyStudentReceive();
            }
        }
    }

    public void modifyStudentReceive() throws IOException, ClassNotFoundException {
        //boolean flag = true;

        int choose = sic.getOis().read();
        switch (choose) {
            case 1:
                Student st = (Student) sic.getOis().readObject();
                studentList = sd.addStudent(studentList, st);
                for(Student s : studentList){
                    System.out.println(s);
                }
                //sic.getOos().writeObject(studentList);
                System.out.println("增加学生成功");
                sic.getOos().writeObject("增加学生成功");
                sic.getOos().flush();
                break;
            case 2:
                int id = sic.getOis().read();
                sd.delete(studentList, id);
                sic.getOos().writeObject("删除学生成功");
                sic.getOos().flush();
                break;
            case 3:
                id = sic.getOis().read();
                Student newS = (Student) sic.getOis().readObject();
                sd.update(studentList, id, newS);
                sic.getOos().writeObject("修改学生成功");
                sic.getOos().flush();
                break;
            case 4:
                id = sic.getOis().read();
                st = sd.search(studentList, id);
                if (st.equals(null)) {
                    sic.getOos().writeObject("不存在");
                    sic.getOos().flush();
                }else {
                    sic.getOos().writeObject(st);
                    sic.getOos().flush();
                }
                break;
            default:
                //flag = false;
                break;
        //}

        }
    }

}
