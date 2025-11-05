package payment.securitysystempayment;

import space.SecuritySystem;

import java.util.EnumMap;
import java.util.Map;

public class SecurityPaymentFactory {
    private final Map<SecuritySystem,SecurityPayment> registy=new EnumMap<>(SecuritySystem.class);
    public SecurityPaymentFactory() {
        registy.put(SecuritySystem.CCTV,new Cctv());
        registy.put(SecuritySystem.DOOR_LOCK,new DoorLock());
        registy.put(SecuritySystem.ACCESS_CARD,new KeyCard());
    }

    public SecurityPayment get(SecuritySystem securitySystem) {
        SecurityPayment payment=registy.get(securitySystem);
        if(payment==null) {
            throw new IllegalArgumentException("Unsupported securityMethod: " +securitySystem);
        }
        return payment;
    }

}
