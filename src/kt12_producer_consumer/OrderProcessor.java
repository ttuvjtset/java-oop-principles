package kt12_producer_consumer;


public class OrderProcessor implements Runnable {
    private static final double DISCOUNTED_RATE_FOR_BIG_ORDERS = 0.9;
    private static final double PRICE_PER_UNIT = 0.55;
    private static final int MINIMUM_PERIOD_ELIGIBLE_FOR_DISCOUNT = 6;

    //private BlockingQueue<Order> queue;

    private Orders orders;
    private String threadName;

    OrderProcessor(Orders orders, String threadName) {
        this.orders = orders;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Order order = orders.getOrder();
                System.out.println("Thread: " + threadName + " | " + order + " | Price: "
                        + order.getPrice(unitsPerWeek -> {
                    if (isEligibleForDiscount(order)) {
                        return unitsPerWeek * PRICE_PER_UNIT * DISCOUNTED_RATE_FOR_BIG_ORDERS;
                    }
                    return unitsPerWeek * PRICE_PER_UNIT;
                }));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isEligibleForDiscount(Order order) {
        return order.getOrderLengthInMonths() >= MINIMUM_PERIOD_ELIGIBLE_FOR_DISCOUNT;
    }
}
