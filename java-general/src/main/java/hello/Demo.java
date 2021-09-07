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
}
