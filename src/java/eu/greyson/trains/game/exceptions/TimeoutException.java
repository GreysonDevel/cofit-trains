package eu.greyson.trains.game.exceptions;

/** Max time to execute is exceeded. */
public class TimeoutException extends Exception {
    public TimeoutException() {
        super("Vypršel časový limit");
    }
}
