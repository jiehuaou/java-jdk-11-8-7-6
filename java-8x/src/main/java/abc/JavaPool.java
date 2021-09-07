package abc;

public class JavaPool {
    public static void main(String[] args) {
        String constantString = "interned Baeldung";
        String newString = new String("interned Baeldung");
        System.out.println(constantString == newString);

        String internedString = newString.intern();
        System.out.println(constantString == internedString);
    }
}
