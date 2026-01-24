package idv.kuma;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OrderProcessor {

    private final CalculateDiscountByUserType calculateDiscountByUserType;
    private final CalculatePrizeByShipping calculatePrizeByShipping;

    private final printer printer;
    private final CalculateByLucky calculateByLucky;

    public OrderProcessor(CalculateByLucky calculateByLucky, CalculateDiscountByUserType calculateDiscountByUserType, CalculatePrizeByShipping calculatePrizeByShipping, printer printer) {
        this.calculateByLucky = calculateByLucky;
        this.calculateDiscountByUserType = calculateDiscountByUserType;
        this.calculatePrizeByShipping = calculatePrizeByShipping;
        this.printer = printer;
    }

    public void process(List<Map<String, Object>> items, String userType, boolean shippingEnabled) {
        double d = 0;

        if (items == null || items.size() <= 0) {
            printer.print("No items to process.");
            return;
        }
        d = getOriginalPrice(items, d);

        d = calculateByLucky.getPriceByLuckyDiscount(d, printer::print);
        // -----------------------

        d = calculateDiscountByUserType.getPriceByUserTypeDiscount(d, userType);

        d = calculatePrizeByShipping.getPriceByShipping(d, userType, shippingEnabled);

        printer.print("Customer Type: " + userType);
        printer.print("Total Price: " + Math.round(d * 100.0) / 100.0);

        if (d > 1000) printer.print("Status: Large Order");
        else printer.print("Status: Normal Order");
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

    private double getPriceByLuckyDiscount(double d, Consumer<String> printingFunction) {
        return calculateByLucky.getPriceByLuckyDiscount(d, printingFunction);
    }


}