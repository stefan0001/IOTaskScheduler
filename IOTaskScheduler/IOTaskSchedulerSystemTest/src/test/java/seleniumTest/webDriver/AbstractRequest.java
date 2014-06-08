package seleniumTest.webDriver;

import static seleniumTest.webDriver.URL.url;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public abstract class AbstractRequest {
	protected WebDriver driver;

	protected abstract void initializeWebDriver();

	@Before
	public void setUp() throws Exception {
		initializeWebDriver();
		driver.get(url);

		Resource timeshiftScriptResource = new ClassPathResource("timeshift.js");
		Resource mocktimeScriptResource = new ClassPathResource("mocktime.js");

		
		if (driver instanceof JavascriptExecutor) {
			//create string
			String script = "";
			script += IOUtils.toString(timeshiftScriptResource.getInputStream(), "UTF-8");
			script += IOUtils.toString(mocktimeScriptResource.getInputStream(), "UTF-8");
			//execute
			((JavascriptExecutor) driver).executeScript(script);
			
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
	
	

}
