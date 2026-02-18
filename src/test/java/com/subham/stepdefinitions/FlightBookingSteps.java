package com.subham.stepdefinitions;

import com.subham.pageActions.HomePageActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlightBookingSteps {

    HomePageActions homePageActions = new HomePageActions();


    @Given("user navigates to MakeMyTrip website {string}")
    public void userNavigatesToMakeMyTripWebsite(String url) {
        homePageActions.openSite(url);
    }

    @And("user closes any promotional popups if present")
    public void userClosesAnyPromotionalPopupsIfPresent() {
        homePageActions.handlePopup();
    }

    @Given("user is on the flights booking page")
    public void userIsOnTheFlightsBookingPage() {
        homePageActions.onFlightBookingPage();
    }

    @When("user selects {string} trip type")
    public void userSelectsTripType(String tripType) {
        if (tripType.equalsIgnoreCase("oneWayTrip")) {
            homePageActions.selectOneWayTrip();
        } else if (tripType.equalsIgnoreCase("roundTrip")) {
            homePageActions.selectRoundTrip();
        } else if (tripType.equalsIgnoreCase("multiCityTrip")) {
            homePageActions.selectMultiCityTrip();
        }
    }

    @And("user enters {string} as departure city")
    public void userEntersAsDepartureCity(String departureCity) {
        homePageActions.enterFromCity(departureCity);
    }

    @And("user enters {string} as destination city")
    public void userEntersAsDestinationCity(String destinationCity) {
        homePageActions.enterToCity(destinationCity);
    }

    @And("user selects departure date as {string}")
    public void userSelectsDepartureDateAs(String departureDate) {
        homePageActions.selectDate(departureDate);
    }

    @And("user selects return date as {string}")
    public void userSelectsReturnDateAs(String returnDate) {
        homePageActions.selectReturnDate(returnDate);
    }

    @And("user selects {string} adults, {string} children and {string} infants as passengers")
    public void userSelectsPassengers(String adults, String children, String infants) {
        homePageActions.selectPassengers(adults, children, infants);
    }

    @And("user selects travel class as {string}")
    public void userSelectsTravelClassAs(String travelClass) {
        homePageActions.selectTravelClass(travelClass);
    }

    @And("user clicks on search flights button")
    public void userClicksOnSearchFlightsButton() {
        homePageActions.clickSearchFlights();
    }

    @Then("user should see available flights for the selected route")
    public void userShouldSeeAvailableFlightsForTheSelectedRoute() {

    }

    @And("user enters {string} to {string} for leg {int} on {string}")
    public void userEntersToForLegOn(String fromCity, String toCity, int legNumber, String date) {

    }

    @Then("user should see available flights for all legs")
    public void userShouldSeeAvailableFlightsForAllLegs() {

    }
}









