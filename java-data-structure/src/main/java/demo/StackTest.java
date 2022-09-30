package demo;

import java.util.*;

public class StackTest {

    // last-in-first-out
    public static void test_stack() {
        // The Stack class represents a last-in-first-out (LIFO) stack of objects.
        // It extends class Vector with five operations that allow a vector to be treated as a stack.
        Stack<Integer> stack = new Stack<>();
        //stack.addAll(Arrays.asList(1,2,3));
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.empty()){
            System.out.println(stack.pop());
        }
        System.out.println("----------Stack");
    }
    // Deques can also be used as LIFO (Last-In-First-Out) stacks.
    // This interface should be used in preference to the legacy Stack class
    public static void test_stack2() {
        Deque<Integer> stack = new ArrayDeque<Integer>();
//        stack.addAll(Arrays.asList(1,2,3));
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        System.out.println("----------ArrayDeque");
    }

    public static void test_stack3() {
        Deque<Integer> stack = new LinkedList<Integer>();
//        stack.addAll(Arrays.asList(1,2,3));
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        System.out.println("----------LinkedList");
    }


    public static void main(String[] args) {
        test_stack();
        test_stack2();
        test_stack3();
    }
}