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

public class BrowserBasedAcceptanceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE;

    protected WebDriver driver;
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
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/usr/bin/chromedriver"))//the driver - not the executable usr/bin/google-chrome
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
    }

    @After
    public void tearDown() throws Exception {
        service.stop();
    }
}
