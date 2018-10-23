package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.TimeoutException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {
    private final int maxX;
    private final int maxY;
    private final int totalTicks;
    private final List<Train> trains;
    private final List<Building> buildings;
    private final List<Tree> decoratins;
    private final GameInfo gameInfo;

    private final List<GameField> observers;

    private Timer timer;

    private boolean isInTick = false;
    private boolean isEnd = false;
    private int ticked = 0;

   private final ScoreHolder scoreHolder;

    void incrementScore(int increment) {
        scoreHolder.incrementScore(increment);
    }

    int getScore() {
        return scoreHolder.getScore();
    }

    Game(int maxX, int maxY, int totalTicks, List<Train> trains, List<Building> buildings, ScoreHolder scoreHolder) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.totalTicks = totalTicks;
        this.trains = trains;
        this.buildings = buildings;
        this.scoreHolder = scoreHolder;

        observers = new ArrayList<>();

        gameInfo = new GameInfo(this);
        decoratins = generateDecorations();
    }

    /**
     * @return vrací maximální rozměry hracího pole na ose X
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @return vrací maximální rozměry hracího pole na ose Y
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @return vrací počet jednotek času
     */
    public int getTotalTicks() {
        return totalTicks;
    }

    int getTicked() {
        return ticked;
    }

    /**
     * @return Vrátí všechny vlaky na mapě
     */
    public List<Train> getTrains() throws TimeoutException {
        TimeChecker.checkTimeout();
        return Collections.unmodifiableList(trains);
    }

    /**
     * @return Vrátí všechny doly na mapě
     */
    public List<Mine> getMines() throws TimeoutException {
        TimeChecker.checkTimeout();
        return filterBuildings(Building::isMine, Mine.class);
    }

    /**
     * @return Vrátí všechny továrny na mapě
     */
    public List<Factory> getFactories() throws TimeoutException {
        TimeChecker.checkTimeout();
        return filterBuildings(Building::isFactory, Factory.class);
    }

    /**
     * @return Vrátí všechny obchody na mapě
     */
    public List<Shop> getShops() throws TimeoutException {
        TimeChecker.checkTimeout();
        return filterBuildings(Building::isShop, Shop.class);
    }

    Integer play() {
        trains.forEach(Train::finishTimetable);

        for (int i = 0; i < totalTicks; i++) {
            getAllGameObjects().forEach(GameObject::doTick);
        }

        return scoreHolder.getScore();
    }

    void start() {
        trains.forEach(Train::finishTimetable);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isInTick) {
                    isInTick = true;
                    doTick();
                    isInTick = false;
                }
            }
        }, 0, 100);
    }

    void finishGui() {
        timer.cancel();

        do {
            getAllGameObjects().forEach(GameObject::doTick);
        } while (!handleEnd());
    }

    private void doTick() {
        getAllGameObjects().forEach(GameObject::doTick);
        notifyObservers();
        handleEnd();
    }

    private boolean handleEnd() {
        if (++ticked > totalTicks) {
            timer.cancel();
            isEnd = true;
            gameInfo.setFinalScore();
            notifyObservers();
            return true;
        }
        return false;
    }

    private List<Tree> generateDecorations() {
        List<Tree> ret = new ArrayList<>();
        Random rand = new Random();

        int numberOfTrees = rand.ints(maxX / 10, maxX).findFirst().getAsInt();

        for (int i = 0; i < numberOfTrees; i++) {
            ret.add(new Tree(new Location(rand.nextInt(maxX), rand.nextInt(maxY))));
        }

        return ret;
    }

    private <T> List<T> filterBuildings(Predicate<Building> filterPredicate, Class<T> targetClass) {
        return Collections.unmodifiableList(buildings.stream().filter(filterPredicate).map(targetClass::cast).collect(Collectors.toList()));
    }

    List<GameObject> getAllGameObjects() {
        if (isEnd) {
            return Collections.singletonList(gameInfo);
        }

        List<GameObject> all = new ArrayList<>();

        all.addAll(decoratins);
        all.addAll(buildings);
        all.addAll(trains);
        all.add(gameInfo);

        return all;
    }

    void attach(GameField gf) {
        observers.add(gf);
    }

    void notifyObservers() {
        observers.forEach(GameField::update);
    }

}
