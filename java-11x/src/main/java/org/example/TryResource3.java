package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TryResource3 {

    /**
     * resource(1,2,3,4) created, resource(4,3,2,1) closed, even though exception in resource3.close()
     */
    @Test
    void test2() {
        try (ConditionDestroyCloseable resource1 = new ConditionDestroyCloseable(1);
             ConditionDestroyCloseable resource2 = new ConditionDestroyCloseable(2);
             ConditionDestroyCloseable resource3 = new ConditionDestroyCloseable(3);
             ConditionDestroyCloseable resource4 = new ConditionDestroyCloseable(4);) {
            System.out.println("do something ----------- ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
