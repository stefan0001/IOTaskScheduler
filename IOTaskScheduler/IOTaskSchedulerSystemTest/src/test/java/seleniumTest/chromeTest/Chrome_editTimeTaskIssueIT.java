package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_editTimeTaskIssueIT;

public class Chrome_editTimeTaskIssueIT extends WebDriver_editTimeTaskIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
