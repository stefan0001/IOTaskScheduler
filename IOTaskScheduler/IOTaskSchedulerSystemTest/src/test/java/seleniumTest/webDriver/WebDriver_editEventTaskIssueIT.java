package seleniumTest.webDriver;

import static org.junit.Assert.assertEquals;
import static seleniumTest.webDriver.Selectors.button_editEventTask;
import static seleniumTest.webDriver.Selectors.button_editTimeTask;
import static seleniumTest.webDriver.Selectors.button_neuerTask;
import static seleniumTest.webDriver.Selectors.button_speichernTask;
import static seleniumTest.webDriver.Selectors.button_speichernTaskNeuesIssue;
import static seleniumTest.webDriver.Selectors.button_taskErstellenNeuesEvent;
import static seleniumTest.webDriver.Selectors.button_taskErstellenNeuesIssue;
import static seleniumTest.webDriver.Selectors.day;
import static seleniumTest.webDriver.Selectors.eingabefeld_issueBeschreibungFuerTask;
import static seleniumTest.webDriver.Selectors.eingabefeld_issueNameFuerTask;
import static seleniumTest.webDriver.Selectors.eingabefeld_taskErstellenNeuesEventName;
import static seleniumTest.webDriver.Selectors.eingabefeld_taskName;
import static seleniumTest.webDriver.Selectors.radioButton_eventbasiertTaskErstellen;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class WebDriver_editEventTaskIssueIT extends AbstractRequest {

	@Before
	public void beforeMethod() throws Exception {

		// create a task based on event
		Thread.sleep(1500);
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1500);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Event");
		// based on event
		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
				.click();
		// select a event
		driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
		driver.findElement(By.xpath(eingabefeld_taskErstellenNeuesEventName))
				.sendKeys("Beispielevent");
		// create a new issue
		driver.findElement(By.id(button_taskErstellenNeuesIssue)).click();
		Thread.sleep(1500);
		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
				"Muster");
		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
				.sendKeys("Beispiel");
		driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
		Thread.sleep(1500);
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
		
		//open event task list
		Thread.sleep(1500);
		driver.findElement(By.id(button_editEventTask)).click();
		Thread.sleep(1500);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button")).click();
	}


	@Test
	public void selectEventTaskIssue() throws Exception {
		// show issue list
		Thread.sleep(1500);
		driver.findElement(By.id("selectSelectIssue")).click();
		// select the first issue
		Thread.sleep(1500);
		int selectorNumber = 0;
		boolean select;
		WebElement firstIssue;
		do {
			selectorNumber++;
			firstIssue = driver.findElement(By.id(selectorNumber
					+ "selectIssuesForTask"));
			select = firstIssue.isSelected();
		} while (select == true);
		firstIssue.click();
		// save selected issues
		Thread.sleep(1500);
		driver.findElement(By.id("saveSelectedIssueForExistTaskButton"))
				.click();
		// ok
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"modalTwoFooter\"]/button"))
				.click();

		// compare
		driver.navigate().refresh();
		// open a time task
		//open event task list
		Thread.sleep(1500);
		driver.findElement(By.id(button_editEventTask)).click();
		//select a task
		Thread.sleep(1500);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button")).click();
		// show issue list
		Thread.sleep(1500);
		driver.findElement(By.id("selectSelectIssue")).click();
		Thread.sleep(1500);
		assertEquals(
				driver.findElement(
						By.id(selectorNumber + "selectIssuesForTask"))
						.isSelected(), true);

	}

	@Test
	public void createEventTaskIssue() throws Exception {
		// click button to create a new issue
		Thread.sleep(1500);
		driver.findElement(By.id("selectNewIssue")).click();
		// fill name and description
		Thread.sleep(1500);
		driver.findElement(By.id("newIssueNameForTask")).sendKeys(
				"editTimeTaskIssueNewIssueName");
		driver.findElement(By.id("IssueDescription")).sendKeys(
				"editTimeTaskIssueNewIssueDescription");
		// save
		driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();
	}

	@Test
	public void deleteEventTaskIssue() throws Exception {
		// click button to create a new issue
		Thread.sleep(1500);
		driver.findElement(By.id("selectNewIssue")).click();
		// fill name and description
		Thread.sleep(1500);
		driver.findElement(By.id("newIssueNameForTask")).sendKeys(
				"editTimeTaskIssueNewIssueName");
		driver.findElement(By.id("IssueDescription")).sendKeys(
				"editTimeTaskIssueNewIssueDescription");
		// save
		driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();

		// remove the first issue
		Thread.sleep(1500);
		driver.findElements(By.className("remove")).get(0).click();
	}

}
