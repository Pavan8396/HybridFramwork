package com.tutorialninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//i[@class='fa fa-user']")
	private WebElement myAccountMainMenu;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	private WebElement loginOptions;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchField;
	
	@FindBy(xpath = "//span[@class='input-group-btn']")
	private WebElement searchButton;
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount() {
		myAccountMainMenu.click();
	}
	
	public LoginPage clickOnLogin() {
		loginOptions.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegister() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void searchTextField(String validProduct) {
		searchField.sendKeys(validProduct);
	}
	
	public SearchResultPage clickOnSearchButton() {
		searchButton.click();
		return new SearchResultPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountMainMenu.click();
		loginOptions.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountMainMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
}
