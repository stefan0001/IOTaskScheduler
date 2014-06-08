package seleniumTest.webDriver;

import static seleniumTest.webDriver.URL.url;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class AbstractRequest {
	protected WebDriver driver;

	protected abstract void initializeWebDriver();

	@Before
	public void setUp() throws Exception {
		initializeWebDriver();
		driver.get(url);

	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
	
	

}
