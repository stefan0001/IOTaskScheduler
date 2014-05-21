package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_editTaskIT;

public class Firefox_editTaskIT extends WebDriver_editTaskIT {

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
