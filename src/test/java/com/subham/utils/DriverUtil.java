package com.subham.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverUtil {

    private static final Logger logger = LogManager.getLogger(DriverUtil.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigUtil.loadConfig("browser");
            logger.info("Initializing WebDriver for browser: {}", browser);

            WebDriver webDriver;
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    if (Boolean.parseBoolean(ConfigUtil.loadConfig("headless"))) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    webDriver = new FirefoxDriver();
                    break;
                case "edge":
                    webDriver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.set(webDriver);
            logger.info("WebDriver initialized: {}", webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting WebDriver: {}", driver.get());
            driver.get().quit();
            driver.remove();
        }
    }
}
