package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.pageAndActionObjects.APIs.RegisterUserActions;
import info.seleniumcucumber.utils.BaseTest;
import info.seleniumcucumber.utils.DriverManager;
import info.seleniumcucumber.utils.DriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage implements BaseTest {

    private final DriverManager driverManager = new DriverManager();
    private final DriverWait driverWait = new DriverWait(driverManager);
    private APHomePage homePage;
    private APMyAccountPage myAccountPage;
    private APSignInPage signInPage;
    private APOrderConfirmationPage orderConfirmationPage;
    private APOrderPage orderPage;
    private APTshirtsSectionPage tShirtsSectionPage;
    private Saucedemo_SignInPage saucedemo_SignInPage;
    private RegisterUserActions registerUserActions;
    private Weather weather;

    protected AbstractPage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public DevTools getDevTools() {
        return driverManager.getDevTools();
    }

    public DriverWait getDriverWait() {
        return driverWait;
    }

    public void wait(String time) throws InterruptedException {
        Thread.sleep(Integer.parseInt(time));
    }

    public APHomePage getAPHomePage(){
        return (homePage == null) ? homePage = new APHomePage() : homePage;
    }

    public APMyAccountPage getAPMyAccountPage(){
        return (myAccountPage == null) ? myAccountPage = new APMyAccountPage() : myAccountPage;
    }

    public APSignInPage getAPSignInPage(){
        return (signInPage == null) ? signInPage = new APSignInPage() : signInPage;
    }

    public APTshirtsSectionPage getAPTshirtsSectionPage(){
        return (tShirtsSectionPage == null) ? tShirtsSectionPage = new APTshirtsSectionPage() : tShirtsSectionPage;
    }
    public APOrderConfirmationPage getAPOrderConfirmationPage(){
        return (orderConfirmationPage == null) ? orderConfirmationPage = new APOrderConfirmationPage() : orderConfirmationPage;
    }

    public APOrderPage getAPOrderPage(){
        return (orderPage == null) ? orderPage = new APOrderPage() : orderPage;
    }

    public Saucedemo_SignInPage getSaucedemo_SignInPage(){
        return (saucedemo_SignInPage == null) ? saucedemo_SignInPage = new Saucedemo_SignInPage() : saucedemo_SignInPage;
    }

    public RegisterUserActions getRegisterUserActions(){
        return (registerUserActions == null) ? registerUserActions = new RegisterUserActions() : registerUserActions;
    }

    public Weather getWeather(){
        return (weather == null) ? weather = new Weather() : weather;
    }


}
