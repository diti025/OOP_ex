package main;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStatistics {

    public static double averagePoints(List<Driver> drivers) {

        int total = 0;

        for (Driver d : drivers) {
            total += d.getTotalPoints();
        }

        return (double) total / drivers.size();
    }

    public static String mostSuccessfulCountry(List<Driver> drivers) {

        Map<String, Integer> countryPoints = new HashMap<>();

        for (Driver d : drivers) {
            countryPoints.put(
                    d.getCountry(),
                    countryPoints.getOrDefault(d.getCountry(), 0) + d.getTotalPoints()
            );
        }

        return countryPoints.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    public static int totalChampionshipPoints(List<Driver> drivers) {

        int sum = 0;

        for (Driver d : drivers) {
            sum += d.getTotalPoints();
        }

        return sum;
    }
}