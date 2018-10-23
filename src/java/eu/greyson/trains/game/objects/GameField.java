package eu.greyson.trains.game.objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

class GameField extends JPanel implements MouseListener {
    private final Game game;
    private final BufferedImage backgroundImage;

    GameField(Game game) {
        this.game = game;
        setBackground(new Color(255, 255, 255));
        setDoubleBuffered(true);
        setLocation(0, 0);
        addMouseListener(this);

        setPreferredSize(GraphicsScalier.computeWindowSize(game.getMaxX(), game.getMaxY()));
        try {
            backgroundImage = ImageIO.read(new File("src/icons/logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        Drawer drawer = new Drawer(g, getPreferredSize());
        List<GameObject> gameObjects = game.getAllGameObjects();
        gameObjects.forEach(object -> object.accept(drawer));
    }

    private void paintBackground(Graphics graphics) {
        int x = 180;
        int y = x / 3; // picture ratio
        graphics.drawImage(
                backgroundImage,
                (int) (getPreferredSize().getWidth() / 2 - x / 2),
                (int) (getPreferredSize().getHeight() / 2 - y / 2),
                x,
                y,
                null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.finishGui();
        removeMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
