package pattern;

import java.util.List;
import space.*;


/**
 * [Factory Method Pattern]
 * 공간 객체 생성을 위한 팩토리 인터페이스
 */
public interface SpaceFactory {
    Space createSpace(
        String id,
        String name,
        SpaceType type,
        SpaceScale scale,
        List<UnitSpace> units,
        List<SecuritySystem> securities
    );
}
