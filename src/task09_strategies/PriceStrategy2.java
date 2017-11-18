package task09_strategies;

public class PriceStrategy2 implements PriceStrategy {
    private static final double PRICE_STRATEGY_ONE = 1.15;

    @Override
    public double getLiterPrice(Tank tank, int fillAmount) {
        if (fillAmount > 30) {
            return PRICE_STRATEGY_ONE * 0.9;
        } else {
            return PRICE_STRATEGY_ONE;
        }
    }
}
