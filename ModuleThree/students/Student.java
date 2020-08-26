package ModuleThree.students;

/**
 * 使用 List 集合实现简易的学生信息管理系统，要求打印字符界面提示用户选择相应的功 能，根据用户输入的选择去实现增加、删除、修改、查找以及遍历所有学生信息的功能。
 *
 *  其中学生的信息有：学号、姓名、年龄。 要求： 尽量将功能拆分为多个.java 文件。
 */
public class Student {
    // 1.私有化成员变量，使用private关键字修饰
    // private关键字修饰表示私有的含义，也就是该成员变量只能在当前类的内部使用
    private int id;       // 用于描述学号的成员变量
    private String name;  // 用于描述姓名的成员变量
    private int age;

    // 3.在公有的构造方法中调用set方法进行合理值的判断
    public Student() {}
    public Student(int id, String name,int age) {
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
            System.out.println("学号不合理哦！！！");
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
            System.out.println("年龄不合理哦！！！");
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
