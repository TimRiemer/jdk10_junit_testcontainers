package mobi.riemer;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DockerDSLContainer {


    @Test
    void testDockerDSLContainer() {
        var container = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfileFromBuilder(builder -> {
                            builder
                                    .from("alpine:3.2")
                                    .run("apk add --update git")
                                    .cmd("git", "version")
                                    .build();
                        }))
                .withExposedPorts(80);

        assertEquals(Arrays.asList(80), container.getExposedPorts());
    }
}
