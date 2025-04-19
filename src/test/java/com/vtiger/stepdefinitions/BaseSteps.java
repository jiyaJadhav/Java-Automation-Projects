package com.vtiger.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseSteps {

    public static WebDriver driver;
    public static LoginPage lp;
    public static LeadPage ldp;

    public static Properties prop;
    public static Map<String, Map<String, String>> td;
    public static String ScenarioName;

    public static ExtentHtmlReporter htmlreporter;
    public static ExtentReports extent;
    public static ExtentTest logger;



    public void LaunchApp() throws IOException, FilloException {
        ReadProperties();
        ReadExcel( System.getProperty("user.dir") + "/src/test/resources/Data/TestData.xlsx", "Sheet1");
        System.out.println(td);

        if (prop.getProperty("browser").equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }
        else if (prop.getProperty("browser").equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else
            driver = new ChromeDriver();


        driver.get(prop.getProperty("app_url"));
        lp= new LoginPage(driver, logger);
        ldp= new LeadPage(driver, logger);

    }

    public void ReadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        prop.load(fis);


    }

    public void CreateExtentReport()
    {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
        // Create an object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlreporter);
        extent.setSystemInfo("Host Name", "Automation Test Hub");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "Rajesh U");
        htmlreporter.config().setDocumentTitle("Title of the Report Comes here ");
        // Name of the report
        htmlreporter.config().setReportName("Name of the Report Comes here ");
        // Dark Theme
        htmlreporter.config().setTheme(Theme.DARK);

    }


    /*
    public void ReadExcel(String workbook, String sheet) throws FilloException {
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(workbook);
        String strQuery = "Select * from" + sheet;
        Recordset recordset = connection.executeQuery(strQuery);
        ArrayList<String> lst = recordset.getFieldNames();
        td= new HashMap<>();
        while (recordset.next()) {

            Map<String, String> rowdata = new HashMap<>();


            for (String colm:lst)
            {
                rowdata.put(colm, recordset.getField(colm));
            }
            td.put(recordset.getField("ScenerioName"), rowdata);
        }

        recordset.close();
        connection.close();
    }
    */
    public void ReadExcel(String workbook, String sheet) throws FilloException {
        Fillo fillo = new Fillo();
        Connection connection = null;
        Recordset recordset = null;

        try {
            // Establish connection to the Excel workbook
            connection = fillo.getConnection(workbook);

            // Prepare the query (ensure space between * and from)
            String strQuery = "Select * from " + sheet;

            // Execute the query and get the recordset
            recordset = connection.executeQuery(strQuery);

            // Fetch column names
            ArrayList<String> lst = recordset.getFieldNames();

            // Initialize the td map
            td = new HashMap<>();

            // Iterate through the recordset rows
            while (recordset.next()) {
                Map<String, String> rowdata = new HashMap<>();

                // For each column, fetch the data and populate rowdata map
                for (String colm : lst) {
                    rowdata.put(colm, recordset.getField(colm));
                }

                // Ensure that "ScenerioName" exists before accessing
                if (recordset.getField("ScenerioName") != null) {
                    td.put(recordset.getField("ScenerioName"), rowdata);
                } else {
                    // Handle case where "ScenerioName" is missing
                    throw new FilloException("ScenerioName column is missing in the sheet");
                }
            }
        } catch (FilloException e) {
            // Log or print exception details for troubleshooting
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to avoid memory leaks
            if (recordset != null) {
                recordset.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}






