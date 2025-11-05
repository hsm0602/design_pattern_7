package payment.spacescale;

import java.math.BigDecimal;

public interface ScalePayment {
    BigDecimal apply(BigDecimal amount);
}
