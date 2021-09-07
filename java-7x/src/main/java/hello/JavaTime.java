package hello;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class JavaTime {
    public static void main(String[] args) {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = dt1.plus(1, ChronoUnit.HOURS);
        System.out.println(dt1);
        System.out.println(dt2);
    }
}
