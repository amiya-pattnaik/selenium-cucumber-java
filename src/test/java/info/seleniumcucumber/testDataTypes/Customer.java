package info.seleniumcucumber.testDataTypes;

public class Customer {
	  public String username;
	  public String password;
	  public String firstName;
	  public String lastName;
	  public int age;
	  public String emailAddress;
	  public Address address;
	  public PhoneNumber phoneNumber;	
	  
	  public class Address {
		  public String streetAddress;
		  public String city;
		  public String postCode;
		  public String state;
		  public String country;
		  public String county;
	  }

	  public class PhoneNumber {
		  public String home;
		  public String mob;
	  }
	  
	  public void setUserName(String username)
	  {
		  this.username = username;
	  }
	  
	  public String getUserName()
	  {
		  return username;
	  }
	  
	  public void setPassword(String password)
	  {
		  this.password = password;
	  }
	  
	  public String getPassword()
	  {
		  return password;
	  }
	  
}
	 
