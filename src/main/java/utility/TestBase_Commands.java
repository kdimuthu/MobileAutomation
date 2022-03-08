package utility;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestBase_Commands extends AppiumTestBase {

    /**
     * The time-out.
     */
    private static final int timeOut = 10;
    /**
     * Number of retry.
     */
    private static final int NumberOfRetries = 10;
    /**
     * Ip Address of database.
     */
    private static final String DatabaseIp = "172.17.229.90";
    /**
     * Port Number
     */
    private static final String PortNumber = "5432";
    /**
     * Username of Database
     */
    private static final String UsernameDB = "postgres";
    /**
     * Username to Database
     */
    private static final String PasswordDB = "mit@123";
    /**
     * The web driver.
     */
    public static WebDriver driver = null;
    /**
     * Wait for the explicit wait
     */
    private static WebDriverWait wait;

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Sets the driver.
     *
     * @param driver the new web driver
     */
    public void setDriver(WebDriver driver) {
        TestBase_Commands.driver = driver;
    }


    /**
     * Launch the application.
     *
     * @param url-> the url
     */

    public void Open(String url) {

        try {
            getDriver().navigate().to(url);
            logs.log(Status.INFO, "Opened the browser [" + url + "] successfully");
        } catch (Exception e) {
            logs.log(Status.ERROR,
                    MarkupHelper.createLabel("Fail to open the browser due to " + e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Type on an input fields
     *
     * @param byLocator -> object locator
     * @param text      -> the text to be typed
     */

    public void Type(By byLocator, String text) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(text);
            logs.log(Status.INFO, "Typed the text " + text + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Enter a random generated text(characters ) to an input field
     *
     * @param byLocator      -> object locator
     * @param characterCount -> Character count of the text to be entered
     */

    public void EnterRandomCharacters(By byLocator, int characterCount) throws Exception {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // String randomText = UUID.randomUUID().toString();
            String randomText = RandomStringUtils.randomAlphabetic(characterCount);
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(randomText);
            logs.log(Status.INFO, "Entered a text as '**" + randomText + "**' with " + characterCount
                    + " characters randomly to element '**" + StoreInnerHTML(byLocator) + "**'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Enter a random generated integers to an input field
     *
     * @param byLocator    -> object locator
     * @param IntegerCount -> Integer count of the text to be entered
     */

    public void EnterRandomNumbers(By byLocator, int IntegerCount) throws Exception {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String randomIntegers = RandomStringUtils.randomNumeric(IntegerCount);
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(randomIntegers);
            logs.log(Status.INFO, "Entered a text as '**" + randomIntegers + "**' with " + IntegerCount
                    + " integers randomly to element '**" + StoreInnerHTML(byLocator) + "**'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Enter a random generated text(characters) with a known prefix to an input field
     *
     * @param byLocator      -> object locator *
     * @param characterCount -> Character count of the text to be entered
     * @param prefix->       Known text to be entered
     */

    public void EnterRandomCharactersWithPrefix(By byLocator, String prefix, int characterCount) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // String randomText = UUID.randomUUID().toString();
            String randomText = RandomStringUtils.randomAlphabetic(characterCount);
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(prefix + randomText);
            logs.log(Status.INFO, "Entered a text as '**" + prefix + randomText + "**' randomly to element '**"
                    + StoreInnerHTML(byLocator) + "**'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Enter a random generated integers with a known prefix to an input field
     *
     * @param byLocator    -> object locator
     * @param byLocator    -> known integers to be entered
     * @param IntegerCount -> Integer count of the text to be entered
     */

    public void EnterRandomIntegersWithPrefix(By byLocator, String prefix, int IntegerCount) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String randomInteger = RandomStringUtils.randomNumeric(IntegerCount);
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(prefix + randomInteger);
            logs.log(Status.INFO, "Entered a text as '**" + prefix + randomInteger + "**' randomly to element '**"
                    + StoreInnerHTML(byLocator) + "**'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Generate & type a random date
     *
     * @param byLocator -> object locator
     */

    public void EnterRandomDate(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //Generate random date
            long minDay = LocalDate.of(2022, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2050, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            //Enter the date to a field
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(randomDate.toString());
            logs.log(Status.INFO, "Entered random date " + randomDate + " in element '" + StoreInnerHTML(byLocator) + "'");

        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the current date to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterCurrentDate(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(LocalDate.now().toString());
            logs.log(Status.INFO, "Entered the current date " + LocalDate.now() + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the tomorrow date to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterTomorrowDate(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            LocalDate tomorrowDate = LocalDate.now().plusDays(1);
            //Enter the date to the input field
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(tomorrowDate.toString());
            logs.log(Status.INFO, "Entered tomorrow date " + LocalDate.now().plusDays(1) + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the end date of current month to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterEndDateOfCurrentMonth(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            LocalDate monthEndDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            //Enter the date to the input field
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(monthEndDate.toString());
            logs.log(Status.INFO, "Entered the month end date " + monthEndDate + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the end date of current year to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterEndDateOfCurrentYear(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            LocalDate yearEndDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
            //Enter the date to the input field
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(yearEndDate.toString());
            logs.log(Status.INFO, "Entered year end date " + yearEndDate + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the future date to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterFutureDate(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            Random randomDays = ThreadLocalRandom.current();
            //Enter a future date after 2 days from today's date
            LocalDate futureDate = LocalDate.now().plusDays(randomDays.nextInt(365) + 2);
            WebElement element = getDriver().findElement(byLocator);
            //Enter the date to the input field
            element.sendKeys(futureDate.toString());
            logs.log(Status.INFO, "Entered a future end date " + futureDate + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type the yesterday to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterYesterdayDate(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            LocalDate previousDate = LocalDate.now().minusDays(1);
            WebElement element = getDriver().findElement(byLocator);
            //Enter the date to the input field
            element.sendKeys(previousDate.toString());
            logs.log(Status.INFO, "Entered yesterday " + previousDate + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Type a past date to an input fields
     *
     * @param byLocator -> object locator
     */

    public void EnterPastDate(By byLocator) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            //generate the date
            LocalDate startDate = LocalDate.of(2020, 1, 1);
            long start = startDate.toEpochDay();
            LocalDate endDate = LocalDate.now().minusDays(1);
            long end = endDate.toEpochDay();
            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
            LocalDate pastDate = LocalDate.ofEpochDay(randomEpochDay);
            //Enter the date to the input field
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(pastDate.toString());
            logs.log(Status.INFO, "Entered past date " + pastDate + " in element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Click on a web element.
     *
     * @param byLocator -> object locator
     */

    public void Click(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));

            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            element.click();
            logs.log(Status.INFO, "Clicked on the element '" + byLocator + "'");
        } catch (Exception e) {
            //	logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Clear a text on an input field
     *
     * @param byLocator -> object locator
     */

    public void Clear(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            element.clear();
            logs.log(Status.INFO, "Cleared the text on the element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            throw e;
        }
    }

    /**
     * Clear before typing & Type on an input field
     *
     * @param byLocator -> object locator
     * @param text      -> the text to be typed
     */

    public void ClearAndType(By byLocator, String text) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            element.clear();
            element.sendKeys(text);
            logs.log(Status.INFO, "Typed the input '" + text + "' in element '" + StoreInnerHTML(byLocator) + "'.");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Click on an element where click is not working
     *
     * @param byLocator-> the object locator to be clicked
     */
    public void ClickAt(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            WebElement element = getDriver().findElement(byLocator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            logs.log(Status.INFO, "Click on the on element '" + StoreInnerHTML(byLocator) + "'");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that radio button is selected or not by default
     *
     * @param byLocator      -> object locator*
     * @param selectedStatus -> radio button selected or not(true or false)
     */

    public void VerifyRadioButtonSelected(By byLocator, boolean selectedStatus) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementStatus = getDriver().findElement(byLocator).getAttribute("checked");
            if (elementStatus != null && !elementStatus.isEmpty()) {
                if (selectedStatus) {
                    logs.log(Status.INFO, "Radio button with the object '" + byLocator + "' is selected.");
                } else {
                    FailTest("Radio button with the object '" + byLocator
                            + "' should be selected . But actually it is not selected.");
                }
            } else {
                if (!selectedStatus) {
                    logs.log(Status.INFO, "Radio button with the object '" + byLocator + "' is not selected.");
                } else {
                    FailTest("Radio button with the object '" + byLocator
                            + "' should not be selected. But actually it is selected.");
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify a text or a massage
     *
     * @param byLocator       -> object locator
     * @param expectedMessage -> the expectedMessage to be verified
     */

    public void VerifyText(By byLocator, String expectedMessage) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String ActualMessage = null;
            try {
                ActualMessage = getDriver().findElement(byLocator).getText();
            } catch (NoSuchElementException e) {
                FailTest("Unable to find the element.May be due to wrong object identification for element '" + byLocator + "' & Throwing error.====" + e);
            }
            String ExpectedMessage = expectedMessage;
            if (ActualMessage.equals(ExpectedMessage)) {
                WritePassMessageToReport("Verification done for the element with text '" + StoreInnerHTML(byLocator)
                        + "'. & Label is correctly visible as ** " + ActualMessage + " **");
            } else {
                FailTest("Verification fail for the element '" + StoreInnerHTML(byLocator) + "'. Expected **"
                        + ExpectedMessage + "** but found **" + ActualMessage + "**");
            }
        } catch (Exception e) {
            FailTest("Unable to find the element " + byLocator + ". Showing error " + e);
        }
    }

    /**
     * Verify a text or a massage & enter a custom message
     *
     * @param byLocator       -> object locator
     * @param expectedMessage -> the expectedMessage to be verified
     * @param expectedMessage -> the expectedMessage to be verified
     */

    public void VerifyTextAndEnterCustomMessage(By byLocator, String expectedMessage, String CustomMessage) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String ActualMessage = getDriver().findElement(byLocator).getText();
            String ExpectedMessage = expectedMessage.replaceAll("  ", " ");
            if (ActualMessage.equals(ExpectedMessage)) {
                WritePassMessageToReport(CustomMessage);
            } else {
                FailTest("Verification fail for the element '" + StoreInnerHTML(byLocator) + "'. Expected **"
                        + ExpectedMessage + "** but found **" + ActualMessage + "**");
            }
        } catch (Exception e) {
            //	logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify a validation message of an input field
     *
     * @param byLocator       -> object locator
     * @param expectedMessage -> the expectedMessage to be verified
     */

    public void VerifyValidationMessage(By byLocator, String expectedMessage) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String ActualMessage = null;
            try {
                ActualMessage = getDriver().findElement(byLocator).getText();
            } catch (NoSuchElementException e) {
                FailTest("Unable to find the element.May be due to wrong object identification for element '" + byLocator + "' & Throwing error.====" + e);
            }
            String ExpectedMessage = expectedMessage;
            if (ActualMessage.equals(ExpectedMessage)) {
                WritePassMessageToReport("Validation message is showing correctly as : " + ActualMessage + " for element " + StoreInnerHTML(byLocator));
            } else {
                FailTest("Verification fail for the element '" + StoreInnerHTML(byLocator) + "'. Expected **"
                        + ExpectedMessage + "** but found **" + ActualMessage + "**");
            }
        } catch (Exception e) {
            FailTest("Unable to find the element " + byLocator + ". Showing error " + e);
        }
    }

    /**
     * Verify the title of a page
     *
     * @param title -> the Expected title of the page
     */

    public void VerifyTitle(String title) {
        try {
            getDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
            String actualTitle = getDriver().getTitle();
            String expectedTitle = title;
            if (actualTitle.equals(expectedTitle)) {
                logs.log(Status.INFO, "Title Verification done. Actual & Expected titles are same :" + actualTitle);
            } else {
                FailTest("Verification fail. Expected **" + expectedTitle + "** but found **" + actualTitle + "**");
            }
        } catch (Exception e) {
        }
    }

    /**
     * Verify the placeholder of an element
     *
     * @param byLocator   -> the object locator
     * @param placeholder -> placeholder to be verified
     */

    public void VerifyPlaceHolder(By byLocator, String placeholder) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String ActualPlaceholder = getDriver().findElement(byLocator).getAttribute("placeholder");
            String ExpectedPlaceholder = placeholder;
            if (ActualPlaceholder.equals(ExpectedPlaceholder)) {
                WritePassMessageToReport("Verification done.Placeholder of element'" + StoreInnerHTML(byLocator)
                        + "' is showing correctly as '**" + ActualPlaceholder + "**'.");
            } else {
                FailTest("Verification fail. Expected placeholder of element '" + StoreInnerHTML(byLocator) + "' is '**"
                        + ExpectedPlaceholder + "**' but found '**" + ActualPlaceholder + "**'.");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that date field display current date by default
     *
     * @param byLocator -> the object locator
     */

    public void VerifyElementShowCurrentDate(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String getDate = getDriver().findElement(byLocator).getAttribute("value");
            String CurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            if (getDate.equals(CurrentDate)) {
                logs.log(Status.INFO, "Date field '" + StoreInnerHTML(byLocator) + "' is showing current date ' "
                        + CurrentDate + " ' as default date");
            } else {
                FailTest("Verification fail. Expected current date ' " + CurrentDate
                        + " ' by default, but showing the date " + getDate + " ' for date " + StoreInnerHTML(byLocator)
                        + " ' field");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that date field
     *
     * @param byLocator -> the object locator
     * @param date      -> expected date to be verified
     */

    public void VerifyDate(By byLocator, String date) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String getDate = getDriver().findElement(byLocator).getAttribute("value");
            if (getDate.equals(date)) {
                logs.log(Status.INFO, "Date field '" + StoreInnerHTML(byLocator) + "' is showing correct date ' "
                        + getDate + " ' by default.");
            } else {
                FailTest("Verification fail. Expected  date ' " + date + " ' by default, but showing the date "
                        + getDate + " ' for date " + StoreInnerHTML(byLocator) + " ' field");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify the header message
     *
     * @param byLocator -> object locator
     * @param Message   -> expected header message to be verified
     */

    public void VerifyHeader(By byLocator, String Message) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            if (IsElementPresent(byLocator)) {
                String ActualHeaderMessage = getDriver().findElement(byLocator).getText();
                String ExpectedHeaderMessage = Message;
                if (ActualHeaderMessage.equals(ExpectedHeaderMessage)) {
                    logs.log(Status.INFO, MarkupHelper.createLabel(
                            "Navigated to ' " + ActualHeaderMessage + " ' page successfully .", ExtentColor.GREEN));
                } else {
                    FailTest("Expected header message is ' " + ExpectedHeaderMessage + " '. but found '"
                            + ActualHeaderMessage + " '");
                }
            } else {
                FailTest("Fail to navigates to '" + Message + " '. page");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify the successful page navigation
     *
     * @param byLocator -> object locator
     */

    public void VerifyPageNavigation(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            if (IsElementPresent(byLocator)) {
                String ActualText = getDriver().findElement(byLocator).getText();
                WritePassMessageToReport("Navigated to page with '" + ActualText + " ' successfully.");
            } else {
                FailTest("Fail To Navigate to '" + getDriver().findElement(byLocator).getText() + "' page.");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify the element is presented or not in the page
     *
     * @param byLocator     -> object Locator
     * @param elementStatus -> true or false statue. true -> presented false-> Not
     *                      presented
     * @param elementName   -> Element Name
     */

    public void CheckElementPresent(By byLocator, boolean elementStatus, String elementName) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Boolean eleStatus = null;
            if (elementStatus) {
                try {
                    eleStatus = getDriver().findElement(byLocator).isDisplayed();
                } catch (NoSuchElementException e) {
                }
                if (eleStatus == null) {
                    FailTest("The Element '" + elementName
                            + "' should be exist. But found it does not' exist in the page");
                } else {
                    WritePassMessageToReport("The Element '" + elementName + "' exists in the page");
                    //logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' exists in the page");
                }
            } else {
                try {
                    eleStatus = getDriver().findElement(byLocator).isDisplayed();
                } catch (NoSuchElementException e) {
                }
                if (eleStatus == null) {
                    WritePassMessageToReport("Element '" + elementName + "' doesn't exists in the page as expected.");

                } else {
                    FailTest("The Element '" + elementName
                            + "' should not be exist. But found it exist in the page.");
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify the element is exists, enabled & editable
     *
     * @param byLocator     -> object Locator
     * @param elementStatus -> true or false statue. true -> Enabled false-> Not
     *                      Enabled
     * @param elementName   -> Element Name
     */

    public void CheckElementEnabled(By byLocator, boolean elementStatus, String elementName) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            List<WebElement> all = driver.findElements(byLocator);
            for (WebElement element : all) {
                Boolean eleStatus = element.isEnabled();
                if (elementStatus) {
                    if (eleStatus) {
                        WritePassMessageToReport("The Element '" + elementName + "' is exists & enabled/Editable");
                        //	logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' is Enable & Editable");
                    } else {
                        FailTest("The Element '" + elementName
                                + "' should be enabled & editable. But found it is disabled / readonly");
                    }
                } else {
                    if (!eleStatus) {
                        //	logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' is in readonly mode");
                        WritePassMessageToReport("Element '" + elementName + "' is in readonly mode");
                    } else {
                        FailTest("Element '" + elementName
                                + "' should be in read only mode. But found it is editable");
                    }
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify the element is selected
     *
     * @param byLocator     -> object Locator
     * @param elementStatus -> true or false statue. true -> Selected false-> Not
     *                      Selected
     * @param elementName   -> Element Name
     */

    public void CheckElementSelected(By byLocator, boolean elementStatus, String elementName) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            List<WebElement> all = driver.findElements(byLocator);
            for (WebElement element : all) {
                boolean eleStatus = element.isSelected();
                if (elementStatus) {
                    if (eleStatus) {
                        WritePassMessageToReport("The Element '" + elementName + "' is selected");
                        //logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' is selected");
                    } else {
                        FailTest("Element '" + elementName
                                + "' should be selected. But found it is not selected");
                    }
                } else {
                    if (!eleStatus) {
                        WritePassMessageToReport("The Element '" + elementName + "' is not selected");
                        //	logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' is not selected");
                    } else {
                        FailTest("The Element '" + elementName
                                + "' should be not selected. But found it is selected");
                    }
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Get the status as true or false if the element is visible or not in the page
     *
     * @param byLocator-> object locator
     */

    public Boolean IsElementPresent(By byLocator) {

        try {
            wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        } catch (TimeoutException e) {
            logs.log(Status.INFO, byLocator + " element is not visible");
        }
        try {
            Boolean elementStatus = getDriver().findElement(byLocator).isDisplayed();
            if (elementStatus.equals(true)) {
                logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' Exists in the page.");
            }
            return elementStatus;
        } catch (NoSuchElementException e) {
            // logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }

        WriteToReport("Element '" + StoreInnerHTML(byLocator) + "' Doesn't exists in the page");
        return false;
    }

    /**
     * Get the status of element as true or false , whether element is selected or
     * not
     *
     * @param byLocator-> object locator
     */

    public Boolean IsElementSelected(By byLocator) {
        try {
            wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        } catch (TimeoutException e) {
            logs.log(Status.INFO, byLocator + " element is not visible");
        }
        try {
            Boolean elementStatus = getDriver().findElement(byLocator).isSelected();
            if (elementStatus.equals(true)) {
                logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' is selected in the page.");
            }
            return elementStatus;
        } catch (NoSuchElementException e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' Doesn't select in the page.");
        return false;
    }

    /**
     * Get the status of element true or false , whether element is enabled or not
     *
     * @param byLocator -> object locator
     */

    public Boolean IsElementEnabled(By byLocator) {

        try {
            wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        } catch (TimeoutException e) {
            logs.log(Status.INFO, byLocator + " element is not visible");
        }
        try {
            Boolean elementStatus = getDriver().findElement(byLocator).isEnabled();
            if (elementStatus.equals(true)) {
                logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' Enabled in the page.");
            }
            return elementStatus;
        } catch (NoSuchElementException e) {
            //   logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        logs.log(Status.INFO, "Element '" + StoreInnerHTML(byLocator) + "' Doesn't enabled in the page.");
        return false;
    }

    /**
     * Verify the property of an element using getAttribute method
     *
     * @param byLocator     -> object locator
     * @param propertyType  -> Expected propertyType to be verified
     * @param propertyValue -> Expected propertyValue to be verified
     */

    public void VerifyElementProperty(By byLocator, String propertyType, String propertyValue) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
            }
            String ActualProperty = getDriver().findElement(byLocator).getAttribute(propertyType);
            String ExpectedProperty = propertyValue;
            if (ActualProperty.equals(ExpectedProperty)) {
                logs.log(Status.INFO, "Verification done.'" + propertyType + "' Property of element '"
                        + StoreInnerHTML(byLocator) + "' is showing correctly as '**" + propertyValue + "**'.");
            } else {
                FailTest("Verification fail. Expected value of '" + propertyType + "'" + " property is '**"
                        + ExpectedProperty + "**' but found '**" + ActualProperty + "**'.");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Select a value from dropdown using SelectByVisibleText
     *
     * @param byLocator -> object locator
     * @param text      -> text to be selected
     */

    public void SelectValueSelectByVisibleText(By byLocator, String text) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            ddValue.selectByVisibleText(text);
            logs.log(Status.INFO, "Selected the option '" + text + "' from the dropdown " + StoreInnerHTML(byLocator));
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Select a value from dropdown using Select By Value
     *
     * @param byLocator -> object locator
     * @param value     -> value to be selected
     */

    public void SelectValueSelectByValue(By byLocator, String value) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            ddValue.selectByValue(value);
            logs.log(Status.INFO, "Selected the option '" + value + "' from the dropdown " + StoreInnerHTML(byLocator));
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Select a value from dropdown using Select by index
     *
     * @param byLocator -> object locator
     * @param index     -> index to be selected
     */

    public void SelectValueSelectByIndex(By byLocator, String index) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            ddValue.selectByIndex(Integer.parseInt(index));
            logs.log(Status.INFO, "Selected the option '" + index + "' from the dropdown " + StoreInnerHTML(byLocator));
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify selected option in a dropdown by default
     *
     * @param byLocator      - > object locator
     * @param expectedOption -> the expected Option to be verified
     */

    public void VerifySelectedOption(By byLocator, String expectedOption) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            String selectedOption = ddValue.getFirstSelectedOption().getText();
            if (selectedOption.equals(expectedOption)) {
                WritePassMessageToReport("Default option for " + byLocator + " dropdown display correctly as " + selectedOption);
            } else {
                FailTest("Expected default option is ' " + expectedOption + " '. But found '" + selectedOption + " '");
            }
        } catch (Exception e) {
            //  logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Store a selected option in a dropdown to a variable
     *
     * @param byLocator -> object locator
     */

    public String StoreSelectedOption(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            String selectedOption = ddValue.getFirstSelectedOption().getText();
            logs.log(Status.INFO, "Stored the selected option  '**" + selectedOption + "**'. in dropdown '"
                    + StoreInnerHTML(byLocator) + "'");
            return selectedOption;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Verify that particular option is displayed in a dropdown & option name is correct
     *
     * @param byLocator  -> object locator
     * @param OptionName -> option name to be verified
     * @param i          -> index of the option
     */

    public void VerifyOption(By byLocator, int i, String OptionName) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            List<WebElement> optionList = ddValue.getOptions();
            String ActualOptionName = optionList.get(i).getText();
            if (ActualOptionName.equals(OptionName)) {
                logs.log(Status.INFO, "Verification done.Option in dropdown '" + StoreInnerHTML(byLocator)
                        + "' is showing correctly as '**" + ActualOptionName + "**'");
            } else {
                FailTest("Verification fail. Expected Option is '**" + OptionName + "**' but found '**"
                        + ActualOptionName + "**'.");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Store a particular option in a  dropdown
     *
     * @param byLocator -> object locator
     * @param i         -> index of the option
     */

    public String StoreAOption(By byLocator, int i) {

        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            List<WebElement> optionList = ddValue.getOptions();
            String ActualOptionName = optionList.get(i).getText();
            if (ActualOptionName != null && !ActualOptionName.isEmpty()) {
                logs.log(Status.INFO, "Stored the option name for option ' " + i + " ' & Stored option name is '**" + ActualOptionName + "**'");
            } else {
                logs.log(Status.INFO,
                        "NO option name is available for the  option '" + i + "' to be stored.");
            }
            return ActualOptionName;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Verify that particular option exists in the dropdown
     *
     * @param byLocator  - > object locator
     * @param OptionName -> option name to be verified
     */

    public boolean CheckOptionPresent(By byLocator, String OptionName) {
        boolean OptionPresentStatus = false;
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            List<WebElement> optionList = ddValue.getOptions();
            for (int i = 0; i < optionList.size(); i++) {
                if (optionList.get(i).getText() != null) {
                    if (optionList.get(i).getText().contains(OptionName)) {
                        logs.log(Status.INFO, "Option **'" + optionList.get(i).getText()
                                + "**' exists in the dropdown '" + StoreInnerHTML(byLocator) + "'.");
                        OptionPresentStatus = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        if (!OptionPresentStatus) {
            FailTest("Dropdown '**" + StoreInnerHTML(byLocator) + "**' doesn't contain '**" + OptionName + "**'option");
        }
        return true;
    }

    /**
     * Get option count in a dropdown
     *
     * @param byLocator -> object locator
     * @return
     */

    public int GetOptionCount(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            Select ddValue = new Select(getDriver().findElement(byLocator));
            List<WebElement> optionList = ddValue.getOptions();
            int optionCount = optionList.size();

            logs.log(Status.INFO, "Dropdown '**" + StoreInnerHTML(byLocator) + "**' has '**" + optionCount
                    + "**' options including default value");
            return optionCount;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return 0;
    }

    /**
     * Verify all the option in a dropdown
     *
     * @param byLocator - > object locator
     * @param Options   -> Expected list of options to be verified
     */

    public void CheckAllOptions(By byLocator, String Options) {
        try {
            wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        } catch (TimeoutException e) {
            logs.log(Status.INFO, byLocator + " element is not visible");
        }
        try {
            String[] expectedOptions = Options.split(",");
            if (expectedOptions.length > 1) {
                ArrayList<String> Expected_options = new ArrayList<String>();
                Expected_options.addAll(Arrays.asList(expectedOptions));
                Select ddValue = new Select(getDriver().findElement(byLocator));
                List<WebElement> dropdownList = ddValue.getOptions();
                if (dropdownList.size() == Expected_options.size()) {
                    WritePassMessageToReport("All the options ( " + dropdownList.size()
                            + " options including default option ) are available in the drop down ' " + byLocator
                            + "' as expected");
                    for (int i = 0; i < dropdownList.size(); i++) {
                        if (expectedOptions[i].equals(dropdownList.get(i).getText())) {
                            WritePassMessageToReport(
                                    "Options ' " + dropdownList.get(i).getText() + " ' is available in the drop down");
                        } else {
                            logs.log(Status.ERROR, MarkupHelper.createLabel("Expected ' " + expectedOptions[i]
                                    + " ' but found ' " + dropdownList.get(i).getText() + " '.", ExtentColor.RED));
                            FailTest("Mismatch found in option names");
                        }
                    }
                } else if (dropdownList.size() > Expected_options.size()) {
                    for (int i = 0; i < Expected_options.size(); i++) {
                        for (int j = 0; j < dropdownList.size(); j++) {
                            if (Expected_options.get(i).equals(dropdownList.get(j).getText())) {
                                WriteToReport(
                                        "Option '" + dropdownList.get(j).getText() + "' is available in the drop down");
                                dropdownList.remove(dropdownList.get(j));
                            } else {
                                WriteFailMessageToReport(
                                        "Option ' " + expectedOptions[i] + " ' not available in the dropdown");
                                WriteFailMessageToReport("Additional Option ' " + dropdownList.get(j).getText()
                                        + " ' is available in the dropdown");
                            }
                            if (dropdownList.size() > 0) {
                                WriteFailMessageToReport("Additional options ' " + dropdownList.get(j).getText()
                                        + " ' is available in the dropdown");
                            }
                        }
                    }
                    FailTest("Additional Options are available in the drop down " + byLocator + ".");
                } else {
                    for (int i = 0; i < Expected_options.size(); i++) {
                        for (int j = 0; j < dropdownList.size(); j++) {
                            if (Expected_options.get(i).equals(dropdownList.get(j).getText())) {
                                WriteToReport("Option '" + Expected_options.get(i) + "' is available in drop down ");
                                Expected_options.remove(Expected_options.get(i));
                            } else {
                                WriteFailMessageToReport(
                                        "Option ' " + Expected_options.get(i) + " ' not available in the dropdown");
                            }
                        }
                    }
                    if (Expected_options.size() > 0) {
                        FailTest("Missing options in the dropdown " + byLocator + ".");
                    }
                }
            } else {
                logs.log(Status.SKIP,
                        "At least drop down should have two options. Hence please enter more than or equal to two options in expected option list");
            }
        } catch (Exception e) {
            //  logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Store a text or a message to a variable
     *
     * @param byLocator -> object locator
     */

    public String StoreText(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementText = getDriver().findElement(byLocator).getText();
            if (elementText != null && !elementText.isEmpty()) {
                logs.log(Status.INFO, "Stored the text message of element '" + StoreInnerHTML(byLocator)
                        + "' & Stored text is '**" + elementText + "**'");
            } else {
                logs.log(Status.INFO,
                        "NO text is available for the '" + StoreInnerHTML(byLocator) + "' element to be stored.");
            }
            return elementText;
        } catch (Exception e) {
            //	logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Store the property of an element to a variable
     *
     * @param byLocator    -> object locator
     * @param propertyType -> property type (Inner HTML) value of the element need
     *                     to be stored
     */

    public String StoreProperty(By byLocator, String propertyType) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementProperty = getDriver().findElement(byLocator).getAttribute(propertyType);
            if (elementProperty != null && !elementProperty.isEmpty()) {
                logs.log(Status.INFO, "Stored the Property '" + propertyType + "' of the element"
                        + StoreInnerHTML(byLocator) + " & Stored property is '**" + elementProperty + "**'");
            } else {
                logs.log(Status.INFO, "NO Property value is available for the property '" + propertyType + "' for the"
                        + StoreInnerHTML(byLocator) + " element to be stored.");
            }
            return elementProperty;
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Store value property of a element to a variable
     *
     * @param byLocator -> object locator
     */

    public String StoreValue(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementValue = getDriver().findElement(byLocator).getAttribute("value");
            if (elementValue != null && !elementValue.isEmpty()) {
                logs.log(Status.INFO, "Stored the value of '" + StoreInnerHTML(byLocator) + "' the element & it is '**"
                        + elementValue + "**'");
            } else {
                logs.log(Status.INFO,
                        "No value is available for the '" + StoreInnerHTML(byLocator) + "'element to be stored.");
            }
            return elementValue;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Verify the maximum length of the characters that can be entered to an input
     * field.
     *
     * @param byLocator        -> object locator
     * @param ExpectedMaxCount -> Expected max length of the input field
     */

    public void VerifyMaxLength(By byLocator, int ExpectedMaxCount) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String randomText = RandomStringUtils.randomAlphabetic(ExpectedMaxCount + 1);
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys(randomText);
            String elementValue = getDriver().findElement(byLocator).getAttribute("value");
            if (elementValue != null && !elementValue.isEmpty()) {
                int ActualCharacterCount = elementValue.length();
                if (ActualCharacterCount == ExpectedMaxCount) {
                    logs.log(Status.INFO, "Only ' " + ExpectedMaxCount + " ' characters are allowed to enter ' "
                            + StoreInnerHTML(byLocator) + " ' input field");
                } else {
                    FailTest("More than ' " + ExpectedMaxCount + " ' characters are allowed to enter ' "
                            + StoreInnerHTML(byLocator) + " ' input field");
                }
            } else {
                String randomIntegers = RandomStringUtils.randomNumeric(ExpectedMaxCount + 1);
                WebElement _element = getDriver().findElement(byLocator);
                _element.clear();
                _element.sendKeys(randomIntegers);
                String _elementValue = getDriver().findElement(byLocator).getAttribute("value");
                if (_elementValue != null && !_elementValue.isEmpty()) {
                    int ActualCharacterCount = _elementValue.length();
                    if (ActualCharacterCount == ExpectedMaxCount) {
                        logs.log(Status.INFO, "Only ' " + ExpectedMaxCount + " ' characters are allowed to enter ' "
                                + StoreInnerHTML(byLocator) + " ' input field");
                    } else {
                        FailTest("More than ' " + ExpectedMaxCount + " ' characters are allowed to enter ' "
                                + StoreInnerHTML(byLocator) + " ' input field");
                    }
                } else {
                    logs.log(Status.INFO,
                            "No value is available for the '" + StoreInnerHTML(byLocator) + "'element to be retrieved.");
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that only integers are allowed to an input field
     *
     * @param byLocator -> object locator
     */

    public void CheckAllowOnlyIntegers(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("1234567890");
            String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
            if (IntegerValue != null && !IntegerValue.isEmpty()) {
                int integerCount = IntegerValue.length();
                WriteToReport("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
                String characterValue = getDriver().findElement(byLocator).getAttribute("value");
                int CharacterCount = characterValue.length();
                if (CharacterCount > 0) {
                    sAssert.fail("Characters ' " + characterValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    sAssert.fail("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                    sAssert.assertAll();
                } else {
                    WriteToReport("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that only characters are allowed to an input field
     *
     * @param byLocator -> object locator
     */

    public void CheckAllowOnlyCharacters(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
            String CharacterValue = getDriver().findElement(byLocator).getAttribute("value");
            if (CharacterValue != null && !CharacterValue.isEmpty()) {
                int CharacterCount = CharacterValue.length();
                WriteToReport("Characters ' " + CharacterValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("1234567890");
                String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
                int IntegerCount = IntegerValue.length();
                if (IntegerCount > 0) {
                    sAssert.fail("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Integers are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    sAssert.fail("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that input field is allowed only characters and numbers , not allowed special
     * characters
     *
     * @param byLocator -> object locator
     */

    public void CheckNotAllowSpecialCharacters(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("1234567890");
            String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
            if (IntegerValue != null && !IntegerValue.isEmpty()) {
                int integerCount = IntegerValue.length();
                WriteToReport("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
                String characterValue = getDriver().findElement(byLocator).getAttribute("value");
                int CharacterCount = characterValue.length();
                if (CharacterCount > 0) {
                    WriteToReport("Characters ' " + characterValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    sAssert.fail("Character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    sAssert.fail("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that input field is not allowed to enter characters
     *
     * @param byLocator -> object locator
     */

    public void CheckNotAllowCharacters(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("1234567890");
            String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
            if (IntegerValue != null && !IntegerValue.isEmpty()) {
                int integerCount = IntegerValue.length();
                WriteToReport("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    WriteToReport("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    sAssert.fail("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
                element.clear();
                element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
                String characterValue = getDriver().findElement(byLocator).getAttribute("value");
                int CharacterCount = characterValue.length();
                if (CharacterCount > 0) {
                    sAssert.fail("Characters ' " + characterValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that input field is not allowed to enter integers
     *
     * @param byLocator -> object locator
     */

    public void CheckNotAllowIntegers(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
            String CharacterValue = getDriver().findElement(byLocator).getAttribute("value");
            if (CharacterValue != null && !CharacterValue.isEmpty()) {
                int CharacterCount = CharacterValue.length();
                WriteToReport("Characters ' " + CharacterValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    WriteToReport("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    sAssert.fail("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                element.clear();
                element.sendKeys("0987654321");
                String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
                int IntegerCount = IntegerValue.length();
                if (IntegerCount > 0) {
                    sAssert.fail("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    WriteToReport("Integers are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify that input field is allowed any character including special characters
     *
     * @param byLocator -> object locator
     */

    public void CheckAllowAnyCharacters(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // Implementing soft assertions
            SoftAssert sAssert = new SoftAssert();
            WebElement element = getDriver().findElement(byLocator);
            element.sendKeys("1234567890");
            String IntegerValue = getDriver().findElement(byLocator).getAttribute("value");
            if (IntegerValue != null && !IntegerValue.isEmpty()) {
                int integerCount = IntegerValue.length();
                WriteToReport("Integers ' " + IntegerValue + " ' are allowed to enter element ' "
                        + StoreInnerHTML(byLocator) + " '");
                element.clear();
                element.sendKeys("ewerTYUIopLKJugfulDSXcviBNm");
                String characterValue = getDriver().findElement(byLocator).getAttribute("value");
                int CharacterCount = characterValue.length();
                if (CharacterCount > 0) {
                    WriteToReport("Characters ' " + characterValue + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    sAssert.fail("Character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                element.clear();
                element.sendKeys("@!#$~(%^)&*/-_+=:<>?");
                String Special_element_Value = getDriver().findElement(byLocator).getAttribute("value");
                int SpecialCharacterCount = Special_element_Value.length();
                if (SpecialCharacterCount > 0) {
                    WriteToReport("Special characters ' " + Special_element_Value + " ' are allowed to enter element ' "
                            + StoreInnerHTML(byLocator) + " '");
                } else {
                    sAssert.fail("Special character are not allowed to element '" + StoreInnerHTML(byLocator) + " '");
                }
                sAssert.assertAll();
            } else {
                logs.log(Status.INFO, "No value is available for the '" + StoreInnerHTML(byLocator)
                        + "'element to get the character length");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Get the count of set of similar elements
     *
     * @param byLocator -> object locator
     */

    public int getCount(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            int itemCount = 0;
            try {
                itemCount = getDriver().findElements(byLocator).size();
            } catch (NoSuchElementException e) {
            }
            if (itemCount > 0) {
                logs.log(Status.INFO,
                        "Item count for the elements '" + StoreInnerHTML(byLocator) + "' is '**" + itemCount + "**'");
            } else {
                logs.log(Status.INFO, "No Items are available for the '" + StoreInnerHTML(byLocator) + "' element");
            }
            return itemCount;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return 0;
    }

    /**
     * Verify the count of similar elements
     *
     * @param byLocator     -> object locator
     * @param ExpectedCount -> Expected count to be verified
     */

    public void VerifyRecordsCounts(By byLocator, int ExpectedCount) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            int ActualItemCount = getDriver().findElements(byLocator).size();
            if (ActualItemCount > 0) {
                if (ActualItemCount == ExpectedCount) {
                    logs.log(Status.INFO,
                            MarkupHelper
                                    .createLabel("Expected & Actual records count are tally. & it is showing as '**"
                                            + ActualItemCount + "**'.", ExtentColor.GREEN));
                } else {
                    FailTest("Verification fail. Expected record count is ' " + ExpectedCount + " ' but found ' "
                            + ActualItemCount + " ' records.");
                }
            } else {
                logs.log(Status.INFO, "No Items are available for the '" + StoreInnerHTML(byLocator) + "' element");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Store inner HTML values with the purpose of using in reporting
     *
     * @param byLocator -> object locator
     */

    public String StoreInnerHTML(By byLocator) {
        try {
            String elementProperty = null;
            String text = getDriver().findElement(byLocator).getText();
            if (text != null && !text.isEmpty()) {
                elementProperty = text;
            } else {
                String name = getDriver().findElement(byLocator).getAttribute("name");
                if (name != null && !name.isEmpty()) {
                    elementProperty = name;
                } else {
                    String placeholder = getDriver().findElement(byLocator).getAttribute("placeholder");
                    if (placeholder != null && !placeholder.isEmpty()) {
                        elementProperty = placeholder;
                    } else {
                        String id = getDriver().findElement(byLocator).getAttribute("id");
                        if (id != null && !id.isEmpty()) {
                            elementProperty = id;
                        } else {
                            String title = getDriver().findElement(byLocator).getAttribute("title");
                            if (title != null && !title.isEmpty()) {
                                elementProperty = title;
                            } else {
                                elementProperty = byLocator.toString();
                            }
                        }
                    }
                }
            }
            return elementProperty;
        } catch (NoSuchElementException e) {
        }
        return byLocator.toString();
    }

    /**
     * Mouse hover & click
     *
     * @param byMainLink -> the main link to be clicked
     * @param bySubLink  -> the sub link to be clicked
     */

    public void MouseHoverAndClick(By byMainLink, By bySubLink) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byMainLink));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byMainLink + " element is not visible");
            }
            Actions builder = new Actions(driver);
            builder.moveToElement(getDriver().findElement(byMainLink)).perform();
            getDriver().findElement(bySubLink).click();
            logs.log(Status.INFO, "Mouse hover the element '" + StoreInnerHTML(byMainLink) + "' & Click on the '"
                    + StoreInnerHTML(bySubLink) + "' link.");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Mouse hover over an element
     *
     * @param byLocator -> object locate for mouse hover
     */

    public void MouseHover(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                //    logs.log(Status.INFO, byLocator+" element is not visible");
            }
            Actions builder = new Actions(driver);
            builder.moveToElement(getDriver().findElement(byLocator)).perform();
            logs.log(Status.INFO, "Mouse hover the element ' " + StoreInnerHTML(byLocator) + " '.");
        } catch (Exception e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Navigates back to previous page
     */

    public void NavigateBack() {
        try {
            getDriver().navigate().back();
            logs.log(Status.INFO, "Navigated back to previous screen");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Navigates forwards to next page
     */

    public void NavigateForward() {
        try {
            getDriver().navigate().forward();
            logs.log(Status.INFO, "Navigated to next screen");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Navigates to given page or URL
     *
     * @param url -> the URL to be navigated
     */

    public void NavigateToURL(String url) {
        try {
            getDriver().navigate().to(url);
            logs.log(Status.INFO, "Navigated to the URL '**" + url + "**'successfully");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Refresh the page
     */

    public void Refresh() {
        try {
            getDriver().navigate().refresh();
            logs.log(Status.INFO, "Refreshed the browser successfully");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Explicit Wait with presentOfElementLocated condition (Wait maximum time till
     * specific condition meet before throwing exception)
     *
     * @param byLocator -> object locator
     *                  *
     */

    public void WaitForElementPresent(By byLocator) {

        for (int i = 0; i < NumberOfRetries; i++) {
            try {
                Boolean elementStatus = getDriver().findElement(byLocator).isDisplayed();
                if (elementStatus) {
                    logs.log(Status.INFO, byLocator + " element is visible & jumped to next element");
                    break;
                }
            } catch (NoSuchElementException e) {
            }
            try {
                logs.log(Status.INFO, "Waiting '" + timeOut + "' seconds till element '" + StoreInnerHTML(byLocator)
                        + "' is presented in the page");
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                //	logs.log(Status.INFO, "Element not visible yet & retrying again");
            }
        }
    }

    /**
     * Explicit Wait with TextToBePresent condition (Wait maximum time till
     * specific condition meet before throwing exception)
     *
     * @param byLocator -> object locator
     * @param text      -> text to be present
     *                  *
     */

    public void WaitForTextToBePresent(By byLocator, String text) {

        for (int i = 0; i < NumberOfRetries; i++) {
            try {
                Boolean elementStatus = getDriver().findElement(byLocator).isDisplayed();
                if (elementStatus) {
                    logs.log(Status.INFO, byLocator + " element is visible & jumped to next element");
                    break;
                }
            } catch (NoSuchElementException e) {
            }
            try {
                logs.log(Status.INFO, "Waiting '" + timeOut + "' seconds till element '" + StoreInnerHTML(byLocator)
                        + "' is presented in the page");
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.textToBe(byLocator, text));
                //wait.until(ExpectedConditions.textToBe())
            } catch (TimeoutException e) {
                //	logs.log(Status.INFO, "Element not visible yet & retrying again");
            }
        }
    }

    /**
     * Explicit Wait until elementToBbeClickable
     *
     * @param byLocator - object locator
     */

    public void WaitForElementToBeClickable(By byLocator) {
        try {
            wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            logs.log(Status.INFO, "Waited '" + timeOut + "' seconds till element '" + StoreInnerHTML(byLocator)
                    + "' is to be clickable in the page.");
        } catch (TimeoutException e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Explicit Wait until elementToBbeSelected
     *
     * @param byLocator - object locator
     */

    public void WaitForElementToBeSelected(By byLocator) {
        try {
            wait = new WebDriverWait(getDriver(), timeOut);

            wait.until(ExpectedConditions.elementToBeSelected(byLocator));
            logs.log(Status.INFO, "Waited '" + timeOut + "' seconds till element '" + StoreInnerHTML(byLocator)
                    + "' is to be selected in the page.");
        } catch (TimeoutException e) {
            //logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Explicit Wait until test to be loaded
     *
     * @param byLocator - object locator
     * @param Text      - the text to be waited
     */

    public void WaitTillTextToBeLoaded(By byLocator, String Text) {
        try {
            wait = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(byLocator, Text));
            logs.log(Status.INFO,
                    "Waited '" + timeOut + "' seconds till text '" + Text + " ' is loaded in the element '" + " '");
        } catch (TimeoutException e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Implicit Wait - Wait till maximum time exceed before throwing exception
     *
     * @param byLocator-> object locator
     * @param time        -> time to be waited
     */

    public void ImplicitWait(By byLocator, int time) {

        for (int i = 0; i < NumberOfRetries; i++) {
            try {
                getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
                Boolean elementStatus = getDriver().findElement(byLocator).isDisplayed();
                if (elementStatus) {
                    logs.log(Status.INFO, byLocator + " element is visible after an implicit wait of '" + time + "' seconds");
                    break;
                }
            } catch (NoSuchElementException e) {
            }
        }
        logs.log(Status.INFO, byLocator + " element is no visible even after an implicit wait of '" + time + "' seconds");
    }

    /**
     * Tread Sleep
     *
     * @param time the time
     */

    public void Sleep(int time) {
        try {
            Thread.sleep(time);
            logs.log(Status.INFO, "Waited '" + time + "' seconds till page or element is loaded.");
        } catch (InterruptedException e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Press keyboard keys
     *
     * @param KeyLabel -> name to the key to be pressed
     */

    public void PresKey(String KeyLabel, int NuOfTimes) {
        try {
            for (int i = 0; i < NuOfTimes; i++) {
                Robot _Robot = new Robot();
                switch (KeyLabel) {
                    case "Enter":
                        _Robot.keyPress(KeyEvent.VK_ENTER);
                        _Robot.keyRelease(KeyEvent.VK_ENTER);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;

                    case "Tab":
                        _Robot.keyPress(KeyEvent.VK_TAB);
                        _Robot.keyRelease(KeyEvent.VK_TAB);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;

                    case "PageUp":
                        _Robot.keyPress(KeyEvent.VK_PAGE_UP);
                        _Robot.keyRelease(KeyEvent.VK_PAGE_UP);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;

                    case "PageDown":
                        _Robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                        _Robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "Down":
                        _Robot.keyPress(KeyEvent.VK_DOWN);
                        _Robot.keyRelease(KeyEvent.VK_DOWN);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "Up":
                        _Robot.keyPress(KeyEvent.VK_UP);
                        _Robot.keyRelease(KeyEvent.VK_UP);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "backspace":
                        _Robot.keyPress(KeyEvent.VK_BACK_SPACE);
                        _Robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "A":
                        _Robot.keyPress(KeyEvent.VK_A);
                        _Robot.keyRelease(KeyEvent.VK_A);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "F":
                        _Robot.keyPress(KeyEvent.VK_F);
                        _Robot.keyRelease(KeyEvent.VK_F);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "E":
                        _Robot.keyPress(KeyEvent.VK_E);
                        _Robot.keyRelease(KeyEvent.VK_E);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "T":
                        _Robot.keyPress(KeyEvent.VK_T);
                        _Robot.keyRelease(KeyEvent.VK_T);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "R":
                        _Robot.keyPress(KeyEvent.VK_R);
                        _Robot.keyRelease(KeyEvent.VK_R);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "L":
                        _Robot.keyPress(KeyEvent.VK_L);
                        _Robot.keyRelease(KeyEvent.VK_L);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    case "P":
                        _Robot.keyPress(KeyEvent.VK_P);
                        _Robot.keyRelease(KeyEvent.VK_P);
                        logs.log(Status.INFO, "Press the key '" + KeyLabel + "' from the keyboard.");
                        break;
                    default:
                        logs.log(Status.ERROR, MarkupHelper
                                .createLabel("Undefined key '" + KeyLabel + " 'is entered by the user", ExtentColor.RED));
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Fail a test case
     *
     * @param message -> message for the failure
     */

    public void FailTest(String message) {
        Assert.fail(message);
    }

    /**
     * Fail a test case (Soft Accession, Fail the test, but Continue with the rest of steps
     *
     * @param message -> message for the failure
     */

    public void SoftFail(String message) {
        // Implementing soft assertions
        SoftAssert sAssert = new SoftAssert();
        sAssert.fail(message);
        sAssert.assertAll();
    }

    /**
     * Scroll Down
     */

    public void ScrollDown() {
        try {
            JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
            jsx.executeScript("window.scrollBy(0,800)", "");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Scroll Down up to bottom of the page
     */

    public void ScrollDownUpToBottom() {
        try {
            JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
            jsx.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Scroll Up
     */

    public void ScrollUp() {
        try {
            JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
            jsx.executeScript("", "window.scrollBy(0,450)");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Upload a file using Robot Class
     *
     * @param fileName -> name of the image to be uploaded & Image should be
     *                 located in resources folder
     */

    public void FileUpload(By byLocator, String fileName) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
            }
            WebElement element = getDriver().findElement(byLocator);
            element.click();
            String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\" + fileName;
            StringSelection sel = new StringSelection(filepath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
            // Create object of Robot class
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
            }
            assert robot != null;
            robot.delay(1000);
            // Press CTRL+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            // Release CTRL+V
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            logs.log(Status.INFO,
                    "Uploaded the file ' " + fileName + " ' for the element '" + byLocator + " ' element");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Get the font color of an element
     *
     * @param byLocator -> object locator
     */

    public String GetFontColor(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementColour = getDriver().findElement(byLocator).getCssValue("color");
            String hexColor = Color.fromString(elementColour).asHex();
            if (hexColor != null && !hexColor.isEmpty()) {
                logs.log(Status.INFO,
                        "Font colour of element '" + StoreInnerHTML(byLocator) + "' is '**" + hexColor + "**'");
            } else {
                logs.log(Status.INFO, "No Font Color is available for the '" + StoreInnerHTML(byLocator) + "'element");
            }
            return hexColor;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Get the background color of an element
     *
     * @param byLocator -> object locator
     */

    public String GetBackgroundColor(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            String elementBgColour = getDriver().findElement(byLocator).getCssValue("background-color");
            String hexBgColor = Color.fromString(elementBgColour).asHex();
            if (hexBgColor != null && !hexBgColor.isEmpty()) {
                logs.log(Status.INFO, "Background colour of element '**" + StoreInnerHTML(byLocator) + "**' is '**"
                        + hexBgColor + "**'");
            } else {
                logs.log(Status.INFO,
                        "No Background Color is available for the '" + StoreInnerHTML(byLocator) + " 'element");
            }
            return hexBgColor;
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return null;
    }

    /**
     * Get the count of matching result for a particular text from an item list
     *
     * @param byLocator - > object locator
     * @param FindText  - > Text name to be searched or found
     */

    public int FindMatchCount(By byLocator, String FindText) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
            }
            List<WebElement> elementNames = getDriver().findElements(byLocator);
            int itemCount = elementNames.size();
            if (itemCount > 0) {
                int matchingCount = 0;
                for (int i = 0; i < itemCount; i++) {
                    String productName = elementNames.get(i).getText();
                    if (productName.equals(FindText)) {
                        matchingCount++;
                    }
                }
                if (matchingCount == 0) {
                    logs.log(Status.INFO, "No matching item found for the '**" + FindText + "**' text in the '**"
                            + StoreInnerHTML(byLocator) + "**' element. Total item count is '**" + itemCount + "'.");
                } else {
                    logs.log(Status.INFO,
                            "'**" + matchingCount + "**' out of '**" + itemCount
                                    + "**' matching items found for the '**" + FindText + "**' text in the '**"
                                    + StoreInnerHTML(byLocator) + "**' element.");
                }
                return matchingCount;
            } else {
                logs.log(Status.INFO,
                        "No matching records found for the element '**" + StoreInnerHTML(byLocator) + "**'");
                return 0;
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
        return 0;
    }

    /**
     * Verify the matching records for a particular search
     *
     * @param byLocator the by locator
     * @param FindText  - > Text name to be searched or found
     */

    public void VerifyMatchText(By byLocator, String FindText) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            List<WebElement> elementNames = getDriver().findElements(byLocator);
            int itemCount = elementNames.size();
            if (itemCount > 0) {
                int UnmatchedCount = 0;
                for (int i = 0; i < itemCount; i++) {
                    String itemName = elementNames.get(i).getText();
                    if (!itemName.equals(FindText)) {
                        UnmatchedCount++;
                        WriteFailMessageToReport("Incorrect record found. element '**" + itemName + "**' not matched with '**"
                                + FindText + "**' search item ");
                    }
                }
                if (UnmatchedCount > 0) {
                    FailTest("Incorrect matching records");
                } else {
                    logs.log(Status.INFO, "No mismatch found after searching for '**" + FindText + "**'");
                }
            } else {
                logs.log(Status.INFO,
                        "No matching records found for the element '**" + StoreInnerHTML(byLocator) + "**'");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Select All check boxes for a particular item
     *
     * @param byLocator -> object locator
     */

    public void SelectAllCheckBoxes(By byLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            List<WebElement> allCheckBoxes = driver.findElements(byLocator);
            for (WebElement element : allCheckBoxes) {
                if (!element.isSelected()) {
                    element.click();
                }
            }
            logs.log(Status.INFO,
                    "Selected all the checkboxes matched to element '**" + StoreInnerHTML(byLocator) + "**'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * ---- need to fix - not working Select multiple check boxes for a particular
     * item
     *
     * @param byLocator -> object locator
     */

    public void SelectCheckBox(By byLocator, String checkboxes) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            // int temp=0;
            String[] checkBoxesToBeClicked = checkboxes.split(",");
            List<WebElement> allCheckBoxes = driver.findElements(byLocator);
            System.out.println(allCheckBoxes.size());
            for (int i = 0; i < checkBoxesToBeClicked.length; i++) {
                for (int j = 0; j < allCheckBoxes.size(); j++) {
                    if (checkBoxesToBeClicked[i].contains(allCheckBoxes.get(j).getText())) {
                        WriteToReport("Selected check box ' " + allCheckBoxes.get(j).getText() + " '");
                        System.out.println(checkBoxesToBeClicked[i]);
                        System.out.println(allCheckBoxes.get(j).getText());
                        allCheckBoxes.remove(allCheckBoxes.get(j));
                        WriteToReport("list size is " + allCheckBoxes.size());
                    }
                    /*
                     * if(!checkBoxesToBeClicked[i].contains(checkboxes.get(j).getText())) {
                     * WriteFailMessageToReport("Check box"); }
                     */
                }
                /*
                 * for (WebElement element : checkboxes) {
                 * if(Arrays.asList(checkBoxesToBeClicked).contains(element.getText())) {
                 *
                 * if (!element.isSelected()) { // checkboxes.get(j).click();
                 * WriteToReport("Selected check box ' " + element.getText() + " '"); break; //
                 * } }
                 *
                 *
                 * //if(element.getText().equals(Arrays.toString(checkBoxesToBeClicked)))
                 *
                 *
                 * } else { WriteFailMessageToReport("Check box ' " + element.getText() +
                 * " ' not found, but found '" + checkBoxesToBeClicked[i]); }
                 *
                 * } }
                 */
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * compare data set of tow string arrays
     *
     * @param Expected -> Array of the Expected data set
     * @param Actual   -> Array of the Actual data set
     */

    public void CompareTowArray(String[] Expected, String[] Actual) {
        try {
            for (int i = 0; i < Expected.length; i++) {
                if (!Expected[i].equals(Actual[i])) {
                    FailTest("Mismatch found. Expected '**" + Expected[i] + "**' But found '**" + Actual[i] + "**'");
                } else {
                    logs.log(Status.INFO, "No mismatch found comparing two data sets. Expected ' " + Expected[i]
                            + " '. Actual ' " + Actual[i] + " '");
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Click on a searched or a selected element
     *
     * @param byLocator    -> object locator
     * @param SelectedItem -> selected or searched item name
     */

    public void ClickOnSelectedItem(By byLocator, String SelectedItem) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, byLocator + " element is not visible");
            }
            List<WebElement> elementNames = getDriver().findElements(byLocator);
            for (WebElement element : elementNames) {
                if (element.isDisplayed() && element.getText().equals(SelectedItem)) {
                    element.click();
                    logs.log(Status.INFO, "Clicked on the element '**" + byLocator + "**' matching with **'"
                            + SelectedItem + "**' text");
                    break;
                }
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Verify set of similar elements exist in the web page
     *
     * @param byLocator -> The object locator
     * @param elements  -> Expected list of elements to be verified
     */

    public void CheckAllElements(By byLocator, String elements) {
        try {
            wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        } catch (TimeoutException e) {
            logs.log(Status.INFO, byLocator + " element is not visible");
        }
        try {
            String[] expectedElements = elements.split(",");
            if (expectedElements.length > 0) {
                ArrayList<String> listExpectedElements = new ArrayList<String>();
                listExpectedElements.addAll(Arrays.asList(expectedElements));
                List<WebElement> elementNames = getDriver().findElements(byLocator);
                if (elementNames.size() == listExpectedElements.size()) {
                    WriteToReport("All the elements ( " + elementNames.size() + "  ) are available in object' "
                            + byLocator + "' as expected");
                    for (int i = 0; i < elementNames.size(); i++) {
                        if (expectedElements[i].equals(elementNames.get(i).getText())) {
                            WriteToReport("Element ' " + elementNames.get(i).getText() + " ' is available.");
                        } else {
                            logs.log(Status.ERROR, MarkupHelper.createLabel("Expected ' " + expectedElements[i]
                                    + " ' but found ' " + elementNames.get(i).getText() + " '.", ExtentColor.RED));
                            FailTest(
                                    "Mismatch found in elements names.Element order may be wrong or incorrect labeling");
                        }
                    }
                } else if (elementNames.size() > listExpectedElements.size()) {
                    for (int i = 0; i < listExpectedElements.size(); i++) {
                        for (int j = 0; j < elementNames.size(); j++) {
                            if (listExpectedElements.get(i).equals(elementNames.get(j).getText())) {
                                WriteToReport("Element '" + elementNames.get(j).getText() + "' is available in the.");
                                elementNames.remove(elementNames.get(j));
                            } else {
                                WriteFailMessageToReport("Element ' " + expectedElements[i] + " ' not available");
                                WriteFailMessageToReport(
                                        "Additional Element ' " + elementNames.get(j).getText() + " ' is available.");
                            }
                            if (elementNames.size() > 0) {
                                WriteFailMessageToReport(
                                        "Additional element ' " + elementNames.get(j).getText() + " ' is available");
                            }
                        }
                    }
                    FailTest("Additional elements are available " + byLocator + ".");

                } else {
                    for (int i = 0; i < listExpectedElements.size(); i++) {
                        for (int j = 0; j < elementNames.size(); j++) {
                            if (listExpectedElements.get(i).equals(elementNames.get(j).getText())) {
                                WriteToReport("Element '" + listExpectedElements.get(i) + "' is available.");
                                listExpectedElements.remove(listExpectedElements.get(i));
                            } else {
                                WriteFailMessageToReport(
                                        "Element ' " + listExpectedElements.get(i) + " ' not available.");
                            }
                        }
                    }
                    if (listExpectedElements.size() > 0) {
                        FailTest("Missing Elements in the." + byLocator + ".");
                    }
                }
            } else {
                logs.log(Status.SKIP,
                        "At least you need to enter one name. Hence please enter more one element in expected element list");
            }
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Accepting Alerts
     */

    public void AcceptAlert() {
        try {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.alertIsPresent());
            } catch (TimeoutException e) {
                logs.log(Status.INFO, " element is not visible");
            }
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
            logs.log(Status.INFO, "Clicked OK from the alert ' " + alert.getText() + " '");
        } catch (NoAlertPresentException e) {
        }
    }

    /**
     * Write something on the report.
     *
     * @param message -> the message to be written
     */

    public void WriteToReport(String message) {
        try {
            logs.log(Status.INFO, message);
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Write failure message to report in red color
     *
     * @param message - the message to be written
     */

    public void WritePassMessageToReport(String message) {
        try {
            logs.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.GREEN));
        } catch (Exception e) {
        }
    }

    /**
     * Write failure message to report in red color
     *
     * @param message - the message to be written
     */

    public void WriteFailMessageToReport(String message) {
        try {
            logs.log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.RED));
        } catch (Exception e) {
        }
    }

    /**
     * Skip the test
     *
     * @param message - the message to be written
     */

    public void SkipTest(String message) {
        try {
            throw new SkipException("Skipping this exception");

        } catch (Exception e) {
            logs.log(Status.SKIP, MarkupHelper.createLabel(e.getMessage() + message, ExtentColor.AMBER));
        }
    }

    /**
     * Switch to iframe
     *
     * @param indexNumber - index number of the frame
     */

    public void SwitchToFrame(int indexNumber) {
        try {
            getDriver().switchTo().frame(indexNumber);
            logs.log(Status.INFO, "Moved to frame ' " + indexNumber + " '");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Switch to iframe by id or name
     *
     * @param idOrName - index number of the frame
     */

    public void SwitchToFrameByIdOrName(String idOrName) {
        try {
            getDriver().switchTo().frame(idOrName);
            logs.log(Status.INFO, "Moved to frame ' " + idOrName + " '");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Switch back to main frame
     */

    public void SwitchBackToMainFrame() {
        try {
            getDriver().switchTo().defaultContent();
            logs.log(Status.INFO, "Switched back to main frame");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Open a new tab in browser	 *
     */

    public void OpenANewTabAndSwitch() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.open()");
            logs.log(Status.INFO, "Open a new blank tab in a browser");
            //Switch to newly opened browser
            SwitchToChildWindow();
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Switched to child window
     */

    public void SwitchToChildWindow() {
        try {
            // return the parent window name as a String
            String parent = getDriver().getWindowHandle();
            // return all the chile windows
            Set<String> childWindows = getDriver().getWindowHandles();
            Iterator<String> I1 = childWindows.iterator();
            while (I1.hasNext()) {
                String child_window = I1.next();
                if (!parent.equals(child_window)) {
                    getDriver().switchTo().window(child_window);
                }
            }
            logs.log(Status.INFO, "Switched to child window");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Switched to parent window
     */

    public void SwitchToParentWindow() {
        try {
            // return the parent window name as a String
            String parent = getDriver().getWindowHandle();
            getDriver().switchTo().window(parent);
            logs.log(Status.INFO, "Switched to parent window");

        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Update a data base column
     *
     * @param DatabaseName -> database name to be updated
     * @param updateQuery  -> Query to be run
     */

    public void UpdateATableColumn(String DatabaseName, String updateQuery) throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:postgresql://" + DatabaseIp + "/" + DatabaseName;
        String username = UsernameDB;
        String password = PasswordDB;
        logs.log(Status.INFO, "Connected to PostgresSQL database!");
        //Load postgres sql JDBC Driver
        Class.forName("org.postgresql.Driver");
        //Creating connection to the database
        Connection con = DriverManager.getConnection(dbURL, username, password);
        logs.log(Status.INFO, "Opened database successfully");
        try {
            PreparedStatement prepaid = con.prepareStatement(updateQuery);
            prepaid.execute();
            logs.log(Status.INFO, "Run the query " + updateQuery + " in database " + DatabaseName);
        } catch (SQLException e) {
            logs.log(Status.ERROR, e.getMessage());
        }
        //Closing DB Connection
        con.close();
    }

    /**
     * Store a text or a message to a variable
     *
     * @param DatabaseName -> database name to be updated
     */

    public String ReadADatabaseColumn(String DatabaseName, String columnName, String selectQuery) throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:postgresql://" + DatabaseIp + "/" + DatabaseName;
        String username = UsernameDB;
        String password = PasswordDB;
        logs.log(Status.INFO, "Connected to PostgresSQL database!");
        //Load postgres sql JDBC Driver
        Class.forName("org.postgresql.Driver");
        //Creating connection to the database
        Connection con = DriverManager.getConnection(dbURL, username, password);
        logs.log(Status.INFO, "Opened database successfully");
        //Creating statement object
        Statement st = con.createStatement();
        //Executing the SQL Query and store the results in ResultSet
        ResultSet rs = st.executeQuery(selectQuery);
        while (rs.next()) {
            //Access column name of the result set
            String getColumnValue = rs.getString(columnName);
            logs.log(Status.INFO, "DB value is " + rs.getString(columnName) + "of " + columnName);
            return getColumnValue;
        }
        //Closing DB Connection
        con.close();
        return null;
    }

    /**
     * Drag and drop an element
     *
     * @param fromLocator -> object locator of the element to be dragged
     * @param toLocator   -> object locator of the place to be dragged
     */

    public void DragAndDropAnElement(By fromLocator, By toLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(fromLocator),
                        ExpectedConditions.elementToBeClickable(toLocator)));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, fromLocator + " element is not visible");
            }
            //Source element
            WebElement sourceElement = driver.findElement(fromLocator);
            //Target element
            WebElement targetElement = driver.findElement(toLocator);
            Actions actionProvider = new Actions(driver);
            actionProvider.dragAndDrop(sourceElement, targetElement).build().perform();
            logs.log(Status.INFO, "Drag and drop was done from '" + fromLocator + "' to '" + "'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Drag and drop an element using Click And Hold method
     *
     * @param fromLocator -> object locator of the element to be dragged
     * @param toLocator   -> object locator of the place to be dragged
     */

    public void DragAndDropUsingClickAndHold(By fromLocator, By toLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(fromLocator),
                        ExpectedConditions.elementToBeClickable(toLocator)));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, fromLocator + " element is not visible");
            }
            //Source element
            WebElement sourceElement = driver.findElement(fromLocator);
            //Target element
            WebElement targetElement = driver.findElement(toLocator);
            Actions actionProvider = new Actions(driver);
            actionProvider.clickAndHold(sourceElement).moveToElement(targetElement).build().perform();
            // Performs release event
            actionProvider.release().build().perform();
            logs.log(Status.INFO, "Drag and drop was done from '" + fromLocator + "' to '" + "'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

    /**
     * Drag and drop an element
     *
     * @param fromLocator -> object locator of the element to be dragged
     * @param toLocator   -> object locator of the place to be dragged
     */

    public void DragAndDropUsingJavaScriptExecutor(By fromLocator, By toLocator) {
        try {
            try {
                wait = new WebDriverWait(getDriver(), timeOut);
                wait.until(ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(fromLocator),
                        ExpectedConditions.elementToBeClickable(toLocator)));
            } catch (TimeoutException e) {
                logs.log(Status.INFO, fromLocator + " element is not visible");
            }
            //Source element
            WebElement sourceElement = driver.findElement(fromLocator);
            //Target element
            WebElement targetElement = driver.findElement(toLocator);
            Actions actionProvider = new Actions(driver);
            actionProvider.dragAndDrop(sourceElement, targetElement).build().perform();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                    + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                    + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                    + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                    + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                    + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                    + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                    + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                    + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                    + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                    + "var dropEvent = createEvent('drop');\n"
                    + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                    + "var dragEndEvent = createEvent('dragend');\n"
                    + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                    + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                    + "simulateHTML5DragAndDrop(source,destination);", sourceElement, targetElement);
            logs.log(Status.INFO, "Drag and drop was done from '" + fromLocator + "' to '" + "'");
        } catch (Exception e) {
            logs.log(Status.ERROR, MarkupHelper.createLabel(e.getMessage(), ExtentColor.RED));
        }
    }

}
