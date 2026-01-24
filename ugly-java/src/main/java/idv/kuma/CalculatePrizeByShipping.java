package idv.kuma;

public class CalculatePrizeByShipping {
    public CalculatePrizeByShipping() {
    }

    double getPriceByShipping(String userType, boolean shippingEnabled, double d) {
        if (shippingEnabled) {
            if (!(userType.equals("VIP") && d > 500)) d = d + 60;
        }
        return d;
    }
}