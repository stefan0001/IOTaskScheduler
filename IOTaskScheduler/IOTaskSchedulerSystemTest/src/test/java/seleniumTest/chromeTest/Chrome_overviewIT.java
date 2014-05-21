package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_overviewIT;

public class Chrome_overviewIT extends WebDriver_overviewIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
