package info.seleniumcucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features/api/registeruser.feature"}
)


public class ApiTestRunner{}
