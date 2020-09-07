package ModuleFour.studentsIO;


import java.util.List;

public class AddStudent{


    /**
     * 增加学生信息
     * @param l
     * @param s
     */
    public static List<Student> add(List<Student> l, Student s){
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


}
