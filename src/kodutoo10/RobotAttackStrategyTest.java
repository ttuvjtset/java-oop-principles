package kodutoo10;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class RobotAttackStrategyTest {
    private RobotMotor robotMotor;
    private Robot robot;


    @Before
    public void setUp() throws Exception {
        Strategy defenceStrategy = new AttackStrategy();
        robotMotor = mock(RobotMotor.class);
        robot = new Robot(robotMotor, defenceStrategy, 5);
    }

    @Test
    public void reactToOtherRobotsAttackSmallRobot() throws Exception {
        robot.reactToOtherRobotsAttack(5, 10);

        verify(robotMotor).setMotorSpeed(2000);

        assertEquals(3, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsAttackSmallRobotMiddleRobot1() throws Exception {
        robot.reactToOtherRobotsAttack(9, 10);

        verify(robotMotor).setMotorSpeed(2000);

        assertEquals(3, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsAttackSmallRobotMiddleRobot2() throws Exception {
        robot.reactToOtherRobotsAttack(10, 10);

        verify(robotMotor).setMotorSpeed(1500);

        assertEquals(2, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsAttackSmallRobotMiddleRobot3() throws Exception {
        robot.reactToOtherRobotsAttack(11, 10);

        verify(robotMotor).setMotorSpeed(1500);

        assertEquals(2, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsAttackSmallRobotBigRobot() throws Exception {
        robot.reactToOtherRobotsAttack(20, 10);

        verify(robotMotor).setMotorSpeed(1500);

        assertEquals(2, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsRetreat1() throws Exception {
        robot.reactToOtherRobotsRetreat(499);

        verify(robotMotor).setMotorSpeed(1500);
    }

    @Test
    public void reactToOtherRobotsRetreat2() throws Exception {
        robot.reactToOtherRobotsRetreat(500);

        verify(robotMotor).setMotorSpeed(1500);
    }

    @Test
    public void reactToOtherRobotsRetreat3() throws Exception {
        robot.reactToOtherRobotsRetreat(501);

        verify(robotMotor).setMotorSpeed(2500);
    }

    @Test
    public void reactToDeadlock() throws Exception {
        robot.reactToDeadlock();

        InOrder order = Mockito.inOrder(robotMotor);
        order.verify(robotMotor).setMotorSpeed(1500);
        order.verify(robotMotor).setMotorSpeed(300);
        order.verify(robotMotor).setMotorSpeed(2000);
        verifyNoMoreInteractions(robotMotor);

        assertEquals(2, robot.getGear());
    }

}