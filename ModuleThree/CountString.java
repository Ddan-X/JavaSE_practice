package ModuleThree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  准备一个 HashMap 集合，统计字符串"123,456,789,123,456"中每个数字字符串出现的次数并打印出来。
 *
 *  如：123 出现了 2 次 , 456 出现了 2 次   789 出现了 1 次
 */
public class CountString {

    public static void main(String[] args) {

        String str = "123,456,789,123,456";
        //字符串作为key值, 次数为value
        Map<String, Integer> m1 = new HashMap<>();

        String[]sArr=str.split(","); //分隔


        for (String s : sArr) {

            //key存在， value+1
            if (m1.containsKey(s)) {

                m1.put(s, m1.get(s) + 1);
            //不存在，value=1
            } else {

                m1.put(s, 1);
            }
        }

        //获取Map集合中所有的键值对并组成Set视图
        Set<Map.Entry<String, Integer>> entries = m1.entrySet();
        for (Map.Entry<String, Integer> me : entries) {
            System.out.println(me);
        }
    }
}
