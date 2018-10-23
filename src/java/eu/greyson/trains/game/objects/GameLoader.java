package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.WrongMapException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class GameLoader {

    private static final int maxXMapLimit = 140;
    private static final int maxYMapLimit = 80;
    private static final int minXMapLimit = 80;
    private static final int minYMapLimit = 60;

    static Game loadGameFromFile(File map) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(map));
        int maxX = maxXLimitValidator(br.readLine());
        int maxY = maxYLimitValidator(br.readLine());
        int totalTicks = Integer.parseInt(br.readLine());
        List<Building> buildings = new ArrayList<>();
        List<Train> trains = new ArrayList<>();
        String line = br.readLine();

        ScoreHolder scoreHolder = new ScoreHolder();
        while (!line.equals("xxx")) {
            switch (line) {
                case "Mine":
                    Mine mine = new Mine(new Location(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())), itemSetter(br.readLine()));
                    buildings.add(mine);
                    break;
                case "Factory":
                    Factory factory = new Factory(new Location(Integer.parseInt(br.readLine()),
                            Integer.parseInt(br.readLine())), itemSetter(br.readLine()), itemSetter(br.readLine()), itemSetter(br.readLine()));
                    buildings.add(factory);
                    break;
                case "Shop":
                    Shop shop = new Shop(new Location(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())),scoreHolder);
                    buildings.add(shop);
                    break;
                default:
                    throw new WrongMapException();
            }

            line = br.readLine();
        }
        line = br.readLine();
        while (!line.equals("xxx")) {
            Train train = new Train(Integer.parseInt(line));
            trains.add(train);
            line = br.readLine();
        }

        validateBuildings(buildings, maxX, maxY);
        samePlaceValidator(buildings);

        TimeChecker.reset();
        return new Game(maxX, maxY, totalTicks, trains, buildings, scoreHolder);
    }

    private static Item itemSetter(String fileItem) {
        return Item.valueOf(fileItem.toUpperCase());
    }

    private static void validateBuildings(List<Building> validateObject, int maxX, int maxY) {
        for (Building aValidateObject : validateObject) {
            if (maxX < aValidateObject.getLocation().getX() || maxY < aValidateObject.getLocation().getY()) {
                throw new WrongMapException();
            }
        }
    }

    private static int maxXLimitValidator(String currentMax) {
        int maxToValidate = Integer.parseInt(currentMax);
        if (maxToValidate > maxXMapLimit || maxToValidate < minXMapLimit) {
            throw new WrongMapException();
        }
        return maxToValidate;
    }

    private static int maxYLimitValidator(String currentMax) {
        int maxToValidate = Integer.parseInt(currentMax);
        if (maxToValidate > maxYMapLimit || maxToValidate < minYMapLimit) {
            throw new WrongMapException();
        }
        return maxToValidate;
    }

    private static void samePlaceValidator(List<Building> buildings) {
        for (int a = 0; a < buildings.size(); a++) {
            Location location = buildings.get(a).getLocation();
            for (int b = 0; b < buildings.size(); b++) {
                if (a != b) {
                    Location locationTwo = buildings.get(b).getLocation();
                    if (location.getY() == locationTwo.getY() && locationTwo.getX() == location.getX()) {
                        throw new WrongMapException();
                    }
                }
            }
        }
    }
}
