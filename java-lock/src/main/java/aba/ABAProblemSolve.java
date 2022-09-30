package aba;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAProblemSolve {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //  balance, 100, and timestamp, 0.
//        int balance = 100;
//        int stamp = 1;
//        AtomicStampedReference<Integer> account = new AtomicStampedReference<>(balance, stamp);
//
//        boolean ret = account.compareAndSet(balance, balance + 10, stamp, stamp + 1);
//        System.out.println(" compareAndSet = " + ret);
//        System.out.println(String.format(" new account %d , new stamp %d ", account.getReference(), account.getStamp()));
//
//        //
//        {
//            int[] stamps = new int[1];
//            int balance2 = account.get(stamps);
//            System.out.println(String.format(" new account %d , new stamp %d ", balance2, stamps[0]));
//        }

        StampedAccount sa = new StampedAccount();
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean ret = sa.deposit(100);
                    System.out.println("deposit(100) " + ret);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        CompletableFuture<Void> t2 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                boolean ret1 = sa.withdrawal(10);
                System.out.println("withdrawal(10) " + ret1);
                boolean ret2 = sa.withdrawal(-10);
                System.out.println("withdrawal(-10) " + ret2);
            }
        });

        t1.get();
        t2.get();


    }
}
