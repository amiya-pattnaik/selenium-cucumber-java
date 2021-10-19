package info.seleniumcucumber.utils;

import info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AssertionMethodsByElement extends AbstractPage implements BaseTest {
    // This file contains assertion methods which are called from
    // predefinedStepDefinitions

    private final SelectElementByType selectElementByType = new SelectElementByType();
    // SelectElementByType eleType= new SelectElementByType();
    private WebElement element = null;

    /**
     * Method to get page title
     *
     * @return String
     */
    public String getPageTitle() {
        return getDriver().getTitle();
    }

    /**
     * Method to verify page title
     *
     * @param title    : String : expected title
     * @param testCase : Boolean : test case [true or false]
     */
    public void checkTitle(String title, boolean testCase) throws TestCaseFailed {
        String pageTitle = getPageTitle();

        if (testCase) {
            if (!pageTitle.equals(title))
                throw new TestCaseFailed("Page Title Not Matched, Actual Page Title : " + pageTitle);
        } else {
            if (pageTitle.equals(title))
                throw new TestCaseFailed("Page Title Matched, Actual Page Title : " + pageTitle);
        }
    }

    /**
     * Method to verify partial page title
     *
     * @param partialTitle : String : partial title string
     * @param testCase     : Boolean : test case [true or false]
     */
    public void checkPartialTitle(String partialTitle, boolean testCase) throws TestCaseFailed {
        String pageTitle = getPageTitle();
        if (testCase) {
            if (!pageTitle.contains(partialTitle))
                throw new TestCaseFailed("Partial Page Title Not Present, Actual Page Title : " + pageTitle);
        } else {
            if (pageTitle.contains(partialTitle))
                throw new TestCaseFailed("Partial Page Title Present, Actual Page Title : " + pageTitle);
        }
    }

    /**
     * Method to get element text
     *
     * @param element : WebElement : WebElement type
     * @return String
     */
    public String getElementText(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        return element.getText();

    }

    /**
     * Method to check element text
     *
     * @param element : WebElement : WebElement type
     * @param actualValue : String : Expected element text
     * @param testCase    : Boolean : test case [true or false]
     */
    public void checkElementText(WebElement element, String actualValue, boolean testCase)
            throws TestCaseFailed,NoSuchFieldException {
        String elementText = getElementText(element);

        if (testCase) {
            if (!elementText.equals(actualValue))
                throw new TestCaseFailed("Text Not Matched");
        } else {
            if (elementText.equals(actualValue))
                throw new TestCaseFailed("Text Matched");
        }
    }

    /**
     * Method to check partial element text
     *
     * @param element : WebElement : WebElement type
     * @param actualValue : String : Expected element text
     * @param testCase    : Boolean : test case [true or false]
     */
    public void checkElementPartialText(WebElement element, String actualValue, boolean testCase)
            throws TestCaseFailed,NoSuchFieldException {
        String elementText = getElementText(element);

        if (testCase) {
            if (!elementText.contains(actualValue))
                throw new TestCaseFailed("Text Not Matched");
        } else {
            if (elementText.contains(actualValue))
                throw new TestCaseFailed("Text Matched");
        }
    }

    /**
     * Method to return element status - enabled?
     *
     * @param element : WebElement : WebElement type
     * @return Boolean
     */
    public boolean isElementEnabled(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        return element.isEnabled();
    }

    /**
     * Element enabled checking
     *
     * @param element : WebElement : WebElement type
     * @param testCase   : Boolean : test case [true or false]
     */
    public void checkElementEnable(WebElement element, boolean testCase) throws TestCaseFailed,NoSuchFieldException {
        boolean result = isElementEnabled(element);
        if (testCase) {
            if (!result)
                throw new TestCaseFailed("Element Not Enabled");
        } else {
            if (result)
                throw new TestCaseFailed("Element Enabled");
        }
    }

    /**
     * method to get attribute value
     *
     * @param element : WebElement : WebElement type
     * @param attributeName : String : attribute name
     * @return String
     */
    public String getElementAttribute(WebElement element, String attributeName) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        return element.getAttribute(attributeName);
    }

    /**
     * method to check attribute value
     *
     * @param element : WebElement : WebElement type
     * @param attributeName  : String : attribute name
     * @param attributeValue : String : attribute value
     * @param testCase       : Boolean : test case [true or false]
     */
    public void checkElementAttribute(WebElement element, String attributeName, String attributeValue,
                                      boolean testCase) throws TestCaseFailed,NoSuchFieldException {
        String attrVal = getElementAttribute(element, attributeName);
        if (testCase) {
            if (!attrVal.equals(attributeValue))
                throw new TestCaseFailed("Attribute Value Not Matched");
        } else {
            if (attrVal.equals(attributeValue))
                throw new TestCaseFailed("Attribute Value Matched");
        }
    }

    /**
     * method to get element status - displayed?
     *
     * @param element : WebElement : WebElement type
     * @return Boolean
     */
    public boolean isElementDisplayed(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        return element.isDisplayed();
    }

    /**
     * method to check element presence
     *
     * @param element : WebElement : WebElement type
     * @param testCase   : Boolean : test case [true or false]
     */
    public void checkElementPresence(WebElement element, boolean testCase) throws TestCaseFailed,NoSuchFieldException {
        if (testCase) {
            if (!isElementDisplayed(element))
                throw new TestCaseFailed("Element Not Present");
        } else {
            try {
                if (isElementDisplayed(element))
                    throw new Exception("Present"); // since it is negative test
                // and we found element
            } catch (Exception e) {
                if (e.getMessage().equals("Present")) // only raise if it
                    // present
                    throw new TestCaseFailed("Element Present");
            }
        }
    }

    /**
     * method to assert checkbox check/uncheck
     *
     * @param element : WebElement : WebElement type
     * @param shouldBeChecked : Boolean : test case [true or false]
     */
    public void isCheckboxChecked(WebElement element, boolean shouldBeChecked) throws TestCaseFailed,NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        if ((!element.isSelected()) && shouldBeChecked)
            throw new TestCaseFailed("Checkbox is not checked");
        else if (element.isSelected() && !shouldBeChecked)
            throw new TestCaseFailed("Checkbox is checked");
    }

    /**
     * method to assert radio button selected/unselected
     *
     * @param element : WebElement : WebElement type
     */
    public void isRadioButtonSelected(WebElement element, boolean shouldBeSelected)
            throws TestCaseFailed,NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        if ((!element.isSelected()) && shouldBeSelected)
            throw new TestCaseFailed("Radio Button not selected");
        else if (element.isSelected() && !shouldBeSelected)
            throw new TestCaseFailed("Radio Button is selected");
    }

    // method to assert option from radio button group is selected/unselected
    public void isOptionFromRadioButtonGroupSelected(List<WebElement> elementList, String by, String option,
                                                     boolean shouldBeSelected) throws TestCaseFailed,NoSuchFieldException {
        getDriverWait().waitForElementToLoad(elementList.get(0));

        for (WebElement rb : elementList) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option)) {
                    if ((!rb.isSelected()) && shouldBeSelected)
                        throw new TestCaseFailed("Radio Button not selected");
                    else if (rb.isSelected() && !shouldBeSelected)
                        throw new TestCaseFailed("Radio Button is selected");
                }
            } else if (rb.getText().equals(option)) {
                if ((!rb.isSelected()) && shouldBeSelected)
                    throw new TestCaseFailed("Radio Button not selected");
                else if (rb.isSelected() && !shouldBeSelected)
                    throw new TestCaseFailed("Radio Button is selected");
            }
        }
    }

    /**
     * method to get javascript pop-up alert text
     *
     * @return String
     */
    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    /**
     * method to check javascript pop-up alert text
     *
     * @param text : String : Text to verify in Alert
     * @throws TestCaseFailed
     */
    public void checkAlertText(String text) throws TestCaseFailed {
        if (!getAlertText().equals(text))
            throw new TestCaseFailed("Text on alert pop up not matched");
    }

    /**
     * Method to verify if the particular option is Selected from Dropdown
     *
     * @param element : WebElement : WebElement type
     * @param by               : String : Select element from dropdown by text or value
     * @param option           : String : Element to select from dropdown
     * @param shouldBeSelected : Boolean : test case [true or false]
     * @throws TestCaseFailed, NoSuchFieldException
     */
    public void isOptionFromDropdownSelected(WebElement element, String by, String option,
                                             boolean shouldBeSelected) throws TestCaseFailed,NoSuchFieldException {
        Select selectList = null;
        getDriverWait().waitForElementToLoad(element);
        selectList = new Select(element);

        String actualValue = "";
        if (by.equals("text"))
            actualValue = selectList.getFirstSelectedOption().getText();
        else
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");

        if ((!actualValue.equals(option)) && (shouldBeSelected))
            throw new TestCaseFailed("Option Not Selected From Dropwdown");
        else if ((actualValue.equals(option)) && (!shouldBeSelected))
            throw new TestCaseFailed("Option Selected From Dropwdown");
    }
}
