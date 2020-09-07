package ModuleFour.studentsIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * ObjectInputStream：用于从输入流中一次性将对象整体读取出来
 *  反序列化 将有效组织的字节序列恢复为一个对象及相关信息的转化过程
 */
public class ReadStudent {

    public static List<Student> read(){
        ObjectInputStream ois = null;
        List<ModuleFour.studentsIO.Student> studentList =null;
        try {
            // 1.创建ObjectInputStream类型的对象与ModuleFour/studentsIO/student.txt
            ois = new ObjectInputStream(new FileInputStream("./src/ModuleFour/studentsIO/student.txt"));
            // 2.从输入流中读取一个对象并打印
             Object obj = ois.readObject();
             studentList = (List<Student>)obj;
            System.out.println("student information：" + obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentList;
    }
}
