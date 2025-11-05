package payment.spacetype;

import java.math.BigDecimal;

public interface SpaceTypePayment {
    BigDecimal apply(BigDecimal amount);
}
