package me.atam.planes4sale;

import io.dropwizard.Configuration;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class BrowserBasedAcceptanceTest {


    public static final DropwizardTestSupport<Configuration> RULE;

    private static Logger LOGGER = LoggerFactory.getLogger(BrowserBasedAcceptanceTest.class);
    protected RemoteWebDriver driver;
    protected DriverService service;


    static {
        if (AcceptanceTestConfigLoader.getConfig().isManagesDropWizard()) {
            RULE = new DropwizardTestSupport<>(Planes4SaleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));

        } else {
            RULE = null;
        }
    }

    public AcceptanceTestConfig getConfig(){
        return AcceptanceTestConfigLoader.getConfig();
    }

    protected String getHomePageAddress() {
        return getConfig().getSiteAddress();
    }

    @Before
    public void setUp() throws Exception {
        LOGGER.info("Setup with config: " + getConfig());

        if (getConfig().isManagesDropWizard()){
            LOGGER.info("Managing Dropwizard. Calling .before()...");
            RULE.before();
        }

        if(getConfig().isStartSeleniumLocally()){
            LOGGER.info("Starting Selenium locally");
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(getConfig().getLocalChromeDriver()))
                    .usingAnyFreePort()
                    .build();
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
            LOGGER.info("Starting started locally");
        }
        else{
            LOGGER.info("Using remote webdriver...");
            driver = new RemoteWebDriver(new URL(getConfig().getSeleniumRemoteAddress()), DesiredCapabilities.chrome());
        }

    }

    @After
    public void tearDown() throws Exception {

        if (getConfig().isManagesDropWizard()){
            LOGGER.info("Managing Dropwizard. Calling .after()...");
            RULE.after();
        }

        if(getConfig().isStartSeleniumLocally()) {
            LOGGER.info("Stopping Selenium locally");
            service.stop();
        }
    }
}
