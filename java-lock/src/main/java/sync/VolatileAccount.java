package sync;

public class VolatileAccount {
    // Definition: Some variables
    private int first = 1;
    private int second = 2;
    private int third = 3;
    private volatile int hasValue = 0;


    protected void update(int inc)  {
        // First Snippet: A sequence of write operations being executed by Thread 1
        first += inc;
        second += inc;
        third += inc;
        hasValue += 1;

        String msg = "write {" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
//                ", hasValue=" + hasValue +
                '}';
        System.out.println(msg);
    }

    protected void read()  {
        // Second Snippet: A sequence of read operations being executed by Thread 2
        String msg = "read {" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", hasValue=" + hasValue +
                "} \n";
        System.out.println(msg);
    }

    @Override
    public String toString() {
        return "VolatileAccount{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", hasValue=" + hasValue +
                '}';
    }
}
