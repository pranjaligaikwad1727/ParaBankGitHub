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

public class OpenNewAccount {

	WebDriver driver;

	@FindBy(css = "[href*='openaccount']")
	WebElement openNewAccount;

	@FindBy(css = "#type")
	WebElement accountTypeDropdown;

	@FindBy(css = "[value='Open New Account']")
	WebElement openNewAccount_submit;

	@FindBy(css = ".ng-scope p:nth-of-type(1)")
	WebElement accountOpenedMsg;

	@FindBy(css = ".ng-scope p:nth-of-type(2)")
	WebElement accountNumber;

	@FindBy(xpath = "//h1[text()='Account Opened!']")
	WebElement accountOpened;

	public OpenNewAccount(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@Step("Validate specified Account Type option is available in dropdown")
	public boolean validateSpecifiedAccountTypeOptionAvailableInDrpdown(String accountType) {
		
		CommonMethods.waitForElement(driver, "Account Type", accountTypeDropdown);
		return CommonMethods.validateSpecifiedTextPresentInDropdown(accountTypeDropdown,"Account Type", accountType);
	}

	@Step("Select Account Type from the dropdown")
	public void selectAccountTypeFromDropdown(String ddVisibleText) {
		CommonMethods.waitForElement(driver, "Account Type", accountTypeDropdown);
		CommonMethods.selectDropDownByVisibleText_custom(accountTypeDropdown, "Account Type", ddVisibleText);
	}

	@Step("Click to Open New Account Button")
	public void clickOnOpenNewAccountButton() throws InterruptedException {
		Thread.sleep(2000);
		CommonMethods.waitForElementClickable(driver, "Open New Account Button", openNewAccount_submit);
		CommonMethods.click_custom(openNewAccount_submit, "Open New Account Button");
	}

	@Step("Validate the New Account Opened Sucessfully or not")
	public boolean validateNewAccountOpenedSuccessfullyOrNot(String expectedSuccessMsg) throws InterruptedException {
		boolean accountStatus = false;
		try {
			CommonMethods.waitForElement(driver, "Account Opened Success Text ", accountOpened);
			if (accountOpenedMsg.getText().equalsIgnoreCase(expectedSuccessMsg)) {
				accountStatus = true;
				ExtentLogger.pass("Success Message :" + accountOpenedMsg.getText());
				AllureListener.saveTextLog("Success Message :" + accountOpenedMsg.getText());
			} else {
				accountStatus = false;
				ExtentLogger.fail("User not able to open new account");
				AllureListener.saveTextLog("User not able to open new account");
                Assert.fail("User not able to open new account");
			}

		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User not able to open new account");
			AllureListener.saveTextLog("User not able to open new account");
            Assert.fail("User not able to open new account");
		}

		return accountStatus;
	}


}
