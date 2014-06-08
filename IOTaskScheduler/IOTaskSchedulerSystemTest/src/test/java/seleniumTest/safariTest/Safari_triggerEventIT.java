package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_triggerEventIT;

public class Safari_triggerEventIT extends WebDriver_triggerEventIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}

}
