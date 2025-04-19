package com.vtiger.utility;

import com.aventstack.extentreports.ExtentTest;
import jdk.jshell.spi.ExecutionControlProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageActions {

    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest logger;

    public PageActions(WebDriver driver, ExtentTest logger)
    {
        this.logger=logger;
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public void SetInput(WebElement elm, String value, String msg)
    {
        try {

            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
            logger.pass(msg);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            logger.pass("unable to fill the data in the field due to"+e.getMessage());

        }
    }

    public void ClickElement(WebElement elm, String msg)
    {
        try {

            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
            logger.pass(msg);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            logger.pass("unable to click the element due to"+e.getMessage());

        }
    }

    public void ElementExist(WebElement elm, String msg)
    {
        try {

            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();

            logger.pass(msg);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            logger.pass("unable to find the element" + ""+e.getMessage());

        }
    }



}
