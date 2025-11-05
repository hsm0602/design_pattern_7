package space;

import pattern.SpaceComponent;

import java.util.Collections;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * 공간 정보를 담는 클래스. Decorator Pattern의 기본 컴포넌트 역할.
 */
// space/Space.java
public class Space implements SpaceComponent {
    private final String id;
    private final SpaceType type;
    private final SpaceScale scale;
    private final List<UnitSpace> units;  
    private final List<SecuritySystem> securities;
    private final String name;   // 예: "강남 회의실 A"
    private final int baseCost;  // 기본 가격

    public Space(String id, String name,
                 SpaceType type, SpaceScale scale,
                 List<UnitSpace> units,
                 List<SecuritySystem> securities,
                 int baseCost) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.type = type;
        this.scale = scale;
        this.units = new ArrayList<>(units);
        this.securities = new ArrayList<>(securities);
        this.baseCost = baseCost;
    }


    @Override public String getId() { return id; }

    @Override
    public String getDescription() {
        return String.format("%s [%s/%s] Units: %s / Security: %s",
                name, type, scale,
                units, securities);
    }

    @Override public int getCost() { return baseCost; }

    // 필요하면 getter (getType(), getScale(), getUnits(), getSecurities(), getName()) 주석 해제하여 사용.
     public SpaceType getType() { return type; }
     public SpaceScale getScale() { return scale; }
     public List<UnitSpace> getUnits() { return Collections.unmodifiableList(units); }
     public List<SecuritySystem> getSecurities() { return Collections.unmodifiableList(securities); }
     public String getName() { return name; }
}

