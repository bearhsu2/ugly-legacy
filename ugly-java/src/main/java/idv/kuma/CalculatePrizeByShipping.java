package idv.kuma;

import java.util.function.Consumer;

public class CalculatePrizeByShipping {
    public CalculatePrizeByShipping() {
    }

    double getPriceByShipping(double d, String userType, boolean shippingEnabled, Consumer<String> printingFunction) {
        if (shippingEnabled) {
            if (!(userType.equals("VIP") && d > 500)) d = d + 60;
        }
        return d;
    }
}