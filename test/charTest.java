package test;

import java.util.Scanner;

public class charTest {
    public static void main(String[] args) {
        for(int i =0; i< 16;i++){
            System.out.print(Long.toHexString(i)+" ");//利用十六进制，打印

        }
        System.out.println("enter: 0 1 2 3 4 5 6 7 8 9 a b c d e f: ");
        Scanner scanner = new Scanner(System.in);
        String c = scanner.next();
        int l= c.length();
        System.out.println(l);
        String la = "0123456789abcdef";
        int x,y;

//        byte[] b = d.getBytes();
//        System.out.println(b[0]);
//
//        System.out.println("d: "+d+" "+ d.indexOf("0123456789abcdef")+" "+la.indexOf(d)+" "+la.contains(d));

        boolean empty = true;

        do{

            String d =c.substring(0,1);
            String e =c.substring(1,2);

            empty = la.contains(d)&&la.contains(e);

            System.out.println("1: "+empty);
            while (!empty){
                c = scanner.next();
                d =c.substring(0,1);
                e =c.substring(1,2);
                empty = la.contains(d)&&la.contains(e);
            }
            y=Integer.parseInt(d, 16);

            x =Integer.parseInt(e, 16);

            empty = c.length()==2;

            System.out.println("2: "+empty);

        }while (!empty);

        System.out.println("y: "+y+"  x:"+ x);
        System.out.println("-----------------------------------------------");
        String str1 = new String("world");
        System.out.println("str1 = " + str1); // world

        System.out.println("-----------------------------------------------");
        // 2.实现将String类型转换为byte数组类型并打印
        // 思路：先将字符串拆分为字符，将再每个字符转换为byte类型，也就是获取所有字符的ASCII
        byte[] bArr = str1.getBytes();
        for (int i = 0; i < bArr.length; i++) {
            System.out.println("下标为i的元素是：" + bArr[i]);
        }
        // 将byte数组转回String类型并打印
        String str2 = new String(bArr);
        System.out.println("转回字符串为：" + str2); // world
        System.out.println("-----------------------------------------------");
        String sr = "hello";
        String sr1 ="hello";
        System.out.println(sr==sr1);
        sr.indexOf("l");
    }
}
