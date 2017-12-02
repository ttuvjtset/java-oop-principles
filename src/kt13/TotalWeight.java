package kt13;


class TotalWeight {
    private double totalWeight;

    TotalWeight() {
        this.totalWeight = 0;
    }

    private double getTotalWeight() {
        return totalWeight;
    }

    synchronized void registerWeight(int toAddWeight) throws InterruptedException {
        while (totalWeight + toAddWeight >= 1500) {
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
