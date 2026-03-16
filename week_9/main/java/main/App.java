package main;

import java.util.Map;

public class App {

    public static void main(String[] args) {

        ChampionshipManager manager = ChampionshipManager.getInstance();

        RallyCar gravelCar = new GravelCar("Toyota", "GR Yaris", 380, 80);
        RallyCar asphaltCar = new AsphaltCar("Hyundai", "i20 N", 370, 70);

        Driver d1 = new Driver("Sebastien Ogier", "France", gravelCar);
        Driver d2 = new Driver("Kalle Rovanpera", "Finland", asphaltCar);
        Driver d3 = new Driver("Ott Tanak", "Estonia", gravelCar);
        Driver d4 = new Driver("Thierry Neuville", "Belgium", asphaltCar);

        manager.registerDriver(d1);
        manager.registerDriver(d2);
        manager.registerDriver(d3);
        manager.registerDriver(d4);

        // ===== RACE 1 =====
        RallyRaceResult race1 = new RallyRaceResult("Rally Finland", "Jyväskylä");

        race1.recordResult(d1, 1);
        race1.recordResult(d3, 2);
        race1.recordResult(d2, 3);
        race1.recordResult(d4, 4);

        manager.addRace(race1);

        // Demonstrate car switching
        d2.setCar(gravelCar);

        // ===== RACE 2 =====
        RallyRaceResult race2 = new RallyRaceResult("Monte Carlo Rally", "Monaco");

        race2.recordResult(d2, 1);
        race2.recordResult(d4, 2);
        race2.recordResult(d1, 3);
        race2.recordResult(d3, 4);

        manager.addRace(race2);

        // ===== STANDINGS =====
        System.out.println("===== CHAMPIONSHIP STANDINGS =====");

        int position = 1;
        for (Driver d : manager.getStandings()) {
            System.out.println(position++ + ". "
                    + d.getName() + " ("
                    + d.getCountry() + "): "
                    + d.getTotalPoints() + " points");
        }

        // ===== LEADER =====
        System.out.println("\n===== CHAMPIONSHIP LEADER =====");
        System.out.println(manager.getLeader().getName()
                + " with "
                + manager.getLeader().getTotalPoints()
                + " points");

        // ===== STATISTICS =====
        System.out.println("\n===== CHAMPIONSHIP STATISTICS =====");

        System.out.println("Total Drivers: "
                + ChampionshipManager.getTotalDrivers());

        System.out.println("Total Races: "
                + ChampionshipManager.getTotalRaces());

        System.out.println("Average Points Per Driver: "
                + ChampionshipStatistics.averagePoints(manager.getDrivers()));

        System.out.println("Most Successful Country: "
                + ChampionshipStatistics.mostSuccessfulCountry(manager.getDrivers()));

        System.out.println("Total Championship Points: "
                + ChampionshipStatistics.totalChampionshipPoints(manager.getDrivers()));

        // ===== RACE RESULTS =====
        System.out.println("\n===== RACE RESULTS =====");
        for (RaceResult race : manager.getRaces()) {
            RallyRaceResult rallyRace = (RallyRaceResult) race;
            System.out.println("Race: " + rallyRace.getRaceName());
            
            for (Map.Entry<Integer, Driver> entry : rallyRace.getPositions().entrySet()) {
                int racePosition = entry.getKey();   // renamed variable
                Driver driver = entry.getValue();
                int points = rallyRace.getResults().get(driver);

                System.out.println(
                    "Position " + racePosition + ": "
                    + driver.getName()
                    + " - "
                    + points + " points"
                );
            }
        }
        // ===== CAR PERFORMANCE =====
        System.out.println("\n===== CAR PERFORMANCE RATINGS =====");

        System.out.println("Gravel Car Performance: "
                + gravelCar.calculatePerformance());

        System.out.println("Asphalt Car Performance: "
                + asphaltCar.calculatePerformance());
    }
}