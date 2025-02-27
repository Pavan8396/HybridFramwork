package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.baseclass.Base;
import com.tutorialninja.qa.pageobjects.AccountPage;
import com.tutorialninja.qa.pageobjects.HomePage;
import com.tutorialninja.qa.pageobjects.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public LoginTest() {
		super();
	}

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setup() {
		driver = inicializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		loginPage = homepage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyLoginWithValidCredintials() {
		accountPage = loginPage.Login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		Assert.assertTrue(accountPage.getDisplayStatusOFEditYourAccountInformationOption(),
				"Edit your account information not displayed");
	}

	@Test(priority = 6, dataProvider = "validCredentialSupplier")
	public void VerifyLoginWithValidCredintialsUsingDataDriven(String email, String password) {
		accountPage = loginPage.Login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOFEditYourAccountInformationOption(),
				"Edit your account information not displayed");
	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredintials() {
		loginPage.Login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(
				loginPage.retriveEmailPasswordNotMatchingWarningMessageText()
						.contains(dataprop.getProperty("emailPasswordWaringNotMatch")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void VerifyLoginWithInvalidEmailValidPassword() {
		loginPage.Login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(
				loginPage.retriveEmailPasswordNotMatchingWarningMessageText()
						.contains(dataprop.getProperty("emailPasswordWaringNotMatch")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 4)
	public void VerifyLoginWithValidEmailandInvalidPassword() {
		loginPage.Login(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(
				loginPage.retriveEmailPasswordNotMatchingWarningMessageText()
						.contains(dataprop.getProperty("emailPasswordWaringNotMatch")),
				"Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void VerifyLoginWithoutCredintials() {

		loginPage.clickOnLoginButton();
		Assert.assertTrue(
				loginPage.retriveEmailPasswordNotMatchingWarningMessageText()
						.contains(dataprop.getProperty("emailPasswordWaringNotMatch")),
				"Expected warning message is not displayed");
	}
}