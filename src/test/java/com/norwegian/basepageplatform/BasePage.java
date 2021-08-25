package com.norwegian.basepageplatform;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author anna
 */
public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected AndroidTouchAction t;
    protected Logger log;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 15);
        t = new AndroidTouchAction(this.driver);
        log = Logger.getLogger(this.getClass().getName());
    }

    public void waitForElementExplicitly(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> getElementsByLocator(By locator) {
        waitForElementExplicitly(locator);
        return driver.findElements(locator);
    }

    protected WebElement getElementByLocator(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getTextAsString(By locator) {
        return getElementByLocator(locator).getText();
    }
}