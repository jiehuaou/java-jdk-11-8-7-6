package hello;

public class Number1 {
    public static void main(String[] args) {
        long l1 = 1_000_000;
        System.out.println("number = " + l1);

        int maskBytes = 0b1100_1100;
        System.out.println("maskBytes=" + maskBytes);
//        System.out.println("maskBytes=" + 0xff);

        // using underscore for improved readability
        int veryLongMask = 0b1101_1000_1101_0001;

        System.out.println("veryLongMask=" + veryLongMask);
    }
}
