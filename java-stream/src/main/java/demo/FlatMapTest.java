package demo;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FlatMapTest {

    /**
     * join all element from array of array
     */
    @Test
    public  void array_of_array1()
    {
        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

        List<String> listOfAllChars = Arrays.stream(dataArray)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println(listOfAllChars);
    }

    /**
     * join all element from array of array then sort
     */
    @Test
    public  void array_of_array2() {
        int[][] a = new int[][] {{1,2,7}, {4, 5, 6}, {3, 8, 9}};
        Arrays.stream(a)
                .flatMapToInt(x -> Arrays.stream(x))
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public  void array_of_array3() {
        Integer[][] a = new Integer[][] {{1,2,3}, {4, 5, 6}, {7, 8, 9}};
        Arrays.stream(a)
                .flatMap(x -> Arrays.stream(x))
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void array_of_array4() {

        Integer [] a1 = new Integer[]{3,2,1};
        Integer [] a2 = new Integer[]{9,8,7};
        List<Integer[]> list =  Arrays.asList(a2,a1);
        list.stream().flatMap(Stream::of)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test_int_stream() {

        IntStream st = Arrays.stream(new int[]{1,2,3});

        List<Integer> ss = st.mapToObj(x -> Integer.valueOf(x)).collect(Collectors.toList());
        Collections.reverse(ss);
        System.out.println(ss);
    }
}
