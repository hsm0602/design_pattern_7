package space.domain;

import space.option.SecuritySystem;
import space.option.SpaceScale;
import space.option.SpaceType;
import space.option.UnitSpace;

import java.util.List;

public class DefaultSpaceFactory implements SpaceFactory {


    @Override
    public Space createSpace(
        Long id,
        String name,
        SpaceType type,
        SpaceScale scale,
        List<UnitSpace> units,
        List<SecuritySystem> securities
    ) {
        // 기본 속성으로 Space 객체를 생성하는 책임만 가짐
        System.out.println("[Factory] Creating space: " + name + " (" + type + ")");
        return new Space(id, name, type, scale, units, securities);
    }
}
