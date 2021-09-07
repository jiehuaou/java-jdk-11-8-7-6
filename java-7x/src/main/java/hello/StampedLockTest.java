package hello;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    static StampedLock lock = new StampedLock();

    public static long testReadLock() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> fu = CompletableFuture.supplyAsync(()->{
            long data = lock.readLock();
            System.out.println("readLock = " + ((data==0)?"zero":"" + data));
            //lock.unlock(data);
            return data;
        });
        return fu.get();
    }

    public static long testWriteLock() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> fu = CompletableFuture.supplyAsync(()->{
            long data = lock.writeLock();
            System.out.println("writeLock = " + ((data==0)?"zero":"" + data));
            //lock.unlock(data);
            return data;
        });
        return fu.get();
    }

    static void testOptimisticRead() {
        long token = lock.tryOptimisticRead();
        System.out.println("tryOptimisticRead = " + ((token==0)?"zero":"" + token));
        System.out.println("validate = " + lock.validate(token));
    }

    static void foobar(){}
    static int getTarget(){
        return 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      long token =  testReadLock();
//        long token = testWriteLock();
        testOptimisticRead();
        lock.unlock(token);
        testOptimisticRead();


    }

    private static void tryReadWrite() {
        long token = lock.readLock(); // get read lock
        long target = getTarget() ;   // read state
        if (target==0){               // it is what we expect
            long ws = lock.tryConvertToWriteLock(token);   // convert to writeLock,
            if (ws != 0L) {
                token = ws;
            }else {
                lock.unlockRead(token);
                token = lock.writeLock();  // or else get write lock
            }
            target = 1; // change target
        }
        lock.unlock(token);
        System.out.println("unlock");
    }
}
