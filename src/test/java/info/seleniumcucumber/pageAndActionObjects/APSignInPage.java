package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.utils.TestCaseFailed;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import info.seleniumcucumber.testDataTypes.Customer;

public class APSignInPage extends AbstractPage{

	private String pageTitle = "Login - My Store";
	
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	private WebElement txtEmail;
	
	@FindBy(how = How.XPATH, using = "//input[@id='passwd']")
	private WebElement txtPassword;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space() = 'Sign in']")
	private WebElement btnSignIn;

	public void verifySignInPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
	}
	
	public void signInOperation(Customer customer) throws NoSuchFieldException
	{
		inputObj.enterText(txtEmail, customer.emailAddress);
		inputObj.enterText(txtPassword, customer.password);
		clickObj.click(btnSignIn);
	}

}
