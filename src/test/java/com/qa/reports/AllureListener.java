 package com.qa.reports;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.testComponents.BaseClass;

import io.qameta.allure.Attachment;




public class AllureListener implements ITestListener  {

	
	 private static String getTestMethodName(ITestResult iTestResult) { return
	 iTestResult.getMethod().getConstructorOrMethod().getName();
	} 

	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment
	public  byte[] saveScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}




	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in on Start method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BaseClass.getDriver());

	}

	
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in on Finish method and Closed Browser" + iTestContext.getName());		

	}

	
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in on Test Start method " + getTestMethodName(iTestResult) + " start");
	}

	
	public void onTestSuccess(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseClass.getDriver();
		// Allure ScreenShot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case: Passed" );//+ getTestMethodName(iTestResult)
			saveScreenShot(driver);
		}
		saveTextLog(" Test case passed -screenshot taken!");//getTestMethodName(iTestResult) + 
		System.out.println(" Test case passed -screenshot taken!");//+ getTestMethodName(iTestResult) +
	//	driver.quit();

	}

	
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseClass.getDriver();
		// Allure ScreenShot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveFailureScreenShot(driver);
		}
		saveTextLog(" Test case failed and screenshot taken!");	//getTestMethodName(iTestResult) + 
		//driver.quit();
	}



	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	

	
}

	
	

