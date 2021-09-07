package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Test004 {
    static Map<LeakObject, String> mapData = new HashMap<>();
    static class Task extends RecursiveTask<Integer> {
        int count ;
        public Task(int iCount){
            count = iCount;
            mapData.put(new LeakObject(iCount), "count=" + count);
            mapData.put(new LeakObject(iCount), "count=" + count);
        }
        @Override
        protected Integer compute() {
            if(count>5){
                ArrayList<Task> ls =  splitTask(count);
                for(Task subtask : ls){
                    subtask.fork();  // schedules these subtasks for execution
                }
                int result = 0;
                for(Task subtask : ls) {
                    result += subtask.join(); //receives the result
                }
                return result;
            }
            System.out.printf("simply t1=%d ,  \n", count);
            return doWork();
        }

        private ArrayList<Task> splitTask(int iCount){
            int c1 = count/2;
            int c2 = count - c1;
            ArrayList<Task> ls = new ArrayList<>();
            ls.add(new Task(c1));
            ls.add(new Task(c2));
            System.out.printf("split t1=%d , t2=%d \n", c1, c2);
            return ls;
        }

        private int doWork(){
            try {
                Thread.sleep(count * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return count * 10;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "count=" + count +
                    '}';
        }
    }
    public static void main(String[] args) {
        Task t = new Task(3000);
        ForkJoinPool fj = new ForkJoinPool(2);
        Integer x = fj.invoke(t);
        System.out.println(x);
    }
}
