package com.subham.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public static void waitForVisibleElement(WebElement element) {
        try {
            logger.info("Waiting for element to be visible: {}", element);
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element {} is visible", element);
        } catch (Exception e) {
            logger.error("Element not visible within timeout: {}", element, e);
            throw e;
        }
    }

    public static void waitForClickableElement(WebElement element) {
        try {
            logger.info("Waiting for element to be clickable: {}", element);
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element {} is clickable", element);
        } catch (Exception e) {
            logger.error("Element not clickable within timeout: {}", element, e);
            throw e;
        }
    }

    public static void waitForElementsToBeVisible(List<WebElement> elements) {
        try {
            logger.info("Waiting for elements to be visible: {}", elements);
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            logger.info("Elements are visible: {}", elements);
        } catch (Exception e) {
            logger.error("Elements not visible within timeout: {}", elements, e);
            throw e;
        }
    }

    public static void waitForElementsToBePresent(By locator) {
        try {
            logger.info("Waiting for elements to be present: {}", locator);
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            logger.info("Elements are present: {}", locator);
        } catch (Exception e) {
            logger.error("Elements did not appear within timeout: {}", locator, e);
            throw e;
        }
    }

    public static void waitForSuggestionsToAppear(String cityName) {
        waitForElementsToBePresent(By.xpath("//ul[contains(@class,'suggestions-list')]/li[contains(.,'" + cityName + "')]"));
    }

    public static WebElement wait(By locator) {
        try {
            logger.info("Waiting for element to be present: {}", locator);
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element is present: {}", locator);
        } catch (Exception e) {
            logger.error("Element did not appear within timeout: {}", locator, e);
        }
        return DriverUtil.getDriver().findElement(locator);
    }
}
