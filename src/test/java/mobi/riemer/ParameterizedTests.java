package mobi.riemer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParameterizedTests {


    @ParameterizedTest
    @ValueSource(strings = {"java8", "java9", "java10"})
    void parameterizedTest(String param) {
        assertTrue(param.contains("java"));
    }

    @ParameterizedTest(name = "[{index}] => {arguments}")
    @ValueSource(strings = {"java8", "java9", "java10"})
    void parameterizedTestWithNewNamePattern(String param) {
        assertTrue(param.contains("java"));
    }
}
