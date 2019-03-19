package eu.greyson.trains.game.objects;

import java.util.Collections;
import java.util.List;

class GameInfo extends GameObject {
    private final Game game;

    private int score;
    private int currentTick;
    private int totalTicks;

    private boolean isFinalScore = false;

    boolean isFinalScore() {
        return isFinalScore;
    }

    void setFinalScore() {
        isFinalScore = true;
    }

    GameInfo(Game game) {
        super(new Location(0, 10));
        this.game = game;
    }

    @Override
    void doTick() {
        score = game.getScore();
        currentTick = game.getTicked();
        totalTicks = game.getTotalTicks();
    }

    @Override
    List<Info> getExtraInfo() {
        return Collections.emptyList();
    }

    void accept(Drawer visitor) {
        visitor.visitGameInfo(this);
    }

    int getScore() {
        return score;
    }

    int getCurrentTick() {
        return currentTick;
    }

    int getTotalTicks() {
        return totalTicks;
    }
}
