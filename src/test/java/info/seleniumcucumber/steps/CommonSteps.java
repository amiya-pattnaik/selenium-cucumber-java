package info.seleniumcucumber.steps;

import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.utils.TestCaseFailed;
import io.cucumber.java.en.*;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.var;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.*;

import org.apache.log4j.LogMF;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.openqa.selenium.remote.http.Contents.utf8String;

public class CommonSteps extends AbstractPage {

    // Step to navigate to specified URL
    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link) {
        navigationObj.navigateTo(link);
    }

    // Step to navigate forward
    @Then("^I navigate forward")
    public void navigate_forward() {
        navigationObj.navigate("forward");
    }

    // Step to navigate backward
    @Then("^I navigate back")
    public void navigate_back() {
        navigationObj.navigate("back");
    }

    // steps to refresh page
    @Then("^I refresh page$")
    public void refresh_page() {
        getDriver().navigate().refresh();
    }

    // Switch between windows

    // Switch to new window
    @Then("^I switch to new window$")
    public void switch_to_new_window() {
        navigationObj.switchToNewWindow();
    }

    // Switch to old window
    @Then("^I switch to previous window$")
    public void switch_to_old_window() {
        navigationObj.switchToOldWindow();
    }

    // Switch to new window by window title
    @Then("^I switch to window having title \"(.*?)\"$")
    public void switch_to_window_by_title(String windowTitle) throws Exception {
        navigationObj.switchToWindowByTitle(windowTitle);
    }

    // Close new window
    @Then("^I close new window$")
    public void close_new_window() {
        navigationObj.closeNewWindow();
    }

    // Switch between frame

    // step to switch to main content
    @Then("^I switch to main content$")
    public void switch_to_default_content() {
        navigationObj.switchToDefaultContent();
    }

    // To interact with browser

    // step to resize browser
    @Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
    public void resize_browser(int width, int heigth) {
        navigationObj.resizeBrowser(width, heigth);
    }

    // step to maximize browser
    @Then("^I maximize browser window$")
    public void maximize_browser() {
        navigationObj.maximizeBrowser();
    }

    // zoom in/out page

    // steps to zoom in page
    @Then("^I zoom in page$")
    public void zoom_in() {
        navigationObj.zoomInOut("ADD");
    }

    // steps to zoom out page
    @Then("^I zoom out page$")
    public void zoom_out() {
        navigationObj.zoomInOut("SUBTRACT");
    }

    // reset webpage view use

    @Then("^I reset page view$")
    public void reset_page_zoom() {
        navigationObj.zoomInOut("reset");
    }

    // scroll webpage

    @Then("^I scroll to (top|end) of page$")
    public void scroll_page(String to) throws Exception {
        navigationObj.scrollPage(to);
    }

    // Assertion steps

    /**
     * page title checking
     *
     * @param present :
     * @param title   :
     */
    @Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
    public void check_title(String present, String title) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkTitle(title, present.isEmpty());
    }

    // step to check element partial text
    @Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
    public void check_partial_text(String present, String partialTextTitle) throws TestCaseFailed {
        // System.out.println("Present :" + present.isEmpty());
        assertionObj.checkPartialTitle(partialTextTitle, present.isEmpty());
    }

    // step to assert javascript pop-up alert text
    @Then("^I should see alert text as \"(.*?)\"$")
    public void check_alert_text(String actualValue) throws TestCaseFailed {
        assertionObj.checkAlertText(actualValue);
    }

    // JavaScript handling steps

    // Step to handle java script
    @Then("^I accept alert$")
    public void handle_alert() {
        javascriptObj.handleAlert("accept");
    }

    // Steps to dismiss java script
    @Then("^I dismiss alert$")
    public void dismiss_alert() {
        javascriptObj.handleAlert("dismiss");
    }

    // Screen shot methods

    @Then("^I take screenshot$")
    public void take_screenshot() throws IOException {
        screenshotObj.takeScreenShot();
    }

    // Dev tool - only for chrome

    @Then("^I get matching request response$")
    public void getMatchingRequestResponse() throws IOException {
        DevTools devTools = getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    System.out.println("Request URL: " + entry.getRequest().getUrl() + "\n"
                    + " With method: " + entry.getRequest().getMethod() + "\n");
                    entry.getRequest().getMethod();
                });
        devTools.addListener(Network.responseReceived(),
                entry -> {
                    System.out.println("Request URL: " + entry.getResponse().getUrl() + "\n"
                    + " With status: " + entry.getResponse().getStatus() + " " + entry.getResponse().getStatusText() + "\n");
                });
        navigationObj.navigateTo("http://www.executeautomation.com");
        devTools.send(Network.disable());
    }

    @Given("^I interceptor api$")
    public void interceptorAPIs() throws InterruptedException {
        String jsonString = "{\"dal\":{\"getSunV3LocationSearchUrlConfig\":{\"language:en-US;locationType:locale;query:ho chi minh\":{\"loading\":false,\"loaded\":true,\"data\":{\"location\":{\"address\":[\"Ho Chi Minh City, Vietnam\",\"Chu Minh, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Tiến, Huyện Ngọc Lặc, Thanh Hóa, Vietnam\",\"Minh Sơn, Huyện Ngọc Lặc, Thanh Hóa, Vietnam\",\"Minh Châu, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Nông, Việt Trì, Phú Thọ, Vietnam\",\"Minh Thanh, Huyện Sơn Dương, Tuyên Quang, Vietnam\",\"Minh Hợp, Huyện Quỳ Hợp, Nghệ An, Vietnam\",\"Minh Quang, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Phương, Việt Trì, Phú Thọ, Vietnam\"],\"adminDistrict\":[null,\"Hanoi\",\"Thanh Hóa\",\"Thanh Hóa\",\"Hanoi\",\"Phú Thọ\",\"Tuyên Quang\",\"Nghệ An\",\"Hanoi\",\"Phú Thọ\"],\"adminDistrictCode\":[null,null,null,null,null,null,null,null,null,null],\"city\":[null,\"Huyện Ba Vì\",\"Huyện Ngọc Lặc\",\"Huyện Ngọc Lặc\",\"Huyện Ba Vì\",\"Việt Trì\",\"Huyện Sơn Dương\",\"Huyện Quỳ Hợp\",\"Huyện Ba Vì\",\"Việt Trì\"],\"country\":[\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\"],\"countryCode\":[\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\"],\"displayName\":[\"Ho Chi Minh City\",\"Chu Minh\",\"Minh Tiến\",\"Minh Sơn\",\"Minh Châu\",\"Minh Nông\",\"Minh Thanh\",\"Minh Hợp\",\"Minh Quang\",\"Minh Phương\"],\"ianaTimeZone\":[\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\"],\"latitude\":[10.817,21.198,20.004,20.041,21.204,21.309,21.779,19.305,21.079,21.328],\"locale\":[{\"locale1\":null,\"locale2\":\"Ho Chi Minh City\",\"locale3\":null,\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Chu Minh\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ngọc Lặc\",\"locale3\":\"Minh Tiến\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ngọc Lặc\",\"locale3\":\"Minh Sơn\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Minh Châu\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Việt Trì\",\"locale3\":\"Minh Nông\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Sơn Dương\",\"locale3\":\"Minh Thanh\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Quỳ Hợp\",\"locale3\":\"Minh Hợp\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Minh Quang\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Việt Trì\",\"locale3\":\"Minh Phương\",\"locale4\":null}],\"longitude\":[106.633,105.44,105.399,105.391,105.455,105.378,105.401,105.274,105.324,105.366],\"neighborhood\":[null,null,null,null,null,null,null,null,null,null],\"placeId\":[\"e4f3028ded4eaa85aa504baa51acd7b6df7932ebc68c9d7aff1c838d1178f42c\",\"61a6375cfebc24b92aaee5bcbb47e04f236f3e1d113ff10ccde50d85e831700f\",\"ddfab3d8e7e594ad052f38a74ae083ff677776975f0def48804481805beb08fd\",\"14483da0b0dae5ba40655f498952b17ffbdf5f3a9c15753d2c1a687506c1975e\",\"34afc6fdd9fdf0d09a443d5e7e69c637854d25bbd38e67afc9b1305c08d94de4\",\"7b442e6276afd90941ff244a6c1298bfb5238ccf9b6c3319a43264b5ab3398cd\",\"e1f86b615edecab35c81da75e2819424810a46dcf5a98fe4b74d44a6cc409432\",\"f523666f9e469dea445c2c44ee5ef623709f02fc3d175afa67b06378d20d1c08\",\"d6c94383f5371d579443fb00ca83e59bb310b64ed67a29b7e4f78f56058988be\",\"5caeaab2e09676fd591a10c4692f0fba00315b8184284b6da6bda8a2f92e85c7\"],\"postalCode\":[\"72\",\"28\",\"45\",\"45\",\"28\",\"28\",\"30\",\"46\",\"29\",\"28\"],\"postalKey\":[\"72:VN\",\"28:VN\",\"45:VN\",\"45:VN\",\"28:VN\",\"28:VN\",\"30:VN\",\"46:VN\",\"29:VN\",\"28:VN\"],\"disputedArea\":[false,false,false,false,false,false,false,false,false,false],\"disputedCountries\":[null,null,null,null,null,null,null,null,null,null],\"disputedCountryCodes\":[null,null,null,null,null,null,null,null,null,null],\"disputedCustomers\":[null,null,null,null,null,null,null,null,null,null],\"disputedShowCountry\":[[false],[false],[false],[false],[false],[false],[false],[false],[false],[false]],\"iataCode\":[\"SGN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"VII\",\"HAN\",\"HAN\"],\"icaoCode\":[\"VVTS\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVVH\",\"VVNB\",\"VVNB\"],\"locId\":[null,null,null,null,null,null,null,null,null,null],\"locationCategory\":[null,null,null,null,null,null,null,null,null,null],\"pwsId\":[\"IQUNTNBN2\",\"IHUYNA2\",\"IHUYNY4\",\"IHUYNY4\",\"IHUYNA2\",\"IHUYNA2\",\"IHUYNS1\",\"IHUYNQ3\",\"IHUYNA2\",\"IHUYNS1\"],\"type\":[\"state\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\"]}},\"status\":200,\"statusText\":\"OK\"}}}}";
        JSONObject jsonObject = new JSONObject(jsonString);


        Thread.sleep(5000);
                Predicate<HttpRequest> uriPredicate = httpRequest -> httpRequest.getUri().contains("weather.com/api/v1/p/redux-dal");
        var interceptor =
                new NetworkInterceptor(
                        getDriver(),
//                                        Route.matching(httpRequest -> true)
                        Route.matching(uriPredicate)
                                .to(
                                        () ->
//                                                req -> new HttpResponse().setStatus(200)
                                                req ->
                                                {
                                                    HttpResponse httpResponse = new HttpResponse();
                                                    httpResponse.setStatus(200);
                                                    httpResponse.addHeader("Content-Type", MediaType.HTML_UTF_8.toString());
                                                    httpResponse.setContent(Contents.asJson(jsonObject));
//                                                    httpResponse.setContent(req.getContent());

                                                    try{
                                                        httpResponse.setContent(changeValueInJson(req.getContent()));
                                                    } catch (IOException e) {
                                                     }
                                                    return httpResponse;
                                                }


                                ));
    }

    public Supplier<InputStream> changeValueInJson(Supplier<InputStream> supplier) throws IOException {
        InputStream inputStream = supplier.get();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            line.replace("Ho Chi Minh City, Vietnam", "Ho Chi Minh City, Vietnam test test");
        }
        return supplier;
    }


    @Then("^Mocking response$")
    public void mockingResponse() throws InterruptedException {
        Predicate<HttpRequest> uriPredicate = httpRequest -> httpRequest.getUri().contains("weather.com/api/v1/p/redux-dal");

        NetworkInterceptor interceptor = new NetworkInterceptor(
                getDriver(),
                Route.matching(uriPredicate)
                        .to(() -> req -> new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                                .setContent(utf8String("Creamy, delicious cheese!"))));

        getDriver().get("https://weather.com/?Goto=Redirected");

        String source = getDriver().getPageSource();

        Assert.assertThat(source, CoreMatchers.containsString("delicious cheese!"));
        Thread.sleep(50000);
    }
}