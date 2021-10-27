package info.seleniumcucumber.pageAndActionObjects;

import info.seleniumcucumber.testDataTypes.UserLogin;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class Weather extends AbstractPage{

	@FindBy(how = How.ID, using = "LocationSearch_input")
	private WebElement txtLocationSearch;

	@FindBy(how = How.XPATH, using = "//*[@id='LocationSearch_listbox']/button")
	private List<WebElement> btnResultList;

	@FindBy(how = How.XPATH, using = "//*[@id='LocationSearch_listbox']/button[1]")
	private WebElement btnResult;
	
	public void inputKeywordIntoSearchField(String keyword) throws NoSuchFieldException
	{
		clickObj.click(txtLocationSearch);
		inputObj.enterText(txtLocationSearch, keyword);
	}

	public String getTextListResult() throws NoSuchFieldException
	{
		return assertionObj.getElementText(btnResult);
	}

	public String verifySearchResult(String expectedString) throws NoSuchFieldException
	{
		return assertionObj.getElementText(btnResultList.get(0));
	}

}
