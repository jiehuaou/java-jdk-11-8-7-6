package hello.interview;

public class SampleCase {
    public static void main(String[] args) {
        SampleImpl sam = new SampleImpl();
        DecorateImpl dec = new DecorateImpl(sam, "**");
        //DecorateImpl dec2 = new DecorateImpl(dec, "++");

        endUser(dec);
    }

    static void endUser(Sample sample){
        System.out.println(sample.proc("abc"));
    }
}
