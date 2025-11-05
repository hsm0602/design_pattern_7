package payment.discount;

import java.math.BigDecimal;

public class GoldDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal discount(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0.85"));
    }
}
