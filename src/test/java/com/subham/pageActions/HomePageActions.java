package com.subham.pageActions;

import com.subham.pageLocators.HomePageLocators;
import com.subham.utils.DriverUtil;
import com.subham.utils.ScrollUtil;
import com.subham.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePageActions {

    private static final Logger logger = LogManager.getLogger(HomePageActions.class);

    public HomePageActions() {
        PageFactory.initElements(DriverUtil.getDriver(), HomePageLocators.class);
        logger.info("HomePageActions initialized");
    }

    public void openSite(String url) {
        DriverUtil.getDriver().get(url);
        logger.info("Navigated to URL: {}", url);
    }

    public void handlePopup() {
        WaitUtils.waitForClickableElement(HomePageLocators.closeLoginPopup);
        HomePageLocators.closeLoginPopup.click();
        logger.info("Login popup closed");
            try {
                WaitUtils.waitForClickableElement(HomePageLocators.coachmarkPopup);
                HomePageLocators.coachmarkPopup.click();
                logger.info("Coachmark popup closed");
            } catch (Exception e) {
                logger.info("No coachmark popup found to close");
            }
    }

    public void onFlightBookingPage() {
        WaitUtils.waitForClickableElement(HomePageLocators.flightsMenu);
//        HomePageLocators.flightsMenu.click();
        logger.info("User is on the flight booking page");
        try {
            WaitUtils.waitForClickableElement(HomePageLocators.minimizeChatPopup);
            HomePageLocators.minimizeChatPopup.click();
            logger.info("Chat popup minimized");
        } catch (Exception e) {
            logger.info("No chat popup found to minimize");
        }
    }

    public void selectOneWayTrip() {
        WaitUtils.waitForClickableElement(HomePageLocators.oneWayTripOption);
        HomePageLocators.oneWayTripOption.click();
        logger.info("One way trip option selected");
    }

    public void selectRoundTrip() {
        WaitUtils.waitForClickableElement(HomePageLocators.roundTripOption);
        HomePageLocators.roundTripOption.click();
        logger.info("Round trip option selected");
    }

    public void selectMultiCityTrip() {
        WaitUtils.waitForClickableElement(HomePageLocators.multiCityTripOption);
        HomePageLocators.multiCityTripOption.click();
        logger.info("Multi-city trip option selected");
    }

    public void enterFromCity(String fromCity) {
        WaitUtils.waitForClickableElement(HomePageLocators.fromCityClick);
        HomePageLocators.fromCityClick.click();
        WaitUtils.waitForVisibleElement(HomePageLocators.fromCityInput);
        HomePageLocators.fromCityInput.sendKeys(fromCity);
        logger.info("Entered from city: {}", fromCity);
        WaitUtils.waitForSuggestionsToAppear(fromCity);

        WebElement suggestion = DriverUtil.getDriver().findElement(By.xpath("//ul[contains(@class,'suggestions-list')]/li[contains(.,'" + fromCity + "')]"));
        suggestion.click();
        logger.info("Selected from suggestion matching: {}", fromCity);
    }

    public void enterToCity(String toCity) {
        WaitUtils.waitForClickableElement(HomePageLocators.toCityClick);
        HomePageLocators.toCityClick.click();
        WaitUtils.waitForVisibleElement(HomePageLocators.toCityInput);
        HomePageLocators.toCityInput.sendKeys(toCity);
        logger.info("Entered to city: {}", toCity);
        WaitUtils.waitForSuggestionsToAppear(toCity);

        WebElement suggestion = DriverUtil.getDriver().findElement(By.xpath("//ul[contains(@class,'suggestions-list')]/li[contains(.,'" + toCity + "')]"));
        suggestion.click();
        logger.info("Selected to suggestion matching: {}", toCity);
    }

    public void selectDate(String date) {
        logger.info("Date: {}", date);
        String[] dateParts = date.split(" ");
        String day = dateParts[0];
        logger.info("Day: {}", day);
        String monthYear = dateParts[1] + " " + dateParts[2];
        logger.info("Month Year: {}", monthYear);

        while (true) {
            WaitUtils.waitForElementsToBeVisible(HomePageLocators.monthYearDisplay);
            String leftMonthYear = HomePageLocators.monthYearDisplay.getFirst().getText();
            logger.info("Left month year: {}", leftMonthYear);
            String rightMonthYear = HomePageLocators.monthYearDisplay.get(1).getText();
            logger.info("Right month year: {}", rightMonthYear);
            if (leftMonthYear.equalsIgnoreCase(monthYear) || rightMonthYear.equalsIgnoreCase(monthYear)) {
                break;
            } else {
                HomePageLocators.nextMonthButton.click();
                logger.info("Clicked on next month button to navigate calendar");
                WaitUtils.waitForVisibleElement(HomePageLocators.monthYearDisplay.getFirst());
            }
        }
        String dynamicXpath = String.format("//div[contains(text(),'%s')]/../..//div[contains(@aria-label,'%s')]", monthYear, day);
        WebElement dayElement = DriverUtil.getDriver().findElement(By.xpath(dynamicXpath));
        ScrollUtil.scrollToElement(dayElement);
        WaitUtils.waitForClickableElement(dayElement);
        dayElement.click();
        logger.info("Successfully selected date: {}", date);
    }

    public void selectReturnDate(String returnDate) {
        WaitUtils.waitForClickableElement(HomePageLocators.returnDateSection);
        HomePageLocators.returnDateSection.click();
        logger.info("Clicked on return date section");
        selectDate(returnDate);
        logger.info("Selected return date: {}", returnDate);
    }

    public void selectPassengers(String adults, String children, String infants) {
        ScrollUtil.scrollToElement(HomePageLocators.passengerSection);
        WaitUtils.waitForClickableElement(HomePageLocators.passengerSection);
        HomePageLocators.passengerSection.click();
        logger.info("Clicked on passenger section");
        //Select adults
        String adultsXpath = String.format("//li[@data-cy='adults-%s']", adults);
        WebElement adultsOption = WaitUtils.wait(By.xpath(adultsXpath));
        ScrollUtil.scrollToElement(adultsOption);
        WaitUtils.waitForVisibleElement(adultsOption);
        adultsOption.click();
        logger.info("Selected adults: {}", adults);
        //Select children
        String childrenXpath = String.format("//li[@data-cy='children-%s']", children);
        WebElement childrenOption = DriverUtil.getDriver().findElement(By.xpath(childrenXpath));
        ScrollUtil.scrollToElement(childrenOption);
        WaitUtils.waitForVisibleElement(childrenOption);
        childrenOption.click();
        logger.info("Selected children: {}", children);
        //Select infants
        String infantsXpath = String.format("//li[@data-cy='infants-%s']", infants);
        WebElement infantsOption = DriverUtil.getDriver().findElement(By.xpath(infantsXpath));
        ScrollUtil.scrollToElement(infantsOption);
        WaitUtils.waitForVisibleElement(infantsOption);
        infantsOption.click();
        logger.info("Selected infants: {}", infants);
    }

        public void selectTravelClass(String travelClass) {
            switch (travelClass) {
                case "Economy":
                    WaitUtils.waitForVisibleElement(HomePageLocators.economyClassOption);
                    ScrollUtil.scrollToElement(HomePageLocators.economyClassOption);
                    HomePageLocators.economyClassOption.click();
                    logger.info("Selected travel class: Economy");
                    break;
                case "Premium Economy":
                    ScrollUtil.scrollToElement(HomePageLocators.premiumEconomyClassOption);
                    WaitUtils.waitForVisibleElement(HomePageLocators.premiumEconomyClassOption);
                    HomePageLocators.premiumEconomyClassOption.click();
                    logger.info("Selected travel class: Premium Economy");
                    break;
                case "Business":
                    ScrollUtil.scrollToElement(HomePageLocators.businessClassOption);
                    WaitUtils.waitForVisibleElement(HomePageLocators.businessClassOption);
                    HomePageLocators.businessClassOption.click();
                    logger.info("Selected travel class: Business");
                    break;
                case "First Class":
                    ScrollUtil.scrollToElement(HomePageLocators.firstClassOption);
                    WaitUtils.waitForVisibleElement(HomePageLocators.firstClassOption);
                    HomePageLocators.firstClassOption.click();
                    logger.info("Selected travel class: First Class");
                    break;
                default:
                    logger.warn("Invalid travel class provided: {}", travelClass);
            }
            ScrollUtil.scrollToElement(HomePageLocators.applyPassengersButton);
            WaitUtils.waitForVisibleElement(HomePageLocators.applyPassengersButton);
            logger.info("Scrolled to apply passengers button after selecting travel class");
            HomePageLocators.applyPassengersButton.click();
            logger.info("Clicked on apply passengers button after selecting travel class");
        }

        public void clickSearchFlights() {
            WaitUtils.waitForVisibleElement(HomePageLocators.searchFlightsButton);
            ScrollUtil.scrollToElement(HomePageLocators.searchFlightsButton);
            logger.info("Scrolled to search flights button");
            HomePageLocators.searchFlightsButton.click();
            logger.info("Clicked on search flights button");
        }

}
