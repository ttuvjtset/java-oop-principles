package kodutoo10;

class RobotMotor {
    private int motorSpeed;

    int getMotorSpeed() {
        return motorSpeed;
    }

    void setMotorSpeed(int motorSpeed) {
        this.motorSpeed = motorSpeed;
        System.out.println(motorSpeed);
    }
}
