package info.seleniumcucumber.steps;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks extends AbstractPage {
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        endOfTest(scenario);
    }

    public void endOfTest(Scenario scenario) throws IOException {
        if (scenario.getStatus() != null && scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshotBytes, "image/png");
        }

        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("================================Test " + scenario.getName().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
