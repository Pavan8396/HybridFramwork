package com.tutorialninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	WebDriver driver;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement NoProductMessage;
	
	@FindBy(xpath = "//a[normalize-space()='HP LP3065']")
	private WebElement productResult;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public boolean getSearchedProduct() {
		boolean HPproduct = productResult.isDisplayed();
		return HPproduct;
	}
	
	public String noSearchedProduct() {
		String noProductMessageText = NoProductMessage.getText();
		return noProductMessageText;
	}
}
