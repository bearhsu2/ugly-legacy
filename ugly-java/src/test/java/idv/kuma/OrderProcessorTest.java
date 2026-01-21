package idv.kuma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

class OrderProcessorTest {

    @Test
    void random_discount_LUCKY() {

        OrderProcessor sut = new FakeOrderProcessor();

        List<Map<String, Object>> order = List.of(
                Map.of("p", 1000D, "q", 1)
        );

        sut.process(order, "ORDINARY", false);

        Assertions.assertThat(((FakeOrderProcessor) sut).messages)
                .containsExactly("LUCKY! You got a random discount: 1%",
                        "Customer Type: ORDINARY",
                        "Total Price: 970.0",
                        "Status: Normal Order"
                );

    }

    @Test
    void no_discounts() {

        OrderProcessor sut = new FakeOrderProcessor();

        List<Map<String, Object>> order = List.of(
                Map.of("p", 100D, "q", 1),
                Map.of("p", 20D, "q", 2)
        );

        sut.process(order, "ORDINARY", false);

        Assertions.assertThat(((FakeOrderProcessor) sut).messages)
                .containsExactly("Customer Type: ORDINARY",
                        "Total Price: 140.0",
                        "Status: Normal Order"
                );

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