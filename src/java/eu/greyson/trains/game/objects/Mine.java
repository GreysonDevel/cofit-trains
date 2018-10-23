package eu.greyson.trains.game.objects;

public class Mine extends Building {

    private static final int TICK_PRODUCTION = 1;

    private final Item producedItem;

    Mine(Location location, Item material) {
        super(location);
        this.producedItem = material;
        storage.put(material, 0);
    }

    @Override
    void doTick() {
        storage.computeIfPresent(producedItem, (item, stored) -> stored + TICK_PRODUCTION);
    }

    /**
     * Vrací produkovanou surovinu.
     *
     * @return produkovaná surovina
     */
    public Item getProducedItem() {
        return producedItem;
    }

    @Override
    public boolean isMine() {
        return true;
    }

    @Override
    void accept(Drawer visitor) {
        visitor.visitMine(this);
    }
}
