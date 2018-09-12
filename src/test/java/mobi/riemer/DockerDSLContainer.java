package mobi.riemer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("testcontainers")
class DockerDSLContainer {


    @Test
    void testDockerDSLContainer() {
        var container = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfileFromBuilder(builder -> builder
                                .from("alpine:3.2")
                                .run("apk add --update git")
                                .cmd("git", "version")
                                .build()))
                .withExposedPorts(80);

        assertEquals(Collections.singletonList(80), container.getExposedPorts());
    }
}
