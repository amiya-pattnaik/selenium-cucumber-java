package info.seleniumcucumber.pageAndActionObjects.APIs;

import info.seleniumcucumber.constants.HttpOperation;
import info.seleniumcucumber.utils.ApiHelper;

public class Auth extends ApiHelper{
	

	
    public Auth(){}
      
    /**
     * Creates a new auth token to use for access to the PUT and DELETE /booking
     * 
     * */
    private void createToken(String userName, String passWord) {
		initBase("Host");
		init("/auth", HttpOperation.POST);
		setHeader("Content-Type","application/json");
		setBody("{ \"username\" : \""+userName+"\", \"password\" : \""+passWord+"\"}");
	}
	

	/**
	 * @param	userName (Username string value for the restful bokker application) 			
	 * 			passWord (password string value for the restful bokker application)
	 * 
	 * @return 	returns login token
	 * */
	public String getLoginToken(String userName, String passWord) {
		createToken(userName, passWord);
		String response = callIt();
		return response;
	}

	
	
}
