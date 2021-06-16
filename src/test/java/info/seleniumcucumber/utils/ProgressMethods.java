package info.seleniumcucumber.utils;

import info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressMethods extends AbstractPage implements BaseTest {

    private final SelectElementByType selectElementByType = new SelectElementByType();

    /**
     * Method to Explicitly wait for element to be displayed
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param duration   : String : Time to wait for element to be displayed
     */
    public void waitForElementToDisplay(String accessType, String accessName, String duration) {
        By byEle = selectElementByType.getelementbytype(accessType, accessName);
        WebDriverWait wait = (new WebDriverWait(getDriver(), Integer.parseInt(duration) * 1000L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }

    /**
     * Method to Explicitly wait for element to be enabled=click
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param duration   : String : Time to wait for element to be clickable
     */
    public void waitForElementToClick(String accessType, String accessName, String duration) {
        By byEle = selectElementByType.getelementbytype(accessType, accessName);
        WebDriverWait wait = (new WebDriverWait(getDriver(), Integer.parseInt(duration) * 1000L));
        wait.until(ExpectedConditions.elementToBeClickable(byEle));
    }

}
