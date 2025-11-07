package space.addon;

import space.AddonDecorator;
import pattern.SpaceComponent;

// space/addon/WifiAddon.java
public class WifiAddon extends AddonDecorator {
   //  private static final int COST = 2000;

    public WifiAddon(SpaceComponent delegate) { super(delegate); }

    @Override
    public String getDescription() {
        return delegate.getDescription() + " + Wi-Fi";
    }

//    @Override
//    public int getCost() {
//        return delegate.getCost() + COST;
//    }
}
