package ModuleThree;

/**
 * 编程统计字符串"ABCD123!@#$%ab"中大写字母、小写字母、数字、其它字符的个数并打 印出来。
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "ABCD123!@#$%ab";

        String upCase = "[A-Z]";
        String lowerCase ="[a-z]";
        String number = "[0-9]";
        String symbol = "\\W";


        //声明变量 统计个数
        int up = 0;
        int low = 0;
        int num = 0;
        int sym = 0;

        //将字符串 转换 char 数组
       char[] cha = str.toCharArray();

       for(char c : cha){

           String s = String.valueOf(c); //单个char字符 转换为 string

            //判断匹配正则表达式
           if(s.matches(upCase)){
               up++;
               System.out.print(" 大写："+s);
           }else if(s.matches(lowerCase)){
               low++;
               System.out.print(" 小写："+s);
           }else if(s.matches(number)){
               num++;
               System.out.print(" 数字：" +s);

           }else if(s.matches(symbol)){
               sym++;
               System.out.print(" 符号：" +s);
           }

       }
        System.out.println();
        System.out.println("upcase: " +up + ", lower: "+low+", number: " +num + ", sym:"+ sym);

    }

}
