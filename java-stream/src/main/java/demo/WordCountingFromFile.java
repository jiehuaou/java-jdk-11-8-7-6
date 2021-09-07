package demo;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toMap;

public class WordCountingFromFile {
    public static void main(String[] args) throws Exception {

        URI file =  WordCountingFromFile.class.getResource("/hello.txt").toURI();

        Stream<String> st = Files.lines(Paths.get(file))
                .flatMap(ln-> Arrays.stream(ln.split(" ")));

        //st.forEach(System.out::println);

        Map<String, Integer> ret1 = st.collect(Collectors.toMap(
                        e->e,  // key lambda
                        e->1,  // 1
                        (a,b)->a+b  // merge lambda
                    , TreeMap::new
                ));

        System.out.println(ret1);

    }
}
