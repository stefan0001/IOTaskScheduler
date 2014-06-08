package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_editEventTaskIssueIT;

public class Firefox_editEventTaskIssueIT extends WebDriver_editEventTaskIssueIT{

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
