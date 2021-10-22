package info.seleniumcucumber.steps.APIs;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import io.cucumber.java.en.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.seleniumcucumber.pageAndActionObjects.APIs.RegisterUserActions;
import info.seleniumcucumber.testDataTypes.RegisterUserResponse;

public class RegisterUserSteps extends AbstractPage {
	
	RegisterUserActions registerUserActions = getRegisterUserActions();

	@Given("^the user have proper register request data$")
	public void the_user_have_proper_register_request_data() throws Exception {
		registerUserActions.getJSONRequestWithValidRegisterUserData();
	}

	@When("^the user sents a POST request to register API with valid request$")
	public void the_user_sents_a_POST_request_to_register_API_with_valid_request() throws Exception {
		registerUserActions.requestQuoteAPIWithPostMethod();
	}

	@Then("^register API should have status code as (\\d+) and content-type as JSON$")
	public void register_API_should_have_status_code_as_and_content_type_as_JSON(int statusCode) throws Exception {
		assertThat("Verify Content Type for Order Api ", registerUserActions.getContentType(),
				equalTo("application/json; charset=utf-8"));
		assertThat("Verify Status code for Order Api ", registerUserActions.getStatusCode(), equalTo(statusCode));	
	}

	@Then("^the register API should return proper json response$")
	public void the_register_API_should_return_proper_json_response() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		RegisterUserResponse registerUserResponse = gson.fromJson(registerUserActions.response.asString(), RegisterUserResponse.class); 
				
		assertThat("Verify User registration Token", registerUserResponse.getId(),
				is(notNullValue()));
	}
}
