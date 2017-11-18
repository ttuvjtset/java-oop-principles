package task9;

public class PriceStrategy1 implements PriceStrategy {

    private static final double PRICE_STRATEGY_ONE = 1.20;

    @Override
    public double getLiterPrice(Tank tank, int fillAmount) {
        if (tank.getBensinLeft() / Tank.getFullAmount() <= 0.4) {
            return PRICE_STRATEGY_ONE * 1.2;
        } else {
            return PRICE_STRATEGY_ONE;
        }
    }
}
