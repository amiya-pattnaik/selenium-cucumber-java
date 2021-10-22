package info.seleniumcucumber.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.empty;
import java.io.File;
import java.util.List;

import info.seleniumcucumber.constants.HttpOperation;
import info.seleniumcucumber.constants.ValidatorOperation;
import info.seleniumcucumber.testDataTypes.ApiUserLogin;
import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.anyOf;

public class ApiHelper implements ApiUserLogin {

    RequestSpecification reqSpec;
    HttpOperation method;
    String url;
    Response resp;
    private ConfigFileReader configFileReader = new ConfigFileReader();

    public void init(String url, HttpOperation method) {
        this.url = url;
        this.method = method;
        reqSpec = given();
    }

    public void initBase(String baseConst) {
        try {
            RestAssured.baseURI = configFileReader.getHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqSpec = RestAssured.given();
    }

    public void setHeader(String[][] head) {
        for (int row = 0; row < head.length; row++)
            for (int col = 0; col < head[row].length; col++)
                reqSpec.header(head[row][col], head[row][col + 1]);
    }

    public void setHeader(String head, String val) { reqSpec.header(head, val);}

    public void setBody(String body) { reqSpec.body(body); }

    public void setFormParam(String key, String val) { reqSpec.formParam(key, val);}

    public void setQueryParam(String key, String val) { reqSpec.queryParam(key, val);}

    public String callIt() {
        if (method.toString().equalsIgnoreCase("get")) {
            resp = reqSpec.get(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("post")) {
            resp = reqSpec.post(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("patch")) {
            resp = reqSpec.patch(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("put")) {
            resp = reqSpec.put(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("delete")) {
            resp = reqSpec.delete(url);
            return resp.asString();
        }
        return "invalid method set for API";
    }

    public ApiHelper assertIt(String key, Object val, ValidatorOperation operation) {

        switch (operation.toString()) {
            case "EQUALS":
                resp.then().body(key, equalTo(val));
                break;

            case "KEY_PRESENTS":
                resp.then().body(key, hasKey(key));
                break;

            case "HAS_ALL":
                break;

            case "NOT_EQUALS":
                resp.then().body(key, not(equalTo(val)));
                break;

            case "EMPTY":
                resp.then().body(key, empty());
                break;

            case "NOT_EMPTY":
                resp.then().body(key, not(emptyArray()));
                break;

            case "NOT_NULL":
                resp.then().body(key, notNullValue());
                break;

            case "HAS_STRING":
                resp.then().body(key, containsString((String)val));
                break;

            case "SIZE":
                resp.then().body(key, hasSize((int)val));
                break;
        }

        return this;
    }

    public void assertIt(List<List<Object>> data) {
        for (List<Object> li : data) {
            switch (((ValidatorOperation) li.get(2)).toString()) {
                case "EQUALS":
                    resp.then().body(((String) li.get(0)), equalTo((String) li.get(1)));
                    break;

                case "KEY_PRESENTS":
                    resp.then().body(((String) li.get(0)), hasKey((String) li.get(1)));
                    break;

                case "HAS_ALL":
                    break;
            }
        }
    }

    public ApiHelper assertIt(int code) {

        resp.then().statusCode(code);

        return this;
    }

    public String extractString(String path) { return resp.jsonPath().getString(path);}

    public int extractInt(String path) { return resp.jsonPath().getInt(path);}

    public List<?> extractList(String path) { return resp.jsonPath().getList(path);}

    public Object extractIt(String path) { return resp.jsonPath().get(path); }

    public String extractHeader(String header_name) { return resp.header(header_name);}

    public String getResponseString() { return resp.asString();}

    public void fileUpload(String key, String path, String mime) {
        reqSpec.multiPart(key, new File(path), mime);
    }

    public void multiPartString(String key, String input) { reqSpec.multiPart(key,input);}

    public void printResp() { resp.print();}

    public String getCookieValue(String cookieName) { return resp.getCookie(cookieName); }

    public ApiHelper assertIt(int code, int optionalCode) {
        resp.then().statusCode(anyOf(equalTo(code),equalTo(optionalCode)));
        return this;
    }

    public void setRedirection(boolean followRedirects) {
        reqSpec.when().redirects().follow(followRedirects);
    }

    public void ListResponseHeaders()
    {
        // Get all the headers. Return value is of type Headers.
        Headers allHeaders = resp.headers();
        // Iterate over all the Headers
        for(Header header : allHeaders)
        {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    public int getStatusCode() { return resp.getStatusCode(); }

    public Headers getAllHeaders() {return resp.getHeaders();}
}
