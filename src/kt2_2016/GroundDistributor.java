package kt2_2016;

import kt2_2016.vehicle.GroundVehicle;

import java.util.Random;
import java.util.concurrent.Callable;


public class GroundDistributor implements Callable<Integer> {
    private static final int MAX_VEHICLE_PRICE = 8000;
    private static final int MIN_VEHICLE_PRICE = 2000;

    private int vehicleLimit;
//    private String vehicleType;
    private VehicleShop vehicleShop;

    GroundDistributor(int vehicleLimit, String vehicleType, VehicleShop vehicleShop) {
        this.vehicleLimit = vehicleLimit;
//        this.vehicleType = vehicleType;
        this.vehicleShop = vehicleShop;
    }

    VehicleShop getVehicleShop() {
        return vehicleShop;
    }

    @Override
    public Integer call() throws Exception {

        int counter = 0;
        while (counter < vehicleLimit) {
            System.out.println("iterating");
            int vehiclePrice = new Random().nextInt(MAX_VEHICLE_PRICE - MIN_VEHICLE_PRICE) + MIN_VEHICLE_PRICE;

            int vehicleRegistrationYear;
            boolean seaVehicleWithSails = false;

            if (firstHalfOfOrders(counter)) {
                vehicleRegistrationYear = 2009;
            } else {
                vehicleRegistrationYear = 2011;
                seaVehicleWithSails = true;
            }

            createVehicle(vehiclePrice, vehicleRegistrationYear, seaVehicleWithSails);

            counter++;
            Thread.sleep(5);
        }

        return counter;
    }

    void createVehicle(int vehiclePrice, int vehicleRegistrationYear, boolean seaVehicleWithSails) {
        vehicleShop.addVehicle(new GroundVehicle(vehiclePrice, vehicleRegistrationYear, "Mercedes",
                50000));
    }

    private boolean firstHalfOfOrders(int counter) {
        double vehicleLimitDouble = vehicleLimit;
        return ((double) counter / vehicleLimitDouble) < 0.5;
    }
}
