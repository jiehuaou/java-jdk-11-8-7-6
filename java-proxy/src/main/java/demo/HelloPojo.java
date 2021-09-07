package demo;

public class HelloPojo implements Hello{
    private int counter = 1;
    @Override
    public void show(String message) {
        System.out.println("hello " + message);
    }

    @Override
    public String status() {
        final String status = "" + counter++;
        System.out.println("status --> " + status);
        return status;
    }
}
