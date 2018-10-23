package eu.greyson.trains.game.objects;

import java.awt.*;

class GraphicsScalier {

    static Dimension computeWindowSize(int maxX, int maxY) {
        maxX += 9;
        maxY += 9;

        maxX *= 9.8;
        maxY *= 9.8;

        return new Dimension(maxX, maxY);
    }

    static Location computeLocation(Location location) {
        return new Location((location.getX() * 10) + 10, (location.getY() * 10) + 10);
    }

}
