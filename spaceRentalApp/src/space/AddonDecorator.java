package space;

import pattern.SpaceComponent;

/**
 * [Decorator Pattern]
 * 부가 서비스를 추가하기 위한 추상 데코레이터 클래스
 */
public abstract class AddonDecorator implements SpaceComponent {
    protected final SpaceComponent delegate;

    protected AddonDecorator(SpaceComponent delegate) {
        this.delegate = delegate;
    }

    @Override public String getId() { return delegate.getId(); } // 동일 공간 식별 유지


}

