package info.seleniumcucumber.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Hooks {
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @After
    public void afterScenario(Scenario scenario) {
        endOfTest(scenario);
    }

    public void endOfTest(Scenario scenario) {
        if (scenario.getStatus() != null && scenario.isFailed()) {
            String filename = scenario.getName().replaceAll("\\s+", "_");
            final String featureError = scenario.getId().replaceAll("\\s+", "_").replaceAll(":", "_").split("\\.")[1];
            filename = filename + "_" + featureError;
            scenario.embed(filename.getBytes(StandardCharsets.UTF_8), "image/png", filename);
        }

        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
