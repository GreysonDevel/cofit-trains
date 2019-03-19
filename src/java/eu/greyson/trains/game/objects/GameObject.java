package eu.greyson.trains.game.objects;

import java.awt.*;
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

    abstract List<Info> getExtraInfo();

    abstract void accept(Drawer visitor);

    class Info {
        private final String message;
        private final Color color;

        Info(String message) {
            this(message, Color.BLACK);
        }

        Info(String message, Color color) {
            this.message = message;
            this.color = color;
        }

        String getMessage() {
            return message;
        }

        Color getColor() {
            return color;
        }
    }
}
