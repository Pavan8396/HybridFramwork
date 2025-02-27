package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.baseclass.Base;
import com.tutorialninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialninja.qa.pageobjects.HomePage;
import com.tutorialninja.qa.pageobjects.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	RegisterPage register;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void setup() {

		driver = inicializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));

		HomePage homePage = new HomePage(driver);
		register = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterWithonlyMandatoryFields() {

		accountSuccessPage = register.registerWithOnlyMandatoryFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessCreatedMessage(),
				dataprop.getProperty("accountCreatedSuccessHeading"), "Account Success page is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {

		accountSuccessPage = register.registerWithAllFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataprop.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessCreatedMessage(),
				dataprop.getProperty("accountCreatedSuccessHeading"), "Account Success page is not displayed");
	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmailAdress() {
		accountSuccessPage = register.registerWithOnlyMandatoryFields(dataprop.getProperty("firstName"),
				dataprop.getProperty("lastName"), prop.getProperty("validEmail"), dataprop.getProperty("telephone"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertTrue(
				register.retriveDuplicateEmailWarning().contains(dataprop.getProperty("duplicateEmailWarning")),
				"Warning Message Regading the duplicate Email Adress is not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisterWithoutEnteringAnyData() {

		register.registerButtonOption();

		Assert.assertTrue(register.retriveprivacyPolicyWarning().contains(dataprop.getProperty("privacyPolicyWarning")),
				"Privacy Policy Warning Message is not displayed");
		Assert.assertEquals(register.retriveFirstNameWarning(), dataprop.getProperty("firstNameInlineWarning"),
				"FirstName Inline Error Message is not displayed");
		Assert.assertEquals(register.retriveLastNameWarning(), dataprop.getProperty("lastNameInlineWarning"),
				"LastName Inline Error Message is not displayed");
		Assert.assertEquals(register.retriveEmailWarning(), dataprop.getProperty("emailInlineWarning"),
				"Email Inline Error Message is not displayed");
		Assert.assertEquals(register.retriveTelephoneWarning(), dataprop.getProperty("telephoneInlineWarning"),
				"Telephone Inline Error Message is not displayed");
		Assert.assertEquals(register.retrivePasswordWarning(), dataprop.getProperty("passwordWarning"),
				"Password Inline Error Message is not displayed");
	}
}
