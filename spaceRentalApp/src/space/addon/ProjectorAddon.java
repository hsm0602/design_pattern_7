package space.addon;

import space.AddonDecorator;
import pattern.SpaceComponent;

// space/addon/ProjectorAddon.java
public class ProjectorAddon extends AddonDecorator {
    // private static final int COST = 5000;
    
    public ProjectorAddon(SpaceComponent delegate) { super(delegate); }

    @Override public String getDescription() {
        return delegate.getDescription() + " + Projector";
    }
//    @Override public int getCost() { return delegate.getCost() + COST; }
}
