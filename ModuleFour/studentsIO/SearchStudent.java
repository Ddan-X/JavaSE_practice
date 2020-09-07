package ModuleFour.studentsIO;

import java.util.List;

public class SearchStudent {

    public static Student search(List<Student> l, int id){
        for(Student s : l){
            if(id == s.getId() ){
                System.out.println(s);
                return s;

            }
        }
        System.out.println("不存在");
        return null;
    }
}
