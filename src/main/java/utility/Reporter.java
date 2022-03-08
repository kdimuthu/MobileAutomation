package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;

public class Reporter {

    /**
     * Capture screen shot
     */
    public static String CaptureScreenShot(WebDriver driver, String folderLocation, String screenShotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(source, new File(folderLocation + screenShotName + timestamp() + ".png"));

            System.out.println("Screenshot taken");

        } catch (Exception e) {

            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return screenShotName;

    }

    /**
     * Get time stamp
     */
    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());
    }


}
