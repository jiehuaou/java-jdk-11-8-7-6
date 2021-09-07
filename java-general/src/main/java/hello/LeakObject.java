package hello;

import java.util.Objects;

public class LeakObject {
    String data;
    public LeakObject(int i){
        String str = String.format("data with %d", System.currentTimeMillis() + i);
        data = str.intern();
        data = "123";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeakObject that = (LeakObject) o;
        return data.equalsIgnoreCase(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

}
