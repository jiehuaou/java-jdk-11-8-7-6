package aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class StampedAccount {
//    private AtomicInteger stamp = new AtomicInteger(0);
    private AtomicStampedReference<Integer> account = new AtomicStampedReference<>(0, 0);

    public int getBalance() {
        return account.getReference();
    }

    public int getStamp() {
        return account.getStamp();
    }

    public boolean deposit(int funds) throws InterruptedException {
        int[] stamps = new int[1];
        int current = this.account.get(stamps);   // fetch current balance & timestamp
//        Thread.currentThread() sleep()
        Thread.sleep(2000);

//        int newStamp = this.stamp.incrementAndGet();
        return this.account.compareAndSet(current, current + funds, stamps[0], stamps[0]+1);
    }

    public boolean withdrawal(int funds) {
        int[] stamps = new int[1];
        int current = this.account.get(stamps); // fetch current balance & timestamp
//        int newStamp = this.stamp.incrementAndGet();
        return this.account.compareAndSet(current, current - funds, stamps[0], stamps[0]+1);
    }
}
