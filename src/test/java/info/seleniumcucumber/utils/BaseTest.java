package info.seleniumcucumber.utils;

import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;
import info.seleniumcucumber.utils.dataproviders.ExcelDataReader;
import info.seleniumcucumber.utils.dataproviders.JsonDataReader;

public interface BaseTest {
    NavigateMethodsByElement navigationObj = new NavigateMethodsByElement();
    AssertionMethodsByElement assertionObj = new AssertionMethodsByElement();
    ClickElementsMethodsByElement clickObj = new ClickElementsMethodsByElement();
    InputMethodsByElement inputObj = new InputMethodsByElement();
    JavascriptHandlingMethods javascriptObj = new JavascriptHandlingMethods();
    ScreenShotMethods screenshotObj = new ScreenShotMethods();
    ConfigFileReader configFileReader = new ConfigFileReader();
    ExcelDataReader excelDataReader = new ExcelDataReader();
    JsonDataReader jsonDataReader = new JsonDataReader();
}
