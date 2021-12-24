package demo.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Customer {
    private int id;
    private String name;
    private double totalSpendMoney;

    public void combineSpendMoney(double spending){
        totalSpendMoney += spending;
    }
}
