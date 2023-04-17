package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TryResource2 {

    /**
     * this syntax have issue when constructor exception happen,
     */
    @Test
    void test1() {
        final ConditionCreateCloseable resource1 = new ConditionCreateCloseable(1);
        final ConditionCreateCloseable resource2 = new ConditionCreateCloseable(2);
        final ConditionCreateCloseable resource3 = new ConditionCreateCloseable(3);
        final ConditionCreateCloseable resource4 = new ConditionCreateCloseable(4);
        try (resource1;resource2;resource3;resource4) {
            System.out.println("do something ----------- ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * 1. resource1-resource2 created, resource2-resource1 closed.
     * 2. resource3 throw exception.
     * 3. resource4 does not do anything.
     */
    @Test
    void test2() {
        try (ConditionCreateCloseable resource1 = new ConditionCreateCloseable(1);
             ConditionCreateCloseable resource2 = new ConditionCreateCloseable(2);
             ConditionCreateCloseable resource3 = new ConditionCreateCloseable(3);
             ConditionCreateCloseable resource4 = new ConditionCreateCloseable(4);) {
            System.out.println("do something ----------- ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * same as test2
     */
    @Test
    void test3() {
        try (ConditionCreateCloseable resource1 = new ConditionCreateCloseable(1);
             ConditionCreateCloseable resource2 = new ConditionCreateCloseable(2);
             ConditionCreateCloseable resource3 = new ConditionCreateCloseable(3);
             ConditionCreateCloseable resource4 = new ConditionCreateCloseable(4);) {
            System.out.println("do something ----------- ");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            System.out.println(" finally ----- end ");
        }
    }
}
