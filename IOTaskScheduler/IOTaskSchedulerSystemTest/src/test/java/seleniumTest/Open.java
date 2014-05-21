package seleniumTest;

//import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Open {
	private String url = "http://localhost:55555/IOTaskScheduler/static/gui/";
	private WebDriver driver;

	 @Test
	 public void safariTest() {
	 driver = new SafariDriver();
	 driver.get(url);
	 }

	@Test
	public void chromeTest() {
		driver = new ChromeDriver();
		driver.get(url);
	}

	

	@Test
	public void firefoxTest() {
		driver = new FirefoxDriver();
		driver.get(url);
	}

	@After
	public void closeDriver() {
		driver.quit();
		 driver.close();
	}
}
