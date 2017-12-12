package kt2_2016;

import kt2_2016.vehicle.SeaVehicle;


class SeaVehicleDistributor extends GroundDistributor {
    SeaVehicleDistributor(int vehicleLimit, String vehicleType, VehicleShop vehicleShop) {
        super(vehicleLimit, vehicleType, vehicleShop);
    }

    @Override
    void createVehicle(int vehiclePrice, int vehicleRegistrationYear, boolean seaVehicleWithSails) {
        super.getVehicleShop().addVehicle(new SeaVehicle(vehiclePrice, vehicleRegistrationYear, "Honda",
                seaVehicleWithSails));
    }
}
