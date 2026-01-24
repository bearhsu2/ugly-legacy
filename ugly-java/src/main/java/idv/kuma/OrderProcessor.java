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

        double price = orderOpt.get().getPrice();
        for (CalculatePrice calculatePrice : this.calculatePrices) {
            price = calculatePrice.calculate(price, userType, shippingEnabled, printer::print);
        }

        printer.print("Customer Type: " + userType);
        printer.print("Total Price: " + Math.round(price * 100.0) / 100.0);
        printer.print("Status: " + (price > 1000 ? "Large Order" : "Normal Order"));


    }


}