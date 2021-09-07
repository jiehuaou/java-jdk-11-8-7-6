package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WorkStealingPoolTest {
    // ForkJoinPool (each thread with its own queue)
    ExecutorService es2 = Executors.newWorkStealingPool(4);

    public static void main(String[] args) throws Exception {
        WorkStealingPoolTest test = new WorkStealingPoolTest();
        System.out.println("--------------------test_work_steal_pool------------------------");
        test.test_work_steal_pool();
    }

    void test_work_steal_pool()throws InterruptedException, ExecutionException {

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(CreateTask.createOneCallable(10));
        callableTasks.add(CreateTask.createOneCallable(20));
        callableTasks.add(CreateTask.createOneCallable(30));
        callableTasks.add(CreateTask.createOneCallable(40));
        callableTasks.add(CreateTask.createOneCallable(50));


        // blocking here
        List<Future<String>> result = es2.invokeAll(callableTasks);

        System.out.println(es2);
        System.out.println("invokeAll-------");

        // execute without return
        es2.execute(CreateTask.createOneRunnable(1));
        es2.execute(CreateTask.createOneRunnable(2));
        es2.execute(CreateTask.createOneRunnable(3));
        es2.execute(CreateTask.createOneRunnable(4));
        es2.execute(CreateTask.createOneRunnable(5));
        es2.execute(CreateTask.createOneRunnable(6));
        es2.execute(CreateTask.createOneRunnable(7));
        es2.execute(CreateTask.createOneRunnable(8));

        String state1 = es2.toString();
        System.out.println(state1);
        System.out.println("execute -------");

        // submit with future
        Future<String> fu1 = es2.submit(CreateTask.createOneCallable(100, 10));
        Future<String> fu2 = es2.submit(CreateTask.createOneCallable(101, 15));
        Future<String> fu3 = es2.submit(CreateTask.createOneCallable(102, 20));

        String state2 = es2.toString();
        System.out.println(state2);
        System.out.println("submit -------");

        waitUntilFinish(es2);

        System.out.println("future --> " + fu1.get());

        for (int i = 0; i < result.size(); i++) {
            System.out.println("other future -----> " + result.get(i).get());
        }


    }



    static void waitUntilFinish(ExecutorService es) throws InterruptedException {
        es.shutdown();
        while (!es.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("not yet finished");
            System.out.println(es);
        }
    }

}
