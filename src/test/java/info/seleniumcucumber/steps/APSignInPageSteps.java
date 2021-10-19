package info.seleniumcucumber.steps;

import io.cucumber.java.en.*;
import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.utils.TestCaseFailed;
import info.seleniumcucumber.pages.APSignInPage;
import info.seleniumcucumber.testDataTypes.Customer;

public class APSignInPageSteps extends AbstractPage {
	APSignInPage signInPage;
	
	public APSignInPageSteps() {
		signInPage = getAPSignInPage();
	}
	
	@Then("^I should see the login page$")
	public void i_should_see_the_login_page() throws TestCaseFailed {
		
		signInPage.verifySignInPageTitle();
	    
	}
	
	@When("^I enter \"([^\"]*)\" login credentials$")
	public void i_enter_login_credentials(String customerName) throws NoSuchFieldException {
//		Customer customer = FileReaderManager.getInstance().getJsonReader().getCustomerByName(customerName);
		Customer customer = jsonDataReader.getCustomerByName(customerName);
		signInPage.signInOperation(customer);
	}

}
