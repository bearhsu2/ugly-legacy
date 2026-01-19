package idv.kuma;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AdderTest {

    @Test
    void add() {
        Adder adder = new Adder();
        Assertions.assertThat(adder.add(2, 3)).isEqualTo(5);
    }
}