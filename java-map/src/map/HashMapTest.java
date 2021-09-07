package map;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(5, 300f);
        init(map);
        map.put(Integer.valueOf(500), "world");

        System.out.println(map);
    }

    static void init(HashMap<Integer, String> map){
        for (int i = 0; i < 5000; i++) {
            map.put(Integer.valueOf(i), "hello");
        }
    }
}
