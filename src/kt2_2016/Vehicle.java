package kt2_2016;

public abstract class Vehicle {

    abstract double getVehiclePrice();

    abstract int getRegistrationYear();

    abstract String getManufactureName();

    public boolean isVehicleWithSail() {
        return false;
    }
}
