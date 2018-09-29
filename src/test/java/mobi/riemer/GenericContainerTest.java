package mobi.riemer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.GenericContainer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("testcontainers")
@ExtendWith(TestcontainersExtension.class)
class GenericContainerTest {

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
