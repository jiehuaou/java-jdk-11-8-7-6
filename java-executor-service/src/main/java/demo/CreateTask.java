package demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CreateTask {
    public static Runnable createOneRunnable(final int x){
        //final int index = x;
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("Runnable ...... " + x);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };
        return runnableTask;
    }

    public static Callable<String> createOneCallable(final int x){
//        final int index = x;
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.println("Callable ...... " + x);
            return "Task's execution " + x;
        };
        return callableTask;
    }

    public static Callable<String> createOneCallable(final int x, final int waitSec){
//        final int index = x;
        Callable<String> callableTask = () -> {
            TimeUnit.SECONDS.sleep(waitSec);
            System.out.println("long Callable ...... " + x);
            return "Task's execution " + x;
        };
        return callableTask;
    }

}
