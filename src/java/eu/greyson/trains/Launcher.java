package eu.greyson.trains;

import eu.greyson.trains.game.exceptions.TimeoutException;
import eu.greyson.trains.game.objects.GameInitializer;

import java.io.File;
import java.io.IOException;

public class Launcher {

    /**
     * Příznak zda spustit GUI
     */
    private static final boolean START_GUI = true;

    /**
     * Můžeš předat mapu jako parametr
     */
    public static void main(String[] arg) throws IOException, TimeoutException {
        String map = arg.length == 0 ? "src/train.txt" : arg[0];

        GameInitializer gameInitializer = new GameInitializer(new File(map));

        if (START_GUI){
            // start gui
            gameInitializer.startGame();
        } else {
            // pouze vytiskne skóre
            System.out.println("Final Score:" + gameInitializer.startGameOnlyScore());
        }
    }
}
