package mobi.riemer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.VncRecordingContainer;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebDriverContainerTest {

    BrowserWebDriverContainer chrome = new BrowserWebDriverContainer<>()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withNetwork(Network.SHARED)
            .withNetworkAliases("vnchost")
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null);


    VncRecordingContainer vnc = new VncRecordingContainer(chrome);

    @BeforeEach
    void tearUp() {
        chrome.start();
        vnc.start();
    }

    @AfterEach
    void tearDown() {
        vnc.saveRecordingToFile(new File("build/", "Test.flv"));
        vnc.stop();
        chrome.stop();
    }

    @Test
    void searchForTestcontainersOnGoogle() {
        var driver = chrome.getWebDriver();

        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("testcontainers");
        driver.findElement(By.name("q")).submit();
        assertEquals("testcontainers", driver.findElement(By.name("q")).getAttribute("value"));
    }
}
