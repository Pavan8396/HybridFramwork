package com.tutorialninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstNameText;

	@FindBy(xpath = "//input[@id='input-lastname']")
	private WebElement lastNameText;
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailText;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	private WebElement telephoneText;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordText;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	private WebElement confirmPasswordText;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement AgreeCheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement RegisterButton;
	
	@FindBy(xpath = "//label[normalize-space()='Yes']//input[@name='newsletter']")
	private WebElement selectYesInNewsletter;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement EmailWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void firstNameField(String firstName) {
		firstNameText.sendKeys(firstName);
	}
	
	public void lastNameField(String lastName) {
		lastNameText.sendKeys(lastName);
	}
	
	public void emailField(String email) {
		emailText.sendKeys(email);
	}
	
	public void telephoneField(String telephone) {
		telephoneText.sendKeys(telephone);
	}
	
	public void passwordField(String password) {
		passwordText.sendKeys(password);
	}
	
	public void confirmPaswordField(String confirmPassword) {
		confirmPasswordText.sendKeys(confirmPassword);
	}
	
	public void agreeCheckboxOption() {
		AgreeCheckBox.click();
	}
	
	public AccountSuccessPage registerButtonOption() {
		RegisterButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesInNewsLetterOption() {
		selectYesInNewsletter.click();
	}
	
	public String retriveDuplicateEmailWarning() {
		String duplicateEmailWaringMessage = duplicateEmailWarning.getText();
		return duplicateEmailWaringMessage;
	}
	
	public String retriveprivacyPolicyWarning() {
		String privacyPolicyWaringMessage = privacyPolicyWarning.getText();
		return privacyPolicyWaringMessage;
	}
	
	public String retriveFirstNameWarning() {
		String firstNameWaringMessage = firstNameWarning.getText();
		return firstNameWaringMessage;
	}
	
	public String retriveLastNameWarning() {
		String lastNameWaringMessage = lastNameWarning.getText();
		return lastNameWaringMessage;
	}
	
	public String retriveEmailWarning() {
		String EmailWaringMessage = EmailWarning.getText();
		return EmailWaringMessage;
	}
	
	public String retriveTelephoneWarning() {
		String telephoneWaringMessage = telephoneWarning.getText();
		return telephoneWaringMessage;
	}
	
	public String retrivePasswordWarning() {
		String passwordWaringMessage = passwordWarning.getText();
		return passwordWaringMessage;
	}
	
	public AccountSuccessPage registerWithOnlyMandatoryFields(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
		firstNameText.sendKeys(firstName);
		lastNameText.sendKeys(lastName);
		emailText.sendKeys(email);
		telephoneText.sendKeys(telephone);
		passwordText.sendKeys(password);
		confirmPasswordText.sendKeys(confirmPassword);
		AgreeCheckBox.click();
		RegisterButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
		firstNameText.sendKeys(firstName);
		lastNameText.sendKeys(lastName);
		emailText.sendKeys(email);
		telephoneText.sendKeys(telephone);
		passwordText.sendKeys(password);
		confirmPasswordText.sendKeys(confirmPassword);
		selectYesInNewsletter.click();
		AgreeCheckBox.click();
		RegisterButton.click();
		return new AccountSuccessPage(driver);
	}
}
