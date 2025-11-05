package payment.unitspace;

import java.math.BigDecimal;

public interface UnitSpacePayment {
    BigDecimal apply(BigDecimal amount);
}
