package kt13;


class Flag {
    private boolean rideAllowed;

    Flag() {
        this.rideAllowed = true;
    }

    boolean isRideAllowed() {
        return rideAllowed;
    }

    void setRedFlag() {
        rideAllowed = false;
    }
}
