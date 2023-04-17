package org.example;

import java.io.Closeable;
import java.io.IOException;

public class ConditionCreateCloseable implements Closeable {
    private final Integer index;

    public ConditionCreateCloseable(Integer index) {
        this.index = index;
        System.out.println("created Resource ....... " + index);
        if(index.intValue() == 3) {
            throw new RuntimeException("something wrong with " + index);
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("closed Resource ....... " + index);
    }
}
