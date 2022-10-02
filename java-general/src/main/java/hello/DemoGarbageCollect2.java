package hello;

public class DemoGarbageCollect2 {
    public static void main(String[] args) throws InterruptedException {
        DemoGarbageCollect dt = new DemoGarbageCollect();
        dt.start();
        System.gc();
        dt = null;
        System.out.println("----end----");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("for " + i);
            System.gc();
        }
    }
}
