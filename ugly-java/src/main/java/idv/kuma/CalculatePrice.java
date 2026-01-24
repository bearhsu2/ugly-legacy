package idv.kuma;

import java.util.function.Consumer;

public interface CalculatePrice {
    double calculate(double d, String userType, boolean shippingEnabled, Consumer<String> printingFunction);
}
