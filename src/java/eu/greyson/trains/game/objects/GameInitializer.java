package eu.greyson.trains.game.objects;

import eu.greyson.trains.ImplementMePlayer;
import eu.greyson.trains.game.exceptions.GameAlreadyPlayedException;
import eu.greyson.trains.game.exceptions.TimeoutException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class GameInitializer {

    private final Game game;
    private boolean started = false;

    public GameInitializer(File map) throws IOException, TimeoutException {
        game = GameLoader.loadGameFromFile(map);
        Dispatcher dispatcher = new ImplementMePlayer(game);
        dispatcher.dispatchTrainPaths();
    }

    public void startGame() {
        handleDuplicatePlay();

        SwingUtilities.invokeLater(() -> {
            GameField gameField = new GameField(game);
            MainWindow window = new MainWindow(gameField);
            window.setVisible(true);
            game.attach(gameField);
            game.start();
        });
    }

    public int startGameOnlyScore() {
        handleDuplicatePlay();
        return game.play();
    }

    public static Integer serverRun(File map, final Class<Dispatcher> playerClass) throws Exception {
        Game game = GameLoader.loadGameFromFile(map);
        Dispatcher dispatcher = playerClass.getDeclaredConstructor(Game.class).newInstance(game);
        dispatcher.dispatchTrainPaths();
        return game.play();
    }

    private void handleDuplicatePlay() {
        if (started) {
            throw new GameAlreadyPlayedException();
        }
        started = true;
    }
}
