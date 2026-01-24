package idv.kuma;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingTest {

    @Test
    void goodday() {
        Greeting sut = new Greeting();
        String result = sut.greet();
        assertEquals("good day", result);
    }

    @Test
    void holiday() {
        Greeting sut = new Greeting();
        String result = sut.greet();
        assertEquals("happy holiday", result);
    }
}