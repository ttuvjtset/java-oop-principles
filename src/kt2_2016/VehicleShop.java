package kt2_2016;

import kt2_2016.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class VehicleShop {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private Predicate<? super Vehicle> lambda;


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    void addVehicle(Vehicle vehicle) {
        synchronized (vehicles) {
            vehicles.add(vehicle);
            vehicles.notifyAll();
        }
    }

    Vehicle popVehicleIfExists(Predicate<? super Vehicle> filterLambda) {

        synchronized (vehicles) {
            Optional<Vehicle> vehicleInContainer = Optional.empty();
            while (!vehicleInContainer.isPresent()) {

                vehicleInContainer = vehicles.stream().filter(filterLambda).findFirst();
                if (!vehicleInContainer.isPresent()) {
                    try {
                        vehicles.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Shutting down sleeping client thread");
                        break;
                    }
                }
            }
            vehicles.remove(vehicleInContainer.get());
            return vehicleInContainer.get();
        }


//        synchronized (tickets) {
//            Optional<Ticket> ticketInContainer = Optional.empty();
//            while (!ticketInContainer.isPresent()) {
//                ticketInContainer = tickets.stream().findAny();
//                if (!ticketInContainer.isPresent()) {
//                    tickets.wait();
//                }
//            }
//            tickets.remove(ticketInContainer.get());
//            return ticketInContainer.get();
//        }

    }

}
