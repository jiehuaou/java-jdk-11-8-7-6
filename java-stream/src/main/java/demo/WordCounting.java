package demo;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * counting on each word
 */
public class WordCounting {

    /**
     * use toMap( e->key, e->1, when_duplicated (v1,v2)->v1+v2 )
     */
    @Test
    public void sample1() {
        List<String> ls = Arrays.asList("abc", "hello", "hello", "world");

        Map<String, Integer> list = ls.stream().collect(toMap(
                w->w,  // lambda to map key
                w->1,  // lambda to map value
                (a,b)->a+b  // lambda to merge value when same key
                , TreeMap::new  // optional, mapFactory
        ));

        System.out.println(list);
    }

    /**
     * use groupingBy( e->key, reducing(...))
     *                                   \
     *                                   reducing(0, e->1, merge(a,b)->a+b )
     */
    @Test
    public void sample2() {
        List<String> ls = Arrays.asList("abc", "hello", "hello", "world");

        Map<String, Integer> list = ls.stream().collect(
                groupingBy(
                        e->e,
                        //Collectors.counting()
                        Collectors.reducing(0, e->1, (a,b)->a+b)
                )
        );

        System.out.println(list);
    }

    /**
     * convert string to tuple(str, 1) first, then combine the value by key
     */
    @Test
    public void sample3() {
        List<String> ls = Arrays.asList("abc", "hello", "hello", "world");

        Map<String, Integer> list = ls.stream()
                .map(e-> new AbstractMap.SimpleEntry<String, Integer>(e, 1))
                .collect(
                        toMap(
                            e->e.getKey(),
                            e-> e.getValue(),
                            (v1,v2)->v1+v2
                        )
                )
        ;

        System.out.println(list);
    }

    /**
     * use groupingBy( e->key, reducing(...))
     *                            \
     *                           mapping( e->1, merge(a,b)->a+b )
     */
    @Test
    public void sample4() {
        List<String> ls = Arrays.asList("abc", "hello", "hello", "world");

        Map<String, Integer> list = ls.stream()
                .collect(
                        groupingBy(
                                e->e,
                                Collectors.mapping(
                                        e->1,
                                        Collectors.reducing(0, (a,b)->a+b)
                                )
//                                Collectors.summingInt(e->1)
//                                Collectors.counting()
//                                Collectors.reducing(0, e->1, (a,b)->a+b)
                        )
                )
                ;

        System.out.println(list);
    }

    void other(){
        HashMap<Object, Integer> data = new HashMap<>();
        data.put(new Object(), 1);
    }
}
