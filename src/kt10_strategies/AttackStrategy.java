package kt10_strategies;


public class AttackStrategy implements Strategy {

    private static final int MOTOR_SPEED_WHEN_DEADLOCK_HIGH_RPM = 2000;
    private static final int MOTOR_SPEED_WHEN_DEADLOCK_LOW_RPM = 300;
    private static final int MOTOR_SPEED_WHEN_DEADLOCK_MIDDLE_RPM = 1500;
    private static final int TIME_BETWEEN_MIDDLE_AND_LOW_SPEED_CHANGE = 1000;
    private static final int TIME_BETWEEN_LOW_AND_HIGH_SPEED_CHANGE = 100;
    private static final int GEAR_WHEN_DEADLOCK = 2;
    private static final int ROBOT_HEIGHT_LIMIT = 10;
    private static final int MOTOR_SPEED_HIGH = 2000;
    private static final int MOTOR_SPEED_MIDDLE = 1500;
    private static final int GEAR_HIGHER = 3;
    private static final int GEAR_MIDDLE = 2;
    private static final int RETREAT_SPEED_LIMIT = 500;

    @Override
    public void reactionToOtherRobotsAttack(Robot robot, int robotHeight, int approximationSpeed) {
        if (robotHeight < ROBOT_HEIGHT_LIMIT) {
            robot.setGear(GEAR_HIGHER); // optional?
            robot.setMotorSpeedTo(MOTOR_SPEED_HIGH);
        } else {
            robot.setGear(GEAR_MIDDLE); // optional?
            robot.setMotorSpeedTo(MOTOR_SPEED_MIDDLE);
        }
    }

    @Override
    public void reactionToOtherRobotsRetreat(Robot robot, int retreatSpeed) {
        if (retreatSpeed > RETREAT_SPEED_LIMIT) {
            robot.setGear(GEAR_HIGHER); // optional?
            robot.setMotorSpeedToMax(); // attack at max speed
        } else {
            robot.setGear(GEAR_MIDDLE); // optional?
            robot.setMotorSpeedTo(MOTOR_SPEED_MIDDLE); // attack at max speed
        }
    }

    @Override
    public void reactionToDeadlock(Robot robot) throws InterruptedException {
        robot.setGear(GEAR_WHEN_DEADLOCK); // optional?
        robot.setMotorSpeedTo(MOTOR_SPEED_WHEN_DEADLOCK_MIDDLE_RPM);
        Thread.sleep(TIME_BETWEEN_MIDDLE_AND_LOW_SPEED_CHANGE);
        robot.setMotorSpeedTo(MOTOR_SPEED_WHEN_DEADLOCK_LOW_RPM);
        Thread.sleep(TIME_BETWEEN_LOW_AND_HIGH_SPEED_CHANGE);
        robot.setMotorSpeedTo(MOTOR_SPEED_WHEN_DEADLOCK_HIGH_RPM);
    }

    @Override
    public String toString() {
        return "AttackStrategy";
    }
}
