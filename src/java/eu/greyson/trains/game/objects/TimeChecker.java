package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.TimeoutException;

import java.util.Date;

abstract class TimeChecker {
    private static Date created = new Date();

    /**
     * Check if max time to execute is exceeded.
     */
    static void checkTimeout() throws TimeoutException {
        if ((new Date().getTime() - created.getTime()) > 30000) {
            throw new TimeoutException();
        }
    }

    static void reset(){
        created = new Date();
    }
}
