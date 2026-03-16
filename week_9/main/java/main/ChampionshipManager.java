package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChampionshipManager {

    private static ChampionshipManager instance;

    private static int totalDrivers = 0;
    private static int totalRaces = 0;

    private List<Driver> drivers;
    private List<RaceResult> races;

    private ChampionshipManager() {
        drivers = new ArrayList<>();
        races = new ArrayList<>();
    }

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
        totalDrivers++;
    }

    public void addRace(RaceResult race) {
        races.add(race);
        totalRaces++;
    }

    public List<Driver> getStandings() {

        List<Driver> sortedDrivers = new ArrayList<>(drivers);

        sortedDrivers.sort(
                Comparator.comparingInt(Driver::getTotalPoints).reversed()
        );

        return sortedDrivers;
    }

    public Driver getLeader() {
        return getStandings().get(0);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<RaceResult> getRaces() {
        return races;
    }

    public static int getTotalDrivers() {
        return totalDrivers;
    }

    public static int getTotalRaces() {
        return totalRaces;
    }
}