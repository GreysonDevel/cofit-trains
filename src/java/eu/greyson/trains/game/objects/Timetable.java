package eu.greyson.trains.game.objects;

import eu.greyson.trains.game.exceptions.EmptyTimetableException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Timetable {
    private final List<Station> stations;
    private Iterator<Station> stationIterator;
    private Station currentStation;
    private boolean hasStations;

    Timetable() {
        stations = new LinkedList<>();
        hasStations = false;
    }

    void addStation(Station station) {
        stations.add(station);
    }

    void finishTimetable() {
        if (!stations.isEmpty()) {
            hasStations = true;

            stationIterator = stations.iterator();
            currentStation = getNextStation();
        }
    }

    Station getNextStation() {
        if (!hasStations){
            throw new EmptyTimetableException();
        }

        if (!stationIterator.hasNext()) {
            stationIterator = stations.iterator();
        }
        currentStation = stationIterator.next();
        return getCurrentStation();
    }

    Station getCurrentStation() {
        if (!hasStations){
            throw new EmptyTimetableException();
        }
        return currentStation;
    }

    boolean hasStations(){
        return hasStations;
    }
}
