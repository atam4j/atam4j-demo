package me.atam.planes4sale;

import io.dropwizard.Configuration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomepageAcceptanceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<Configuration>(Planes4SaleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));

    @Test
    public void homepageHasCorrectTitleAndHeading() throws IOException {
//        System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
//        WebDriver driver = new ChromeDriver();

        ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("/usr/bin/chromedriver"))//the driver - not the executable usr/bin/google-chrome
                        .usingAnyFreePort()
                        .build();
        service.start();


        WebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

        driver.get("http://localhost:8080/");

        assertThat(driver.getTitle(), CoreMatchers.is("Planes 4 Sale"));



    }
}
