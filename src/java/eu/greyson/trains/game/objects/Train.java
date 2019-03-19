package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.TimeoutException;
import eu.greyson.trains.game.exceptions.UnknownActionException;

import java.util.*;

public class Train extends GameObject {

    private final int capacity;
    private final Map<Item, Integer> storage;

    private Timetable timetable;
    private boolean isHeadedLeft = false;

    Train(int capacity) {
        super(new Location(0, 0));
        this.capacity = capacity;
        this.storage = new HashMap<>();
        timetable = new Timetable();
    }

    /**
     * @return Vrací maximální kapacitu vlaku
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Vloží stanici do jízdního řádu vlaku
     *
     * @param station stanice
     */
    public void addStation(Station station) throws TimeoutException {
        TimeChecker.checkTimeout();
        timetable.addStation(station);
    }

    void finishTimetable() {
        timetable.finishTimetable();
    }

    @Override
    void doTick() {
        if (!timetable.hasStations()) {
            return;
        }
        Station currentStation = timetable.getCurrentStation();

        handleHeading(getLocation(), currentStation.getBuilding().getLocation());

        if (getLocation().equals(currentStation.getBuilding().getLocation())) {
            handleLoading(currentStation);
        } else {
            handleMovement(currentStation);
        }
    }

    private void handleLoading(Station currentStation) {
        Building building = currentStation.getBuilding();
        Action action = currentStation.getAction();

        switch (action) {
            case LOAD:
                Item item = currentStation.getItem();
                float percentageOfCapacity = currentStation.getPercentageOfCapacity();
                handleLoad(building, item, percentageOfCapacity);
                break;
            case UNLOAD:
                handleUnload(building);
                break;
            default:
                throw new UnknownActionException();
        }
        timetable.getNextStation();
    }

    private void handleLoad(Building building, Item item, float percentageOfCapacity) {
        int currentTrainLoad = storage.values().stream().mapToInt(Integer::intValue).sum();
        int freeSpace = capacity - currentTrainLoad;
        int loaded = building.loadFrom(item, (int) (freeSpace * percentageOfCapacity));

        storage.compute(item, (it, stored) -> {
            if (Objects.isNull(stored)) {
                stored = loaded;
            } else {
                stored += loaded;
            }
            return stored;
        });
    }

    private void handleUnload(Building building) {
        for (Map.Entry<Item, Integer> storedItems : storage.entrySet()) {
            int stored = building.loadInto(storedItems.getKey(), storedItems.getValue());
            storedItems.setValue(storedItems.getValue() - stored);
        }
    }

    private void handleMovement(Station currentStation) {
        Location nextLocation = currentStation.getBuilding().getLocation();
        Location currentLocation = getLocation();


        int newX = handleAxis(currentLocation.getX(), nextLocation.getX());
        int newY = handleAxis(currentLocation.getY(), nextLocation.getY());

        setLocation(new Location(newX, newY));
    }

    private int handleAxis(int axis, int finalAxis) {
        int newAxis;

        if (axis < finalAxis) {
            newAxis = axis + 1;
        } else if (axis > finalAxis) {
            newAxis = axis - 1;
        } else {
            newAxis = axis;
        }
        return newAxis;
    }

    private void handleHeading(Location current, Location next) {
        if (current.getX() < next.getX()) {
            isHeadedLeft = false;
        } else if (current.getX() > next.getX()) {
            isHeadedLeft = true;
        }
    }

    @Override
    void accept(Drawer visitor) {
        visitor.visitTrain(this);
    }

    boolean isHeadedLeft() {
        return isHeadedLeft;
    }

    @Override
    List<Info> getExtraInfo() {
        List<Info> ret = new ArrayList<>();

        storage.forEach((item, stored) -> {
            if (stored > 0) {
                ret.add(new Info(item + ":" + stored));
            }
        });

        return ret;
    }
}
