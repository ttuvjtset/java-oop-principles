package kt2_2016;

public class GroundVehicle extends Vehicle {
    private double vehiclePrice;
    private int registrationYear;
    private String manufactureName;
    private int mileage;

    GroundVehicle(double vehiclePrice, int registrationYear, String manufactureName) {
        this.vehiclePrice = vehiclePrice;
        this.registrationYear = registrationYear;
        this.manufactureName = manufactureName;
    }

    GroundVehicle(double vehiclePrice, int registrationYear, String manufactureName, int mileage) {
        this.vehiclePrice = vehiclePrice;
        this.registrationYear = registrationYear;
        this.manufactureName = manufactureName;
        this.mileage = mileage;
    }

    public int getMileage() {
        return mileage;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public String getManufactureName() {
        return manufactureName;
    }


    @Override
    public String toString() {
        return "GroundVehicle{" +
                "vehiclePrice=" + vehiclePrice +
                ", registrationYear=" + registrationYear +
                ", manufactureName='" + manufactureName + '\'' +
                '}';
    }
}
