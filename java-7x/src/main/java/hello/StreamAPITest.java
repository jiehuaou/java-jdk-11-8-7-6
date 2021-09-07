package hello;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class StreamAPITest {

    public void foo() {
        ForkJoinPool fj = ForkJoinPool.commonPool();
    }

    public static void main(String[] args) {
        List<Integer> aList = Arrays.asList(6,88,23,14,17,12,32,51,79,94);

        List<Integer> last = aList.stream().filter(x -> x>60).collect(Collectors.toList());
        System.out.println(last);
    }
}
