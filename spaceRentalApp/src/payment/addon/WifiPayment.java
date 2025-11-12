package payment.addon;

import java.math.BigDecimal;

public class WifiPayment implements AddOnPayment{
    @Override
    public BigDecimal applyPayment(BigDecimal amount) {
        return amount.add( new BigDecimal(500));
    }
}
