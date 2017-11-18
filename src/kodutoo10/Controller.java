package kodutoo10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Controller {
    private RobotView robotView = new RobotView();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.out.println((new Controller()).getTextInfo());

        FileOutputStream out = new FileOutputStream("output.txt");
        System.setOut(new PrintStream(out));

        Strategy defenceStrategy = new DefenceStrategy();
        RobotMotor robotMotor = new RobotMotor();
        Robot robot = new Robot(robotMotor, defenceStrategy, 5);

        robot.reactToDeadlock();

        Strategy attackStrategy = new AttackStrategy();
        RobotMotor robotMotor2 = new RobotMotor();
        Robot robot2 = new Robot(robotMotor2, attackStrategy, 5);

        robot2.reactToOtherRobotsAttack(9, 15);

        robot2.reactToOtherRobotsRetreat(500);

        robot2.reactToOtherRobotsRetreat(501);
    }

    private String getTextInfo() {
        Strategy strategy = new DefenceStrategy();
        RobotMotor robotMotor = new RobotMotor();
        Robot robot = new Robot(robotMotor, strategy, 5);

        return robotView.getTextInfoOn(robot);
    }


}
