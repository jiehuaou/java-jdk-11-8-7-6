package demo.money;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 1) Intermediate operations are always lazy, such as filter, does not scan the whole stream.
 * 2) findAny() free to select any element in stream, allowing for maximal performance in parallel.
 * 3) findFirst() return an Optional<T>, strictly the first element of the stream.
 */
public class FindElement {
    public static void main(String[] args) {
        final Optional<Customer> any = getCustomer().stream()
                .filter(c->c.getName().startsWith("www")) // Intermediate operations are always lazy.
                                                            // filter does not scan the whole stream.
                .findAny();  // find any, faster in parallel stream
        System.out.println(any);

        //
        final Optional<Customer> first = getCustomer().stream()
                .filter(c->c.getName().startsWith("www")) // Intermediate operations are always lazy.
                // filter does not scan the whole stream.
                .findFirst();  // find first,
        System.out.println(first);
    }

    private static List<Customer> getCustomer(){
        return Arrays.asList(
                new Customer(1, "abc", 0),
                new Customer(2, "epm", 0),
                new Customer(3, "www", 0),
                new Customer(4, "www2", 0),
                new Customer(5, "xyz", 0)
        );
    }
}
