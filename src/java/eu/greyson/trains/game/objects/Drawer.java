package eu.greyson.trains.game.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Drawer {

    private static final String TRAIN_ICON = "src/icons/train.png";
    private static final String TRAIN_ICON_RIGHT = "src/icons/trainRight.png";
    private static final String MINE_ICON_COAL = "src/icons/mine-coal.png";
    private static final String MINE_ICON_IRON = "src/icons/mine-iron.png";
    private static final String MINE_ICON_WOOD = "src/icons/mine-wood.png";
    private static final String FACTORY_ICON = "src/icons/factory.png";
    private static final String SHOP_ICON = "src/icons/shop.png";
    private static final String TREE_ICON_1 = "src/icons/tree1.png";
    private static final String TREE_ICON_2 = "src/icons/tree2.png";

    private static final Map<String, BufferedImage> images = new HashMap<>();

    static {
        images.put(TRAIN_ICON, loadImage(TRAIN_ICON));
        images.put(TRAIN_ICON_RIGHT, loadImage(TRAIN_ICON_RIGHT));
        images.put(MINE_ICON_COAL, loadImage(MINE_ICON_COAL));
        images.put(MINE_ICON_IRON, loadImage(MINE_ICON_IRON));
        images.put(MINE_ICON_WOOD, loadImage(MINE_ICON_WOOD));
        images.put(FACTORY_ICON, loadImage(FACTORY_ICON));
        images.put(SHOP_ICON, loadImage(SHOP_ICON));
        images.put(TREE_ICON_1, loadImage(TREE_ICON_1));
        images.put(TREE_ICON_2, loadImage(TREE_ICON_2));
    }

    private final Graphics graphics;
    private final Dimension windowSize;

    Drawer(Graphics graphics, Dimension preferredSize) {
        this.graphics = graphics;
        windowSize = preferredSize;
    }

    void visitGameInfo(GameInfo gameInfo) {
        if (gameInfo.isFinalScore()) {
            String message = "Final Score: " + gameInfo.getScore();
            graphics.drawString(
                    message,
                    (int) (windowSize.getWidth() / 2 - message.length() * 1.6),
                    (int) (windowSize.getHeight() / 2 + 25)
            );
        } else {
            String message = new StringBuilder()
                    .append("Score ").append(gameInfo.getScore())
                    .append(", Current tick ").append(gameInfo.getCurrentTick())
                    .append(", Total ticks ").append(gameInfo.getTotalTicks())
                    .toString();
            graphics.drawString(message, gameInfo.getLocation().getX(), gameInfo.getLocation().getY());
        }
    }

    void visitMine(Mine mine) {
        switch (mine.getProducedItem()) {
            case COAL:
                simpleDraw(images.get(MINE_ICON_COAL), mine, 40, 40);
                break;
            case IRON:
                simpleDraw(images.get(MINE_ICON_IRON), mine, 40, 40);
                break;
            case WOOD:
                simpleDraw(images.get(MINE_ICON_WOOD), mine, 40, 40);
                break;
        }
    }

    void visitFactory(Factory factory) {
        simpleDraw(images.get(FACTORY_ICON), factory, 40, 40);
    }

    void visitShop(Shop shop) {
        simpleDraw(images.get(SHOP_ICON), shop, 40, 40);
    }

    void visitTrain(Train train) {
        BufferedImage image;
        if (train.isHeadedLeft()) {
            image = images.get(TRAIN_ICON);
        } else {
            image = images.get(TRAIN_ICON_RIGHT);
        }
        simpleDraw(image, train, 60, 60);
    }

    void visitTree(Tree tree) {
        if (tree.isSimple()) {
            simpleDraw(images.get(TREE_ICON_1), tree, 40, 40);
        } else {
            simpleDraw(images.get(TREE_ICON_2), tree, 70, 50);
        }


    }

    private void simpleDraw(BufferedImage image, GameObject object, int x, int y) {
        Location location = GraphicsScalier.computeLocation(object.getLocation());

        graphics.drawImage(
                image,
                location.getX(),
                location.getY(),
                x,
                y,
                null);

        int counter = 0;
        List<String> extraInfo = object.getExtraInfo();
        for (String s : extraInfo) {
            graphics.drawString(s, location.getX() + x, location.getY() + (y / 2) + counter);
            counter += 10;
        }
    }

    private static BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
