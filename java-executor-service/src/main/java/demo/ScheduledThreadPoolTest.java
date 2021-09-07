package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        schedule();
    }

    static void schedule() throws InterruptedException {
        // ScheduledExecutorService
        ScheduledExecutorService es3 = Executors.newScheduledThreadPool(3);

        es3.schedule(CreateTask.createOneRunnable(1), 15, TimeUnit.MILLISECONDS);
        es3.schedule(CreateTask.createOneRunnable(2), 25, TimeUnit.MILLISECONDS);
        es3.schedule(CreateTask.createOneRunnable(3), 35, TimeUnit.MILLISECONDS);
        es3.schedule(CreateTask.createOneCallable(1), 45, TimeUnit.MILLISECONDS);
        es3.schedule(CreateTask.createOneCallable(2), 55, TimeUnit.MILLISECONDS);
        es3.schedule(CreateTask.createOneCallable(3), 65, TimeUnit.MILLISECONDS);

        waitUntilFinish(es3);

    }

    static void waitUntilFinish(ExecutorService es) throws InterruptedException {
        es.shutdown();
        while (!es.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("not yet finished");
        }
    }
}
