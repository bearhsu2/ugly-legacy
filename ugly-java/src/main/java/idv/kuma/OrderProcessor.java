package idv.kuma;

import java.util.List;
import java.util.Map;

public class OrderProcessor {

    private final CalculateByLuckyDiscount calculateByLuckyDiscount;
    private final CalculateByUserTypeDiscount calculateByUserTypeDiscount;
    private final CalculateByShipping calculateByShipping;
    private final printer printer;

    public OrderProcessor(CalculateByLuckyDiscount calculateByLuckyDiscount,
                          CalculateByUserTypeDiscount calculateByUserTypeDiscount,
                          CalculateByShipping calculateByShipping,
                          printer printer) {
        this.calculateByLuckyDiscount = calculateByLuckyDiscount;
        this.calculateByUserTypeDiscount = calculateByUserTypeDiscount;
        this.calculateByShipping = calculateByShipping;
        this.printer = printer;
    }

    public void process(List<Map<String, Object>> items, String userType, boolean shippingEnabled) {
        double d = 0;

        if (items == null || items.size() <= 0) {
            printer.print("No items to process.");
            return;
        }
        d = getOriginalPrice(items, d);

        d = calculateByLuckyDiscount.getPriceByLuckyDiscount(d, userType, shippingEnabled, printer::print);

        d = calculateByUserTypeDiscount.getPriceByUserTypeDiscount(d, userType, shippingEnabled, printer::print);

        d = calculateByShipping.getPriceByShipping(d, userType, shippingEnabled, printer::print);

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


}