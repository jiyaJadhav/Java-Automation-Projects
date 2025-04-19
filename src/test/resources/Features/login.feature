Feature: Login Functionality
  Background:
    Given User should be on login page

    @validlogin
  Scenario: TC01_Valid_Login
    When User enter the valid credential and click on the login button
    Then user should be navigated to login page
    And User should see the logout button on the top right corner

     @invalidlogin
  Scenario:  TC02_Invalid_Login
        When User enter the invalid credential and click on the login button
        Then user can see the error message