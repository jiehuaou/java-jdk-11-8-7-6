package demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxy implements InvocationHandler {
    private final Hello original;

    public HelloProxy(Hello original) {
        this.original = original;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("BEFORE");
        Object ret = method.invoke(original, args);
        System.out.println("AFTER");
        return ret;
    }
}
