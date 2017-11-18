package kodutoo10;

class Robot {
    private static final int MAXIMUM_RPM_SPEED = 2500;
    private RobotMotor robotMotor;
    private Strategy strategy;
    private int robotHeight;
    private int gear;

    Robot(RobotMotor robotMotor, Strategy strategy, int robotHeight) {
        this.robotMotor = robotMotor;
        this.strategy = strategy;
        this.robotHeight = robotHeight;
        this.gear = 0;
    }

    RobotMotor getRobotMotor() {
        return robotMotor;
    }

    Strategy getStrategy() {
        return strategy;
    }

    int getGear() {
        return gear;
    }

    void setGear(int gear) {
        this.gear = gear;
    }

    void reactToOtherRobotsAttack(int robotHeight, int approximationSpeed) {
        this.strategy.reactionToOtherRobotsAttack(this, robotHeight, approximationSpeed);
    }

    void reactToOtherRobotsRetreat(int retreatSpeed) {
        this.strategy.reactionToOtherRobotsRetreat(this, retreatSpeed);
    }

    void reactToDeadlock() throws InterruptedException {
        this.strategy.reactionToDeadlock(this);
    }

    int getRobotHeight() {
        return robotHeight;
    }

    void setMotorSpeedTo(int motorSpeedTo) {
        this.robotMotor.setMotorSpeed(motorSpeedTo);
    }

    void setMotorSpeedToMax() {
        this.robotMotor.setMotorSpeed(MAXIMUM_RPM_SPEED);
    }
}
