package hello;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test03 {
    public static void main(String[] args) {
        HashMap<String, String> hash1 = new HashMap<>();
        hash1.put("001", "001");
        hash1.put(null, "002");
        hash1.put(null, "003");
        hash1.put("004", null);

        for(Iterator<Map.Entry<String,String>> kit = hash1.entrySet().iterator(); kit.hasNext();){
            Map.Entry<String,String> k = kit.next();
            System.out.println("" + k.getKey() + " -- " + k.getValue());
            //hash1.remove(k);  // remove key
        }
    }
}
