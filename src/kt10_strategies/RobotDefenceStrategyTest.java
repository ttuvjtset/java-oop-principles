package kt10_strategies;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class RobotDefenceStrategyTest {
    private Robot robot;
    private RobotMotor robotMotor;

    @Before
    public void setUp() throws Exception {
        Strategy defenceStrategy = new DefenceStrategy();
        robotMotor = mock(RobotMotor.class);
        robot = new Robot(robotMotor, defenceStrategy, 5);
    }

    @Test
    public void reactToOtherRobotsAttack() throws Exception {
        robot.reactToOtherRobotsAttack(10, 10);

        verify(robotMotor).setMotorSpeed(1000);

        assertEquals(1, robot.getGear());
    }

    @Test
    public void reactToOtherRobotsRetreat() throws Exception {
        robot.reactToOtherRobotsRetreat(20);

        verify(robotMotor).setMotorSpeed(0);

        assertEquals(0, robot.getGear());
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