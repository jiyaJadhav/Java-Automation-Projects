
Feature: Lead creation

  Background:

    Given User should be on login page
  @leads
  Scenario: TC03_Create_Lead_With_Mandatory_fields
    When User enter the valid credential and click on the login button
    When user should be navigated to login page
    When user clicks on new lead and fills all mandatory fields and clicks on save button
      | lastname | company |
      | Harish   | TBL     |
      | Rajesh   | ATH     |
      | Ramesh   | TGH     |
    Then lead should be created

