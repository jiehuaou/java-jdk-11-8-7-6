package hello.interview;

public class SampleImpl implements Sample{
    @Override
    public String proc(String input) {
        return input.toUpperCase();
    }
}
