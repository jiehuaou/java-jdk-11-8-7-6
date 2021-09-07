package hello;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.function.BinaryOperator;

public class Task005 {

    static int func(int subtotal, int element) {
        return subtotal + element;
    }

    static interface Ptr {
        int func(int subtotal, int element);
    }

    static class Task {
        int count;
        public Task(int iCount){
            count = iCount;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "count=" + count +
                    '}';
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ls.add(new Task(i));
        }

        //BinaryOperator<Integer> ptr = Task005::func;

        //Ptr ptr = Task005::func;

//        Ptr ptr = new Ptr(){
//            @Override
//            public int func(int subtotal, int element) {
//                return subtotal + element;
//            }
//        };

        BinaryOperator<Integer> ptr = new BinaryOperator<Integer>(){

            @Override
            public Integer apply(Integer o, Integer o2) {
                return o+o2;
            }
        } ;

        Object x = ls.stream().map(task -> {
                System.out.println(task);
                return task.count;}).
                reduce(0, ptr);

        System.out.println(x);

    }
}
