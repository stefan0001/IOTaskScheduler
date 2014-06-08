package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_editEventTaskIssueIT;

public class Safari_editEventTaskIssueIT extends WebDriver_editEventTaskIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}

}
