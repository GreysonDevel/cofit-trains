package eu.greyson.trains.game.objects;

import java.util.Map;

public class Shop extends Building {

    private final ScoreHolder scoreHolder;

    protected Shop(Location location, ScoreHolder scoreHolder) {
        super(location);
        this.scoreHolder = scoreHolder;
    }

    @Override
    void doTick() {
        for (Map.Entry<Item, Double> itemRecord : storage.entrySet()) {
            Item item = itemRecord.getKey();
            double stored = itemRecord.getValue();
            scoreHolder.incrementScore((int) stored * item.getSellPrice());
        }
        storage.clear();
    }

    @Override
    public boolean isShop() {
        return true;
    }

    @Override
    void accept(Drawer visitor) {
        visitor.visitShop(this);
    }
}
