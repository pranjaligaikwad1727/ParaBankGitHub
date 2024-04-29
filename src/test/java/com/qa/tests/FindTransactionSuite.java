package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pageObjects.AccountOverviewPage;
import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.pageObjects.TransactionDetailsPage;
import com.qa.pageObjects.TransferFundsPage;
import com.qa.reusableComponents.ReadExcel;
import com.qa.testComponents.BaseClass;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners({ AllureListener.class })
@Listeners(com.qa.reports.ExtentListener.class)
@Epic("Account services")
@Feature("Account services_Find Transactions")
public class FindTransactionSuite extends BaseClass {

	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_FT_001:To Verify that the user able to find the transaction details by the Transaction ID")
	@Story("Find Transaction By Transactin Id")
	@Test(groups= {"Regression", "Smoke"},enabled=true,priority=1,description = "TC_FT_001:To Verify that the user able to find the transaction details by the Transaction ID")
	public void TC_BP_001_validateWhetherTheUserAbleToFindTransactionById() throws Throwable
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
		Map<String, String> input=ReadExcel.getExcelData(filePath, "Find Transaction", "TC_FT_001");
		
		LoginPage loginPage= new LoginPage(getDriver());
		loginPage.loginIntoApplication(input.get("username"), input.get("password"));
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnTransferFundsTab();
		TransferFundsPage transferFunds=new TransferFundsPage(getDriver());		
		transferFunds.enterTransferFundAmount(input.get("amount"));
		transferFunds.clickOnTransferButton();
		homePage.clickOnAccountOverviewTab();
		AccountOverviewPage accountOverview = new AccountOverviewPage(getDriver());
		accountOverview.clickOnAccountNumberFromAccountOverviewPage();
		accountOverview.clickOnTransactionsFromTransactionDetailsPage(input.get("transactionDesc"),input.get("index"));
		TransactionDetailsPage transactionDetail=new TransactionDetailsPage(getDriver());
		String actualtransactionId = transactionDetail.getTransactionIdFromTransactionDetailsPage();	 
		transactionDetail.clickOnFindTransactionTab();
		transactionDetail.enterTransactionId(actualtransactionId);
		transactionDetail.clickOnBtnFindTransactionsById();
		transactionDetail.clickOnTransactionsFromFindTransactionPage(input.get("transactionDesc"),input.get("index"));
		String expectedtransactionId = transactionDetail.getTransactionIdFromTransactionDetailsPage();
		Assert.assertEquals(actualtransactionId, expectedtransactionId);
	}
}
