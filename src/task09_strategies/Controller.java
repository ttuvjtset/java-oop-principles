package task09_strategies;

public class Controller {

    TankView tankView = new TankView();

    public String getJSON() {
        PriceStrategy priceStrategy = new PriceStrategy1();
        Tank tank = new Tank(priceStrategy);

        return tankView.getJSON(tank);
    }

}
