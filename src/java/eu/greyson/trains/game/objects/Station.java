package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.TimeoutException;

public class Station {
    public static final float DEFAULT_PERCENTAGE = 1.0f;

    private final Building building;
    private final Action action;
    private final Item item;
    private final float percentageOfCapacity;

    Station(Building building, Action action) {
        this(building, action, null, DEFAULT_PERCENTAGE);
    }

    Station(Building building, Action action, Item item, float percentageOfCapacity) {
        this.building = building;
        this.action = action;
        this.item = item;
        this.percentageOfCapacity = checkPercentage(percentageOfCapacity);
    }

    /**
     * Vytvoří novou zastávku vlaku, ve které vlak naloží zadanou surovinu až do své maximální kapacity.
     *
     * @param building cílová budova
     * @param item     surovina k naložení
     * @return zastávka
     */
    public static Station load(Building building, Item item) throws TimeoutException {
        TimeChecker.checkTimeout();
        return load(building, item, DEFAULT_PERCENTAGE);
    }

    /**
     * Vytvoří novou zastávku vlaku, ve které vlak naloží zadanou surovinu až do zadaných procent z aktuální volné kapacity.
     *
     * @param building cílová budova
     * @param item     surovina k naložení
     * @param percentageOfCapacity poměr obsazenosti aktuální volné kapacity
     * @return zastávka
     */
    public static Station load(Building building, Item item, float percentageOfCapacity) throws TimeoutException {
        TimeChecker.checkTimeout();
        return new Station(building, Action.LOAD, item, percentageOfCapacity);
    }

    /**
     * Vytvoří novou zastávku vlaku, ve které vlak kompletně vyloží svůj náklad s ohledem na kapacitu zastávky.
     *
     * @param building cílová budova
     * @return zastávka
     */
    public static Station unload(Building building) throws TimeoutException {
        TimeChecker.checkTimeout();
        return new Station(building, Action.UNLOAD);
    }

    Building getBuilding() {
        return building;
    }

    Action getAction() {
        return action;
    }

    Item getItem() {
        return item;
    }

    float getPercentageOfCapacity() {
        return percentageOfCapacity;
    }

    private float checkPercentage(float percentageOfCapacity) {
        if (percentageOfCapacity < 0 || percentageOfCapacity > 1.0f) {
            throw new NumberFormatException("Invalid percentage [" + percentageOfCapacity + "]");
        }
        return percentageOfCapacity;
    }
}
