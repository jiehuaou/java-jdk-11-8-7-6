package org.example;

import java.io.Closeable;
import java.io.IOException;

public class SimpleCloseable implements Closeable {
    private final Integer index;

    public SimpleCloseable(Integer index) {
        this.index = index;
        System.out.println("created Resource ....... " + index);
    }

    @Override
    public void close() throws IOException {
        System.out.println("closed Resource ....... " + index);
    }
}
