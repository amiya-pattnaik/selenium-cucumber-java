package info.seleniumcucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber/report.json"
        },
        glue = {"info.seleniumcucumber.steps"
        },
        features = {"classpath:features/my_first.feature"}
)
public class RunnerTest {

}


