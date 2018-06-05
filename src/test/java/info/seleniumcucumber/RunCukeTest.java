package info.seleniumcucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;																							

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		//features="src/test/resources/features",
		//tags= {"@smoke, @login"},
		plugin = {
				"pretty","html:target/cucumberHtmlReport",     	// for html result
				"pretty:target/cucumber-json-report.json",   	// for json result
				"pretty:junit:target/cucumber.xml"
		},
		
		glue = {
				"info.seleniumcucumber.stepdefinitions", 	// predefined step definitions package
				"info.seleniumcucumber.userStepDefintions" 	// user step definitions package
		}
	)

public class RunCukeTest {
	
}

