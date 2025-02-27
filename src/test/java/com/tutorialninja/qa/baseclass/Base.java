package com.tutorialninja.qa.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;

	public Base() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\config\\Config.properties");

		dataprop = new Properties();
		File datapropfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\Testdata.properties");
		try {
			FileInputStream fis2 = new FileInputStream(datapropfile);
			dataprop.load(fis2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public WebDriver inicializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
