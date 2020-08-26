package ModuleThree.students;

import java.util.List;

public class DeleteStudent {


    public static void delete(List<Student> l, int id){
        for(Student s : l){

            if(id == s.getId() ){

                l.remove(s);
            }
        }

    }
}
