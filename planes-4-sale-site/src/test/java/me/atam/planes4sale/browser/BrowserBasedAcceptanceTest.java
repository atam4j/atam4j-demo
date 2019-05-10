package me.atam.planes4sale.browser;

import me.atam.planes4sale.AcceptanceTest;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.NoSuchElementException;

public class BrowserBasedAcceptanceTest extends AcceptanceTest {


    private static Logger LOGGER = LoggerFactory.getLogger(BrowserBasedAcceptanceTest.class);
    protected RemoteWebDriver driver;
    protected DriverService service;



    @Before
    public void setUp() throws Exception {
        super.setUp();

        if(getConfig().isStartSeleniumLocally()){
            LOGGER.info("Starting Selenium locally");
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(getConfig().getLocalChromeDriver()))
                    .usingAnyFreePort()
                    .build();
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
            LOGGER.info("Starting started locally");
        }
        else{
            LOGGER.info("Using remote webdriver...");
            driver = new RemoteWebDriver(new URL(getConfig().getSeleniumRemoteAddress()), getChromeOptions());
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-agent=ACCEPTANCE_TESTS_AS_MONITORS_BROWSER_CHROME");
        return chromeOptions;
    }

    protected void waitForElementToBeVisible(String element) throws InterruptedException {
        for (int i = 0; i< 20; i++){
            try{
                driver.findElementById(element);
                LOGGER.info("Found Element");
                return ;
            }
            catch(Throwable noSuchElement){
                LOGGER.info("Not found element, waiting for another attempt...");
                //squash exception, wait a bit
                Thread.sleep(100);
            }
        }

    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();

        if(getConfig().isStartSeleniumLocally()) {
            LOGGER.info("Stopping Selenium locally");
            service.stop();
        }
    }
}
