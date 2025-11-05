package payment.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BronzeDiscountPolicy implements DiscountPolicy {
    @Override public BigDecimal discount(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
