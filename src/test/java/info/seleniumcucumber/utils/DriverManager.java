package info.seleniumcucumber.utils;

import info.seleniumcucumber.constants.EnvironmentType;
import net.serenitybdd.core.webdriver.RemoteDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import info.seleniumcucumber.utils.dataproviders.ConfigFileReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
//    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
//    private static WebDriver driver;
    private final ConfigFileReader configFileReader = new ConfigFileReader();
    private DevTools devTools;

//    private static final Thread CLOSE_THREAD = new Thread() {
//        @Override
//        public void run() {
//            driver.quit();
//        }
//    };

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
                if (configFileReader.getEnvironment() == EnvironmentType.LOCAL)
                    return new EdgeDriver(edgeOptions);
                else {
                    RemoteWebDriver remoteWebDriver = null;
                    try {
                        remoteWebDriver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), edgeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return remoteWebDriver;
                }

            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac_2");
                final ChromeOptions chromeOptions = new ChromeOptions();
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(String.valueOf(Browser.CHROME));

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
                if (configFileReader.getEnvironment() == EnvironmentType.LOCAL) {
                    ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
                    devTools = chromeDriver.getDevTools();
                    return chromeDriver;
                }

                else {
                    RemoteWebDriver remoteWebDriver = null;
                    try {
                        remoteWebDriver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), chromeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return remoteWebDriver;
                }

            default:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                final FirefoxOptions ffOptions = new FirefoxOptions();

                if (headless) {
                    ffOptions.setHeadless(true);
                }
        if (configFileReader.getEnvironment() == EnvironmentType.LOCAL)
          return new FirefoxDriver(ffOptions);
        else {
            RemoteWebDriver remoteWebDriver = null;

              try {
                  remoteWebDriver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), ffOptions);
              } catch (MalformedURLException e) {
                  e.printStackTrace();
              }
              return remoteWebDriver;
        }
    }
    }

    public WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        } else {

            driverThreadLocal.set(chooseDriver());
            if(configFileReader.getBrowserWindowSize())
                driverThreadLocal.get().manage().window().maximize();
            return getDriver();
        }
    }

    public DevTools getDevTools() {
        return devTools;
    }

}
