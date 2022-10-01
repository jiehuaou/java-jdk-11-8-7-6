package demo;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.assertEquals;

/**
 * use non-blocking method: add() peek() poll()
 */
public class ProducerConsumerTest {
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

    public static class Producer implements Runnable{
        final Queue<Integer> queue;
        final int total = 50000;

        public Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < total; i++) {
                queue.add(1);
                produce.incrementAndGet();
            }
        }
    }

    public static class Consumer implements Runnable{
        final Queue<Integer> queue;
        private int total = 0;
        private int retry = 3;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
//            while (produce.get()==0) {
//                sleep(20);
//            }
            long id = Thread.currentThread().getId();
            while (true) {
                Integer data = queue.peek();
                if (data == null) {

                    //consume.addAndGet(total);
                    --retry;
                    sleep(20);
                    if(retry<=0) {
                        System.out.println("Consumer ID-" + id + "  end with ================ " + total);
                        break;
                    } else {
                        System.out.println("Consumer ID-" + id + "  idle with ...... " + total);
                    }

                } else {
                    data = queue.poll();
                    Optional<Integer> optional = Optional.ofNullable(data);
                    optional.map(e -> {
                        ++total;
                        consume.addAndGet(1);
                        if (0 == total%25000)
                            System.out.println("Consumer ID-" + id + " ...... " + total);
                        return e;
                    });
                    yield();
                }
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        produce.set(0); consume.set(0);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Consumer(queue));
        exec.submit(new Consumer(queue));

        exec.shutdown();
        while (!exec.awaitTermination(10, TimeUnit.SECONDS))
        {
            System.out.println("----");
        }
        assertEquals( produce.get(), consume.get());
        System.out.printf(" produce --> %d,   consume --> %d  \n" , produce.get(), consume.get());
    }

    @Test
    public void test2() throws InterruptedException {
        produce.set(0); consume.set(0);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Producer(queue));
        exec.submit(new Consumer(queue));
        //exec.submit(new Consumer(queue));


        exec.shutdown();
        while (!exec.awaitTermination(10, TimeUnit.SECONDS))
        {
            System.out.println("----");
        }
        assertEquals( produce.get(), consume.get());
        System.out.printf(" produce --> %d,   consume --> %d  \n" , produce.get(), consume.get());
    }
}
