package com.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.reports.ExtentLogger;
import com.qa.reports.ExtentManager;
import com.qa.reports.ExtentReport;
import com.qa.reusableComponents.CommonMethods;

import io.qameta.allure.Step;

public class LoginPage  {

	WebDriver driver;

	@FindBy(name = "username")
	WebElement userID;

	@FindBy(name = "password")
	WebElement pass;

	@FindBy(css = "[value='Log In']")
	WebElement loginButton;


	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@Step("Login to the application")
	public void loginIntoApplication(String userId, String password) {
		enterUserID(userId);
		enterUserPassword(password);
		clickOnLoginButoon();
	}

	@Step("Enter User ID")
	public void enterUserID(String userId) {
		CommonMethods.waitForElement(driver, "User ID", userID);
		CommonMethods.sendKeys_custom(userID, "User ID", userId);
		

	}

	@Step("Enter User Password")
	public void enterUserPassword(String password) {
		CommonMethods.waitForElement(driver, "Password", pass);
		CommonMethods.sendKeys_custom(pass, "Password", password);
		
	}

	@Step("Click on Login button")
	public void clickOnLoginButoon() {
		CommonMethods.waitForElement(driver, "Login Button", loginButton);
		CommonMethods.click_custom(loginButton, "Login Button");
		

	}

	
}
