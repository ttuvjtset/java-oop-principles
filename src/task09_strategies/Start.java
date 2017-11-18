package task09_strategies;

public class Start {

    public static void main(String[] args) {
        System.out.println("Strategy 1");
        PriceStrategy priceStrategy1 = new PriceStrategy1();
        Tank tank = new Tank(priceStrategy1);
        tank.fillFullBensinTank();
        tank.buyBensin(700);

        System.out.println(tank.getPrice(10));


        System.out.println("Strategy 2");
        PriceStrategy priceStrategy2 = new PriceStrategy2();
        Tank tank2 = new Tank(priceStrategy2);
        tank2.fillFullBensinTank();

        System.out.println(tank2.getPrice(10));
        System.out.println(tank2.getPrice(31));


        System.out.println("Strategy 3");
        PriceStrategy priceStrategy3 = (tank1, fillAmount) -> {
            if (fillAmount >= 5) {
                return 0.99;
            } else {
                return 1.00;
            }
        };
        Tank tank3 = new Tank(priceStrategy3);
        tank3.fillFullBensinTank();

        System.out.println(tank3.getPrice(1));
        System.out.println(tank3.getPrice(6));

    }

}
