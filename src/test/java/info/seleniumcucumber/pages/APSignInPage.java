package info.seleniumcucumber.pages;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
//import selenium.Helper;
import info.seleniumcucumber.testDataTypes.Customer;

public class APSignInPage extends AbstractPage{

	private String pageTitle = "Login - My Store";
	
//	public String email = "//input[@id='email']";
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	private WebElement txtEmail;
	
//	public String password = "//input[@id='passwd']";
	@FindBy(how = How.XPATH, using = "//input[@id='passwd']")
	private WebElement txtPassword;
	
//	public String signIn = "//span[normalize-space() = 'Sign in']";
	@FindBy(how = How.XPATH, using = "//span[normalize-space() = 'Sign in']")
	private WebElement btnSignIn;

	public void verifySignInPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
//		Helper.verifyTitleContains(driver, pageTitle);
	}
	
	public void signInOperation(Customer customer) throws NoSuchFieldException
	{
//		baseClass.setTextByXpath(email, customer.emailAddress);
//		baseClass.setTextByXpath(password, customer.password);
//		baseClass.clickByXpath(signIn);
		inputObj.enterText(txtEmail, customer.emailAddress);
		inputObj.enterText(txtPassword, customer.password);
		clickObj.click(btnSignIn);
	}

}
