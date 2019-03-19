package eu.greyson.trains.game.objects;

import java.util.Arrays;
import java.util.List;

public class Factory extends Building {
    private static final int TICK_PRODUCTION = 1;

    private final Item inA;
    private final Item inB;
    private final Item out;

    Factory(Location location, Item inA, Item inB, Item out) {
        super(location);
        this.inA = inA;
        this.inB = inB;
        this.out = out;
        storage.putIfAbsent(inB, 0.0);
        storage.putIfAbsent(inA, 0.0);
        storage.putIfAbsent(out, 0.0);
    }

    @Override
    public boolean isFactory() {
        return true;
    }

    /**
     * Vrací seznam přijímaných surovin pro výrobu.
     *
     * @return seznam surovin
     */
    public List<Item> getInputItems() {
        return Arrays.asList(inA, inB);
    }

    /**
     * Vrací produkovanou surovinu.
     *
     * @return produkovaná surovina
     */
    public Item getProducedItem() {
        return out;
    }

    @Override
    void doTick() {
        if (storage.get(inA) >= TICK_PRODUCTION
                && storage.get(inB) >= TICK_PRODUCTION
                && storage.get(out) + TICK_PRODUCTION <= STORAGE_CAPACITY) {
            storage.computeIfPresent(inA, (item, stored) -> stored - TICK_PRODUCTION);
            storage.computeIfPresent(inB, (item, stored) -> stored - TICK_PRODUCTION);
            storage.computeIfPresent(out, (item, stored) -> stored + TICK_PRODUCTION);
        }
    }

    @Override
    void accept(Drawer visitor) {
        visitor.visitFactory(this);
    }
}
