package test;

public class Try_catch {
    public static void main(String[] args) {
        System.out.println(test());// 2
    }
    public static int test(){
        try{
            int ia =5;
            int ib=0;
            System.out.println(ia/ib);
            return 0;
        }catch(ArithmeticException e){
            e.printStackTrace();
            return 1;//返回1 之前 执行finally
        }finally {
            return 2;//由于程序执行过程中无论是否发生异常都会执行finally
        }

    }
}
