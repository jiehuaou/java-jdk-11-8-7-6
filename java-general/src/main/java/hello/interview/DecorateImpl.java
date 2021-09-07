package hello.interview;

public class DecorateImpl implements Sample{
    private Sample sampleImple;
    private String special;

    public DecorateImpl(Sample sample, String special){
        this.sampleImple = sample;
        this.special = special;
    }
    @Override
    public String proc(String input) {
        return special + sampleImple.proc(input) + special;
    }
}
