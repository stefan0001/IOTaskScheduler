package seleniumTest.firefoxTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import seleniumTest.webDriver.WebDriver_createTaskIT;


public class Firefox_createTaskIT extends WebDriver_createTaskIT{

	@Override
	public void initializeWebDriver() {
		driver = new FirefoxDriver();
	}

}
