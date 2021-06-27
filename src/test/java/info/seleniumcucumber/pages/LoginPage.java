package info.seleniumcucumber.pages;

import info.seleniumcucumber.annotations.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@PageObject
public class LoginPage extends AbstractPage {

    @FindBy(how = How.ID, using = "flash")
    private WebElement MESSAGE;

    public WebElement getMessage() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(MESSAGE);
        return MESSAGE;
    }
}

