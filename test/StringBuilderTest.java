package test;

public class StringBuilderTest {
    public static void main(String args[]) {
        StringBuilder sb1 = new StringBuilder("1");
        StringBuilder sb2 = new StringBuilder("2");

        System.out.println(sb1+","+sb2);   // 1， 2

        StringBuilder sb3 = sb2; //sb3 指向了 sb2 的地址，
        System.out.println("sb3: "+ sb3);//2
        show(sb1,sb2,sb3);
        System.out.println(sb1+","+sb2+","+sb3);   // 12， 2, 2
        System.out.println("-----------------------------------------------------------");
        insert(sb1,sb2,sb3);
        System.out.println(sb1+","+sb2+","+sb3);

        sb2.append("sb2地址"); // 只要sb2变化， sb3 就会跟着改变
        System.out.println(sb1+","+sb2+","+sb3); //12,HELLO2sb2地址,HELLO2sb2地址
        System.out.println("-----------------------------------------------------------");


        append(sb1,sb2,sb3);
        System.out.println(sb1+","+sb2+","+sb3);
        System.out.println("-----------------------------------------------------------");



        String st1 = "string";
        String st2 = st1;
        st1.toUpperCase(); //String 不可改变，调用方法 不会返回 改变后的值，
        String st3 = st1.toUpperCase(); // 需要重新定义新的参数
        System.out.println(st1+","+st2+","+st3); //string,string,STRING

        System.out.println("----------------------null------------------------------------");
        StringBuilder sb6 = null;
        StringBuffer sb7 =null;
        System.out.println(sb6+", "+sb7);

    }
    static void show(StringBuilder x, StringBuilder y, StringBuilder z){
        x.append(y);//StringBuilder 是可改变，调用方法时 就会返回 改变后的 值
        y=x;
        z=y;
        System.out.println("show:"+ x+","+y+","+z); //show:12,12,12
    }
    static void insert(StringBuilder x, StringBuilder y, StringBuilder z){
        y.insert(0,"HELLO");
        x=y;
        z=x;
        System.out.println("insert:"+ x+","+y+","+z);//insert:HELLO2,HELLO2,HELLO2
    }
     static StringBuilder append(StringBuilder x, StringBuilder y, StringBuilder z){
        x=y;
        z=x;
        System.out.println("StringBuilder append: "+ x+","+y+","+z);
        return x;
     }

}

