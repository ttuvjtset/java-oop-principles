package kt10_strategies;

class RobotView {

    String getTextInfoOn(Robot robot) {
        return "Robot{" +
                "robotMotorSpeed=" + robot.getRobotMotor().getMotorSpeed() +
                ", strategy=" + robot.getStrategy() +
                ", robotHeight=" + robot.getRobotHeight() +
                ", gear=" + robot.getGear() +
                '}';
    }
}
