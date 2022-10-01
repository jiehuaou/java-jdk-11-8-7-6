package demo;

import org.testng.annotations.Test;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.assertEquals;

/**
 * put() Inserts the specified element into this queue, waiting if necessary for space to become available.
 * take() Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
 */
public class BlockingProducerConsumerTest {
    private static AtomicInteger produce = new AtomicInteger(0);
    private static AtomicInteger consume = new AtomicInteger(0);

    public static void sleep(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            // throw new RuntimeException(e);
        }
    }

    public static void yield() {
        try {
            Thread.yield();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }

    }

    public static class Producer implements Runnable {
        final BlockingQueue<Integer> queue;
        final int total = 50000;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < total; i++) {
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                produce.incrementAndGet();
            }


        }
    }

    public static class Consumer implements Runnable {
        final BlockingQueue<Integer> queue;
        private int total = 0;
        private int retry = 3;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
//            while (produce.get()==0) {
//                sleep(20);
//            }
            long id = Thread.currentThread().getId();
            Integer data = null;
            while (true) {
                try {
                    data = queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (data.intValue() == -1) {
                    System.out.println("Consumer ID-" + id + "  end with ================ " + total);
                    break;
                } else {
                    ++total;
                    consume.addAndGet(1);
                    if (0 == total % 15000)
                        System.out.println("Consumer ID-" + id + " ...... " + total);
                    yield();
                }
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        produce.set(0);
        consume.set(0);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Consumer(queue));
        exec.submit(new Consumer(queue));

        exec.shutdown();
        while (!exec.awaitTermination(2, TimeUnit.SECONDS)) {
            System.out.println("----");
            try {
                queue.put(-1);
                queue.put(-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        assertEquals(produce.get(), consume.get());
        System.out.printf(" produce --> %d,   consume --> %d  \n", produce.get(), consume.get());
    }

//    @Test
//    public void test2() throws InterruptedException {
//        produce.set(0); consume.set(0);
//        ExecutorService exec = Executors.newFixedThreadPool(5);
//        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
//        exec.submit(new Producer(queue));
//        exec.submit(new Producer(queue));
//        exec.submit(new Producer(queue));
//        exec.submit(new Consumer(queue));
//        //exec.submit(new Consumer(queue));
//
//
//        exec.shutdown();
//        while (!exec.awaitTermination(10, TimeUnit.SECONDS))
//        {
//            System.out.println("----");
//        }
//        assertEquals( produce.get(), consume.get());
//        System.out.printf(" produce --> %d,   consume --> %d  \n" , produce.get(), consume.get());
//    }
}
