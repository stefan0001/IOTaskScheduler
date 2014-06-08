package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_triggerEventIT;

public class Chrome_triggerEventIT extends WebDriver_triggerEventIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
