package test;

public class VeriableAddressTest {
    public static void main(String[] args) {
        int[] arr = new int[]{10,20};
        System.out.println("arr=" + arr[0]);
        System.out.println("-----------------------------------------------------------");
        int[] arr2 = new int[]{5,8};
        show2(arr,arr2);
        System.out.println(arr+", 地址, arr2: "+arr2);
    }
    static void show2(int[] arr1,int[] arr2) {//调用引用类型
        //arr1 = new int[2];  // 加上改行代码后，相当于arr1在堆区中又重新申请一块内存空间
        arr1[0] = 200;// 只有这一行，没有上面的arr1 = new int[2];  会打印出 arr[0] =200, arr0[0] =200
        arr2 = arr1;
        System.out.println("在show2方法中：arr1[0] = " + arr1[0]+"; arr1地址: "+arr1+" , arr2地址= "+arr2); // 200  200
    }
}
