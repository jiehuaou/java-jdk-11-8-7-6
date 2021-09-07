package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * transform one list to another kind list
 */
public class CollectToList {
    public static void main(String[] args) {
        List<UserPojo> ls = Arrays.asList(
                new UserPojo("Albert","home1"),
                new UserPojo("Roy", "home2"),
                new UserPojo("Roy","home3"),
                new UserPojo("Leon", "home4")
        );

        // map userPojo -> address, with specific collection class
        List<String> ret1 = ls.stream().map(e->e.address).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(ret1);

        // map userPojo -> address, with default collection
        List<String> ret2 = ls.stream().map(e->e.address).collect(Collectors.toList());
        ret2.add("home-x");
        System.out.println(ret2);
    }
}
