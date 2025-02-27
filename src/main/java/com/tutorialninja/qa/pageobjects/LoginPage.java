package com.tutorialninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailfield;

	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmailField(String emailText) {
		emailfield.sendKeys(emailText);
	}

	public void enterPasswordField(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage Login(String emailText, String passwordText) {
		emailfield.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPasswordNotMatchingWarningMessageText() {
		String emailPasswordWarningText = emailPasswordNotMatchingWarning.getText();
		return emailPasswordWarningText;
	}
}
