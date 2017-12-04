package kt13_future;


class TotalWeight {
    public static final int WEIGHT_THRESHOLD = 1500;
    private double totalWeight;

    TotalWeight() {
        this.totalWeight = 0;
    }

    private double getTotalWeight() {
        return totalWeight;
    }

    synchronized void registerWeight(int toAddWeight) throws InterruptedException {
        while (totalWeight + toAddWeight >= WEIGHT_THRESHOLD) {
            System.out.println("declined to add" + toAddWeight);
            wait();
        }
        totalWeight += toAddWeight;
        System.out.println("added" + toAddWeight + " total: " + getTotalWeight());
    }

    synchronized void removeWeight(int toRemoveWeight) {
        totalWeight -= toRemoveWeight;
        System.out.println("removed" + toRemoveWeight + " total: " + getTotalWeight());
        notifyAll();
    }
}
