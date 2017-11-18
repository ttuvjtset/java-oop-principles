package kodutoo09.destination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Start {

    private static final int SMALL_MIDDLE_AREA_LIMIT = 100000;

    private static final String FIRST_LINE_IN_FILE = "Country,European Union,Accession Year,Council Votes," +
            "European Parliament Seats";

    private static final String FILE_PATH = "src\\kodutoo09\\states.csv";

    private List<Destination> destinationList = new ArrayList<>();



    public static void main(String[] args) {
        new Start().parseAndProcessData();
    }

    private static boolean isNotFirstLine(String s) {
        return !s.startsWith(FIRST_LINE_IN_FILE);
    }

    private void parseAndProcessData() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {

            destinationList = stream
                    .filter(Start::isNotFirstLine)
                    .map(Destination::new)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        processData();
    }

    private void processData() {
        averageGdp();

        euroCountriesGDP(); // minEuroCountriesGdp, averageEuroCountriesGdp, maxEuroCountriesGdp

        uniqueCurrenciesOfSmallMiddleCountries();

        averageReaumurTemperature();
    }

    private void averageGdp() {
        OptionalDouble averageGdp = destinationList.stream()
                .filter(s -> !s.getGdp().equals(""))
                .mapToDouble(s -> Double.parseDouble(s.getGdp()))
                .average();

        if (averageGdp.isPresent()) {
            System.out.println("Average GDP: " + averageGdp.getAsDouble());
        }
    }

    private void euroCountriesGDP() {
        OptionalDouble minEuroCountriesGdp = destinationList.stream()
                .filter(s -> s.getCurrency().equals("Euro"))
                .mapToDouble(s -> Double.parseDouble(s.getGdp()))
                .min();

        OptionalDouble averageEuroCountriesGdp = destinationList.stream()
                .filter(s -> s.getCurrency().equals("Euro"))
                .mapToDouble(s -> Double.parseDouble(s.getGdp()))
                .average();

        OptionalDouble maxEuroCountriesGdp = destinationList.stream()
                .filter(s -> s.getCurrency().equals("Euro"))
                .mapToDouble(s -> Double.parseDouble(s.getGdp()))
                .max();

        if (minEuroCountriesGdp.isPresent()) {
            System.out.print("Euro GDP min: " + minEuroCountriesGdp.getAsDouble());
        }

        if (averageEuroCountriesGdp.isPresent()) {
            System.out.print(" average: " + averageEuroCountriesGdp.getAsDouble());
        }

        if (maxEuroCountriesGdp.isPresent()) {
            System.out.println(" max: " + maxEuroCountriesGdp.getAsDouble());
        }
    }

    private void uniqueCurrenciesOfSmallMiddleCountries() {
        long numberOfUniqueCurrenciesOfSmallMiddleCountries = destinationList.stream()
                .filter(s -> Integer.parseInt(s.getArea()) < SMALL_MIDDLE_AREA_LIMIT)
                .map(Destination::getCurrency)
                .distinct()
                .count();

        System.out.println("Number of unique currencies of countries with area less than 100000 km2: "
                + numberOfUniqueCurrenciesOfSmallMiddleCountries);
    }

    private void averageReaumurTemperature() {
        OptionalDouble averageReaumurTemperature = destinationList.stream()
                .mapToDouble(s -> convertKelvinToReaumur(s.getAverageTemperature()))
                .average();

        if (averageReaumurTemperature.isPresent()) {
            System.out.println("Average Reaumur Temp: " + averageReaumurTemperature.getAsDouble());
        }
    }

    private double convertKelvinToReaumur(double kelvinTemp) {
        return (kelvinTemp - 273.15) * 0.8;
    }
}
