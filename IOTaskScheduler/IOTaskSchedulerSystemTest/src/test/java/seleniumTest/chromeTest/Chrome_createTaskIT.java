package seleniumTest.chromeTest;

import org.openqa.selenium.chrome.ChromeDriver;

import seleniumTest.webDriver.WebDriver_createTaskIT;

public class Chrome_createTaskIT extends WebDriver_createTaskIT{

	@Override
	public void initializeWebDriver() {
		driver = new ChromeDriver();
	}

}
