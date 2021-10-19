package info.seleniumcucumber.pages;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class APMyAccountPage extends AbstractPage {

	private String pageTitle = "My account - My Store";
	
	@FindBy(how = How.XPATH, using = "//*[@id='block_top_menu']//a")
	private WebElement women_link;

	@FindBy(how = How.XPATH, using = "//*[@id='block_top_menu']//a[@title='T-shirts']")
	private WebElement tShirts_link;
	
	public void verifyMyAccountPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
	}
	
	public void navigateTo_TshirtsPage() throws NoSuchFieldException
	{
		navigationObj.hoverOverElement(women_link);
		clickObj.click(tShirts_link);
	}

}