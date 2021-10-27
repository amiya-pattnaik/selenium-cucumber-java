package info.seleniumcucumber.steps;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.pageAndActionObjects.Saucedemo_SignInPage;
import info.seleniumcucumber.pageAndActionObjects.Weather;
import info.seleniumcucumber.testDataTypes.UserLogin;
import io.cucumber.java.en.*;
import lombok.var;
import org.junit.Assert;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.util.Optional;
import java.util.function.Predicate;
public class WeatherSteps extends AbstractPage {
    Weather weather;
    public WeatherSteps()
    {
        weather = getWeather();
    }
    @Given("I input keyword into search field with {string}")
    public void i_input_keyword_into_search_field_with(String keyword) throws NoSuchFieldException {
//        Predicate<HttpRequest> uriPredicate = httpRequest -> httpRequest.getUri().contains("weather.com/api/v1/p/redux-dal");
//    var interceptor =
//        new NetworkInterceptor(
//            getDriver(),
//            //                Route.matching(httpRequest -> true)
//            Route.matching(uriPredicate)
//                .to(
//                    () ->
//                        req ->
//                            new HttpResponse()
//                                .setStatus(200)
////                                .setContent(
////                                    Contents.asJson(
////                                        "{\"dal\":{\"getSunV3LocationSearchUrlConfig\":{\"language:en-US;locationType:locale;query:ho chi minh\":{\"loading\":false,\"loaded\":true,\"data\":{\"location\":{\"address\":[\"Ho Chi Minh City, Vietnam\",\"Chu Minh, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Tiến, Huyện Ngọc Lặc, Thanh Hóa, Vietnam\",\"Minh Sơn, Huyện Ngọc Lặc, Thanh Hóa, Vietnam\",\"Minh Châu, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Nông, Việt Trì, Phú Thọ, Vietnam\",\"Minh Thanh, Huyện Sơn Dương, Tuyên Quang, Vietnam\",\"Minh Hợp, Huyện Quỳ Hợp, Nghệ An, Vietnam\",\"Minh Quang, Huyện Ba Vì, Hanoi, Vietnam\",\"Minh Phương, Việt Trì, Phú Thọ, Vietnam\"],\"adminDistrict\":[null,\"Hanoi\",\"Thanh Hóa\",\"Thanh Hóa\",\"Hanoi\",\"Phú Thọ\",\"Tuyên Quang\",\"Nghệ An\",\"Hanoi\",\"Phú Thọ\"],\"adminDistrictCode\":[null,null,null,null,null,null,null,null,null,null],\"city\":[null,\"Huyện Ba Vì\",\"Huyện Ngọc Lặc\",\"Huyện Ngọc Lặc\",\"Huyện Ba Vì\",\"Việt Trì\",\"Huyện Sơn Dương\",\"Huyện Quỳ Hợp\",\"Huyện Ba Vì\",\"Việt Trì\"],\"country\":[\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\",\"Vietnam\"],\"countryCode\":[\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\",\"VN\"],\"displayName\":[\"Ho Chi Minh City\",\"Chu Minh\",\"Minh Tiến\",\"Minh Sơn\",\"Minh Châu\",\"Minh Nông\",\"Minh Thanh\",\"Minh Hợp\",\"Minh Quang\",\"Minh Phương\"],\"ianaTimeZone\":[\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\",\"Asia/Ho_Chi_Minh\"],\"latitude\":[10.817,21.198,20.004,20.041,21.204,21.309,21.779,19.305,21.079,21.328],\"locale\":[{\"locale1\":null,\"locale2\":\"Ho Chi Minh City\",\"locale3\":null,\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Chu Minh\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ngọc Lặc\",\"locale3\":\"Minh Tiến\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ngọc Lặc\",\"locale3\":\"Minh Sơn\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Minh Châu\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Việt Trì\",\"locale3\":\"Minh Nông\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Sơn Dương\",\"locale3\":\"Minh Thanh\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Quỳ Hợp\",\"locale3\":\"Minh Hợp\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Huyện Ba Vì\",\"locale3\":\"Minh Quang\",\"locale4\":null},{\"locale1\":null,\"locale2\":\"Việt Trì\",\"locale3\":\"Minh Phương\",\"locale4\":null}],\"longitude\":[106.633,105.44,105.399,105.391,105.455,105.378,105.401,105.274,105.324,105.366],\"neighborhood\":[null,null,null,null,null,null,null,null,null,null],\"placeId\":[\"e4f3028ded4eaa85aa504baa51acd7b6df7932ebc68c9d7aff1c838d1178f42c\",\"61a6375cfebc24b92aaee5bcbb47e04f236f3e1d113ff10ccde50d85e831700f\",\"ddfab3d8e7e594ad052f38a74ae083ff677776975f0def48804481805beb08fd\",\"14483da0b0dae5ba40655f498952b17ffbdf5f3a9c15753d2c1a687506c1975e\",\"34afc6fdd9fdf0d09a443d5e7e69c637854d25bbd38e67afc9b1305c08d94de4\",\"7b442e6276afd90941ff244a6c1298bfb5238ccf9b6c3319a43264b5ab3398cd\",\"e1f86b615edecab35c81da75e2819424810a46dcf5a98fe4b74d44a6cc409432\",\"f523666f9e469dea445c2c44ee5ef623709f02fc3d175afa67b06378d20d1c08\",\"d6c94383f5371d579443fb00ca83e59bb310b64ed67a29b7e4f78f56058988be\",\"5caeaab2e09676fd591a10c4692f0fba00315b8184284b6da6bda8a2f92e85c7\"],\"postalCode\":[\"72\",\"28\",\"45\",\"45\",\"28\",\"28\",\"30\",\"46\",\"29\",\"28\"],\"postalKey\":[\"72:VN\",\"28:VN\",\"45:VN\",\"45:VN\",\"28:VN\",\"28:VN\",\"30:VN\",\"46:VN\",\"29:VN\",\"28:VN\"],\"disputedArea\":[false,false,false,false,false,false,false,false,false,false],\"disputedCountries\":[null,null,null,null,null,null,null,null,null,null],\"disputedCountryCodes\":[null,null,null,null,null,null,null,null,null,null],\"disputedCustomers\":[null,null,null,null,null,null,null,null,null,null],\"disputedShowCountry\":[[false],[false],[false],[false],[false],[false],[false],[false],[false],[false]],\"iataCode\":[\"SGN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"HAN\",\"VII\",\"HAN\",\"HAN\"],\"icaoCode\":[\"VVTS\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVNB\",\"VVVH\",\"VVNB\",\"VVNB\"],\"locId\":[null,null,null,null,null,null,null,null,null,null],\"locationCategory\":[null,null,null,null,null,null,null,null,null,null],\"pwsId\":[\"IQUNTNBN2\",\"IHUYNA2\",\"IHUYNY4\",\"IHUYNY4\",\"IHUYNA2\",\"IHUYNA2\",\"IHUYNS1\",\"IHUYNQ3\",\"IHUYNA2\",\"IHUYNS1\"],\"type\":[\"state\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\",\"locality\"]}},\"status\":200,\"statusText\":\"OK\"}}}}"))
//                                        ));
        weather.inputKeywordIntoSearchField(keyword);

    }
    @Then("Verify search result {string}")
    public void i_verify_search_result(String expectedKeyword) throws NoSuchFieldException {
        Assert.assertEquals(expectedKeyword, weather.getTextListResult());
    }

}
