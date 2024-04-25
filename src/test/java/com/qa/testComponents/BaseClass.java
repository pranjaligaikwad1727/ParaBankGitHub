package com.qa.testComponents;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.reports.ExtentReport;

import io.qameta.allure.Step;

public class BaseClass {

	WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	@BeforeMethod(alwaysRun = true)
	public ThreadLocal<WebDriver> initialize_driver() throws Exception {

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			setUpChrome();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			setUpFireFox();
		}

		else if (browserName.equalsIgnoreCase("ie")) {

			setUpIE();

		}
		tdriver = openurl(url);
		return tdriver;

	}
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	@Step("Navigate to url :[0]")
	public ThreadLocal<WebDriver> openurl(String url) throws MalformedURLException {
		// String host=PropertiesOperations.getPropertyValueByKey("hosturl");
		// driver = new RemoteWebDriver(new URL(host), capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		tdriver.set(driver);
		tdriver.get();
		return tdriver;
	}

	@AfterMethod(alwaysRun = true)
	@Step("Closing Browser")
	public void tearDown() {
		BaseClass.getDriver().quit();

	}

	@Step("Chrome Browser Opened ")
	public void setUpChrome() {

		driver = new ChromeDriver();
	}

	@Step("FireFox Browser Opened ")
	public void setUpFireFox() {

		driver = new FirefoxDriver();
	}

	@Step("IE Browser Opened ")
	public void setUpIE() {
		driver = new EdgeDriver();
	}

}
