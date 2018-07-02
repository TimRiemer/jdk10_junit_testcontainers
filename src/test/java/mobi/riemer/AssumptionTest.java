package mobi.riemer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionTest {
    @Test
    void assumptionTest() {
        assumeTrue(true);
        assumeFalse(false);
        assumingThat("abc".equals("abc"),
                () -> assertEquals(2, 1+1));
    }
}
