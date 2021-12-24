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
                .map(customer->mapFromOther(customer, purchases))
                .filter(customer -> customer.getTotalSpendMoney()>0.0)
                .collect(Collectors.toList());

        data.forEach(System.out::println);
    }

    private static Customer mapFromOther(Customer customer, final List<Purchase> purchases){
        purchases.stream()
                .filter(purchase->purchase.getId()==customer.getId())
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
