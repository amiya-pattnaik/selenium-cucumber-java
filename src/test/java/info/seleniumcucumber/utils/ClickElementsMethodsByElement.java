package info.seleniumcucumber.utils;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickElementsMethodsByElement extends AbstractPage implements BaseTest {

    /**
     * Method to click on an element
     *
     * @param element : WebElement : WebElement type
     */
    public void click(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        element.click();
    }

    /**
     * Method to forcefully click on an element
     *
     * @param element : WebElement : WebElement type
     */
    public void clickForcefully(WebElement element)  throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to Double click on an element
     *
     * @param element : WebElement : WebElement type
     */
    public void doubleClick(WebElement element)  throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);

        Actions action = new Actions(getDriver());
        action.moveToElement(element).doubleClick().perform();
    }
}