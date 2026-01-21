package idv.kuma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

class OrderProcessorTest {

    private OrderProcessor sut;
    private List<Map<String, Object>> order;

    @BeforeEach
    void setUp() {
        sut = new FakeOrderProcessor();
    }

    @Test
    void random_discount_LUCKY() {

        given_order(item(1000D, 1));

        when_process();

        then_messages("LUCKY! You got a random discount: 1%",
                "Customer Type: ORDINARY",
                "Total Price: 970.0",
                "Status: Normal Order"
        );

    }

    private void given_order(Map<String, Object>... items) {
        order = List.of(items);
    }

    private Map<String, Object> item(double price, int quantity) {
        return Map.of("p", price, "q", quantity);
    }

    private void when_process() {
        sut.process(order, "ORDINARY", false);
    }

    private void then_messages(String... messages) {

        Assertions.assertThat(((FakeOrderProcessor) sut).messages)
                .containsExactly(messages);
    }

    @Test
    void no_discounts() {


        given_order(item(100D, 1), item(20D, 2));

        when_process();

        then_messages("Customer Type: ORDINARY", "Total Price: 140.0", "Status: Normal Order");

    }

    private class FakeOrderProcessor extends OrderProcessor {


        private List<String> messages = new ArrayList<>();

        @Override
        protected Random getRandom() {
            return new FakeRandom(true, 0D);
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