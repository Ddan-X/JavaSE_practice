package ModuleFour.studentsIO;

/**
 *  基于学生信息管理系统增加以下两个功能：
 *
 *    a.自定义学号异常类和年龄异常类，并在该成员变量不合理时产生异常对象并抛出。
 *
 *    b.当系统退出时将 List 集合中所有学生信息写入到文件中，当系统启动时读取文件中所有学生信息到 List 集合中。
 *
 *    相当于写读对象，Object，会用到ObjectOutputStream and ObjectInputStream
 *    ObjectOutputStream类：主要用于将一个对象的所有内容整体写入到输出流中
 *    只能将支持 java.io.Serializable 接口的对象写入流中
 *    所以这里Student 实现 java.io.Serializable 接口以启用序列化功能
 *    ObjectInputStream：用于从输入流中一次性将对象整体读取出来
 *    反序列化 将有效组织的字节序列恢复为一个对象及相关信息的转化过程
 *
 * @author Dan
 */
public class Student implements java.io.Serializable{

    //序列化：将一个对象需要存储的相关信息有效组织成字节序列的转化过程
    private static final long serialVersionUID = -5664623228837363469L;
    // 1.私有化成员变量，使用private关键字修饰
    // private关键字修饰表示私有的含义，也就是该成员变量只能在当前类的内部使用
    private int id;       // 用于描述学号的成员变量
    private String name;  // 用于描述姓名的成员变量
    private int age;

    // 3.在公有的构造方法中调用set方法进行合理值的判断
    public Student() {}
    public Student(int id, String name, int age) {
        //this.id = id;
        //this.name = name;
        setId(id);
        setName(name);
        setAge(age);
    }

    // 2.提供公有的get和set方法，并在方法体中进行合理值的判断
    // 使用public关键字修饰表示公有的含义，也就是该方法可以在任意位置使用
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id > 0) {
            this.id = id;
        } else {
            //System.out.println("学号不合理哦！！！");
            try{
                throw new IdException("学号不合理哦");
            }catch (IdException idException){
                idException.printStackTrace();
            }
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if(age > 0) {
            this.age = age;
        } else {
            //System.out.println("年龄不合理哦！！！");
            try {
                throw new AgeException("年龄不合理哦！！！");
            }catch (AgeException ageException){
                ageException.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
