package com.vtiger.pages;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utility.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends PageActions {

    public HeaderPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Logout")
    WebElement lnk_logout;

    @FindBy(linkText = "New Lead")
    WebElement lnk_NewLead;

    @FindBy(linkText = "Leads")
    WebElement lnk_leads;

    @FindBy(linkText = "Home")
    WebElement lnk_home;

    public void VerifyLogout()
    {
        ElementExist(lnk_logout, "link logout is displayed");

    }

    public void ClickLogout()
    {
        ClickElement(lnk_logout,"clicked on logout button");

    }

    public void ClickNewLead()
    {
        ClickElement(lnk_NewLead,"clicked on new lead");

    }

    public void ClickLeads()
    {
        ClickElement(lnk_leads,"clicked on leads");

    }

    public void VerifyHome()
    {
        ClickElement(lnk_home,"link home is displayed");

    }



}
