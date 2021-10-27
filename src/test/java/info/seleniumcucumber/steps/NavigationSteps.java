package info.seleniumcucumber.steps;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import io.cucumber.java.en.Given;

public class NavigationSteps extends AbstractPage {

      @Given("^I want to go to saucedemo website$")
      public void i_want_to_go_to_automationpractice_website() {
          navigationObj.navigateTo(configFileReader.getBaseUrl());
      }

      @Given("^I want to go to weather website$")
      public void i_want_to_go_to_weather_website() {
          navigationObj.navigateTo(configFileReader.getBaseUrl3());
      }

}
