package payment.securitysystempayment;

import java.math.BigDecimal;

public interface SecurityPayment {
    BigDecimal applyPayment(BigDecimal amount);
}
