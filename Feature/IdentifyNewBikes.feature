Feature: 91 Wheels

  Scenario: UpcomingBikes
    Given navigating to webpage
    When clicking on Upcoming Bikes
    And selecting Honda in the dropdown
    Then printing the upcoming bikes with price less than four lakhs

  Scenario: UsedCarsInChennai
    Given navigating to webpage
    Given hovering on UsedCars
    And clicking used cars in chennai
    When click read more to get popular models
    Then print all the popular models

  Scenario: IncorrectLogin
    Given navigating to webpage
    Given click login icon
    And sign in with google
    When enter invalid email id
    Then print could not find your google account
