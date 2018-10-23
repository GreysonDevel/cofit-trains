package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.TimeoutException;

public abstract class Dispatcher {

    protected final Game game;

    protected Dispatcher(Game game) {
        this.game = game;
    }

    public abstract void dispatchTrainPaths() throws TimeoutException;
}
