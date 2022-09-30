import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * locker.lock();
 * ...
 * locker.unlock();
 */
public class ReentrantLockSample {
    ReentrantLock locker = new ReentrantLock();
    int count = 0;
    public void add() {
        locker.lock();
        try {
            ++count;
        } finally {
            locker.unlock();
        }
    }
    public static void main(String[] args) throws Exception{
        final ReentrantLockSample lockSample = new ReentrantLockSample();
        
        Runnable task2 = ()->{
            lockSample.add();
        };
        ExecutorService ex = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 100; i++) {
            ex.submit(task2);
        }
        ex.shutdown();
        while(!ex.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("...");
        }
        System.out.println(lockSample.count);
    }
}