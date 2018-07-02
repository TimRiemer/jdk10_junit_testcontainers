package mobi.riemer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest {
    @Test
    void shouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Operation not supported");
        });
    }

    @Test
    void shouldCheckThrownException() {
        var exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Operation not supported");
        });
        assertEquals(exception.getMessage(), "Operation not supported");
    }
}
