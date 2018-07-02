package mobi.riemer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericContainerTest {

    private static GenericContainer wildfly =
            new GenericContainer("jboss/wildfly")
                    .withExposedPorts(8080, 9990);

    @BeforeAll
    static void startUp() {
        wildfly.start();
    }

    @AfterAll
    static void tearDown() {
        wildfly.stop();
    }

    @Test
    void getExposedPorts() {
        var ports = wildfly.getExposedPorts();
        var expectedPorts = Arrays.asList(8080, 9990);

        assertEquals(expectedPorts, ports);
    }
}
