package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_editIssueIT;

public class Chrome_editIssueIT extends WebDriver_editIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}
}
