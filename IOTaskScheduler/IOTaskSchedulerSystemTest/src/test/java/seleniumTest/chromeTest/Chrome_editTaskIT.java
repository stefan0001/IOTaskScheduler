package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_editTaskIT;

public class Chrome_editTaskIT extends WebDriver_editTaskIT {

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
