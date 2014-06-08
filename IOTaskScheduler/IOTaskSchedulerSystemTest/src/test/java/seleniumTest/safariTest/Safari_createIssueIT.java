package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_createIssueIT;

public class Safari_createIssueIT extends WebDriver_createIssueIT {

	@Override
	protected void initializeWebDriver() {
		driver = new SafariDriver();
	}

}
