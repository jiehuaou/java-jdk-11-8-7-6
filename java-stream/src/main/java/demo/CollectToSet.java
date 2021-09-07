package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * get all name from object list
 */
public class CollectToSet {
    public static void main(String[] args) {
        List<UserPojo> ls = Arrays.asList(
                new UserPojo("Albert","home1"),
                new UserPojo("Roy", "home2"),
                new UserPojo("Roy","home3"),
                new UserPojo("Leon", "home4")
        );

        Set<String> list = ls.stream()
                .map(e->e.name.toLowerCase())
                .collect(
                        Collectors.toSet()
                );

        System.out.println(list);
    }
}
