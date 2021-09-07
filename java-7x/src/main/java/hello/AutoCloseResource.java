package hello;

import java.io.*;

public class AutoCloseResource implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("AutoCloseResource is closed with exception");
        throw new RuntimeException("test exception in close");
    }

    public void foo(){
        System.out.println("do something 1");
    }

    public static void main(String[] args) {
//        AutoCloseResource acr = null;
        String msg = "hello world";
        byte [] buf = msg.getBytes();
        try(AutoCloseResource res1 = new AutoCloseResource();
            Resource1 res2 = new Resource1();
            MyInputStream bins = new MyInputStream(buf);
            InputStreamReader inr = new InputStreamReader(bins);
            BufferedReader br = new BufferedReader(inr);
            ) {
            String ln = br.readLine();
            System.out.println("read-line = " + ln);
            res1.foo();
            res2.foo();
        }catch (Exception ex){
            //
        }
    }


}
