package org.example;

import java.io.Closeable;
import java.io.IOException;

public class ConditionDestroyCloseable implements Closeable {
    private final Integer index;

    public ConditionDestroyCloseable(Integer index) {
        this.index = index;
        System.out.println("created Resource ....... " + index);

    }

    @Override
    public void close() throws IOException {
        System.out.println("closed Resource ....... " + index);
        if(index.intValue() == 3) {
            throw new IOException("something wrong with " + index);
        }
    }
}
