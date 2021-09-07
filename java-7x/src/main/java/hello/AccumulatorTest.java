package hello;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AccumulatorTest {
    public static void main2(String[] args) {
        DoubleAccumulator accu
                = new DoubleAccumulator(Double::sum, 0);
        accu.accumulate(1.0);
        accu.accumulate(1.1);
        accu.accumulate(1.2);
        System.out.println(accu.get());
    }

    static LongAdder foo(String k){
        return new LongAdder();
    }

    public static void main(String[] args) {
        ConcurrentMap<String, LongAdder> freqs = new ConcurrentHashMap<>();
        freqs.computeIfAbsent("foo", AccumulatorTest::foo).increment();
        freqs.computeIfAbsent("bar", k -> new LongAdder()).increment();
        freqs.computeIfAbsent("bar", k -> new LongAdder()).add(2);

        System.out.println("foo=" + freqs.get("foo").sum());
        System.out.println("bar=" + freqs.get("bar").sum());
    }
}
