package info.seleniumcucumber.steps;

import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.*;
import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.pageAndActionObjects.APTshirtsSectionPage;

public class APTshirtsSectionPageSteps extends AbstractPage {
	APTshirtsSectionPage tshirtsSectionPage;
	
	public APTshirtsSectionPageSteps()
	{
		tshirtsSectionPage = getAPTshirtsSectionPage();
	}
	
	@Then("^I should see the tshirts section$")
	public void i_should_see_the_tshirts_section() throws TestCaseFailed {
	    tshirtsSectionPage.verifyTshirtsPageTitle();
	}
	
	@When("^I choose medium size$")
	public void i_choose_medium_size() throws NoSuchFieldException {
		tshirtsSectionPage.chooseMediumSize();
	}
	
	@When("^I add the faded short sleeve tshirt to the cart$")
	public void i_add_the_faded_short_sleeve_tshirt_to_the_cart() throws NoSuchFieldException {
		
		tshirtsSectionPage.addFadedTshirtToCart();
	    
	}
	
	@When("^I choose proceed to checkout on tshirts selection page$")
	public void i_choose_proceed_to_checkout_on_tshirts_selection_page() throws NoSuchFieldException {
	    tshirtsSectionPage.selectProceedToCheckout();
	}
	

}
