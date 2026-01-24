package idv.kuma;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderProcessor {

    private final CalculateDiscountByUserType calculateDiscountByUserType;

    public OrderProcessor(CalculateDiscountByUserType calculateDiscountByUserType1) {
        calculateDiscountByUserType = calculateDiscountByUserType1;
    }

    public void process(List<Map<String, Object>> items, String userType, boolean shippingEnabled) {
        double d = 0;

        if (items == null || items.size() <= 0) {
            print("No items to process.");
            return;
        }
        d = getOriginalPrice(items, d);

        d = getPriceByLuckyDiscount(d);
        // -----------------------

        d = calculateDiscountByUserType.getPriceByUserTypeDiscount(userType, d);

        d = getPriceByShipping(userType, shippingEnabled, d);

        print("Customer Type: " + userType);
        print("Total Price: " + Math.round(d * 100.0) / 100.0);

        if (d > 1000) print("Status: Large Order");
        else print("Status: Normal Order");
    }

    protected void print(String message) {
        System.out.println(message);
    }

    private double getOriginalPrice(List<Map<String, Object>> items, double d) {
        for (Map<String, Object> i : items) {
            double p = (double) i.get("p");
            int q = (int) i.get("q");
            if (p > 0 && q > 0) {
                d += p * q;
            }
        }
        return d;
    }

    private double getPriceByLuckyDiscount(double d) {
        if (d > 500) {
            Random r = getRandom();
            if (r.nextBoolean()) { // 50% 機率
                double rd = 0.01 + (0.1 - 0.01) * r.nextDouble(); // 1% ~ 10% 隨機
                print("LUCKY! You got a random discount: " + Math.round(rd * 100) + "%");
                d = d * (1 - rd);
            }
        }
        return d;
    }

    private double getPriceByShipping(String userType, boolean shippingEnabled, double d) {
        if (shippingEnabled) {
            if (!(userType.equals("VIP") && d > 500)) d = d + 60;
        }
        return d;
    }

    protected Random getRandom() {
        return new Random();
    }

    private double getPriceByUserTypeDiscount(String userType, double d) {
        return calculateDiscountByUserType.getPriceByUserTypeDiscount(userType, d);
    }
}