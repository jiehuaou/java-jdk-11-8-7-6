import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
 *
 * lock.writeLock().lock(); // exclusive
 * ... write
 * lock.writeLock().unlock();
 *
 * lock.readLock().lock(); // allow other readLock
 * ... read
 * lock.readLock().unlock();
 *
 */
public class ReadWriteLockSample {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    int count = 0;

    void sleep(final int millsec) {
        try {
            TimeUnit.MILLISECONDS.sleep(millsec);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void add() {
        lock.writeLock().lock();
        try {
            // ++count;
            System.out.println("add count -> " + (++count));
            sleep(200);
            
        } finally {
            lock.writeLock().unlock();
        }
        sleep(1);
    }

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println("read count ---------> " + count);
            sleep(200);
            
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final ReadWriteLockSample lockSample = new ReadWriteLockSample();
        Runnable taskWrite = () -> {
            lockSample.add();
            
        };
        Runnable taskRead = ()->{
            lockSample.read();
        };
        ExecutorService ex = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 20; i++) {
            ex.submit(taskWrite);
        }
        ex.shutdown();

        ExecutorService ex2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            ex2.submit(taskRead);
        }
        ex2.shutdown();

        wait(ex);
        wait(ex2);
        
        System.out.println(lockSample.count);
    }

    public static void wait(ExecutorService ex) throws Exception{
        while(!ex.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("...");
        }
    }
}