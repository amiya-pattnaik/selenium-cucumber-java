package info.seleniumcucumber.pageAndActionObjects.APIs;

import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import info.seleniumcucumber.testDataTypes.RegisterUserRequest;
import info.seleniumcucumber.constants.Constants2;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class RegisterUserActions {

	private String scenarioTestData = Constants2.TEST_DATA_BASEPATH;
	private RegisterUserRequest registerUserRequest;
	public Response response;

	public void getJSONRequestWithValidRegisterUserData() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		File registerUserJson = new File(scenarioTestData + Constants2.REGISTERUSER_REQUEST_DATA);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		registerUserRequest = gson.fromJson(new JsonReader(new java.io.FileReader(registerUserJson)), RegisterUserRequest.class);
		registerUserRequest.setEmail("useremail@test.com");
		registerUserRequest.setPassword("userpassword");
	}

	public void requestQuoteAPIWithPostMethod() throws Exception {
		response = SerenityRest.given().contentType("application/json").body(registerUserRequest).when()
				.post(Constants2.USER_ENDPOINT);
	}

	public int getStatusCode() throws Exception {
		return response.then().extract().statusCode();
	}

	public String getContentType() throws Exception {
		return response.then().extract().contentType();
	}
}
