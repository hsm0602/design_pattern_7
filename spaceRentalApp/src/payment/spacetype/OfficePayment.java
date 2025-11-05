package payment.spacetype;

import payment.spacescale.ScalePayment;

import java.math.BigDecimal;

public class OfficePayment implements SpaceTypePayment {
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(10000));
    }
}
