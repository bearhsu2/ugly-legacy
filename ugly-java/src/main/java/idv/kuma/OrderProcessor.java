package idv.kuma;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderProcessor {

    public void process(List<Map<String, Object>> l, String t, boolean f) {
        double d = 0;

        if (l != null && l.size() > 0) {
            for (Map<String, Object> i : l) {
                double p = (double) i.get("p");
                int q = (int) i.get("q");
                if (p > 0 && q > 0) {
                    d += p * q;
                }
            }

            if (d > 500) {
                Random r = getRandom();
                if (r.nextBoolean()) { // 50% 機率
                    double rd = 0.01 + (0.1 - 0.01) * r.nextDouble(); // 1% ~ 10% 隨機
                    print("LUCKY! You got a random discount: " + Math.round(rd * 100) + "%");
                    d = d * (1 - rd);
                }
            }
            // -----------------------

            if (t.equals("VIP")) {
                if (d > 1000) d = d * 0.85;
                else d = d * 0.9;
            } else if (t.equals("GOLD")) {
                d = d * 0.95;
            } else {
                if (d > 500) d = d - 20;
            }

            if (f) {
                if (!(t.equals("VIP") && d > 500)) d = d + 60;
            }

            print("Customer Type: " + t);
            print("Total Price: " + Math.round(d * 100.0) / 100.0);

            if (d > 1000) print("Status: Large Order");
            else print("Status: Normal Order");
        } else {
            print("No items to process.");
        }
    }

    protected Random getRandom() {
        return new Random();
    }

    protected void print(String message) {
        System.out.println(message);
    }
}