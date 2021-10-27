package info.seleniumcucumber.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driver;
    private ConfigFileReader configFileReader = new ConfigFileReader();
    private DevTools devTools;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    /**
     * By default to web driver will be firefox
     * <p>
     * Override it by passing -Dbrowser=Chrome to the command line arguments
     *
     * @return webdriver
     */
    private WebDriver chooseDriver() {
        boolean headless = System.getProperty("headless", "false").equals("true");

        switch (configFileReader.getBrowser()) {
            case EDGE:
                System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver");
                final EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.setCapability("UseChromium", true);
                    edgeOptions.setCapability("addArguments","headless");
                }
                return new EdgeDriver(edgeOptions);
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac_2");
                final ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless");
                }

                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("-incognito");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");

                ChromeDriver driver = new ChromeDriver(chromeOptions);
                devTools = driver.getDevTools();
                return driver;
            default:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                final FirefoxOptions ffOptions = new FirefoxOptions();

                if (headless) {
                    ffOptions.setHeadless(true);
                }
                return new FirefoxDriver(ffOptions);
//                return new FirefoxDriver();
        }
    }

    public WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        } else {

            driver = chooseDriver();
            if(configFileReader.getBrowserWindowSize())
                driver.manage().window().maximize();
            driverThreadLocal.set(driver);
            Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
            return getDriver();
        }
    }

    public DevTools getDevTools() {
        return devTools;
    }

}
