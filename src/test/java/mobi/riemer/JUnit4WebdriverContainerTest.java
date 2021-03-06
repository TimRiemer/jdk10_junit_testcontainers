package mobi.riemer;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

public class JUnit4WebdriverContainerTest {

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("./build/"));

    @Test
    public void searchForTestcontainersOnGoogle() {
        var driver = chrome.getWebDriver();

        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("testcontainers");
        driver.findElement(By.name("q")).submit();
        assertEquals("testcontainers", driver.findElement(By.name("q")).getAttribute("value"));
    }
}
