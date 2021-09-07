package hello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamReduce {
    public static void main(String[] args) {
        List<Integer> ins = Arrays.asList(1,2,3,4,5);
        Integer ret = ins.stream().reduce(0, (a,b)-> a+b);
        System.out.println(ins.toArray() + " a+b  =  " + ret.toString());
    }
}
