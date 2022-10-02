package hello;

import java.lang.ref.WeakReference;

/**
 * display "demo is release 2"
 */
public class DemoGarbageCollect {
    private Demo d ;
    void start(){
        d = new Demo(1);
        takeOther(d);
        System.gc();
    }
    void takeOther(Demo demo){
        demo = null;
        demo = new Demo(2);
        System.gc();
    }

    public static void main(String[] args)  throws InterruptedException{
        DemoGarbageCollect dt = new DemoGarbageCollect();
        dt.start();
        System.gc();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("for " + i);
            System.gc();
        }
        System.out.println("----end----");
    }
}
