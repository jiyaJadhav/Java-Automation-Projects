package com.vtiger.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage extends HeaderPage {


    public LeadPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement tb_lastname;

    @FindBy(name = "company")
    WebElement tb_company;

    @FindBy(name = "button")
    WebElement btn_save;

    public void CreateLead(String lname, String comp)
    {

        SetLastname(lname);
        SetCompany(comp);
        ClickSave();

    }

    public void SetLastname(String lname)
    {
        SetInput(tb_lastname,lname,"Last name entered successfully");
    }

    public void SetCompany(String comp)
    {
        SetInput(tb_company,comp, "Company name entered successfully");
    }

    public void ClickSave()
    {
        ClickElement(btn_save, "Clicked on the save button");
    }
}
