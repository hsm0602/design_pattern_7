package payment.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class SilverDiscountPolicy implements DiscountPolicy {
    private static final BigDecimal RATE = new BigDecimal("0.90");
    @Override public BigDecimal discount(BigDecimal amount) {
        return amount.multiply(RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
