package demo;

import java.lang.reflect.Proxy;

/**
 * demo jdk dynamic proxy
 */
public class ProxyDemo {
    public static void main(String[] args) {
        HelloPojo original = new HelloPojo();
        HelloProxy handler = new HelloProxy(original);

        Hello f = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(),
                new Class[] { Hello.class },
                handler);
        f.show("world");

        String status = f.status();
        System.out.println(status);
    }
}
