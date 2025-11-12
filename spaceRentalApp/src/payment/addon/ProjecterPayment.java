package payment.addon;

import java.math.BigDecimal;

public class ProjecterPayment implements AddOnPayment{
    @Override
    public BigDecimal applyPayment(BigDecimal amount) {
        return amount.add( new BigDecimal(200));
    }
}
