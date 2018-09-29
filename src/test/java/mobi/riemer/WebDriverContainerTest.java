package mobi.riemer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("testcontainers")
@ExtendWith(TestcontainersExtension.class)
class WebDriverContainerTest {

    private static BrowserWebDriverContainer chrome = new BrowserWebDriverContainer<>()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withNetwork(Network.SHARED)
            .withNetworkAliases("vnchost")
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null);


    private static VncRecordingContainer vnc = new VncRecordingContainer(chrome);

    @Test
    void searchForTestcontainersOnGoogle() {
        var driver = chrome.getWebDriver();
        var name = "testcontainers";

        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys(name);
        driver.findElement(By.name("q")).submit();
        assertEquals(name, driver.findElement(By.name("q")).getAttribute("value"));

        vnc.saveRecordingToFile(new File("build/", name + ".flv"));
    }
}
