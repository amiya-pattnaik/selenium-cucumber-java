package info.seleniumcucumber.steps;

import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.*;
import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.pages.APHomePage;

public class APHomePageSteps extends AbstractPage {

  APHomePage homePage;

  public APHomePageSteps() {

    homePage = getAPHomePage();
  }
      @Given("^I want to go to automationpractice website$")
      public void i_want_to_go_to_automationpractice_website() {

          homePage.navigateTo_ApHomePage();

      }

      @Given("^verify the mystore title$")
      public void verify_the_mystore_title() throws TestCaseFailed {

          homePage.verifyAPHomePageTitle();
      }

      @When("^I click on sign in$")
      public void i_click_on_sign_in() throws NoSuchFieldException {

          homePage.navigateTo_LoginPage();

      }

}
