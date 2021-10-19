package info.seleniumcucumber.utils;

import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;
import info.seleniumcucumber.utils.dataproviders.ExcelDataReader;
import info.seleniumcucumber.utils.dataproviders.JsonDataReader;

public interface BaseTest {
    MiscMethods miscmethodObj = new MiscMethods();
    NavigateMethods navigationObj = new NavigateMethods();
    AssertionMethods assertionObj = new AssertionMethods();
    ClickElementsMethods clickObj = new ClickElementsMethods();
    ConfigurationMethods configObj = new ConfigurationMethods();
    InputMethods inputObj = new InputMethods();
    ProgressMethods progressObj = new ProgressMethods();
    JavascriptHandlingMethods javascriptObj = new JavascriptHandlingMethods();
    ScreenShotMethods screenshotObj = new ScreenShotMethods();
    ConfigFileReader configFileReader = new ConfigFileReader();
    ExcelDataReader excelDataReader = new ExcelDataReader();
    JsonDataReader jsonDataReader = new JsonDataReader();
}
