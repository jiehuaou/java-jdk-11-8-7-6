package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPoolTest {

    // ThreadPoolExecutor (1 queue) ExecutorService
    ThreadPoolExecutor es1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);



    public static void main(String[] args) throws Exception {

        FixedThreadPoolTest test = new FixedThreadPoolTest();

        System.out.println("--------------------testThreadPoolExecutor------------------------");
        test.testThreadPoolExecutor();


    }

    private void testThreadPoolExecutor() throws InterruptedException, ExecutionException {

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(CreateTask.createOneCallable(10));
        callableTasks.add(CreateTask.createOneCallable(20));
        callableTasks.add(CreateTask.createOneCallable(30));
        callableTasks.add(CreateTask.createOneCallable(40));
        callableTasks.add(CreateTask.createOneCallable(50));
        callableTasks.add(CreateTask.createOneCallable(60));

        //List<Future<String>> result = es1.invokeAll(callableTasks);  // blocking wait

        System.out.println(es1); // queued tasks = 0

        // execute without return
        es1.execute(CreateTask.createOneRunnable(1));
        es1.execute(CreateTask.createOneRunnable(2));
        es1.execute(CreateTask.createOneRunnable(3));
        es1.execute(CreateTask.createOneRunnable(4));
        //es1.execute(CreateTask.createOneRunnable(5));
        //es1.execute(CreateTask.createOneRunnable(6));

        // submit with future
        Future<String> fu1 = es1.submit(CreateTask.createOneCallable(100, 10));
        Future<String> fu2 = es1.submit(CreateTask.createOneCallable(101, 15));
        Future<String> fu3 = es1.submit(CreateTask.createOneCallable(102, 20));

        System.out.println(es1);  // queued tasks = 3

        waitUntilFinish(es1);

        System.out.println("future --> " + fu1.get());
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println("other future -----> " + result.get(i).get());
//        }

        es1 = null;

    }

    static void waitUntilFinish(ExecutorService es) throws InterruptedException {
        es.shutdown();
        while (!es.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("not yet finished");
            System.out.println(es);
        }
    }

}
