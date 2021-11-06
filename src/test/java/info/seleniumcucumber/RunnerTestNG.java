package info.seleniumcucumber;


//import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.annotations.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    plugin = {
      "pretty"
//      ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//            ,"com.epam.reportportal.cucumber.StepReporter"
            ,"com.epam.reportportal.cucumber.ScenarioReporter"
    },
    glue = {"info.seleniumcucumber.steps"},
//            features = {"classpath:features/"}
//            features = {"classpath:features/my_first.feature"}
    features = {
            "src/test/resources/features/Purchase.feature",
            "src/test/resources/features/Purchase2.feature",
//            "src/test/resources/features/registeruser.feature",
//            "src/test/resources/features/login.feature",
//            "src/test/resources/features/testDB.feature",
//            "src/test/resources/features/ExecuteAutomationDevTools.feature",
            "src/test/resources/features/weather-intercept.feature",
            "src/test/resources/features/saucedemo-login.feature",
//            "src/test/resources/features/my_first.feature"
    },
        monochrome = true
//        tags = {},

    )
//@Listeners({ReportPortalTestNGListener.class})
public class RunnerTestNG  extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuiteActivities() {


    }

    @AfterSuite
    public void afterActivities() {

    }
}
