package info.seleniumcucumber.steps;

import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.pages.LoginPage;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class LoginSteps extends AbstractPage {

    @Given("^I should get logged-in$")
    public void should_logged_in() throws NoSuchFieldException {
        final LoginPage loginPage = new LoginPage();
        Assert.assertEquals("You logged into a secure area!", loginPage.getMessage().getText().split("\n")[0].trim());
    }
}
