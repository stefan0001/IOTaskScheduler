package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_editTimeTaskIssueIT;


public class Firefox_editTimeTaskIssueIT extends WebDriver_editTimeTaskIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
