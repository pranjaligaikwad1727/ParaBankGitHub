package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.pageObjects.UpdateContactInfoPage;
import com.qa.reusableComponents.ReadExcel;
import com.qa.testComponents.BaseClass;
import com.qa.testComponents.TestRetryAnalyzer;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class UpdateContactInfoSuite extends BaseClass{
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("To Verify that the user able to update address and phone number field from Update profile page")
	@Epic("Update Contact Info")
	@Feature("Update Contact Info")
	@Story("Update Profile")
	@Test(groups= {"Regression", "Smoke"},enabled=true,priority=1,retryAnalyzer = TestRetryAnalyzer.class,description = "To Verify that the user able to update address and phone number field from Update profile page")
	public void validateWhetherTheUserAbleToUpdateContactInfo() throws Throwable
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
		Map<String, String> input=ReadExcel.getExcelData(filePath, "Update Contact Info", "TC_UCI_001");
		
		LoginPage loginPage= new LoginPage(getDriver());
		loginPage.loginIntoApplication(input.get("username"), input.get("password"));
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnUpdateContactInfoTab();
		UpdateContactInfoPage updatePage= new UpdateContactInfoPage(getDriver());
		updatePage.updateContactInformation(input.get("address"), input.get("city"), input.get("state"), input.get("zipCode"),input.get("phone"));
		
		Assert.assertTrue(updatePage.verifyUpdateProfileMsg(input.get("updateSuccessMsg")), "User is able to update contact information");
	
	    
	}
}
