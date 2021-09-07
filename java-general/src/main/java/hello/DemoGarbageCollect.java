package hello;

import java.lang.ref.WeakReference;

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

    public static void main(String[] args) {
        DemoGarbageCollect dt = new DemoGarbageCollect();
        dt.start();
        System.gc();
        System.out.println("----end----");
    }
}
