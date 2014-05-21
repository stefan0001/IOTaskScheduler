package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_overviewIT;

public class Firefox_overviewIT extends WebDriver_overviewIT {

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
