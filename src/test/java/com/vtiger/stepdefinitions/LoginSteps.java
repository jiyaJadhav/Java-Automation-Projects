package com.vtiger.stepdefinitions;

import com.codoid.products.exception.FilloException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;


public class LoginSteps extends BaseSteps {


    @Before
    public void GetScenarioName(Scenario scenario)
    {

        if (extent==null) {
            CreateExtentReport();
        }
        ScenarioName = scenario.getName();
        logger= extent.createTest(ScenarioName);

    }

    @After
    public void SaveReport()
    {
        extent.flush();
        driver.quit();

    }

    @Given("User should be on login page")
    public void userShouldBeOnLoginPage() throws Exception {
        LaunchApp();
    }

    @When("User enter the valid credential and click on the login button")
    public void userEnterTheValidCredentialAndClickOnTheLoginButton() {
       //lp.Login("admin", "admin");
       lp.Login(td.get(ScenarioName).get("UserID"),td.get(ScenarioName).get("Password"));
    }

    @Then("user should be navigated to login page")
    public void userShouldBeNavigatedToLoginPage() {
        ldp.VerifyHome();
    }

    @And("User should see the logout button on the top right corner")
    public void userShouldSeeTheLogoutButtonOnTheTopRightCorner() {
        ldp.VerifyLogout();
    }

    @When("User enter the invalid credential and click on the login button")
    public void userEnterTheInvalidCredentialAndClickOnTheLoginButton() {
        lp.Login("andi88","a4aa");
    }

    @Then("user can see the error message")
    public void userCanSeeTheErrorMessage() {
        lp.VerifyErrorMessage();

    }
}
