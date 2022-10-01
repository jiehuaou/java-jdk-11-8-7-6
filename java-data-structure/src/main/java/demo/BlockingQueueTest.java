package demo;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * **ConcurrentLinkedQueue** is not a blocking queue. It does not implement the **BlockingQueue** interface,
 * and therefore does not provide the blocking methods put() and take(). These methods are necessary for a producer/consumer setup,
 * because you need to arrange for the consumer to block while there is nothing to consume,
 * and for the producer to block when the consumers don't consume quickly enough.
 */
public class BlockingQueueTest {
    public static void test_blocking_1(){
        Queue<Integer> queue = new LinkedBlockingDeque<>();
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        while (null!=queue.peek()){
            Integer e = queue.remove();
            System.out.println(e);
        }
        System.out.println("--------------");
    }
    public static void test_blocking_2(){
        Queue<Integer> queue = new ConcurrentLinkedDeque<>();
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        while (null!=queue.peek()){
            Integer e = queue.remove();
            System.out.println(e);
        }
        System.out.println("--------------");

    }

    public static void test_blocking_2a(){
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        while (null!=queue.peek()){
            Integer e = queue.remove();
            System.out.println(e);
        }
        System.out.println("--------------");

    }
    public static void test_blocking_3(){
        Queue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4); // will be ignored
        while (null!=queue.peek()){
            Integer e = queue.remove();
            System.out.println(e);
        }
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        test_blocking_1();
        test_blocking_2();
        test_blocking_2a();
        test_blocking_3();
    }
}
