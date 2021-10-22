package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class APOrderPage extends AbstractPage {

	private String pageTitle = "Order - My Store";
	
	@FindBy(how = How.XPATH, using = "//*[@id='center_column']//span[contains(text(),'Proceed to checkout')]")
	private WebElement btnProceedToCheckout;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'I agree to the terms of service and will adhere to them unconditionally.')]")
	private WebElement lblReadTheTerms;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Pay by check ')]")
	private WebElement lnkPayByCheck;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'I confirm my order')]")
	private WebElement btnConfirmOrder;

	public void verifyOrderPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
	}
	
	public void selectProceedToCheckOut() throws NoSuchFieldException
	{
		clickObj.click(btnProceedToCheckout);
	}
	
	public void agreeTermsAndConditions() throws NoSuchFieldException
	{
		clickObj.click(lblReadTheTerms);
	}
	
	public void selectPayByCheckMethod() throws NoSuchFieldException
	{
		clickObj.click(lnkPayByCheck);
	}
	
	public void selectConfirmMyOrder() throws NoSuchFieldException
	{
		clickObj.click(btnConfirmOrder);
	}

}
