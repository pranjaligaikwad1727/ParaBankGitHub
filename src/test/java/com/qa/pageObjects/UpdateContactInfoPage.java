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

public class UpdateContactInfoPage {
	WebDriver driver;

	@FindBy(name = "customer.firstName")
	WebElement txtFirstName;
	
	@FindBy(name = "customer.lastName")
	WebElement txtLastName;
	
	@FindBy(name = "customer.address.street")
	WebElement txtAddress;
	
	@FindBy(name = "customer.address.city")
	WebElement txtCity;
	
	@FindBy(name = "customer.address.state")
	WebElement txtstate;
	
	@FindBy(name = "customer.address.zipCode")
	WebElement txtZipCode;
	
	@FindBy(name = "customer.phoneNumber")
	WebElement txtPhoneNumber;
	
	@FindBy(css = "[value='Update Profile']")
	WebElement btnUpdateProfile;
	
	@FindBy(css = "[id='rightPanel'] p")
	WebElement updateProfileMsg;

	public UpdateContactInfoPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@Step("Enter Update Contact Information")
	public void updateContactInformation(String address,String city,String state,String zipCode,String phoneNumber) throws Throwable {
		
		enterAddress(address);
		enterCityName(city);
		enterStateName(state);
		enterZipCode(zipCode);
		enterPhoneNumber(phoneNumber);
		clickOnUpdateProfileButton();
		
	}
	
	@Step("Enter First Name of the user")
	public void enterFirstName(String firstname) {
		CommonMethods.waitForElement(driver, "First Name", txtFirstName);
		CommonMethods.clear_custom(txtFirstName, "First Name");
		CommonMethods.sendKeys_custom(txtFirstName, "First Name", firstname);
	
	}
	
	@Step("Enter Last Name of the user")
	public void enterLastName(String lastname) {
		CommonMethods.waitForElement(driver, "Last Name", txtLastName);
		CommonMethods.clear_custom(txtLastName, "Last Name");
		CommonMethods.sendKeys_custom(txtLastName, "Last Name", lastname);
	}
	
	@Step("Enter Address details of the user")
	public void enterAddress(String addressDetails) {
		CommonMethods.waitForElement(driver, "Address", txtAddress);
		CommonMethods.clear_custom(txtAddress, "Address");
		CommonMethods.sendKeys_custom(txtAddress, "Address", addressDetails);
	}
	
	@Step("Enter City name")
	public void enterCityName(String cityName) {
		CommonMethods.waitForElement(driver, "City", txtCity);
		CommonMethods.clear_custom(txtCity, "City");
		CommonMethods.sendKeys_custom(txtCity, "City", cityName);
	}
	
	@Step("Enter State name")
	public void enterStateName(String stateName) {
		CommonMethods.waitForElement(driver, "State", txtstate);
		CommonMethods.clear_custom(txtstate, "State");
		CommonMethods.sendKeys_custom(txtstate, "State", stateName);
	}

	@Step("Enter Zip Code")
	public void enterZipCode(String zipCodeNumber) throws Throwable {
		CommonMethods.waitForElement(driver, "Zip Code", txtZipCode);
		CommonMethods.clear_custom(txtZipCode, "Zip Code");
		CommonMethods.sendKeys_custom(txtZipCode, "Zip Code", zipCodeNumber);
	}
	
	@Step("Enter Phone number of the user")
	public void enterPhoneNumber(String phoneNo) {
		CommonMethods.waitForElement(driver, "Phone Number", txtPhoneNumber);
		CommonMethods.clear_custom(txtPhoneNumber, "Phone Number");
		CommonMethods.sendKeys_custom(txtPhoneNumber, "Phone Number", phoneNo);
	}
	
	@Step("Click on Update Profile Button")
	public void clickOnUpdateProfileButton() 
	{
		CommonMethods.waitForElement(driver, "Update Profile Button", btnUpdateProfile);
		CommonMethods.click_custom(btnUpdateProfile, "Update Profile Button");

	}
	
	@Step("verify whether the user able to Update Contact Information")
	public boolean verifyUpdateProfileMsg(String expectedMsg) {

		boolean flag = false;
		CommonMethods.waitForElement(driver, "Updated Profile Message", updateProfileMsg);
		String updateMsg=updateProfileMsg.getText().trim();
		try {
			if(updateMsg.equalsIgnoreCase(expectedMsg))
			{
				flag = true;
				ExtentLogger.pass("Updated Message :"+updateMsg);
				AllureListener.saveTextLog("Updated Message :"+updateMsg);
			}
			else
			{
				flag=false;
				ExtentLogger.fail("User is not able to Update Contact Info");
				AllureListener.saveTextLog("User is not able to Update Contact Info");
				Assert.fail("User is not able to Update Contact Info");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User is not able to Update Contact Info");
			AllureListener.saveTextLog("User is not able to Update Contact Info");
			Assert.fail("User is not able to Update Contact Info");
		}
		return flag;
	}
}
