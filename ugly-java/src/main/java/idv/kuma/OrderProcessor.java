package idv.kuma;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderProcessor {

    private final List<CalculatePrice> calculatePrices;

    private final printer printer;

    public OrderProcessor(List<CalculatePrice> calculateByLuckyDiscount, printer printer) {
        this.calculatePrices = calculateByLuckyDiscount;
        this.printer = printer;
    }

    public void process(List<Map<String, Object>> items, String userType, boolean shippingEnabled) {


        Optional<Order> orderOpt = Order.of(items);

        if (orderOpt.isEmpty()) {
            printer.print("No items to process.");
            return;
        }

        double processed = getPrice(orderOpt.get());
        for (CalculatePrice calculatePrice : this.calculatePrices) {
            processed = calculatePrice.calculate(processed, userType, shippingEnabled, printer::print);
        }

        printer.print("Customer Type: " + userType);
        printer.print("Total Price: " + Math.round(processed * 100.0) / 100.0);
        printer.print("Status: " + (processed > 1000 ? "Large Order" : "Normal Order"));


    }

    private double getPrice(Order o) {
        double d = 0D;

        for (Item item : o.getItemList()) {
            double p = item.getPrice();
            int q = item.getQuantity();
            if (p > 0 && q > 0) {
                d += p * q;
            }
        }

        return d;
    }


}