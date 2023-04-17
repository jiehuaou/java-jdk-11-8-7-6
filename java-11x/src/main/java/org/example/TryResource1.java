package org.example;

import java.io.IOException;

/**
 * declare first, close last
 */
public class TryResource1 {
    public static void main(String[] args) {
        final SimpleCloseable resource1 = new SimpleCloseable(1);
        final SimpleCloseable resource2 = new SimpleCloseable(2);
        final SimpleCloseable resource3 = new SimpleCloseable(3);
        try (resource1;resource2;resource3) {
            System.out.println("do something ----------- ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
