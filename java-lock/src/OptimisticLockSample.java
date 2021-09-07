import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class OptimisticLockSample {
    StampedLock lock = new StampedLock();
    Map<String, String> map = new HashMap<>();

    void sleep(final int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void println(final String text) {
        System.out.println(text);
    }

    public void write() {
        long stamp = lock.writeLock();
        try {
            sleep(1);
            System.out.println("Write Lock acquired");
            map.put("foo", "bar");
            sleep(1);
        } finally {
            lock.unlock(stamp);
            System.out.println("Write done");
        }
    }

    public void tryRead() {

        final long stamp = lock.tryOptimisticRead();
        try {
            println("Optimistic Lock Valid 1: " + lock.validate(stamp));
            sleep(1);
            println("Optimistic Lock Valid 2: " + lock.validate(stamp));
            sleep(2);
            println("Optimistic Lock Valid 3: " + lock.validate(stamp));
        } finally {
            ;//lock.unlock(stamp);
        }
    }

    public static void main(final String[] args) throws Exception {
        final OptimisticLockSample lockSample = new OptimisticLockSample();
        final Runnable taskWrite = () -> {
            lockSample.write();
            
        };
        final Runnable taskRead = () -> {
            lockSample.tryRead();
        };
        final ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.submit(taskRead);
        ex.submit(taskRead);

        ex.submit(taskWrite);

        ex.shutdown();

        wait(ex);

        System.out.println(lockSample.map);
    }

    public static void wait(final ExecutorService ex) throws Exception {
        while(!ex.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("...");
        }
    }
}