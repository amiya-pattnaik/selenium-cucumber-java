package info.seleniumcucumber.steps.APIs;

import info.seleniumcucumber.constants.ValidatorOperation;
import info.seleniumcucumber.pageAndActionObjects.APIs.Auth;
import java.lang.reflect.Method;
import io.cucumber.java.en.*;


public class LoginTest {
	
  /**
   * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
   * 
   * */

  @Given("^Valid Login Test$")
  public void validLoginTest() {
	  
	  Auth response = new Auth();
	  response.getLoginToken("admin", "password123");
	  
	  response.assertIt(200);
	  response.assertIt("token",null,ValidatorOperation.NOT_EMPTY);
	  response.assertIt("token",null,ValidatorOperation.NOT_NULL);

	  
	 }

	@Given("^Invalid Login Test$")
  public void invalidLoginTest(Method method) {
	  
	  Auth response = new Auth();
	  response.getLoginToken("dummy", "dummypassword123");
	  response.assertIt(200);
	  response.assertIt("reason","Bad credentials",ValidatorOperation.EQUALS);

	 }

}
