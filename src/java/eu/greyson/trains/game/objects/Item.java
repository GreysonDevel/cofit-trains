package eu.greyson.trains.game.objects;

/**
 * Surovina produkovaná v dole/továrně.
 */
public enum Item {
    IRON(1),
    WOOD(1),
    COAL(2),
    STEEL(10),
    AXE(100);

    private final int sellPrice;

    Item(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * @return vrací cenu suroviny za kterou je prodána v obchodu.
     */
    public int getSellPrice() {
        return sellPrice;
    }
}
