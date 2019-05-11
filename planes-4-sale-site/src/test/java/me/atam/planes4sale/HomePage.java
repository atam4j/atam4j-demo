package me.atam.planes4sale;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static me.atam.planes4sale.BrowserBasedAcceptanceTest.*;

public class HomePage {
    private RemoteWebDriver driver;

    public static HomePage load(RemoteWebDriver driver, String url){
        return new HomePage(driver, url);
    }
    public HomePage(RemoteWebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
        waitForElementToBeVisible(driver,"boeingSearchButton");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElement getBoeingSearchButton() {
        return driver.findElementById("boeingSearchButton");
    }

    public WebElement getAirbusSearchButton() {
        return driver.findElementById("airbusSearchButton");
    }
}
