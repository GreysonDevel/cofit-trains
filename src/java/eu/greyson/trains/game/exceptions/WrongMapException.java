package eu.greyson.trains.game.exceptions;

public class WrongMapException extends RuntimeException {
    public WrongMapException() {
    }

    public WrongMapException(String message) {
        super(message);
    }
}
