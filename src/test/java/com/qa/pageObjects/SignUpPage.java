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

public class SignUpPage  {

	WebDriver driver;
	AllureListener allureListener= new AllureListener();

	@FindBy (linkText = "Register")
	WebElement register;
	
	@FindBy(name = "customer.firstName")
	WebElement firstName;

	@FindBy(name = "customer.lastName")
	WebElement lastName;
	
	@FindBy(name = "customer.address.street")
	WebElement address;
	
	@FindBy(name = "customer.address.city")
	WebElement city;
	
	@FindBy(name = "customer.address.state")
	WebElement state;
	
	@FindBy(name = "customer.address.zipCode")
	WebElement zipCode;

	@FindBy(name = "customer.phoneNumber")
	WebElement phoneNumber;

	@FindBy(name = "customer.ssn")
	WebElement sSN;

	@FindBy(name = "customer.username")
	WebElement userName;
	
	@FindBy(name = "customer.password")
	WebElement password;
	
	@FindBy(name = "repeatedPassword")
	WebElement confirmPassword;
	
	@FindBy(css = "[value='Register']")
	WebElement submit;
	
	@FindBy(css = "[id='rightPanel'] p")
	WebElement signUpSuccessMsg;
	

	
	
	
	
	public SignUpPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@Step("Click on Register Link for Sign Up user details")
	public void clickOnRegisterLink() {
		CommonMethods.waitForElement(driver, "Register Link", register);
		CommonMethods.click_custom(register, "Register Link");

	}
	@Step("Enter First Name of the user")
	public void enterFirstName(String firstname) {
		CommonMethods.waitForElement(driver, "First Name", firstName);
		CommonMethods.sendKeys_custom(firstName, "First Name", firstname);
		
	}
	
	@Step("Enter Last Name of the user")
	public void enterLastName(String lastname) {
		CommonMethods.waitForElement(driver, "Last Name", lastName);
		CommonMethods.sendKeys_custom(lastName, "Last Name", lastname);
	}
	
	@Step("Enter Address details of the user")
	public void enterAddress(String addressDetails) {
		CommonMethods.waitForElement(driver, "Address", address);
		CommonMethods.sendKeys_custom(address, "Address", addressDetails);
	}
	
	@Step("Enter City name")
	public void enterCityName(String cityName) {
		CommonMethods.waitForElement(driver, "City", city);
		CommonMethods.sendKeys_custom(city, "City", cityName);
	}
	
	@Step("Enter State name")
	public void enterStateName(String stateName) {
		CommonMethods.waitForElement(driver, "State", state);
		CommonMethods.sendKeys_custom(state, "State", stateName);
	}

	@Step("Enter Zip Code")
	public void enterZipCode(String zipCodeNumber) {
		CommonMethods.waitForElement(driver, "Zip Code", zipCode);
		CommonMethods.sendKeys_custom(zipCode, "Zip Code", zipCodeNumber);
	}
	
	@Step("Enter Phone number of the user")
	public void enterPhoneNumber(String phoneNo) {
		CommonMethods.waitForElement(driver, "Phone Number", phoneNumber);
		CommonMethods.sendKeys_custom(phoneNumber, "Phone Number", phoneNo);
	}
	
	
	@Step("Enter Social Security Number (SSN)")
	public void enterSSN(String ssNumber) {
		CommonMethods.waitForElement(driver, "SSN", sSN);
		CommonMethods.sendKeys_custom(sSN, "SSN", ssNumber);
	}
	
	@Step("Enter Username")
	public void enterUsername(String UserName) {
		CommonMethods.waitForElement(driver, "UserName",userName);
		CommonMethods.sendKeys_custom(userName, "UserName", UserName);
	}
	
	@Step("Enter Password")
	public void enterPassword(String Password) {
		CommonMethods.waitForElement(driver, "Password",password);
		CommonMethods.sendKeys_custom(password, "Password", Password);
	}
	@Step("Enter Confirm Password")
	public void enterConfirmPassword(String passwordConfirm) {
		CommonMethods.waitForElement(driver, "Confirm Password",confirmPassword);
		CommonMethods.sendKeys_custom(confirmPassword, "Confirm Password", passwordConfirm);
	}
	
	@Step("Click on Register Button for submit user details")
	public void clickOnRegisterButtonForSubmitUserSignUpForm() 
	{
		allureListener.saveScreenShot(driver);
		CommonMethods.waitForElement(driver, "Register Button", submit);
		CommonMethods.click_custom(submit, "Register Button");

	}
	
	@Step("Verify User Successfully Compelted Sign Up Process and Logged into application ")
	public boolean verifyUserSuccessfullyCompletedSignUpProcess(String expecetdSuceessText)
	{
		boolean successTextStatus=false;
		try {
			if(signUpSuccessMsg.getText().trim().equalsIgnoreCase(expecetdSuceessText))
			{
				successTextStatus=true;
				ExtentLogger.pass("Success Text :"+signUpSuccessMsg.getText());
				AllureListener.saveTextLog("Success Text :"+signUpSuccessMsg.getText());
			}
			else
			{
				successTextStatus=false;
				ExtentLogger.fail("User is not able to complete SignUp Process");
				AllureListener.saveTextLog("User is not able to complete SignUp Process");
				Assert.fail("User is not able to complete SignUp Process");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User is not able to complete SignUp Process");
			AllureListener.saveTextLog("User is not able to complete SignUp Process");
			Assert.fail("User is not able to complete SignUp Process");
		}
		return successTextStatus;
	}



}
