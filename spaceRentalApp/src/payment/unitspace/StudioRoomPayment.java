package payment.unitspace;

import java.math.BigDecimal;

public class StudioRoomPayment implements UnitSpacePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(1800));
    }
}
