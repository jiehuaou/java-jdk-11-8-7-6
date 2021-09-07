package demo;

import java.util.Arrays;
import java.util.List;

/**
 * reduce: sum of int list
 */
public class StreamReduce {
    public static void main(String[] args) {
        List<Integer> ins = Arrays.asList(1,2,3,4,5);
        Integer ret = ins.stream().reduce(0, (a,b)-> a+b);
        System.out.println(ins.toString() + ", (a,b)-> a+b  =  " + ret.toString());
    }
}
