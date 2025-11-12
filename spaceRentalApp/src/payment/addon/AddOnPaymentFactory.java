package payment.addon;


import space.option.AddOn;
import java.util.EnumMap;
import java.util.Map;

public class AddOnPaymentFactory {
    private static final Map<AddOn,AddOnPayment> registry = new EnumMap<>(AddOn.class);

    public AddOnPaymentFactory(){
        registry.put(AddOn.WIFI,new WifiPayment());
        registry.put(AddOn.PROJECTOR,new ProjecterPayment());
    }
    public AddOnPayment get(AddOn addOn) {
        AddOnPayment addOnPayment = registry.get(addOn);
        if (addOnPayment == null) throw new IllegalArgumentException("Unsupported grade: " + addOn);
        return addOnPayment;
    }

}
