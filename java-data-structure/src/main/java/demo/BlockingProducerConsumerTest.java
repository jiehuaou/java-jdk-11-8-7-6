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
        final int target = 50000;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < target; i++) {
                try {
                    queue.put(1);
                    if (0 == i%10000) {
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                produce.incrementAndGet();
            }
            sleep(1000);
            long id = Thread.currentThread().getId();
            System.out.println("Producer ID-" + id + "  end with ================ " + target);
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
        CompletableFuture<Void> p1 = CompletableFuture.runAsync(new Producer(queue), exec);
        CompletableFuture<Void> p2 = CompletableFuture.runAsync(new Producer(queue), exec);
        CompletableFuture<Void> p3 = CompletableFuture.runAsync(new Producer(queue), exec);
        exec.submit(new Consumer(queue));
        exec.submit(new Consumer(queue));
        exec.shutdown();


        CompletableFuture.allOf( p1,  p2, p3).thenRun(() -> {
            try {
                queue.put(-1);
                queue.put(-1);
                System.out.println("---- put end flag ---- ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        while (!exec.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("----await Termination----");

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
