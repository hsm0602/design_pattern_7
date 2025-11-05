package payment.securitysystempayment;

import java.math.BigDecimal;

public class Cctv implements SecurityPayment{
    @Override
    public BigDecimal applyPayment(BigDecimal amount) {
        return amount.add(new BigDecimal(1100));
    }
}
