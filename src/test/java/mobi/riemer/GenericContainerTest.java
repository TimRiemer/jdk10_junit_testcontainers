package mobi.riemer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("testcontainers")
@Testcontainers
class GenericContainerTest {

    @Container
    private static GenericContainer wildfly =
            new GenericContainer("jboss/wildfly")
                    .withExposedPorts(8080, 9990);

    @Test
    void getExposedPorts() {
        var ports = wildfly.getExposedPorts();
        var expectedPorts = Arrays.asList(8080, 9990);

        assertEquals(expectedPorts, ports);
    }
}
