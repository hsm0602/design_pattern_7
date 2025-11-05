package payment.spacescale;

import java.math.BigDecimal;

public class MediumPayment implements ScalePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(30000));
    }
}
