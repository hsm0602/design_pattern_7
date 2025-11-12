package payment.spacetype;

import space.option.SpaceType;

import java.util.EnumMap;
import java.util.Map;

public class SpaceTypePaymentFactory {

    private static final Map<SpaceType,SpaceTypePayment> registry = new EnumMap<>(SpaceType.class);

    public SpaceTypePaymentFactory(){
        registry.put(SpaceType.CAFE,new CafePayment());
        registry.put(SpaceType.OFFICE,new OfficePayment());
        registry.put(SpaceType.STORAGE,new StoragePayment());
        registry.put(SpaceType.STUDIO, new StudioPayment());
    }

    public SpaceTypePayment get(SpaceType spaceType){
        SpaceTypePayment st = registry.get(spaceType);
        if(st==null) throw new IllegalArgumentException("Unsupported SpaceType,,");
        return st;
    }
}
