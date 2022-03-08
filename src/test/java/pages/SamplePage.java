package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utility.TestBase_Commands;

public class SamplePage extends TestBase_Commands {

   /*=============== Object identification for native apps (Android) ====================*/

    private final By lnk_English = By.xpath("//android.widget.TextView[@text='English']");
    private final By lbl_ChooseYourLanguage = By.xpath("//android.widget.TextView[@text='Choose your language']");
    private final By tf_TokenNumber = By.xpath("//android.view.ViewGroup[@index='1']/android.widget.EditText");
    private final By btn_Submit = By.xpath("//android.view.ViewGroup[@index='2']/android.widget.TextView");

    /*=============== Object identification for web apps (chrome) ====================*/
    private final By tf_SearchArea = By.xpath("//input[@id='nav-search-keywords']");
    private final By btn_Search = By.xpath("//input[@type='submit']");

    /*=============== Object identification for MJF ====================*/
    private final By lnk_EnglishLanguage = By.xpath("//button[text()='English']");
    private final By lnk_SinhalaLanguage = By.xpath("//button[text()='සිංහල']");
    private final By lnk_TamilLanguage = By.xpath("//button[text()='தமிழ்']");
    private final By tf_EnterMobileNumber = By.xpath("//input[@name='token']");
    private final By btn_Send = By.xpath("//input[@name='token']/following::button[1]");
    private final By btn_ChangeLanguage = By.xpath("//input[@name='token']/following::button[2]");

    public SamplePage(AppiumDriver driver) {
        this.driver = driver;
    }

    // Open the native app
    public void bf_OpenANativeApp() {

        WriteToReport("=======Start of bf_OpenANativeApp=============");

        //Click English button
        Click(lnk_English);
        WaitForElementPresent(tf_TokenNumber);
        //Type token
        Type(tf_TokenNumber,"mjfcp");
        //Click Submit
        Click(btn_Submit);

        WriteToReport("=======End of bf_OpenANativeApp=============");
    }

    // Open the web app
    public void bf_OpenAWebApp() {

        WriteToReport("=======Start of bf_OpenAWebApp=============");

       //Open the application
        Open("https://www.amazon.com");
        //Type a search word
        Type(tf_SearchArea,"Harry porter gifts");
        //Click Search button
        Click(btn_Search);

        WriteToReport("=======End of bf_OpenAWebApp=============");
    }

    // Open the MJF application
    public void bf_OpenMJF() {

        WriteToReport("=======Start of bf_OpenMJF=============");

        //Open the application
        Open("https://mjf-uat-healthvision.azurewebsites.net/login?campaign=mjfcp");
        //Click on English Language
        Click(lnk_EnglishLanguage);
        //Enter a mobile number
        Type(tf_EnterMobileNumber,"94766145268");
        //Click Send button
        Click(btn_Send);
        //Click Change Language
        Click(btn_ChangeLanguage);
        //Click on Sinhala language
        Click(lnk_SinhalaLanguage);
        //Enter a mobile number
        Type(tf_EnterMobileNumber,"94766145268");
        //Click Send button
        Click(btn_Send);
        //Click Change Language
        Click(btn_ChangeLanguage);
        //Click on Sinhala language
        Click(lnk_TamilLanguage);
        //Enter a mobile number
        Type(tf_EnterMobileNumber,"94766145268");
        //Click Send button
        Click(btn_Send);
        //Click Change Language
        Click(btn_ChangeLanguage);

        WriteToReport("=======End of bf_OpenAWebApp=============");
    }

}
