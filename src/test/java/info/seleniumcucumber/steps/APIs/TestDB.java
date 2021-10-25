package info.seleniumcucumber.steps.APIs;

import info.seleniumcucumber.constants.ValidatorOperation;
import info.seleniumcucumber.pageAndActionObjects.APIs.Auth;
import info.seleniumcucumber.utils.dataproviders.DBConnection;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.lang.reflect.Method;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestDB {
	
  /**
   * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
   * 
   * */
	private DBConnection dbConnection = new DBConnection();

  @Given("^Query mysql DB$")
  public void validLoginTest() throws SQLException {
	String query = "select * from table_name";
	  dbConnection.getDbCon();

	  assertThat("Verify User registration Token", dbConnection.getData(query, "id"),
			  equalTo("1"));


	  
	 }


}
