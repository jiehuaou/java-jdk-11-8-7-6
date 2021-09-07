package abc;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSwap {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.addAndGet(10);

        System.out.println(ai);
    }

}
