package hello;

import java.io.Closeable;
import java.io.IOException;

public class Resource1 implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("Resource1 is closed");
    }

    public void foo(){
        System.out.println("do something 2");
    }

    public static void main(String[] args) {
//        AutoCloseResource acr = null;
        try(Resource1 acr = new Resource1();) {
            acr.foo();
        }catch (Exception ex){
            //
        }
    }
}
