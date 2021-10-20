package info.seleniumcucumber.steps;

import info.seleniumcucumber.pages.AbstractPage;
import info.seleniumcucumber.pages.Saucedemo_SignInPage;
import info.seleniumcucumber.testDataTypes.UserLogin;
import io.cucumber.java.en.*;

public class SaucedemoLoginSteps extends AbstractPage {
    Saucedemo_SignInPage saucedemo_SignInPage;
    public SaucedemoLoginSteps() {

        saucedemo_SignInPage = getSaucedemo_SignInPage();
    }
    @When("I login saucedemo with {string}")
    public void i_login_saucedemo_with(String name) throws NoSuchFieldException {
        UserLogin userLogin = jsonDataReader.getUserLoginByName(name);
        saucedemo_SignInPage.signIn(userLogin);
    }

}
