package info.seleniumcucumber.utils;

import info.seleniumcucumber.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InputMethodsByElement extends AbstractPage implements BaseTest {
    private WebElement dropdown = null;
    private Select selectList = null;

    /**
     * Method to enter text into text field
     *
     * @param element : WebElement : WebElement type
     * @param text       : String : Text value to enter in field
     */
    public void enterText(WebElement element, String text) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        element.sendKeys(text);
    }

    /**
     * Method to clear text of text field
     *
     * @param element : WebElement : WebElement type
     */
    public void clearText(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        element.clear();
    }

    /**
     * Method to select element from Dropdown by type
     *
     * @param select_list : Select : Select variable
     * @param bytype      : String : Name of by type
     * @param option      : String : Option to select
     */
    public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
        if (bytype.equals("selectByIndex")) {
            int index = Integer.parseInt(option);
            select_list.selectByIndex(index - 1);
        } else if (bytype.equals("value"))
            select_list.selectByValue(option);
        else if (bytype.equals("text"))
            select_list.selectByVisibleText(option);
    }

    /**
     * Method to select option from dropdown list
     *
     * @param element : WebElement : WebElement type
     * @param option     : String : Option to select
     */
    public void selectOptionFromDropdown(WebElement element, String optionBy, String option) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        selectList = new Select(dropdown);

        if (optionBy.equals("selectByIndex"))
            selectList.selectByIndex(Integer.parseInt(option) - 1);
        else if (optionBy.equals("value"))
            selectList.selectByValue(option);
        else if (optionBy.equals("text"))
            selectList.selectByVisibleText(option);
    }

    // method to select all option from dropdwon list
    // public void select_all_option_from_multiselect_dropdown(String
    // access_type, String access_name)
    // {
    // dropdown = driver.findElement(selectElementByType.getelementbytype(access_type,
    // access_name));
    // selectList = new Select(dropdown);
    //
    // //Select all method not present in JAVA
    // }

    /**
     * Method to unselect all option from dropdwon list
     *
     * @param element : WebElement : WebElement type
     */
    public void unselectAllOptionFromMultiselectDropdown(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /**
     * Method to unselect option from dropdwon list
     *
     * @param element : WebElement : WebElement type
     */
    public void deselectOptionFromDropdown(WebElement element, String optionBy, String option) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        selectList = new Select(dropdown);

        if (optionBy.equals("selectByIndex"))
            selectList.deselectByIndex(Integer.parseInt(option) - 1);
        else if (optionBy.equals("value"))
            selectList.deselectByValue(option);
        else if (optionBy.equals("text"))
            selectList.deselectByVisibleText(option);
    }

    /**
     * Method to check check-box
     *
     * @param element : WebElement : WebElement type
     */
    public void checkCheckbox(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        if (!element.isSelected())
            element.click();
    }

    /**
     * Method to uncheck check-box
     *
     * @param element : WebElement : WebElement type
     */
    public void uncheckCheckbox(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        if (element.isSelected())
            element.click();
    }

    /**
     * Method to toggle check-box status
     *
     * @param element : WebElement : WebElement type
     */
    public void toggleCheckbox(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
    }

    /**
     * Method to select radio button
     *
     * @param element : WebElement : WebElement type
     */
    public void selectRadioButton(WebElement element) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(element);
        if (!element.isSelected())
            element.click();
    }

    /**
     * Method to select option from radio button group
     *
     * @param elementList : List<WebElement> : list of WebElement
     * @param by         : String : Name of by type
     * @param option     : String : Option to select
     */
    public void selectOptionFromRadioButtonGroup( List<WebElement> elementList, String option, String by) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(elementList.get(0));
        for (WebElement rb : elementList) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option) && !rb.isSelected())
                    rb.click();
            } else if (by.equals("text")) {
                if (rb.getText().equals(option) && !rb.isSelected())
                    rb.click();
            }
        }
    }
}
