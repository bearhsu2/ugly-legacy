package idv.kuma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class OrderProcessorTest {

    @Test
    void no_discounts() {

        OrderProcessor sut = new FakeOrderProcessor();

        List<Map<String, Object>> order = List.of(
                Map.of("p", 100D, "q", 1),
                Map.of("p", 20D, "q", 2)
        );

        sut.process(order, "NORMAL", false);

        Assertions.assertThat(((FakeOrderProcessor) sut).messages)
                .containsExactly("Customer Type: NORMAL",
                        "Total Price: 140.0",
                        "Status: Normal Order"
                );

    }

    private class FakeOrderProcessor extends OrderProcessor {


        private List<String> messages = new ArrayList<>();

        @Override
        protected void print(String message) {
            this.messages.add(message);
        }
    }
}