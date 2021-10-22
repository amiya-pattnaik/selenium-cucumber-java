package info.seleniumcucumber.steps;

import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.*;
import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.pageAndActionObjects.APOrderPage;

public class APOrderPageSteps extends AbstractPage {
	
	APOrderPage orderPage;
	
	public APOrderPageSteps()
	{
		orderPage = getAPOrderPage();
	}
	
	@Then("^I should see my order page$")
	public void i_should_see_my_order_page() throws TestCaseFailed {
		
		orderPage.verifyOrderPageTitle();
	}
	
	@When("^I choose proceed to checkout on order page$")
	public void i_choose_proceed_to_checkout_on_order_page() throws NoSuchFieldException {
		
		orderPage.selectProceedToCheckOut();
		orderPage.selectProceedToCheckOut();
		orderPage.agreeTermsAndConditions();
		orderPage.selectProceedToCheckOut();
	    
	}
	
	@When("^I choose the payment method cheque$")
	public void i_choose_the_payment_method_cheque() throws NoSuchFieldException {
	    orderPage.selectPayByCheckMethod();
	}
	
	@When("^I confirm my order$")
	public void i_confirm_my_order() throws NoSuchFieldException {
	    orderPage.selectConfirmMyOrder();
	}

}
