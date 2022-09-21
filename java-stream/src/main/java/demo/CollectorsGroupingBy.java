package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * combine address by name
 */
public class CollectorsGroupingBy {
    public static void main(String[] args) {
        List<UserPojo> ls = Arrays.asList(
                new UserPojo("Albert","home1"),
                new UserPojo("Roy", "home2"),
                new UserPojo("Roy","home3"),
                new UserPojo("Leon", "home4"),
                new UserPojo("Roy","home6")
        );


        Map<String, String>list = ls.stream().collect(
                Collectors.groupingBy(
                        e->e.name,
                        Collectors.mapping(
                                e->e.address,
                                Collectors.joining(",")
                                //Collectors.toList()
                        )
                )
        );

        System.out.println(list);

    }
}
