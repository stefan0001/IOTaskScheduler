package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_triggerEventIT;

public class Firefox_triggerEventIT extends WebDriver_triggerEventIT {

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
