package seleniumTest.safariTest;

import org.openqa.selenium.safari.SafariDriver;

import seleniumTest.webDriver.WebDriver_createTaskIT;

public class Safari_createTaskIT extends WebDriver_createTaskIT{


@Override
public void initializeWebDriver() {
	driver = new SafariDriver();	
}

}
