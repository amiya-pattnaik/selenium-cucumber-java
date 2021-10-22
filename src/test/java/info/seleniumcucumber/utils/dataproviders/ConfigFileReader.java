package info.seleniumcucumber.utils.dataproviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import info.seleniumcucumber.constants.DriverType;
import info.seleniumcucumber.constants.EnvironmentType;



public class ConfigFileReader {	
	private Properties properties;
	private final String propertyFilePath= "src/test/resources/configs/browserstack.properties";

	public ConfigFileReader(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try { properties.load(reader); }
			catch (IOException e) { e.printStackTrace(); }
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		}finally {
			try { if(reader != null) reader.close(); }
			catch (IOException ignore) {}
		}
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}

	public String getHost() {
		String host = properties.getProperty("Host");
		if(host != null) return host;
		else throw new RuntimeException("Host not specified in the Configuration.properties file for the Key:url");
	}

	public String getDBUrl() {
		String dbUrl = properties.getProperty("DBUrl");
		if(dbUrl != null) return dbUrl;
		else throw new RuntimeException("DBUrl not specified in the Configuration.properties file for the Key:url");
	}

	public String getDBName() {
		String dbName = properties.getProperty("DBName");
		if(dbName != null) return dbName;
		else throw new RuntimeException("DBName not specified in the Configuration.properties file for the Key:url");
	}

	public String getDBUserName() {
		String dbUserName = properties.getProperty("DBUserName");
		if(dbUserName != null) return dbUserName;
		else throw new RuntimeException("DBName not specified in the Configuration.properties file for the Key:url");
	}

	public String getDBPassWord() {
		String dbPassWord = properties.getProperty("DBPassWord");
		if(dbPassWord != null) return dbPassWord;
		else throw new RuntimeException("DBName not specified in the Configuration.properties file for the Key:url");
	}
	
	public String getWordPressApplicationUrl() {
		String url = properties.getProperty("wordpressurl");
		if(url != null) return url;
		else throw new RuntimeException("WordPress Url not specified in the Configuration.properties file for the Key:url");
	}
	
	public String getAPApplicationUrl() {
		String url = properties.getProperty("automationstoreurl");
		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}

	public String getBaseUrl() {
		String url = properties.getProperty("baseURL");
		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equalsIgnoreCase("edge")) return DriverType.EDGE;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
	
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
		else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
		else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}
	
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}
	
	public String getTestDataResourcePath(){
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) return testDataResourcePath;
		else throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");		
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}

}
