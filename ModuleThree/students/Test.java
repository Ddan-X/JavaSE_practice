package ModuleThree.students;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 使用 List 集合实现简易的学生信息管理系统，要求打印字符界面提示用户选择相应的功 能，根据用户输入的选择去实现增加、删除、修改、查找以及遍历所有学生信息的功能。
 */
public class Test {
    public static void main(String[] args) {
        List<Student> l = new LinkedList<>();
        System.out.println("简易的学生信息管理系统");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1增加,2删除,3修改,4查找, 5显示所有学生信息. 0退出 输入： ");
        int choice = scanner.nextInt();
        boolean f = true;
        while (f){
            switch (choice) {
                case 1:
                    System.out.println("增加学生信息！");
                    System.out.println("输入学生姓名：");
                    String name = scanner.next();
                    System.out.println("输入学生年龄：");
                    int age = scanner.nextInt();
                    System.out.println("输入学生学号：");
                    int id = scanner.nextInt();
                    Student s = new Student(id, name, age);
                    AddStudent.add(l,s);

                    break;
                case 2:
                    System.out.println("输入学生学号：");
                    id = scanner.nextInt();
                    DeleteStudent.delete(l, id);

                    break;
                case 3:
                    System.out.println("输入需要修改学生学号：");
                    id = scanner.nextInt();
                    SearchStudent.search(l, id);
                    System.out.println("开始修改学生信息！");
                    System.out.println("输入学生姓名：");
                    name = scanner.next();
                    System.out.println("输入学生年龄：");
                    age = scanner.nextInt();
                    System.out.println("输入学生学号：");
                    int newId = scanner.nextInt();
                    Student newS = new Student(newId, name, age);
                    UpdateStudent.update(l, id, newS);

                    break;
                case 4:
                    System.out.println("输入查找学生学号：");
                    id = scanner.nextInt();
                    SearchStudent.search(l, id);

                    break;
                case 5:
                    ShowStudent.showAll(l);

                    break;
                default:
                    f=false;
                    System.exit(0);//正常终止程序
                    break;


                }
                System.out.println("1增加,2删除,3修改,4查找, 5显示所有学生信息. 0退出 输入： ");
                choice = scanner.nextInt();

        }
    }
}
