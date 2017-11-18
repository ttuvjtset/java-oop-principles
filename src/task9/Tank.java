package task9;

public class Tank {
    private static final int FULL_AMOUNT = 1000;
    private int bensinLeft = 0;
    //private double literPrice;
    private int id;
    private PriceStrategy strategy;


    public Tank(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    static int getFullAmount() {
        return FULL_AMOUNT;
    }

    int getBensinLeft() {
        return bensinLeft;
    }

    public int getId() {
        return id;
    }

    void buyBensin(int amount) {
        if (bensinLeft + amount >= 0) {
            bensinLeft -= amount;
        }
    }

    //tankservice.buybensin(tank);

    void fillFullBensinTank() {
        bensinLeft = FULL_AMOUNT;
    }


    double getPrice(int fillAmount) {
        return strategy.getLiterPrice(this, fillAmount);
    }

}
