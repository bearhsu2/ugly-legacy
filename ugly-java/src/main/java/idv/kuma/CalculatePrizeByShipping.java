package idv.kuma;

public class CalculatePrizeByShipping {
    public CalculatePrizeByShipping() {
    }

    double getPriceByShipping(double d, String userType, boolean shippingEnabled) {
        if (shippingEnabled) {
            if (!(userType.equals("VIP") && d > 500)) d = d + 60;
        }
        return d;
    }
}