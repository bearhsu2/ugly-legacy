package idv.kuma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

class OrderProcessorTest {

    private final boolean LUCKY = true;
    private final boolean SHIPPING_ENABLED = true;
    private OrderProcessor sut;
    private List<Map<String, Object>> order;

    @Test
    void shipping_enabled() {

        given_sut(!LUCKY);

        given_order(item(501D, 1));

        when_process("ORDINARY", !SHIPPING_ENABLED);

        then_messages("Customer Type: ORDINARY",
                "Total Price: 481.0",
                "Status: Normal Order"
        );

    }

    private void given_sut(boolean nextBoolean) {
        sut = new FakeOrderProcessor(nextBoolean);
    }

    private void given_order(Map<String, Object>... items) {
        order = List.of(items);
    }

    private Map<String, Object> item(double price, int quantity) {
        return Map.of("p", price, "q", quantity);
    }

    private void when_process(String userType, boolean shippingEnabled) {
        sut.process(order, userType, shippingEnabled);
    }

    private void then_messages(String... messages) {

        Assertions.assertThat(((FakeOrderProcessor) sut).messages)
                .containsExactly(messages);
    }

    @Test
    void ORDINARY_expensive() {

        given_sut(!LUCKY);

        given_order(item(501D, 1));

        when_process("ORDINARY", !SHIPPING_ENABLED);

        then_messages("Customer Type: ORDINARY",
                "Total Price: 481.0",
                "Status: Normal Order"
        );

    }

    @Test
    void GOLD() {

        given_sut(!LUCKY);

        given_order(item(1000D, 1));

        when_process("GOLD", !SHIPPING_ENABLED);

        then_messages("Customer Type: GOLD",
                "Total Price: 950.0",
                "Status: Normal Order"
        );

    }

    @Test
    void VIP_cheap() {

        given_sut(!LUCKY);

        given_order(item(900D, 1));

        when_process("VIP", !SHIPPING_ENABLED);

        then_messages("Customer Type: VIP",
                "Total Price: 810.0",
                "Status: Normal Order"
        );

    }

    @Test
    void VIP_expensive() {

        given_sut(!LUCKY);

        given_order(item(2_000D, 1));

        when_process("VIP", false);

        then_messages("Customer Type: VIP",
                "Total Price: 1700.0",
                "Status: Large Order"
        );

    }

    @Test
    void random_discount_UNLUCKY() {

        given_sut(!LUCKY);

        given_order(item(1_000D, 1));

        when_process("ORDINARY", !SHIPPING_ENABLED);

        then_messages("Customer Type: ORDINARY",
                "Total Price: 980.0",
                "Status: Normal Order"
        );

    }

    @Test
    void random_discount_LUCKY() {

        given_sut(LUCKY);

        given_order(item(1_000D, 1));

        when_process("ORDINARY", !SHIPPING_ENABLED);

        then_messages("LUCKY! You got a random discount: 1%",
                "Customer Type: ORDINARY",
                "Total Price: 970.0",
                "Status: Normal Order"
        );

    }

    @Test
    void no_discounts() {

        given_sut(true);

        given_order(
                item(100D, 1),
                item(20D, 2)
        );

        when_process("ORDINARY", false);

        then_messages("Customer Type: ORDINARY", "Total Price: 140.0", "Status: Normal Order");

    }

    private class FakeOrderProcessor extends OrderProcessor {


        private List<String> messages = new ArrayList<>();
        private FakeRandom fakeRandom;

        public FakeOrderProcessor(boolean nextBoolean) {
            fakeRandom = new FakeRandom(nextBoolean, 0D);
        }

        @Override
        protected Random getRandom() {
            return fakeRandom;
        }

        @Override
        protected void print(String message) {
            this.messages.add(message);
        }


    }


    private class FakeRandom extends Random {
        private final boolean nextBoolean;
        private final double nextDouble;

        public FakeRandom(boolean nextBoolean, double nextDouble) {
            this.nextBoolean = nextBoolean;
            this.nextDouble = nextDouble;
        }

        @Override
        public boolean nextBoolean() {
            return this.nextBoolean;
        }

        @Override
        public double nextDouble() {
            return this.nextDouble;
        }
    }
}