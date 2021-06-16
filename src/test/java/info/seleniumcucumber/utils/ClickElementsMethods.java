package info.seleniumcucumber.utils;

import info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickElementsMethods extends AbstractPage implements BaseTest {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    // SelectElementByType eleType= new SelectElementByType();
    private WebElement element = null;

    /**
     * Method to click on an element
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void click(String accessType, String accessName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        element.click();
    }

    /**
     * Method to forcefully click on an element
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clickForcefully(String accessType, String accessName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to Double click on an element
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     */
    public void doubleClick(String accessType, String accessValue) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessValue)));

        Actions action = new Actions(getDriver());
        action.moveToElement(element).doubleClick().perform();
    }
}