package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_createIssueIT;

public class Firefox_createIssueIT extends WebDriver_createIssueIT {

	@Override
	protected void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
