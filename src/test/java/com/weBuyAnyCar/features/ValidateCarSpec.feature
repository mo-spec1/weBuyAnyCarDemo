Feature: Ensure Car details are returned

  Background: Access the weBuyAnyCar Website
    Given User Navigates to the weBuyAnyCar Webpage

  @Regression
  Scenario: Create a new device successfully
    Then User enters the "Registration" Number and validates Car details
