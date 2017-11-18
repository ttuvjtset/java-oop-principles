package kt10_strategies;

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
