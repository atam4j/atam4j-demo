package me.atam.planes4sale;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class BrowserBasedAcceptanceTest extends AcceptanceTest {


    private static final int ATTEMPTS_TO_RETRY_FINDING_ELEMENT = 30;
    private  static final int DELAY_BEFORE_RETRY_FINDING_ELEMENT = 200;
    private static Logger LOGGER = LoggerFactory.getLogger(BrowserBasedAcceptanceTest.class);

    protected RemoteWebDriver driver;
    private DriverService service;




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

    public void waitForElementToBeVisible( String elementId)  {
        waitForElementToBeVisible(driver, elementId);
    }


    public static void waitForElementToBeVisible(RemoteWebDriver driver, String elementId)  {
        for (int i = 0; i< ATTEMPTS_TO_RETRY_FINDING_ELEMENT; i++){
            try{
                driver.findElementById(elementId);
                LOGGER.info("Found Element");
                return ;
            }
            catch(Throwable noSuchElement){
                LOGGER.info("Not found element with id " + elementId + ", waiting for another attempt...");

                //squash exception, wait a bit, retry...
                try {
                    Thread.sleep(DELAY_BEFORE_RETRY_FINDING_ELEMENT);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Could not find element with id: " + elementId + " on page: " + driver.getCurrentUrl() + " after " + ATTEMPTS_TO_RETRY_FINDING_ELEMENT + " attempts");


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
