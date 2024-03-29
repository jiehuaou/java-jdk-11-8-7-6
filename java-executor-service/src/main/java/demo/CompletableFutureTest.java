package demo;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.util.concurrent.*;

/**
 * CompletableFuture Test
 */
public class CompletableFutureTest {
    public static CompletableFuture<String> calculateAsync(ExecutorService exec) throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        exec.submit(() -> {
            System.out.println("Completable start");
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            System.out.println("Completable complete");
            return null;
        });

        return completableFuture;
    }

    public static void main2(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CompletableFuture<String> future = calculateAsync(exec);
        String msg = future.get();
        System.out.println(msg);
        exec.shutdown();
    }

    @Test
    @DisplayName("test supplyAsync CompletableFuture  ")
    public void main3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                //long running process
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "supply Async";
        });
        String msg = future.thenApply(x -> x + ": append x").get();
//        String msg = future.get();
        System.out.println(msg);

    }

    @Test
    @DisplayName("test runAsync CompletableFuture  ")
    public void main1() throws ExecutionException, InterruptedException {
        String msg;
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            //return "supply Async";
        });
        future.get();
        System.out.println("finished");

    }

    @Test
    @DisplayName("test thenCompose CompletableFuture  ")
    public void main5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 1)
                        .thenApply(x -> x + 1);
        Integer ret = future.get();
        System.out.println("thenApply return " + ret);

        CompletableFuture<Integer> future2 =
                CompletableFuture.supplyAsync(() -> 1)
                        .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 1));
        Integer ret2 = future2.get();
        System.out.println("thenCompose return " + ret2);

    }

    @Test
    @DisplayName("test Combine CompletableFuture  ")
    public void main6() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
                    System.out.println("hello sleep");
                    sleep(2);
                    return "hello";
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("world sleep");
                    sleep(2);
                    return " world";
                }), (s1, s2) -> (s1 + s2));
        String msg = future.get();
        System.out.println(msg);
    }

    static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
        }
    }

    @Test
    @DisplayName("test wait all CompletableFuture done ")
    public void test7() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2);
        combinedFuture.get();
        System.out.println("future1 done " + future1.isDone());
        System.out.println("future2 done " + future2.isDone());
    }

    @Test
    @DisplayName("test chain of thenApply and thenCompose")
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 1)
                        .thenApply(x -> x + 1)
                        .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 1));
        Integer x = future.get();
        System.out.println("x=" + x);
    }


}
