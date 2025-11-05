package payment.unitspace;

import java.math.BigDecimal;

public class BreakRoomPayment implements UnitSpacePayment{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.add(new BigDecimal(1700));
    }
}
