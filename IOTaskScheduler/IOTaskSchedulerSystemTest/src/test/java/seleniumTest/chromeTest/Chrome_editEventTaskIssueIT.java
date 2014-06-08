package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_editEventTaskIssueIT;

public class Chrome_editEventTaskIssueIT extends WebDriver_editEventTaskIssueIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
