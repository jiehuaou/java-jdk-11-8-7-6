package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * combine address by user name
 */
public class CollectToMap {

    public static void main(String[] args) {
        List<UserPojo> ls = Arrays.asList(
                new UserPojo("Albert","home1"),
                new UserPojo("Roy", "home2"),
                new UserPojo("Roy","home3"),
                new UserPojo("Leon", "home4")
        );

        Map<String, String> list = ls.stream().collect(Collectors.toMap(
                e->e.name,          // map key
                e->e.address,       // map value
                (s1,s2) -> s1 + "," + s2)  // mergeFunction on value when same key
        );

        System.out.println(list);
    }
}
