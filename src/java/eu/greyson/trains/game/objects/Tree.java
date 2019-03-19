package eu.greyson.trains.game.objects;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class Tree extends GameObject {

    private final boolean isSimple;

    Tree(Location location) {
        super(location);
        isSimple = new Random().nextBoolean();
    }

    @Override
    void doTick() {
        // nothing
    }

    @Override
    void accept(Drawer visitor) {
        visitor.visitTree(this);
    }

   boolean isSimple() {
        return isSimple;
    }

    @Override
    List<Info> getExtraInfo() {
        return Collections.emptyList();
    }
}
