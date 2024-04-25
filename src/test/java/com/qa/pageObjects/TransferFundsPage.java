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

public class TransferFundsPage  {

	WebDriver driver;
	
	@FindBy(xpath = "//h1[text()='Transfer Funds']")
	WebElement transferFunds;	

	@FindBy(css = "#amount")
	WebElement amount;
	
	@FindBy(css = "[value='Transfer']")
	WebElement transfer;
	
	@FindBy(id = "fromAccountId")
	WebElement fromAccount;

	@FindBy(xpath = "//h1[text()='Transfer Complete!']")
	WebElement transferComplete;
	
	
	
	
	public TransferFundsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@Step("Verify whether the user land on Transfer Funds page")
	public boolean verifyUserLandingTransferFundsPage()
	{
		boolean textStatus=false;
		try {
			CommonMethods.waitForElement(driver, "Transfer Funds_Header Text", transferFunds);
			if(transferFunds.isDisplayed())
			{
				textStatus=true;
				ExtentLogger.pass("User Successfully land on Transfer Funds Page");
				AllureListener.saveTextLog("User Successfully land on Transfer Funds Page");
			}
			else {
				textStatus=true;
				ExtentLogger.fail("User not able to land on Transfer Funds Page");
				AllureListener.saveTextLog("User not able to land on Transfer Funds Page");
				Assert.fail("User not able to land on Transfer Funds Page");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User not able to land on Transfer Funds Page");
			AllureListener.saveTextLog("User not able to land on Transfer Funds Page");
			Assert.fail("User not able to land on Transfer Funds Page");
		}
		
		return textStatus ;
		
	}
	
	@Step(" Enter Transfer Fund Amount")
	public void enterTransferFundAmount(String transferFundAmount) throws Throwable
	{   Thread.sleep(2000);
		CommonMethods.waitForElement(driver, "Amount", amount);
		CommonMethods.sendKeys_custom(amount, "Amount", transferFundAmount);
	}
	
	@Step("Click on transfer button")
	public void clickOnTransferButton()
	{
		CommonMethods.waitForElement(driver, "Transfer", transfer);
		CommonMethods.click_custom(transfer, "Transfer");
	}

	@Step("Verify user able to transfer fund")
	public boolean verifyUserSucessfullyTransferedFund(String transferCompleteMsg)
	{
		boolean fundTransferStatus=false;
		try {
			CommonMethods.waitForElement(driver, "Transfer Complete_Header Text", transferComplete);
			if(transferComplete.getText().equalsIgnoreCase(transferCompleteMsg))
			{
				fundTransferStatus=true;
				ExtentLogger.pass("Success Message: "+transferComplete.getText());
				AllureListener.saveTextLog("Success Message: "+transferComplete.getText());
			}
			else
			{
				fundTransferStatus=false;
				ExtentLogger.fail("User not able to transfer the fund");
				AllureListener.saveTextLog("User not able to transfer the fund");
				Assert.fail("User not able to transfer the fund");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User not able to transfer the fund");
			AllureListener.saveTextLog("User not able to transfer the fund");
			Assert.fail("User not able to transfer the fund");
		}
		return fundTransferStatus;
	}



}
