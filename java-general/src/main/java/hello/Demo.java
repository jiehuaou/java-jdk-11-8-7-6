package hello;

public class Demo {
    int index;
    public Demo(int x){
        index = x;
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("demo is release " + index);
    }

    public static void main(String[] args)  throws InterruptedException{
        new Demo(3);
        System.gc();
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("for " + i);
            System.gc();
        }
        System.out.println("----end----");
    }
}
