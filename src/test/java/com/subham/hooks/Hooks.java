package com.subham.hooks;

import com.subham.utils.DriverUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    @Before
    public void setUp() {
        logger.info("Initializing WebDriver");
        driver = DriverUtil.getDriver();
        logger.info("WebDriver initialized successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
//            DriverUtil.quitDriver();
            logger.info("WebDriver closed successfully");
        }
    }
}

