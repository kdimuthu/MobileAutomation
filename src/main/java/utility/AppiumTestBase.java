package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumTestBase extends Util {
    protected static ExtentTest logs;
    //  public ExtentHtmlReporter ExtentReports;
    public AppiumDriver driver;
    private static ExtentReports extent;

    /**
     * Opening the browser.
     */
    @BeforeMethod
    public void openApp(Method result) {

        logs = extent.createTest(result.getName());
        String ExecutionType = Util.getPropertyFileData("MODE");
        if (ExecutionType.equalsIgnoreCase("Android")) {
            logs.log(Status.INFO, "Executing on Android device");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appPackage", "com.nexusvision_mobile");
            capabilities.setCapability("appActivity", "com.nexusvision_mobile.MainActivity");

            try {
                driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            }
        } else if (ExecutionType.equalsIgnoreCase("Web_chrome")) {
            logs.log(Status.INFO, "Executing on chrome browser in Android device");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
            try {
                driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            }
        } else if (ExecutionType.equalsIgnoreCase("IOS")) {
            logs.log(Status.INFO, "Executing on IOS");
            // To be implemented
        } else {
            logs.log(Status.INFO, "The Execution Type is Undefined");
            throw new IllegalArgumentException("The Execution Type is Undefined");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logs.log(Status.INFO, "Starting the execution of tests case " + result.getName());
    }

    /**
     * Start the test execution report using Extent Reports.
     */
    @BeforeSuite
    public void startReport() {
        String filePath = "./src/main/resources/Outputs/Reports/" + Reporter.timestamp() + "_TestReport.html";
        ExtentHtmlReporter ExtentReports = new ExtentHtmlReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(ExtentReports);
        ExtentReports.loadXMLConfig("extent-config.xml");
    }

    /**
     * Write the execution report
     */
    @AfterSuite
    public void closeReport() {
        extent.flush();
    }

    /**
     * Close the browser * Write test case status & failure reason after execution *
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            // Capture Screen shots for the pass test cases.
            // Reporter.CaptureScreenShot(driver,"./Outputs/ScreenshotsSuccess/",result.getName())
            logs.log(Status.INFO, "Ending the Execution of test case " + result.getMethod().getMethodName());
            logs.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String capturedScreenShotName = Reporter.CaptureScreenShot(driver, "./src/main/resources/Outputs/ScreenshotsFailure/",
                    result.getName()) + Reporter.timestamp() + ".png";
            logs.log(Status.ERROR, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.RED));

            String dest = "src/main/resources/Outputs/ScreenshotsFailure/" + capturedScreenShotName;
            File file = new File(dest);
            String imagePath = Reporter.CaptureScreenShot(driver, result.getName(), file.getAbsolutePath());

            logs.log(Status.INFO, "Ending the Execution of test case " + result.getMethod().getMethodName());
            try {
                logs.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

                logs.log(Status.INFO,
                                "Screenshot (" + capturedScreenShotName + ") for the fail test case is attached below")
                        .addScreenCaptureFromPath(imagePath);

            } catch (IOException e) {
                logs.log(Status.ERROR, "Exception is :" + e);
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            logs.log(Status.INFO,
                    "Skipped the test case. Please re-run the testcase" + result.getThrowable().getMessage());
            logs.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER));
        }
        //Close the session
        // driver.quit();

    }
}