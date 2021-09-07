import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    // Resizable-array implementation of the Deque interface. Array deques have no capacity restrictions;
    //  This class is likely to be faster than Stack when used as a stack,
    //  and faster than LinkedList when used as a queue.
    public static void test_queue() {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Integer e = null;
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("----------ArrayDeque");
    }

    // Doubly-linked list implementation of the List and Deque interfaces.
    // Note that this implementation is not synchronized.
    public static void test_queue2() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Integer e = null;
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println("----------LinkedList");
    }

    public static void test_queue3() {
        Queue<Integer> queue = new LinkedList<>();

    }

    public static void main(String[] args) {
        test_queue();
        test_queue2();
    }
}
