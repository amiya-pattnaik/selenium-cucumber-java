package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class APHomePage extends AbstractPage{

	private String pageTitle = "My Store";
	
	@FindBy(how = How.XPATH, using = "//a[normalize-space() = 'Sign in']")
	private WebElement signIn;
	
	public void navigateTo_ApHomePage()
	{
		navigationObj.navigateTo(configFileReader.getAPApplicationUrl());
	}
	
	public void verifyAPHomePageTitle() throws TestCaseFailed
	{
		
		assertionObj.checkTitle(pageTitle,true);
	}
	
	public void navigateTo_LoginPage() throws NoSuchFieldException
	{

		clickObj.click(signIn);
	}

}
