package payment.discount;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal discount(BigDecimal amount);

}
