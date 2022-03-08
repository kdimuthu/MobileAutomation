package tests;

import org.testng.annotations.Test;
import pages.SamplePage;
import utility.AppiumTestBase;
import utility.ExcelReader;


public class Sample_Tests extends AppiumTestBase {

   SamplePage SamplePage_;

    // Open a native application
    @Test(priority = 1, enabled = true)
    public void tc_OpenNativeApp() {
        SamplePage_ = new SamplePage(driver);
        //Open the app
        SamplePage_.bf_OpenANativeApp();
    }


    // Open a web application in a mobile browser
    @Test(priority = 2, enabled = true)
    public void tc_OpenAWebAppAndDoASearch() {
        SamplePage_ = new SamplePage(driver);
        //Open the app
        SamplePage_.bf_OpenAWebApp();
    }

    // Open the MJF client portal
    @Test(priority = 3, enabled = true)
    public void tc_OpenMJFAndDoOperation() {
        SamplePage_ = new SamplePage(driver);
        //Open the app
        SamplePage_.bf_OpenMJF();
    }
}