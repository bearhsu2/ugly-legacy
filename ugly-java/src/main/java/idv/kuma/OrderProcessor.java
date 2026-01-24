package idv.kuma;

import java.util.List;
import java.util.Map;

public class OrderProcessor {

    private final List<CalculatePrice> calculatePrices;

    private final printer printer;

    public OrderProcessor(List<CalculatePrice> calculateByLuckyDiscount, printer printer) {
        this.calculatePrices = calculateByLuckyDiscount;
        this.printer = printer;
    }

    public void process(List<Map<String, Object>> items, String userType, boolean shippingEnabled) {


        if (items == null || items.size() <= 0) {
            printer.print("No items to process.");
            return;
        }

        Order order = Order.of(items);

        double original = order.getPrice();

        double processed = original;
        for (CalculatePrice calculatePrice : this.calculatePrices) {
            processed = calculatePrice.calculate(processed, userType, shippingEnabled, printer::print);
        }

        printer.print("Customer Type: " + userType);
        printer.print("Total Price: " + Math.round(processed * 100.0) / 100.0);
        printer.print("Status: " + (processed > 1000 ? "Large Order" : "Normal Order"));


    }


}