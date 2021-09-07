package sync;

import aba.StampedAccount;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class VolatileTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        VolatileAccount sa = new VolatileAccount();
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("this thread " + Thread.currentThread().getName());
                try {
                    for (int i = 0; i < 5; i++) {
                        sa.update(10);
                        //System.out.println("update() ... " + i);
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        CompletableFuture<String> t2 = CompletableFuture.supplyAsync(()->{
                System.out.println("this thread " + Thread.currentThread().getName());
                for (int i = 0; i < 5; i++) {
                    sa.read();
                    //System.out.println("read() ... " + i );
                    try {
                        TimeUnit.MILLISECONDS.sleep(1010);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "done";
            }
        );

//        t1.get();
        t1.cancel(false);
        System.out.println(t2.get());
    }
}
