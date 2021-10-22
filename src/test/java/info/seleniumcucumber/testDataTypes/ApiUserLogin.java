package info.seleniumcucumber.testDataTypes;

import java.util.List;

import info.seleniumcucumber.constants.HttpOperation;
import info.seleniumcucumber.constants.ValidatorOperation;

public interface ApiUserLogin {

	public void init(String url, HttpOperation method);

	public void setHeader(String[][] head);

	public void setHeader(String head, String val);

	public void setBody(String body);

	public void setFormParam(String key, String val);

	public void setQueryParam(String key, String val);

	public String callIt();

	public Object assertIt(int code);

	public Object assertIt(String key, Object val, ValidatorOperation operation);

	public void assertIt(List<List<Object>> data);

	public static void failTest(String expected, String present) {
		throw new AssertionError("Does not contain the expected\t " + expected + "\n but had\t" + present);
		}

	public static void failTest(String message) {throw new AssertionError(message);}

	public void fileUpload(String key, String path, String mime);

	public String extractString(String path);

	public int extractInt(String path);

	public List extractList(String path);

	public Object extractIt(String path);

	public String extractHeader(String header_name);

	public String getResponseString();

	public void printResp();

}
