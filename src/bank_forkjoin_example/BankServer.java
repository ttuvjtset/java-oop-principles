package bank_forkjoin_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class BankServer {
    private List<Coin> coins = new ArrayList<>();

    void countCoins() {
        ForkJoinPool pool = new ForkJoinPool(4);

//        pool.execute(new CoinCounter(coins));

        pool.invoke(new CoinCounter(coins)); //<< saame tulemise


    }
}
