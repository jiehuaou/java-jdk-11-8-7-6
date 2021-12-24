package demo.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Purchase {
    private int id;
    private Date time;
    private double spendMoney;
}
