package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_overviewIT;

public class Safari_overviewIT extends WebDriver_overviewIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}
}
