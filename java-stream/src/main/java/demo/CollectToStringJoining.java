package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectToStringJoining {
    public static void main(String[] args) {
        List<UserPojo> ls = Arrays.asList(
                new UserPojo("Albert","home1"),
                new UserPojo("Roy", "home2"),
                new UserPojo("Roy","home3"),
                new UserPojo("Leon", "home4")
        );

        String list = ls.stream()
                .map(e-> e.address)
                .collect(
                        Collectors.joining(",")
                );

        System.out.println(list);
    }
}
