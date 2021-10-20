package info.seleniumcucumber.steps;

import info.seleniumcucumber.pages.APHomePage;
import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class NavigationSteps extends AbstractPage {

      @Given("^I want to go to saucedemo website$")
      public void i_want_to_go_to_automationpractice_website() {
          navigationObj.navigateTo(configFileReader.getBaseUrl());
      }

}
