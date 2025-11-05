package payment.spacescale;

import java.math.BigDecimal;

public class OnePersonPayment implements ScalePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(10000));
    }
}
