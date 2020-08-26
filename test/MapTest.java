package test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name",null);
        map.put("name","ddd");
        System.out.println(map.size());
    }
}
