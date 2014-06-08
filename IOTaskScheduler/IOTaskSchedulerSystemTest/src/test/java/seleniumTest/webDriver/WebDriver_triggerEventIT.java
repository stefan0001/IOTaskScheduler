package seleniumTest.webDriver;



import org.junit.Test;
import org.openqa.selenium.By;

import static seleniumTest.webDriver.Selectors.*;

abstract public class WebDriver_triggerEventIT extends AbstractRequest{





	@Test
	public void triggerEvent() throws Exception {
		Thread.sleep(1000);
		driver.findElement(By.id(button_eventOverview)).click();
		//trigger the first event
		Thread.sleep(1000);
		driver.findElements(By.className("trigger")).get(0).click();
	}

}
