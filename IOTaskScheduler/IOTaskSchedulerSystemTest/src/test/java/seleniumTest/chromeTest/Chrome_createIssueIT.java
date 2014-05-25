package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_createIssueIT;

public class Chrome_createIssueIT extends WebDriver_createIssueIT {

	@Override
	protected void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
