package info.seleniumcucumber.pages;

import info.seleniumcucumber.testDataTypes.UserLogin;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Saucedemo_SignInPage extends AbstractPage{

	private String pageTitle = "Login - My Store";
	
	@FindBy(how = How.ID, using = "user-name")
	private WebElement txtUserName;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement txtPassword;
	
	@FindBy(how = How.ID, using = "login-button")
	private WebElement btnSignIn;

	public void signIn(UserLogin userLogin) throws NoSuchFieldException
	{
		inputObj.enterText(txtUserName, userLogin.username);
		inputObj.enterText(txtPassword, userLogin.password);
		clickObj.click(btnSignIn);
	}

}
