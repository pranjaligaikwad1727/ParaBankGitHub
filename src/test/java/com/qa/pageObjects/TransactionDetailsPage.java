package com.qa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.reports.AllureListener;
import com.qa.reports.ExtentLogger;
import com.qa.reusableComponents.CommonMethods;

import io.qameta.allure.Step;

public class TransactionDetailsPage {

	WebDriver driver;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> transactionDetails;
	
	@FindBy(css="[type='submit']")
	List<WebElement> btnFindTransactions;
	
	@FindBy(xpath="[href*='transaction']")
	List<WebElement> transactions;
	
	@FindBy(css = "[href*='findtrans']")
	WebElement findTransactionTab;
	
	@FindBy(id = "criteria.transactionId")
	WebElement txtTransactionId;
	

	
	public TransactionDetailsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Step("Get Account Number from Account Details page")
	public String getTransactionIdFromTransactionDetailsPage()
	{
		String transactionId = null;
		try {
			Thread.sleep(2000);
			CommonMethods.waitForElement(driver, "Transaction Id",transactionDetails.get(0));
			transactionId=transactionDetails.get(0).getText().trim();
		    ExtentLogger.pass("Transaction Id captured from Transaction Details page :"+transactionId);
			AllureListener.saveTextLog("Transaction Id captured from Transaction Details page :"+transactionId);
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("Not able to capture Transaction Id  from Transaction Details page");
			AllureListener.saveTextLog("Not able to capture Transaction Id  from Transaction Details page");
		}
		return transactionId;
	}
	

	@Step("Click On Find Transaction Tab")
	public void clickOnFindTransactionTab() {
		CommonMethods.waitForElement(driver,"Find Transaction Tab", findTransactionTab);
		CommonMethods.click_custom(findTransactionTab, "Find Transaction Tab");
	}
	
	@Step("Enter Transaction Id")
	public void enterTransactionId(String transactionId) {
		CommonMethods.waitForElement(driver, "Transaction Id", txtTransactionId);
		CommonMethods.sendKeys_custom(txtTransactionId, "Transaction Id", transactionId);

	}
	
	@Step("Click on Find Transactions Button from Transaction Details page By Transaction id")
	public void clickOnBtnFindTransactionsById()
	{	
		CommonMethods.waitForElement(driver, "Find Transactions", btnFindTransactions.get(0));
		btnFindTransactions.get(0).click();
		
	}
	
	@Step("Click on Transactions from Transaction Details page")
	public void clickOnTransactionsFromFindTransactionPage(String transactionDesc,String index)
	{
		List<WebElement> transactions=driver.findElements(By.xpath("//*[@id='transactionTable']/tbody/tr["+index+"]/td[2]/a"));
		int transDescription=Integer.parseInt(transactionDesc);
		CommonMethods.waitForElement(driver, "Transactions", transactions.get(transDescription));
		transactions.get(transDescription).click();
		
	}
}
