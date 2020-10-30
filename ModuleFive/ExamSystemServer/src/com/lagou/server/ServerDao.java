package ModuleFive.ExamSystemServer.src.com.lagou.server;


import ModuleFive.ExamSystemClient.src.com.lagou.model.Student;
import ModuleFive.ExamSystemClient.src.com.lagou.model.Test;
import ModuleFive.ExamSystemClient.src.com.lagou.model.User;


import java.util.Collections;
import java.util.List;

/**
 * 编程实现数据的存取
 */
public class ServerDao {

    /**
     * 编程实现管理员账号和密码的校验并将结果返回出去
     * @param user
     * @return
     */
    public boolean serverManagerCheck(User user) {
        if ("admin".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean studentCheck(List<User> uList, User user){
        for(User uu : uList){
            if(uu.getUserName().equals(user.getUserName())){
                return true;
            }
        }
        return false;
    }

    public List<User> addStudentLogin(List<User> l, User s){
        if(l.isEmpty()){
            l.add(s);
        }else {
            for (User st : l) {
                if (st.getUserName() != s.getUserName()) {
                    l.add(s);
                    break;
                }
            }
        }
        return l;

    }

    public void updatePassword(List<User> l, String name, User newS){
        for(User s : l){
            if(s.getUserName() == name ){
                int i = l.indexOf(s);
                l.set(i,newS);
            }
        }
    }


    /**
     * 增加学生信息
     * @param l
     * @param s
     * @return
     */
    public List<Student> addStudent(List<Student> l, Student s){
        if(l.isEmpty()){
            l.add(s);
        }else {
            for (Student st : l) {
                if (st.getId() != s.getId()) {
                    l.add(s);
                    break;
                }
                System.out.println("学号已存在");
            }
        }
        return l;

    }

    public void delete(List<Student> l, int id){
        for(Student s : l){

            if(id == s.getId() ){

                l.remove(s);
            }
        }

    }

    public void update(List<Student> l, int id, Student newS){
        for(Student s : l){
            if(id == s.getId() ){
                int i = l.indexOf(s);
                l.set(i,newS);
            }
        }
    }


    public Student search(List<Student> l, int id){
        for(Student s : l){
            if(id == s.getId() ){
                System.out.println(s);
                return s;

            }
        }
        System.out.println("不存在");
        return (Student) Collections.emptyList();
    }


    public List<Test> addTest(List<Test> l, Test s){
        if(l.isEmpty()){
            l.add(s);
        }else {
            for (Test st : l) {
                if (st.getId() != s.getId()) {
                    l.add(s);
                    break;
                }
                System.out.println("id已存在");
            }
        }
        return l;

    }

    public void deleteTest(List<Test> l, int id){
        for(Test s : l){

            if(id == s.getId() ){

                l.remove(s);
            }
        }

    }

    public void updateTest(List<Test> l, int id, Test newS){
        for(Test s : l){
            if(id == s.getId() ){
                int i = l.indexOf(s);
                l.set(i,newS);
            }
        }
    }


    public Test searchTest(List<Test> l, int id){
        for(Test s : l){
            if(id == s.getId() ){
                System.out.println(s);
                return s;

            }
        }
        System.out.println("不存在");
        return (Test) Collections.emptyList();
    }


}
