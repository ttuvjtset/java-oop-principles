package kt10_strategies;


public interface Strategy {
    void reactionToOtherRobotsAttack(Robot robot, int robotHeight, int approximationSpeed);

    void reactionToOtherRobotsRetreat(Robot robot, int retreatSpeed);

    void reactionToDeadlock(Robot robot) throws InterruptedException;
}
