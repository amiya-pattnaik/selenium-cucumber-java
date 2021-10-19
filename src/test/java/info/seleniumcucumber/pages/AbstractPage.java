package info.seleniumcucumber.pages;

import info.seleniumcucumber.utils.BaseTest;
import info.seleniumcucumber.utils.DriverManager;
import info.seleniumcucumber.utils.DriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import info.seleniumcucumber.pages.APHomePage;

public abstract class AbstractPage implements BaseTest {

    private final DriverManager driverManager = new DriverManager();
    private final DriverWait driverWait = new DriverWait(driverManager);
    private APHomePage homePage;
    private APMyAccountPage myAccountPage;
    private APSignInPage signInPage;

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

}
