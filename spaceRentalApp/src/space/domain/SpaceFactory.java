package space.domain;

import space.option.SecuritySystem;
import space.option.SpaceScale;
import space.option.SpaceType;
import space.option.UnitSpace;

import java.util.List;


/**
 * [Factory Method Pattern]
 * 공간 객체 생성을 위한 팩토리 인터페이스
 */
public interface SpaceFactory {
    Space createSpace(
        Long id,
        String name,
        SpaceType type,
        SpaceScale scale,
        List<UnitSpace> units,
        List<SecuritySystem> securities
    );
}
