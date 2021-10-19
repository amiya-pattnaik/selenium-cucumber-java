package info.seleniumcucumber.utils;

import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;
import info.seleniumcucumber.utils.dataproviders.ExcelDataReader;
import info.seleniumcucumber.utils.dataproviders.JsonDataReader;

public interface BaseTest {
    MiscMethods miscmethodObj = new MiscMethods();
    NavigateMethodsByElement navigationObj = new NavigateMethodsByElement();
    AssertionMethods assertionObj = new AssertionMethods();
    ClickElementsMethodsByElement clickObj = new ClickElementsMethodsByElement();
    ConfigurationMethods configObj = new ConfigurationMethods();
    InputMethods inputObj = new InputMethods();
    ProgressMethods progressObj = new ProgressMethods();
    JavascriptHandlingMethods javascriptObj = new JavascriptHandlingMethods();
    ScreenShotMethods screenshotObj = new ScreenShotMethods();
    ConfigFileReader configFileReader = new ConfigFileReader();
    ExcelDataReader excelDataReader = new ExcelDataReader();
    JsonDataReader jsonDataReader = new JsonDataReader();
}
