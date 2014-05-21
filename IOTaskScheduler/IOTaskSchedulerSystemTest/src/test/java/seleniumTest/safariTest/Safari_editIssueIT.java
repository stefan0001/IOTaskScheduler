package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_editIssueIT;

public class Safari_editIssueIT extends WebDriver_editIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}
}
