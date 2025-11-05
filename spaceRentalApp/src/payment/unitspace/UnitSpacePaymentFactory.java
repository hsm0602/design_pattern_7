package payment.unitspace;

import space.UnitSpace;

import java.util.EnumMap;
import java.util.Map;

public class UnitSpacePaymentFactory {

    private final Map<UnitSpace,UnitSpacePayment> registry = new EnumMap<>(UnitSpace.class);

    public UnitSpacePaymentFactory(){
        registry.put(UnitSpace.BREAK_ROOM,new BreakRoomPayment());
        registry.put(UnitSpace.LOUNGE,new LoungePayment());
        registry.put(UnitSpace.MEETING_ROOM,new MeetingRoomPayment());
        registry.put(UnitSpace.OPEN_SEAT,new OpenSeatPayment());
        registry.put(UnitSpace.PRIVATE_DESK, new PrivateDeskPayment());
        registry.put(UnitSpace.SEMINAR_ROOM, new SeminarRoomPayment());
        registry.put(UnitSpace.STUDIO, new StudioRoomPayment());
    }

    public UnitSpacePayment get(UnitSpace unitSpace){
        UnitSpacePayment us = registry.get(unitSpace);

        if (us==null) throw new IllegalArgumentException("Unsupported Units");
        return us;
    }
}
