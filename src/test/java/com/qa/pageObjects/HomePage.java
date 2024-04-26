package com.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.reports.AllureListener;
import com.qa.reports.ExtentLogger;
import com.qa.reusableComponents.CommonMethods;

import io.qameta.allure.Step;

public class HomePage {

	WebDriver driver;

	@FindBy(css = "[href*='openaccount']")
	WebElement openNewAccount;

	@FindBy(css = "[href*='overview']")
	WebElement accoutOverview;

	@FindBy(css = "[href*='transfer']")
	WebElement transferFunds;


	
	
	@FindBy(css = "[href*='billpay']")
	WebElement billPay;
	
	@FindBy(css = "[href*='updateprofile']")
	WebElement updateContactInfo;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}



	@Step("Click on Open New Account Tab")
	public void clickOnOpenNewAccountTab() {
		CommonMethods.waitForElement(driver,"Open New Account Tab", openNewAccount);
		CommonMethods.click_custom(openNewAccount, "Open New Account Tab");
	}

	@Step("Click on Accont Overview Tab ")
	public void clickOnAccountOverviewTab() {
		CommonMethods.waitForElement(driver,"Account Overview Tab", accoutOverview);
		CommonMethods.click_custom(accoutOverview, "Account Overview Tab");
	}
	
	@Step("Click on Transfer Funds Tab ")
	public void clickOnTransferFundsTab() {
		CommonMethods.waitForElement(driver,"Transfer Funds Tab", transferFunds);
		CommonMethods.click_custom(transferFunds, "Transfer Funds Tab");
	}
	
	@Step("Click on Bill Pay Tab")
	public void clickOnBillPayTab() {
		CommonMethods.click_custom(billPay, "Bill Pay");
	}
	
	@Step("Click on Update Contact Info Tab")
	public void clickOnUpdateContactInfoTab() {
		CommonMethods.click_custom(updateContactInfo, "Update Contact Info");
	}

}
