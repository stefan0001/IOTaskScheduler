package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_editTaskIT;

public class Safari_editTaskIT extends WebDriver_editTaskIT {

	@Override
	public void initializeWebDriver() {
		driver = new SafariDriver();
	}
}
