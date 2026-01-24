package idv.kuma;

import java.util.Random;
import java.util.function.Consumer;

public class CalculateByLucky {
    final Random random;

    public CalculateByLucky(Random random) {
        this.random = random;
    }

    double getPriceByLuckyDiscount(double d, Consumer<String> printingFunction) {
        if (d > 500) {
            if (random.nextBoolean()) { // 50% 機率
                double rd = 0.01 + (0.1 - 0.01) * random.nextDouble(); // 1% ~ 10% 隨機
                printingFunction.accept("LUCKY! You got a random discount: " + Math.round(rd * 100) + "%");
                d = d * (1 - rd);
            }
        }
        return d;
    }
}