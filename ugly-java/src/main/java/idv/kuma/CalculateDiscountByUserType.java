package idv.kuma;

public class CalculateDiscountByUserType {
    public CalculateDiscountByUserType() {
    }

    double getPriceByUserTypeDiscount(String userType, double d) {
        if (userType.equals("VIP")) {
            if (d > 1000) d = d * 0.85;
            else d = d * 0.9;
        } else if (userType.equals("GOLD")) {
            d = d * 0.95;
        } else {
            if (d > 500) d = d - 20;
        }
        return d;
    }
}