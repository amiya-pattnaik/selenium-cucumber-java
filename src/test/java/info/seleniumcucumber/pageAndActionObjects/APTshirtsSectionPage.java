package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.utils.TestCaseFailed;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class APTshirtsSectionPage extends AbstractPage {

//	public String tshirtMediumSize = "//a[contains(text(),'M')]";
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'M')]")
	private WebElement lnkTShirtMediumSize;

	public String pageTitle = "T-shirts - My Store";
	
//	public String mouseoverFadedTshirt = "//a/img[@title='Faded Short Sleeve T-shirts']";
	@FindBy(how = How.XPATH, using = "//a/img[@title='Faded Short Sleeve T-shirts']")
	private WebElement imgMouseoverFadedTShirt;
	
//	public String addToCart = "//span[contains(text(),'Add to cart')]";
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add to cart')]")
	private WebElement btnAddToCart;
	
//	public String proceedToCheckout = "//*[@id='layer_cart']//span[contains(text(),'Proceed to checkout')]";
	@FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//span[contains(text(),'Proceed to checkout')]")
	private WebElement btnProceedToCheckout;
	
	public void verifyTshirtsPageTitle() throws TestCaseFailed
	{
		assertionObj.checkTitle(pageTitle, true);
	}
	
	public void chooseMediumSize() throws NoSuchFieldException
	{
		clickObj.click(lnkTShirtMediumSize);
	}
	
	public void addFadedTshirtToCart() throws NoSuchFieldException
	{
		navigationObj.hoverOverElement(imgMouseoverFadedTShirt);
		clickObj.click(btnAddToCart);
	}
	
	public void selectProceedToCheckout() throws NoSuchFieldException
	{
		clickObj.click(btnProceedToCheckout);
	}

}
