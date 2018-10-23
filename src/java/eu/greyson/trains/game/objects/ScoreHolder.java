package eu.greyson.trains.game.objects;

class ScoreHolder {

    private int score = 0;

    void incrementScore(int increment) {
        score += increment;
    }

    int getScore() {
        return score;
    }
}
