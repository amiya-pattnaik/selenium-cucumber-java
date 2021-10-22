package info.seleniumcucumber.testDataTypes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterUserResponse {

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("createdAt")
	@Expose
	private String createdAt;

	public String getId() {
		return id;
	}

//	public void setToken(String token) {
//		this.token = token;
//	}
}