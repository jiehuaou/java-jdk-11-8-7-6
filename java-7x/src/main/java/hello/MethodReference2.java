package hello;

public class MethodReference2 {
    interface Sayable{
        void say();
    }
    public void saySomething(){
        System.out.println("Hello, this is non-static method.");
    }
    public static void main(String[] args) {
        MethodReference2 obj = new MethodReference2();
        MethodReference2.Sayable ref = obj::saySomething;
        ref.say();
    }
}
