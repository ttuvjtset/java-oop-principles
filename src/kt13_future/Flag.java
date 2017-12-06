package kt13_future;


class Flag {
    private volatile boolean rideAllowed; // << siin on k6igi m6istlikum!!!

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
