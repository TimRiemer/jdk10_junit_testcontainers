package mobi.riemer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;
import org.testcontainers.containers.GenericContainer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

class WebdriverContainerTest {

    private static BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("./build/"))
            .withRecordingFileFactory(new DefaultRecordingFileFactory());

    @BeforeAll
    static void startUp() {
        chrome.start();
    }

    @AfterAll
    static void tearDown() {
        chrome.stop();
    }

    @Test
    void searchForTestcontainersOnGoogle() {
        var driver = chrome.getWebDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("testcontainers");
        driver.findElement(By.name("q")).submit();
        assertEquals("testcontainers", driver.findElement(By.name("q")).getAttribute("value"));
    }
}
