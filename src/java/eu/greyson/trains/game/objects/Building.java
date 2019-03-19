package eu.greyson.trains.game.objects;

import java.awt.*;
import java.util.List;
import java.util.*;

public abstract class Building extends GameObject {

    protected static final int STORAGE_CAPACITY = 1500;

    protected final Map<Item, Double> storage;

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

    /**
     * Vrátí kapacitu skladu pro jednotlivé suroviny.
     *
     * @return kapacita skladu
     */
    public int getStorageCapacity() {
        return STORAGE_CAPACITY;
    }

    int loadFrom(Item item, int quantity) {
        int storedAmount = (int) Math.floor(storage.getOrDefault(item, 0.0));
        int loaded;

        if (storedAmount >= quantity) {
            loaded = quantity;
        } else {
            loaded = storedAmount;
        }

        storage.computeIfPresent(item, (it, stored) -> stored - loaded);
        return loaded;
    }

    /**
     * Uloží do skladu budovi surovinu. Vrací počet úspěšně uložené suroviny.
     *
     * @param item     surovina k uložení do budovy
     * @param quantity počet suroviny k uložení
     * @return počet uložené suroviny
     */
    int loadInto(Item item, int quantity) {
        Double stored = storage.get(item);

        if (Objects.isNull(stored)) {
            stored = (double) quantity;
        } else {
            if (stored + quantity > STORAGE_CAPACITY) {
                double overFlow = (stored + quantity) - STORAGE_CAPACITY;
                quantity -= overFlow;
            }
            stored += quantity;
        }

        storage.put(item, stored);
        return quantity;
    }

    @Override
    List<Info> getExtraInfo() {
        List<Info> ret = new ArrayList<>();
        storage.forEach((item, stored) -> ret.add(generateInfo(item, stored)));
        return ret;
    }

    private Info generateInfo(Item item, Double stored) {
        Info info;
        String message = item + ":" + String.format("%.0f", stored);

        if (STORAGE_CAPACITY <= stored) {
            info = new Info(message, Color.RED);
        } else {
            info = new Info(message);
        }

        return info;
    }
}
