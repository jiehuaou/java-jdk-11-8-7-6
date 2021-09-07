package hello;

import java.util.concurrent.Executors;

public class Test006 {
    public static void main(String[] args) {
        Executors.newWorkStealingPool(3);
    }
}
