package info.seleniumcucumber.steps;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import info.seleniumcucumber.pageAndActionObjects.Saucedemo_SignInPage;
import info.seleniumcucumber.testDataTypes.UserLogin;
import io.cucumber.java.en.*;
import lombok.var;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.net.URI;
import java.util.function.Predicate;

public class SaucedemoLoginSteps extends AbstractPage {
    Saucedemo_SignInPage saucedemo_SignInPage;
    public SaucedemoLoginSteps() {

        saucedemo_SignInPage = getSaucedemo_SignInPage();
    }

    @When("I login saucedemo with {string}")
    public void i_login_saucedemo_with(String name) throws NoSuchFieldException {
//        Predicate<HttpRequest> uriPredicate = httpRequest -> httpRequest.getUri().contains("your-domain.com");
//        var interceptor = new NetworkInterceptor(
//                getDriver(),
////                Route.matching(httpRequest -> true)
//                Route.matching(uriPredicate)
//                        .to(()-> req -> new HttpResponse()
//                                .setStatus(404)
//                                .setContent(Contents.utf8String("Site is being hacked")))
//        );
        UserLogin userLogin = jsonDataReader.getUserLoginByName(name);
        saucedemo_SignInPage.signIn(userLogin);
    }

}
