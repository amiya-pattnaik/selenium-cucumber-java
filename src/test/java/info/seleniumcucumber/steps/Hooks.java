package info.seleniumcucumber.steps;

import info.seleniumcucumber.pageAndActionObjects.AbstractPage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks extends AbstractPage {
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @After
    public void afterScenario(Scenario scenario) {
        endOfTest(scenario);
    }

    public void endOfTest(Scenario scenario) {
        if (scenario.getStatus() != null && scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
//            String filename = scenario.getName().replaceAll("\\s+", "_");
//            final String featureError = scenario.getId().replaceAll("\\s+", "_").replaceAll(":", "_").split("\\.")[1];
//            filename = filename + "_" + featureError;
            scenario.embed(screenshotBytes, "image/png");
//            scenario.embed(filename.getBytes(StandardCharsets.UTF_8), "image/png", filename);
        }

        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
