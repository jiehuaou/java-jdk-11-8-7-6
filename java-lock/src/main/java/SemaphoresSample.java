import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * showcase only 5 active threads are allowed at every time.
 * Semaphore semaphore = new Semaphore(5);
 * semaphore.acquire();
 * ...
 * semaphore.release();
 */
public class SemaphoresSample {

    int count = 0;

    public synchronized int add() { //
        return ++count;
    }

    void sleep(final int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        final SemaphoresSample lockSample = new SemaphoresSample();
        final Semaphore semaphore = new Semaphore(5);
        Runnable task2 = () -> {
            try {
                String ID = Thread.currentThread().getName();
                semaphore.acquire();
                int newInt = lockSample.add();
                System.out.println(ID + " add ----> " + newInt);
                lockSample.sleep(1);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                semaphore.release();
            }
            
        };
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            ex.submit(task2);
        }
        ex.shutdown();
        while(!ex.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("...");
        }
        System.out.println("final result ==== " + lockSample.count);
    }
}