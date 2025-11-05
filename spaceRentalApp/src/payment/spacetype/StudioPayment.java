package payment.spacetype;

import java.math.BigDecimal;

public class StudioPayment implements SpaceTypePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(13000));
    }
}
