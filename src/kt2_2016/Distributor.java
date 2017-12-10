package kt2_2016;

import java.util.Random;
import java.util.concurrent.Callable;


public class Distributor implements Callable<Double> {
    private static final int MAX_VEHICLE_PRICE = 8000;
    private static final int MIN_VEHICLE_PRICE = 2000;

    private double vehicleLimit;
    private String vehicleType;
    private VehicleShop vehicleShop;

    Distributor(double vehicleLimit, String vehicleType, VehicleShop vehicleShop) {
        this.vehicleLimit = vehicleLimit;
        this.vehicleType = vehicleType;
        this.vehicleShop = vehicleShop;
    }

    @Override
    public Double call() throws Exception {

        double counter = 0;
        while (counter < vehicleLimit) {
            System.out.println("iterating");
            int vehiclePrice = new Random().nextInt(MAX_VEHICLE_PRICE - MIN_VEHICLE_PRICE) + MIN_VEHICLE_PRICE;

            int vehicleRegistrationYear = 0;
            boolean seaVehicleWithSails = false;

            if (firstHalfOfTotalOrder(counter)) {
                vehicleRegistrationYear = 2009;
            } else {
                vehicleRegistrationYear = 2011;
                seaVehicleWithSails = true;
            }


            if (vehicleType.equals("sea")) {
                vehicleShop.addVehicle(new SeaVehicle(vehiclePrice, vehicleRegistrationYear, "Honda",
                        seaVehicleWithSails));
            } else if (vehicleType.equals("ground")) {
                vehicleShop.addVehicle(new GroundVehicle(vehiclePrice, vehicleRegistrationYear, "Mercedes",
                        50000));
            }

            counter++;
            Thread.sleep(5);
        }

        return counter;
    }

    private boolean firstHalfOfTotalOrder(double counter) {
        return (counter / vehicleLimit) < 0.5;
    }
}
