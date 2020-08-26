package test;

public class variableTest {
    public static void main(String[] args) {
        int ib =10;
        show1(ib);
        System.out.println("show1 ib= "+ib);

        int[] arr = new int[]{10,20};
        show2(arr);
        System.out.println("show2 arr=" + arr[0]);
        System.out.println("-----------------------------------------------------------");
        int[] arr2 = new int[]{5,8};
        show2(arr,arr2);
        System.out.println(arr+",,,,"+arr2);

        StringBuilder sb1 = new StringBuilder("1");
        StringBuilder sb2 = new StringBuilder("2");


        System.out.println(sb1+","+sb2);   // 1， 2

        StringBuilder sb3 = sb2; //sb3 指向了 sb2 的地址，
        show(sb1,sb2,sb3);
        System.out.println(sb1+","+sb2+","+sb3);   // 12， 2, 2
        System.out.println("-----------------------------------------------------------");

        StringBuilder sb4 = new StringBuilder("abc");
        StringBuilder sb5 = new StringBuilder("hjk");
        show(sb4,sb5);
        System.out.println(sb4+","+sb5);
        System.out.println("-----------------------------------------------------------");
    }

    static void show1(int ia) {//调用数据类型
        ia = 200;
        System.out.println("show方法中：ia = " + ia); // 打印 ia= 200
    }

    // 自定义成员方法打印参数传入的数组内容
    static void show2(int[] arr1) {//调用引用类型
        //arr1 = new int[2];  // 加上改行代码后，相当于arr1在堆区中又重新申请一块内存空间
        arr1[0] = 200;// 只有这一行，没有上面的arr1 = new int[2];  会打印出 arr[0] =200, arr0[0] =200
        System.out.println("show2方法中：arr1[0] = " + arr1[0]); // 200  200
    }

    static void show2(int[] arr1,int[] arr2) {//调用引用类型
        //arr1 = new int[2];  // 加上改行代码后，相当于arr1在堆区中又重新申请一块内存空间
        arr1[0] = 200;// 只有这一行，没有上面的arr1 = new int[2];  会打印出 arr[0] =200, arr0[0] =200
        arr2 = arr1;
        System.out.println("show2方法中：arr1[0] = " + arr1[0]+" arr2= "+arr2); // 200  200
    }
    static void show(StringBuilder a, StringBuilder b, StringBuilder c){
        a.append(b);//StringBuilder 是可改变，调用方法时 就会返回 改变后的 值
        b=a;
        c=b;
        System.out.println("show StringBuilder:"+ a+","+b+","+c); //show:12,12,12
    }

    static StringBuilder show(StringBuilder a, StringBuilder b){
        b=a;
        System.out.println("show StringBuilder:"+ a+","+b); //show:12,12,12
        return b;
    }
}