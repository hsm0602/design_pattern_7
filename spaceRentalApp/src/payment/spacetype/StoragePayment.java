package payment.spacetype;

import java.math.BigDecimal;

public class StoragePayment implements SpaceTypePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(8000));
    }
}
