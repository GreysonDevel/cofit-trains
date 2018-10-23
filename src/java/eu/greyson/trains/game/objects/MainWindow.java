package eu.greyson.trains.game.objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class MainWindow extends JFrame {

    MainWindow(GameField gameField) {
        try {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("GC Trains");
            setResizable(false);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(
                    (int) (screen.getWidth() / 2 - gameField.getPreferredSize().width/2),
                    (int) (screen.getHeight() / 2 - gameField.getPreferredSize().height/2)
            );

            add(gameField);
            pack();
            setIconImage(ImageIO.read(new File("src/icons/train.png")));
            setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
