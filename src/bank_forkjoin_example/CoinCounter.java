package bank_forkjoin_example;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CoinCounter extends RecursiveTask<Long> {
    private List<Coin> coins;

    CoinCounter(List<Coin> coins) {
        this.coins = coins;
    }

    @Override
    protected Long compute() {
        if (coins.size() < 8) {
            // do work directly
            return countCoins();

        } else {
            // devide work
            CoinCounter part1 = new CoinCounter(coins.subList(0, coins.size() / 2));
            CoinCounter part2 = new CoinCounter(coins.subList(coins.size() / 2, coins.size()));

            // fork first part (run in new thread)
            ForkJoinTask<Long> result1 = part1.fork(); // razdeljaet na otdelnij thread // see pool l2heb eraldi threadi
            // compute second part (run in the same thread)
            long result2 = part2.compute(); // << recursive task
            // aggregate (join blocks and waits until result is available)
            return result2 + result1.join();
        }
        //return null;
    }

    private Long countCoins() {
        // do actual counting here
        return 0L;
    }
}
