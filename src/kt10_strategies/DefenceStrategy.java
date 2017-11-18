package kt10_strategies;


public class DefenceStrategy implements Strategy {

    private static final int MOTOR_SPEED_WHEN_ATTACKED = 1000;
    private static final int GEAR_WHEN_ATTACKED = 1;

    private static final int MOTOR_SPEED_WHEN_OTHER_ROBOT_RETREATS = 0;
    private static final int GEAR_WHEN_OTHER_ROBOT_RETREATS = 0;

    private static final int MOTOR_SPEED_WHEN_DEADLOCK_HIGH_RPM = 2000;
    private static final int MOTOR_SPEED_WHEN_DEADLOCK_LOW_RPM = 300;
    private static final int MOTOR_SPEED_WHEN_DEADLOCK_MIDDLE_RPM = 1500;
    private static final int TIME_BETWEEN_MIDDLE_AND_LOW_SPEED_CHANGE = 1000;
    private static final int TIME_BETWEEN_LOW_AND_HIGH_SPEED_CHANGE = 100;
    private static final int GEAR_WHEN_DEADLOCK = 2;

    @Override
    public void reactionToOtherRobotsAttack(Robot robot, int robotHeight, int approximationSpeed) {
        robot.setMotorSpeedTo(MOTOR_SPEED_WHEN_ATTACKED);
        robot.setGear(GEAR_WHEN_ATTACKED);
    }

    @Override
    public void reactionToOtherRobotsRetreat(Robot robot, int retreatSpeed) {
        robot.setMotorSpeedTo(MOTOR_SPEED_WHEN_OTHER_ROBOT_RETREATS);
        robot.setGear(GEAR_WHEN_OTHER_ROBOT_RETREATS);
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
        return "DefenceStrategy";
    }
}
