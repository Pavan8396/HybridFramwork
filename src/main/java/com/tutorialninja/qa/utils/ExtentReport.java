package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);
		
		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setReportName("TutorialNinja Project Test Results");
		sparkreport.config().setDocumentTitle("TutorialNinja Automation Result");
		sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkreport);
		
		Properties prop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\Config.properties");
		
		try {
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		prop.load(fisConfigProp);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extentReport.setSystemInfo("Email", prop.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System Name ", System.getProperty("os.name"));
		extentReport.setSystemInfo("Operating System Version ", System.getProperty("os.version"));
		extentReport.setSystemInfo("User Name ", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}
