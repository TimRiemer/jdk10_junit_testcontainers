package mobi.riemer;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testcontainers.containers.GenericContainer;

import java.util.Arrays;

public class JUnit4GenericContainerTest {

    @ClassRule
    public static GenericContainer wildfly =
            new GenericContainer("jboss/wildfly")
                    .withExposedPorts(8080, 9990);

    @Test
    public void getExposedPorts() {
        var ports = wildfly.getExposedPorts();
        var expectedPorts = Arrays.asList(8080, 9990);

        Assertions.assertEquals(expectedPorts, ports);
    }
}
