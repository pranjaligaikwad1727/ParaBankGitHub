package com.qa.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.reports.AllureListener;
import com.qa.reports.ExtentLogger;
import com.qa.reusableComponents.CommonMethods;

import io.qameta.allure.Step;

public class AccountOverviewPage  {

	WebDriver driver;
	
	@FindBy(xpath="//h1[text()='Accounts Overview']")
	WebElement acountOverviewHeader;
	
	@FindBy(xpath="//td/a")
	List<WebElement> accountNumbers;

	@FindBy(xpath="//h1[text()='Account Details']")
	WebElement accountDetails;
	
	@FindBy(id="accountId")
	WebElement accountNumberDetails;
	
	
	public AccountOverviewPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Step("Validate user land on Account Overview page and Account Overview text displayed on the header of page")
	public boolean validateAccountOverviewTextDisplayedOnHeader()
	{
		boolean textDisplayed=false;
		try {
			CommonMethods.waitForElement(driver, "Account Overview Tab", acountOverviewHeader);
			if(acountOverviewHeader.isDisplayed())
			{
				textDisplayed=true;
				ExtentLogger.pass("User is land on Account Overview Page");
				AllureListener.saveTextLog("User is land on Account Overview Page");
			}
			else {
				textDisplayed=false;
				AllureListener.saveTextLog("User is not able land on Account Overview Page");
				Assert.fail("User is not able land on Account Overview Page");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User is not able land on Account Overview Page");
			AllureListener.saveTextLog("User is not able land on Account Overview Page");
			Assert.fail("User is not able land on Account Overview Page");

		}
		
		return textDisplayed ;
	}
	
	@Step("Get Account Number from Account Overview page")
	public int getAccountNumberFromAccountOverviewPage()
	{
		int accountNumberAO=0;
		try {
			
			CommonMethods.waitForElement(driver, "Account Numbers", accountNumbers.get(0));	
		    accountNumberAO= Integer.parseInt(accountNumbers.get(0).getText());
		    ExtentLogger.pass("Account Number captured from Account Overview page :"+accountNumberAO);
			AllureListener.saveTextLog("Account Number captured from Account Overview page :"+accountNumberAO);
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("Not able to capture Account Number from Account Overview page");
			AllureListener.saveTextLog("Not able to capture Account Number from Account Overview page");
		}

		return accountNumberAO;
		}
	
	@Step("Click on Account Number from Account Overview page")
	public void clickOnAccountNumberFromAccountOverviewPage()
	{
		CommonMethods.waitForElement(driver, "Account Numbers", accountNumbers.get(0));
		accountNumbers.get(0).click();
		
	}
	
	
	@Step("Validate Account Details text is displayed on header")
	public boolean validateAccountDetailsTextDisplyedOnHeader()
	{
		boolean textDisplayed=false;
		try {
			CommonMethods.waitForElement(driver, "Account Details Header Text", accountDetails);
			if(accountDetails.isDisplayed()) 
			{
				textDisplayed=true;
				ExtentLogger.pass("Account Details text is displayed on header of page");
				AllureListener.saveTextLog("Account Details text is displayed on header of page");
				
			}
			else {
				textDisplayed=false;
				ExtentLogger.fail("Account Details text is not displayed on header of page");
				AllureListener.saveTextLog("Account Details text is not displayed on header of page");
				Assert.fail("Account Details text is not displayed on header of page");
			}

		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("Account Details text is not displayed on header of page");
			AllureListener.saveTextLog("Account Details text is not displayed on header of page");
			Assert.fail("Account Details text is not displayed on header of page");
		}
		return textDisplayed;
	}
	
	@Step("Get Account Number from Account Details page")
	public int getAccountNumberFromAccountDetailsPage()
	{
		int accountNumberAD=0;
		try {
			Thread.sleep(2000);
			CommonMethods.waitForElement(driver, "Account Number_Account Details", accountNumberDetails);
		    accountNumberAD=Integer.parseInt(accountNumberDetails.getText());
		    ExtentLogger.pass("Account Number captured from Account Details page :"+accountNumberAD);
			AllureListener.saveTextLog("Account Number captured from Account Details page :"+accountNumberAD);
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("Not able to capture Account Number from Account Details page");
			AllureListener.saveTextLog("Not able to capture Account Number from Account Details page");
		}
		return accountNumberAD;
	}



}
