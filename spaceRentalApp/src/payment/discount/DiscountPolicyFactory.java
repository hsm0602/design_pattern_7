package payment.discount;

import user.UserGrade;
import java.util.EnumMap;
import java.util.Map;

public class DiscountPolicyFactory {
    private final Map<UserGrade, DiscountPolicy> registry = new EnumMap<>(UserGrade.class);

    public DiscountPolicyFactory() {
        registry.put(UserGrade.GOLD,   new GoldDiscountPolicy());
        registry.put(UserGrade.SILVER, new SilverDiscountPolicy());
        registry.put(UserGrade.BRONZE, new BronzeDiscountPolicy());
    }

    public DiscountPolicy get(UserGrade grade) {
        DiscountPolicy policy = registry.get(grade);
        if (policy == null) throw new IllegalArgumentException("Unsupported grade: " + grade);
        return policy;
    }
}