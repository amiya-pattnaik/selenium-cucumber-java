package info.seleniumcucumber.methods;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import env.DriverUtil;
import org.openqa.selenium.WebDriver;

public class ScreenShotMethods implements BaseTest {
	protected WebDriver driver = DriverUtil.getDefaultDriver();

	/** Method to take screen shot and save in ./screenShots folder */
	public void takeScreenShot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy-z-HH:mm:ss", Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal.getTime()));
		FileUtils.copyFile(scrFile, new File("screenShots/" + dateFormat.format(cal.getTime()) + ".png"));		
	}
}
