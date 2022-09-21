package demo.money;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * one stream combine another stream.
 */
public class StreamCombineStream {
    public static void main(String[] args) {
        List<Purchase> purchases = getPurchase();
        List<Customer> data = getCustomer().stream()
                .map(customer->mapFromPurchase(customer, purchases))
                .filter(customer -> customer.getTotalSpendMoney()>0.0)  // Intermediate operations are always lazy.
                .collect(Collectors.toList());

        data.forEach(System.out::println);
    }

    /**
     * sum-up all spending from Purchase stream, into one Customer
     */
    private static Customer mapFromPurchase(Customer customer, final List<Purchase> purchases){
        purchases.stream()
                .filter(purchase->purchase.getId()==customer.getId())
                .peek(purchase -> System.out.printf("found SpendMoney $ %.2f on id[%d] \n", purchase.getSpendMoney(), purchase.getId()))
                .forEach(purchase->customer.combineSpendMoney(purchase.getSpendMoney()));
        return customer;
    }

    private static List<Customer> getCustomer(){
        return Arrays.asList(
            new Customer(1, "abc", 0),
                new Customer(2, "epm", 0),
                new Customer(3, "www", 0),
                new Customer(4, "xyz", 0)
        );
    }

    private static List<Purchase> getPurchase(){
        return Arrays.asList(
            new Purchase(1, null, 1),
                new Purchase(2, null, 2),
                new Purchase(3, null, 3),
                new Purchase(3, null, 4)
        );
    }
}
