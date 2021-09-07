package hello;

import java.util.Hashtable;

public class HashTable001 {
    public static void main(String[] args) {
        Hashtable<String, String> ht = new Hashtable();
        for (int i = 0; i < 10; i++) {
            ht.put("a"+i, "x"+i);
        }


    }
}
