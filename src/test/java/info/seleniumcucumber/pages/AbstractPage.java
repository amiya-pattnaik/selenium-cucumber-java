package info.seleniumcucumber.pages;

import info.seleniumcucumber.utils.BaseTest;
import info.seleniumcucumber.utils.DriverManager;
import info.seleniumcucumber.utils.DriverWait;
import org.openqa.selenium.WebDriver;
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

    protected AbstractPage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverManager.getDriver();
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


}
