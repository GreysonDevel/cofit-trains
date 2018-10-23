package eu.greyson.trains.game.objects;

import java.util.*;

public abstract class Building extends GameObject {

    protected final Map<Item, Integer> storage;

    Building(Location location) {
        super(location);
        storage = new HashMap<>();
    }

    /**
     * @return Příznak zda je budova dolem.
     */
    public boolean isMine() {
        return false;
    }

    /**
     * @return Příznak zda je budova továrnou.
     */
    public boolean isFactory() {
        return false;
    }

    /**
     * @return Příznak zda je budova obchodem.
     */
    public boolean isShop() {
        return false;
    }

    int loadFrom(Item item, int quantity) {
        int storedAmount = storage.getOrDefault(item, 0);
        int loaded;

        if (storedAmount >= quantity) {
            loaded = quantity;
        } else {
            loaded = storedAmount;
        }

        storage.computeIfPresent(item, (it, stored) -> stored - loaded);
        return loaded;
    }

    void loadInto(Item item, int quantity) {
        storage.compute(item, (it, stored) -> {
            if (Objects.isNull(stored)) {
                stored = quantity;
            } else {
                stored += quantity;
            }
            return stored;
        });
    }

    @Override
    List<String> getExtraInfo() {
        List<String> ret = new ArrayList<>();
        storage.forEach((item, stored) -> ret.add(item + ":" + stored));
        return ret;
    }
}
