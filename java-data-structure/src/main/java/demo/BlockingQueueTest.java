package demo;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

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
        test_blocking_3();
    }
}
