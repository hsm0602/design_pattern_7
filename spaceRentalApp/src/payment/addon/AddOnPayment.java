package payment.addon;

import java.math.BigDecimal;

public interface AddOnPayment {
    BigDecimal applyPayment(BigDecimal amount);
}
