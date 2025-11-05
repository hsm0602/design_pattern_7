package payment.spacescale;

import space.SpaceScale;

import java.util.EnumMap;
import java.util.Map;

public class ScalePaymentFactory {
    private final Map<SpaceScale,ScalePayment> registry = new EnumMap<>(SpaceScale.class);

    public ScalePaymentFactory(){
        registry.put(SpaceScale.ONE_PERSON, new OnePersonPayment());
        registry.put(SpaceScale.SMALL,new SmallPayment());
        registry.put(SpaceScale.MEDIUM,new MediumPayment());
        registry.put(SpaceScale.LARGE,new LargePayment());
    }

    public ScalePayment get(SpaceScale spaceScale){
        ScalePayment sp = registry.get(spaceScale);
        if(sp==null) throw new IllegalArgumentException("Unsupported Scale: "+ spaceScale);
        return sp;
    }
}
