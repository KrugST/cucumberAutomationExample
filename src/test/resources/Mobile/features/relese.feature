@MyAccount
Feature: Scan And Go Feature

  Background: setup user on home page
    Then home page is displayed

  @smoke
  Scenario: Launch app verify screen
    When home page is displayed
    When user clicks "account" button on bottom navigation menu
    When user clicks "scan&go" button on bottom navigation menu
    Then scan&go page is displayed
    Then singIn button is displayed
    Then signIn with membershipNumber is displayed
    When user clicks signIn button
    Then signIn form is displayed
    Then user input "roman.sentsov.sav@samsclub.com" email in email field
    Then user input "test123" password in password input field
    When user clicks sign in button on titan screen
   // Then action bar screen is displayed
   // When user click action bar screen close button
   // Then scan and go screen is displayed


