package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.baseclass.Base;
import com.tutorialninja.qa.pageobjects.HomePage;
import com.tutorialninja.qa.pageobjects.SearchResultPage;

//test to github

public class SearchTest extends Base {

	public SearchTest() {
		super();
	}

	public WebDriver driver;
	SearchResultPage searchPage;

	@BeforeMethod
	public void setup() {
		driver = inicializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.searchTextField(dataprop.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.getSearchedProduct());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.searchTextField(dataprop.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
		//Assert.assertEquals(searchPage.noSearchedProduct(), dataprop.getProperty("noSearchedProductWarning"),
				//"There is no product message is not displayed");
		
		Assert.assertEquals(searchPage.noSearchedProduct(), "abcd",
				"There is no product message is not displayed");
	}

	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyData() {

		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.noSearchedProduct(), dataprop.getProperty("noSearchedProductWarning"),
				"There is no product message is not displayed");
	}
}
