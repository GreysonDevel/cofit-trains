package eu.greyson.trains.game.objects;

public class Mine extends Building {

    private final Item producedItem;
    private final double tickProduction;

    Mine(Location location, Item material, double tickProduction) {
        super(location);
        this.producedItem = material;
        this.tickProduction = tickProduction;
        storage.put(material, 0.0);
    }

    @Override
    void doTick() {
        if (storage.get(producedItem) + tickProduction > STORAGE_CAPACITY) {
            storage.put(producedItem, (double) STORAGE_CAPACITY);
        } else {
            storage.computeIfPresent(producedItem, (item, stored) -> stored + tickProduction);
        }
    }

    /**
     * Vrací produkovanou surovinu.
     *
     * @return produkovaná surovina
     */
    public Item getProducedItem() {
        return producedItem;
    }

    /**
     * Vrací kolik suroviny je vyrobeno za jednu jednotku času.
     *
     * @return produkce suroviny
     */
    public double getTickProduction() {
        return tickProduction;
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
