package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
//import org.testng.Assert;

public class APOrderConfirmationPage extends AbstractPage {

	public String pageTitle = "Order confirmation - My Store 1";
	
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'$18.51')]")
	private WebElement btnPaymentAmount;

	public void verifyOrderConfirmationPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
	}
	
	public String getPaymentAmount() throws NoSuchFieldException
	{
		return assertionObj.getElementText(btnPaymentAmount);
	}
	
	public void verifyPaymentAmount() throws NoSuchFieldException
	{
		Assert.assertEquals(getPaymentAmount(),"$18.51");
	}
}
