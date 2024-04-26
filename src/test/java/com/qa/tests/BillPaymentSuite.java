package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pageObjects.BillPayPage;
import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.reusableComponents.ReadExcel;
import com.qa.testComponents.BaseClass;
import com.qa.testComponents.TestRetryAnalyzer;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class BillPaymentSuite extends BaseClass{


	@Severity(SeverityLevel.CRITICAL)
	@Description("To Verify that user able to pay the bill to the Payee account")
	@Epic("Bill Pay")
	@Feature("Bill Pay")
	@Story("Bill Payment")
	@Test(groups= {"Regression", "Smoke"},enabled=true,priority=1,retryAnalyzer = TestRetryAnalyzer.class,description = "To Verify that user able to pay the bill to the Payee account")
	public void validateWhetherTheUserAbleBillPayment() throws Throwable
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
		Map<String, String> input=ReadExcel.getExcelData(filePath, "Bill Pay", "TC_BP_001");
		
		LoginPage loginPage= new LoginPage(getDriver());
		loginPage.loginIntoApplication(input.get("username"), input.get("password"));
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnBillPayTab();
		BillPayPage billPayPage= new BillPayPage(getDriver());
		billPayPage.billPaymentService(input.get("payeeName"), input.get("address"),input.get("city"), input.get("state"),
			input.get("zipCode"), input.get("phone"),input.get("account"),input.get("verifyAccount"),input.get("amount"));
		
		Assert.assertTrue(billPayPage.verifyBillPaymentCompletedMsg(input.get("billPaySuccessMsg")), "User is able to complete bill payment process");
	
	    
	}
}
