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

public class BillPayPage {
	WebDriver driver;

	@FindBy(name = "payee.name")
	WebElement txtPayeeName;
	
	@FindBy(name = "payee.address.street")
	WebElement txtPayeeAddress;
	
	@FindBy(name = "payee.address.city")
	WebElement txtPayeeCity;
	
	@FindBy(name = "payee.address.state")
	WebElement txtPayeeState;
	
	@FindBy(name = "payee.address.zipCode")
	WebElement txtPayeeZipCode;
	
	@FindBy(name = "payee.phoneNumber")
	WebElement txtPayeePhone;
	
	@FindBy(name = "payee.accountNumber")
	WebElement txtPayeeAccountNumber;
	
	@FindBy(name = "verifyAccount")
	WebElement txtVerifyAccountNumber;
	
	@FindBy(name = "amount")
	WebElement txtPayeeAmount;
	
	@FindBy(name = "fromAccountId")
	WebElement drpFromAccountId;
	
	@FindBy(css = "[value='Send Payment']")
	WebElement btnSendPayment;
	
	@FindBy(xpath = "//*[@id='rightPanel']/div/div[2]/h1")
	WebElement successBillPaymentMsg;
	
	public BillPayPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@Step("Enter Payee Information for Bill Payment Service")
	public void billPaymentService(String name, String address,String city,String state,String zipCode,String phone,String accountNumber,String verifyAccount,String amount) throws Throwable {
		enterPayeeName(name);
		enterPayeeAddress(address);
		enterCity(city);
		enterState(state);
		enterZipCode(zipCode);
		enterPhone(phone);
		enterAccount(accountNumber);
		enterVerifyAccount(verifyAccount);
		enterAmount(amount);
		clickOnSendPaymentButton();
	}
	
	@Step("Enter Payee Name")
	public void enterPayeeName(String name) {
		CommonMethods.waitForElement(driver, "Payee Name", txtPayeeName);
		CommonMethods.sendKeys_custom(txtPayeeName, "Payee Name", name);

	}
	
	@Step("Enter Payee Address")
	public void enterPayeeAddress(String address) {
		CommonMethods.waitForElement(driver, "Payee Address", txtPayeeAddress);
		CommonMethods.sendKeys_custom(txtPayeeAddress, "Payee Address", address);

	}
	
	@Step("Enter City")
	public void enterCity(String city) {
		CommonMethods.waitForElement(driver, "City", txtPayeeCity);
		CommonMethods.sendKeys_custom(txtPayeeCity, "City", city);

	}
	
	@Step("Enter State")
	public void enterState(String state) {
		CommonMethods.waitForElement(driver, "State", txtPayeeState);
		CommonMethods.sendKeys_custom(txtPayeeState, "State", state);

	}
	
	@Step("Enter Zip Code")
	public void enterZipCode(String zipCode) {
		CommonMethods.waitForElement(driver, "zipCode", txtPayeeZipCode);
		CommonMethods.sendKeys_custom(txtPayeeZipCode, "zipCode", zipCode);

	}
	
	@Step("Enter Phone Number")
	public void enterPhone(String phone) {
		CommonMethods.waitForElement(driver, "Phone Number", txtPayeePhone);
		CommonMethods.sendKeys_custom(txtPayeePhone, "Phone Number", phone);

	}
	
	@Step("Enter Account Number")
	public void enterAccount(String accountNumber) {
		CommonMethods.waitForElement(driver, "Account Number", txtPayeeAccountNumber);
		CommonMethods.sendKeys_custom(txtPayeeAccountNumber, "Account Number", accountNumber);

	}
	@Step("Enter Verify Account Number")
	public void enterVerifyAccount(String accountNumber) {
		CommonMethods.waitForElement(driver, "Account Number", txtVerifyAccountNumber);
		CommonMethods.sendKeys_custom(txtVerifyAccountNumber, "Account Number", accountNumber);

	}
	
	@Step("Enter Amount")
	public void enterAmount(String amount) {
		CommonMethods.waitForElement(driver, "Amount", txtPayeeAmount);
		CommonMethods.sendKeys_custom(txtPayeeAmount, "Amount", amount);

	}
	
	@Step("Select From Account Id")
	public void selectFromAccount(String fromAccountId) throws Throwable {
		CommonMethods.waitForElement(driver, "From Account Id", drpFromAccountId);
		CommonMethods.selectDropDownByValue_custom(drpFromAccountId, "From account Id", fromAccountId);
	}
	
	@Step("Click on Send Payment button")
	public void clickOnSendPaymentButton() {
		CommonMethods.waitForElement(driver, "Send Payment", btnSendPayment);
		CommonMethods.click_custom(btnSendPayment, "Send Payment");

	}
	
	@Step("verify whether the user able to complete Bill Payment Process")
	public boolean verifyBillPaymentCompletedMsg(String expectedMsg) {

		boolean flag = false;
		CommonMethods.waitForElement(driver, "Bill Payment Messge", successBillPaymentMsg);
		String successText=successBillPaymentMsg.getAttribute("innerHTML").trim();
		try {
			if(successText.equalsIgnoreCase(expectedMsg))
			{
				flag = true;
				ExtentLogger.pass("Success Text :"+successText);
				AllureListener.saveTextLog("Success Text :"+successText);
			}
			else
			{
				flag=false;
				ExtentLogger.fail("User is not able to complete Bill Payment Process");
				AllureListener.saveTextLog("User is not able to complete Bill Payment Process");
				Assert.fail("User is not able to complete Bill Payment Process");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User is not able to complete Bill Payment Process");
			AllureListener.saveTextLog("User is not able to complete Bill Payment Process");
			Assert.fail("User is not able to complete Bill Payment Process");
		}
		return flag;
	}
}

