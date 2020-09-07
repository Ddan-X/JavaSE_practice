package ModuleFour.studentsIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * ObjectOutputStream类：主要用于将一个对象的所有内容整体写入到输出流中
 *      只能将支持 java.io.Serializable 接口的对象写入流中
 */
public class WriteStudents {

    public static void write(List<Student> studentList){
        ObjectOutputStream oos = null;

        try {
            // 1.创建ObjectOutputStream类型的对象与 ModuleFour/studentsIO/student.txt
            oos = new ObjectOutputStream(new FileOutputStream("./src/ModuleFour/studentsIO/student.txt"));

            // 3.将整个list对象写入输出流
            oos.writeObject(studentList);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
