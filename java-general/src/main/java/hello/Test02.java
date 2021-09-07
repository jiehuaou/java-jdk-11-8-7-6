package hello;

import java.util.HashMap;
import java.util.Iterator;

/**
 * test fail-fast
 */
public class Test02 {
    public static void main(String[] args) {
        HashMap<String, String> hash1 = new HashMap<>();
        hash1.put("001", "001");
        hash1.put("002", "002");
        hash1.put("003", "003");
        for(Iterator<String> kit = hash1.keySet().iterator(); kit.hasNext();){
            String k = kit.next();
            hash1.remove(k);  // remove key
        }
    }
}
