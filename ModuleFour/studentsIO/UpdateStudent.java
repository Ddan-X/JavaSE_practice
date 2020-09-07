package ModuleFour.studentsIO;

import java.util.List;

public class UpdateStudent {

    public static void update(List<Student> l, int id, Student newS){
        for(Student s : l){
            if(id == s.getId() ){
                int i = l.indexOf(s);
                l.set(i,newS);
            }
        }
    }
}
