package hello;

public class MethodReference1 {
    interface Sayable{
        void say();
    }

    static void sayHello(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Sayable ref = MethodReference1::sayHello;
        ref.say();
    }
}
