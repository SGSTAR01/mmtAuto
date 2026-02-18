package com.subham.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageLocators {

    //popup locators
    @FindBy(xpath="//span[@data-cy='closeModal']")
    public static WebElement closeLoginPopup;
    @FindBy(xpath = "//img[@alt='minimize']")
    public static WebElement minimizeChatPopup;

    //menu locators
    @FindBy(xpath = "//li[@data-cy='menu_Flights']")
    public static WebElement flightsMenu;

    //flight type locators
    @FindBy(xpath = "//li[@data-cy='oneWayTrip']")
    public static WebElement oneWayTripOption;
    @FindBy(xpath = "//li[@data-cy='roundTrip']")
    public static WebElement roundTripOption;
    @FindBy(xpath = "//li[@data-cy='mulitiCityTrip']")
    public static WebElement multiCityTripOption;

    //city input locators
    @FindBy(xpath="//input[@data-cy='fromCity']")
    public static WebElement fromCityClick;
    @FindBy(xpath="//input[@placeholder='From']")
    public static WebElement fromCityInput;
    @FindBy(xpath="//input[@data-cy='toCity']")
    public static WebElement toCityClick;
    @FindBy(xpath="//input[@placeholder='To']")
    public static WebElement toCityInput;

    //date locators
    @FindBy(xpath = "//div[@class='DayPicker-Caption']/div")
    public static List<WebElement> monthYearDisplay;
    @FindBy(xpath = "//span[@aria-label='Next Month']")
    public static WebElement nextMonthButton;
    @FindBy(id = "return")
    public static WebElement returnDateSection;

    //passenger locators
    @FindBy(xpath = "//div[@data-cy='flightTraveller']")
    public static WebElement passengerSection;

    //travel class locators
    @FindBy(xpath = "//button[@data-cy='travellerApplyBtn']")
    public static WebElement applyPassengersButton;
    @FindBy(xpath = "//li[@data-cy='travelClass_0']")
    public static WebElement economyClassOption;
    @FindBy(xpath = "//li[@data-cy='travelClass_1']")
    public static WebElement premiumEconomyClassOption;
    @FindBy(xpath = "//li[@data-cy='travelClass_2']")
    public static WebElement businessClassOption;
    @FindBy(xpath = "//li[@data-cy='travelClass_3']")
    public static WebElement firstClassOption;

    //search button locator
    @FindBy(linkText = "Search")
    public static WebElement searchFlightsButton;

    
}
