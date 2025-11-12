package space.domain;

import space.option.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * 공간 정보를 담는 클래스. Decorator Pattern의 기본 컴포넌트 역할.
 */
// space/Space.java
public class Space {
    private Long id;
    private SpaceType type;
    private SpaceScale scale;
    private List<UnitSpace> units;
    private List<SecuritySystem> securities;
    private List<AddOn> addOns;
    private String name;   // 예: "강남 회의실 A"
    private BigDecimal price;

    public Space(Long id, String name,
                 SpaceType type, SpaceScale scale,
                 List<UnitSpace> units,
                 List<SecuritySystem> securities) 
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.scale = scale;
        this.units = new ArrayList<>(units);
        this.securities = new ArrayList<>(securities);
        this.price = BigDecimal.ZERO;
    }


    public Long getId() { return id; }


    public String getDescription() {
        return String.format("%s [%s/%s] Units: %s / Security: %s",
                name, type, scale,
                units, securities);
    }

    // @Override public int getCost() { return baseCost; }

    // 필요하면 getter (getType(), getScale(), getUnits(), getSecurities(), getName()) 주석 해제하여 사용.
     public SpaceType getType() { return type; }
     public SpaceScale getScale() { return scale; }
     public List<UnitSpace> getUnits() { return Collections.unmodifiableList(units); }
     public List<SecuritySystem> getSecurities() { return Collections.unmodifiableList(securities); }
     public String getName() { return name; }
     public BigDecimal getPrice() { return price; }
     public void setPrice(BigDecimal price) { this.price = price; }
     public List<AddOn> getAddOn(){return addOns;
     }
     public void SetAddOn(List<AddOn> list){
        this.addOns = list;
     }
}

