package idv.kuma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class OrderProcessorTest {

    @Test
    void no_discounts() {

        OrderProcessor sut = new OrderProcessor();

        List<Map<String, Object>> order = List.of(
                Map.of("p", 100D, "q", 1),
                Map.of("p", 20D, "q", 2)
        );

        sut.process(order, "NORMAL", false);

        // 不是我不測，靠北你寫這麼爛的 code 是要給鬼測嗎？
//        Assertions.assertThat( ? ? ?).isEqualTo(140D);

    }
}