package com.vtiger.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Map;

public class LeadCreate extends BaseSteps {

    /*@Given("User should be on login page")
    public void userShouldBeOnLoginPage() {
        launchapp();
    }*/

    /*@When("User enter the valid credential and click on the login button")
    public void userEnterTheValidCredentialAndClickOnTheLoginButton() {
        driver.findElement(By.name("user_name")).sendKeys("admin");
        driver.findElement(By.name("user_password")).sendKeys("admin");
        driver.findElement(By.name("Login")).click();
    }*/


    @When("user clicks on new lead and fills all mandatory fields and clicks on save button")
    public void userFillsMandatoryFieldsAndClicksSave(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> lst = dataTable.entries();

        for (int i = 0; i < lst.size(); i++) {

            ldp.ClickNewLead();
            ldp.CreateLead(lst.get(i).get("lastname"),lst.get(i).get("company"));
        }
    }

    @Then("lead should be created")
    public void leadShouldBeCreated() {


    }
}
