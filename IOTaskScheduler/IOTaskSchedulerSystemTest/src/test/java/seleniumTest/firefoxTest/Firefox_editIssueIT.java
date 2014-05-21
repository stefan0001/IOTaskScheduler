package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_editIssueIT;

public class Firefox_editIssueIT extends WebDriver_editIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
