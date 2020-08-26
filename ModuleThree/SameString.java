package ModuleThree;

/*
 * 编程获取两个指定字符串中的最大相同子串并打印出来。
 *
 *  如： s1="asdafghjka", s2="aaasdfg" 他们的最大子串为"asd"
 *  提示： 将短的那个串进行长度依次递减的子串与较长的串比较。
 */
public class SameString {
    public static void main(String[] args) {
        String s1 = "asdafghjka";
        String s2="aaasdfg";
        findSubString(s1,s2);

        String s3 ="returnjavaworld";
        String s4= "worjavadabc";
        findSubString(s3,s4);

    }

    public static String findSubString(String s1,String s2){
        String max = s1.length()>=s2.length()? s1:s2;
        String min = s1.length()>=s2.length()? s2:s1;

        //aaasdfg                   i=0 [0,length]
        //aasdfg aaasdf             i=1 [1,length]   [0, length-1]
        //asdfg  aasdf aaasd        i=2 [2, length]  [1, length-1] [0, length-2]
        //sdfg   asdf  aasd   aaas  i=3 [3, length] [2,length-1] [1, length-2] [0, length-3]

        //外层循环行数
        for(int i=0;i<min.length();i++){
            //循环列数
            for(int j=0, l=min.length()-i; j<=i ;j++,l++){

                String sub = s2.substring(j,l);

                if(max.contains(sub)){ //判断是否包含
                    //长度依次递减, 返回的第一个就是最长的
                    System.out.println("最大子串"+ sub);
                    return sub;
                }
            }

        }
        return null;
    }
}
