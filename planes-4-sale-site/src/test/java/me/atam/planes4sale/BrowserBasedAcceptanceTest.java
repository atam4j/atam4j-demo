package me.atam.planes4sale;

import io.dropwizard.Configuration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.URL;

public class BrowserBasedAcceptanceTest {









    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE;

    protected RemoteWebDriver driver;
    protected DriverService service;

    static {
        if (TestRunMode.getMode() == TestRunMode.Mode.BUILD) {
            RULE = new DropwizardAppRule<>(Planes4SaleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));
        } else {
            RULE = null;
        }
    }

    @Before
    public void setUp() throws Exception {
        if (TestRunMode.getMode() == TestRunMode.Mode.BUILD) {
            service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/usr/bin/chromedriver"))//the driver - not the executable usr/bin/google-chrome
                .usingAnyFreePort()
                .build();
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        } else {
            driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), DesiredCapabilities.chrome());
        }
    }

    @After
    public void tearDown() throws Exception {
        if (TestRunMode.getMode() == TestRunMode.Mode.BUILD) {
            service.stop();
        }
    }
}
