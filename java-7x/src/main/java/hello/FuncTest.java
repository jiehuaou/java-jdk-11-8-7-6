package hello;

public class FuncTest {
    public static void main(String[] args) {
        Predicate<String> f1 = (String m)->{
            System.out.println("test " + m);
            return m.equalsIgnoreCase("hello");
        };

        f1.test("hello");

    }

    public static interface Predicate<T> {
        boolean test(T t);
    }
}
