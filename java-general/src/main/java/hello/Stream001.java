package hello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Stream001 {
    public static void main(String[] args) {
        List<Integer> ins = Arrays.asList(1,2,3,4,5);
        IntStream is = ins.stream().mapToInt(x->x);
        is.forEach(System.out::println);

        IntStream.range(1,10).forEach(System.out::println);
    }

}
