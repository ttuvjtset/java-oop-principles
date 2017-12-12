package kt2_2016;

import kt2_2016.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Client implements Runnable {
    private VehicleShop vehicleShop;
    private Predicate<? super Vehicle> lambda;
    private List<Vehicle> boughtVehicles;
    private String clientName;

    Client(VehicleShop vehicleShop, Predicate<? super Vehicle> lambda, String clientName) {
        this.vehicleShop = vehicleShop;
        this.lambda = lambda;
        this.boughtVehicles = new ArrayList<>();
        this.clientName = clientName;
    }

    public List<Vehicle> getBoughtVehicles() {
        return boughtVehicles;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {

            Vehicle vehicle = vehicleShop.popVehicleIfExists(lambda);
            boughtVehicles.add(vehicle);
            System.out.println(clientName + " " + vehicle);

        }
    }
}
