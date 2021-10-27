package info.seleniumcucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/reports/cucumber-json-reports/json-report.json",
                "html:target/reports/cucumber-html-reports/html-report.html"
        },
        glue = {"info.seleniumcucumber.steps"
        },
//        features = {"classpath:features/my_first.feature"}
//        features = {"src/test/resources/features/Purchase.feature"}
//        features = {"src/test/resources/features/api/registeruser.feature"}
//        features = {"src/test/resources/features/api/login.feature"}
//        features = {"src/test/resources/features/api/testDB.feature"}
//        features = {"src/test/resources/features/saucedemo-login.feature"}
        features = {"src/test/resources/features/weather-intercept.feature"}
//        features = {"src/test/resources/features/ExecuteAutomationDevTools.feature"}
)
public class RunnerTest {
}
