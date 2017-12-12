package kt2_2016;


import kt2_2016.vehicle.SeaVehicle;

import java.util.Random;
import java.util.concurrent.*;

public class Constructor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        VehicleShop vehicleShop = new VehicleShop();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        int firstDistributorLimit = new Random().nextInt(99 - 2) + 2;
        int secondDistributorLimit = 200 - firstDistributorLimit;
        System.out.println(firstDistributorLimit + " " + secondDistributorLimit);

        Future<Integer> distributorSeaVehicles = executor.submit(new SeaVehicleDistributor(firstDistributorLimit,
                "sea", vehicleShop));
        Future<Integer> distributorGroundVehicles = executor.submit(new GroundDistributor(secondDistributorLimit,
                "ground", vehicleShop));

        executor.submit(new Client(vehicleShop, s -> s.getVehiclePrice() < 5000, "Less than 5000"));
        executor.submit(new Client(vehicleShop, s -> {
            if (s instanceof SeaVehicle) {
                SeaVehicle seaVehicle = (SeaVehicle) s;
                return seaVehicle.isVehicleWithSail();
            } else {
                return false;
            }
        }, "Sail"));
        executor.submit(new Client(vehicleShop, s -> s.getRegistrationYear() < 2010 && s.getVehiclePrice() < 4500,
                "older than 2010 and less then 4500"));

        System.out.println(">> Sea Vehicles number generated: " + distributorSeaVehicles.get());
        System.out.println(">> Ground Vehicles number generated: " + distributorGroundVehicles.get());

        //System.out.println("total vehicles " + vehicleShop.getVehicles().size());

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdownNow();

    }
}
