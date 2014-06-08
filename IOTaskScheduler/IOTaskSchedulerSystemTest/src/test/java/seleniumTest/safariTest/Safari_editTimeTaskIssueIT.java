package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_editTimeTaskIssueIT;

public class Safari_editTimeTaskIssueIT extends WebDriver_editTimeTaskIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}

}
