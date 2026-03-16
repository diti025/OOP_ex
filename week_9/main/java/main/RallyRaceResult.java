package main;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RallyRaceResult implements RaceResult {

    private String raceName;
    private String location;

    // position -> driver
    private Map<Integer, Driver> positions;

    // driver -> points
    private Map<Driver, Integer> results;

    public RallyRaceResult(String raceName, String location) {
        this.raceName = raceName;
        this.location = location;

        positions = new TreeMap<>(); // keeps order automatically
        results = new HashMap<>();
    }

    @Override
    public void recordResult(Driver driver, int position) {

        int points = calculatePoints(position);

        driver.addPoints(points);

        positions.put(position, driver);
        results.put(driver, points);
    }

    private int calculatePoints(int position) {

        switch (position) {
            case 1: return 25;
            case 2: return 18;
            case 3: return 15;
            case 4: return 12;
            default: return 0;
        }
    }

    @Override
    public Map<Driver, Integer> getResults() {
        return results;
    }

    @Override
    public String getRaceName() {
        return raceName + " (" + location + ")";
    }

    public Map<Integer, Driver> getPositions() {
        return positions;
    }
}