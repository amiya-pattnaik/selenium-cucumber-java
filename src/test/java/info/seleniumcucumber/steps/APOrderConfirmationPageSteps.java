package info.seleniumcucumber.steps;

import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.*;
import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.pageAndActionObjects.APOrderConfirmationPage;

public class APOrderConfirmationPageSteps extends AbstractPage {
	
	APOrderConfirmationPage orderConfirmationPage;
	
	public APOrderConfirmationPageSteps()
	{
		orderConfirmationPage = getAPOrderConfirmationPage();
	}
	
	@Then("^I should see order confirmation page$")
	public void i_should_see_order_confirmation_page() throws TestCaseFailed {
	    orderConfirmationPage.verifyOrderConfirmationPageTitle();
	}
	
	@Then("^validate the payment amount$")
	public void validate_the_payment_amount() throws NoSuchFieldException {
		orderConfirmationPage.verifyPaymentAmount();
	    
	}
	

}
