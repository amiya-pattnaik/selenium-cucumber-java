package info.seleniumcucumber.utils.expectedConditions;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class InvisibilityOfElement implements ExpectedCondition<Boolean> {

    private final WebElement element;

    public InvisibilityOfElement(WebElement element) {
        this.element = element;
    }

    @Override
    public Boolean apply(WebDriver d) {
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return true;
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}
