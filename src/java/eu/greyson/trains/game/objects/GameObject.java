package eu.greyson.trains.game.objects;

import java.util.List;

public abstract class GameObject {

    private Location location;

    GameObject(Location location) {
        this.location = location;
    }

    /**
     * @return Vrací polohu objektu na mapě
     */
    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    abstract void doTick();

    abstract List<String> getExtraInfo();

    abstract void accept(Drawer visitor);
}
