Feature: Flight Search on MakeMyTrip
  As a user of MakeMyTrip
  I want to search for flight tickets
  So that I can view available flights for my travel

  Background:
    Given user navigates to MakeMyTrip website "https://www.makemytrip.com/"
    And user closes any promotional popups if present

  Scenario Outline: Search for one-way flights
    Given user is on the flights booking page
    When user selects "One Way" trip type
    And user enters "<from>" as departure city
    And user enters "<to>" as destination city
    And user selects departure date as "<departureDate>"
    And user selects "<adults>" adults, "<children>" children and "<infants>" infants as passengers
    And user selects travel class as "<travelClass>"
    And user clicks on search flights button
    Then user should see available flights for the selected route

    Examples:
      | from      | to        | departureDate | adults | children | infants | travelClass     |
      | Mumbai    | Delhi     | 25 March 2026 | 1      | 0        | 0       | Economy         |
#      | Bangalore | Goa       | 10 April 2026 | 2      | 1        | 1       | Business        |
#      | Hyderabad | Chennai   | 15 May 2026   | 1      | 2        | 0       | Premium Economy |
#      | Kolkata   | Pune      | 20 June 2026  | 3      | 0        | 2       | Economy         |

  Scenario Outline: Search for round trip flights
    Given user is on the flights booking page
    When user selects "Round Trip" trip type
    And user enters "<from>" as departure city
    And user enters "<to>" as destination city
    And user selects departure date as "<departureDate>"
    And user selects return date as "<returnDate>"
    And user selects "<adults>" adults, "<children>" children and "<infants>" infants as passengers
    And user selects travel class as "<travelClass>"
    And user clicks on search flights button
    Then user should see available flights for the selected route

    Examples:
      | from      | to      | departureDate | returnDate | adults | children | infants | travelClass     |
#      | Mumbai    | Delhi   | 01 April 2026 | 07 April 2026 | 2      | 0        | 1       | Economy         |
#      | Bangalore | Goa     | 10 April 2026 | 17 April 2026 | 1      | 1        | 0       | Business        |
#      | Chennai   | Kolkata | 05 May 2026   | 12 May 2026   | 2      | 2        | 1       | Premium Economy |

  Scenario Outline: Search for multi-city flights
    Given user is on the flights booking page
    When user selects "Multi City" trip type
    And user enters "<city1>" to "<city2>" for leg 1 on "<date1>"
    And user enters "<city2>" to "<city3>" for leg 2 on "<date2>"
    And user selects "<adults>" adults, "<children>" children and "<infants>" infants as passengers
    And user selects travel class as "<travelClass>"
    And user clicks on search flights button
    Then user should see available flights for all legs

    Examples:
      | city1     | city2  | city3     | date1     | date2      | adults | children | infants | travelClass     |
      | Hyderabad | Mumbai | Delhi     | 05 May 2026   | 10 May 2026    | 1      | 0        | 0       | Economy         |
#      | Mumbai    | Dubai  | London    | 15 June 2026  | 20 June 2026   | 2      | 1        | 1       | Business        |
#      | Delhi     | Goa    | Bangalore | 25 July 2026  | 01 August 2026 | 1      | 2        | 2       | Premium Economy |

