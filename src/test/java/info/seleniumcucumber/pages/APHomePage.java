package info.seleniumcucumber.pages;

import info.seleniumcucumber.utils.TestCaseFailed;


public class APHomePage  extends AbstractPage{

//	public APHomePage(WebDriver driver)
//	{
//		this.driver = driver;
//		baseClass = new BaseClass(driver);
//	}
	
	public String pageTitle = "My Store";
	
	public String signIn = "//a[normalize-space() = 'Sign in']";
	
	public void navigateTo_ApHomePage()
	{
//		driver.get(FileReaderManager.getInstance().getConfigReader().getAPApplicationUrl());
		navigationObj.navigateTo(configFileReader.getAPApplicationUrl());
	}
	
	public void verifyAPHomePageTitle() throws TestCaseFailed
	{
		
//		Helper.verifyTitleContains(driver, pageTitle);
		assertionObj.checkTitle(pageTitle,true);
	}
	
	public void navigateTo_LoginPage()
	{

//		baseClass.clickElement(signIn);
//		baseClass.clickElement("xpath",signIn,20);
		clickObj.click("xpath",signIn);
	}

}
