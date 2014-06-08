package seleniumTest.webDriver;


import static seleniumTest.webDriver.Selectors.button_createTaskBreak;
import static seleniumTest.webDriver.Selectors.button_neuerTask;
import static seleniumTest.webDriver.Selectors.button_speichernTask;
import static seleniumTest.webDriver.Selectors.button_speichernTaskIssueAuswaehlen;
import static seleniumTest.webDriver.Selectors.button_speichernTaskNeuesIssue;
import static seleniumTest.webDriver.Selectors.button_taskErstellenIssueAuswaehlen;
import static seleniumTest.webDriver.Selectors.button_taskErstellenNeuesEvent;
import static seleniumTest.webDriver.Selectors.button_taskErstellenNeuesIssue;
import static seleniumTest.webDriver.Selectors.date;
import static seleniumTest.webDriver.Selectors.eingabefeld_datum;
import static seleniumTest.webDriver.Selectors.eingabefeld_issueBeschreibungFuerTask;
import static seleniumTest.webDriver.Selectors.eingabefeld_issueNameFuerTask;
import static seleniumTest.webDriver.Selectors.eingabefeld_taskErstellenNeuesEventName;
import static seleniumTest.webDriver.Selectors.eingabefeld_taskName;
import static seleniumTest.webDriver.Selectors.eingabefeld_uhrzeit;
import static seleniumTest.webDriver.Selectors.radioButton_eventbasiertTaskErstellen;
import static seleniumTest.webDriver.Selectors.radioButton_zeitbasiertTaskErstellen;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class WebDriver_createTaskIT extends AbstractRequest {

	@Test
	public void createTaskBreak() {
		// test case 2,3
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		driver.findElement((By.id(button_createTaskBreak)));
	}

	@Test
	public void createEventTaskBreak() throws Exception {
		// test case 3
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		Thread.sleep(1000);
		// based on event
		WebElement basedOnEvent = driver.findElement(By
				.id(radioButton_eventbasiertTaskErstellen));
		if (!basedOnEvent.isSelected())
			basedOnEvent.click();
		// break
		driver.findElement((By.id(button_createTaskBreak))).click();
	}

	@Test
	public void createTimeTaskBreak() throws Exception {
		// test case 3
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// based on time
		Thread.sleep(1000);
		WebElement basedOnEvent = driver.findElement(By
				.id(radioButton_zeitbasiertTaskErstellen));
		if (!basedOnEvent.isSelected())
			basedOnEvent.click();
		// break
		driver.findElement((By.id(button_createTaskBreak))).click();
	}

	@Test
	public void selectIssueBreak() throws Exception {
		// test case 3
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// select a issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		// break
		driver.findElement((By.id(button_createTaskBreak))).click();
	}

	@Test
	public void saveTimeTaskNothing() throws Exception {
		// test case 5
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// save the task
		Thread.sleep(1000);
		driver.findElement(By.id(button_speichernTask)).click();
	}

	@Test
	public void saveTimeTaskNoName() throws Exception {
		// test case 5, 14
		// create a task based on time
		driver.findElement(By.id(button_neuerTask)).click();
		// based on time
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
		Thread.sleep(1000);
		// choose a day
		driver.findElement(By.id(eingabefeld_datum)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(date)).click();
		// choose a time
		driver.findElement(By.id(eingabefeld_uhrzeit)).click();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[5]/div[2]/table/tbody/tr/td/span[8]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[5]/div[1]/table/tbody/tr/td/span[3]"))
				.click();
		// set repeat
		driver.findElement(
				By.xpath("//*[@id=\"TaskInput\"]/p[6]/select/option[2]"))
				.click();
		// create a new issue
		driver.findElement(By.id(button_taskErstellenNeuesIssue)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
				"Muster");
		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
				.sendKeys("Beispiel");
		driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
	}

	@Test
	public void saveTimeTaskNoTime() throws Exception {
		// test case 5
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// based on time
		driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
		// select a issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
	}

	@Test
	public void saveEventTaskValid() throws Exception {
		// test case 7, 13
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// based on event
		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
				.click();
		// select a event
		driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
		driver.findElement(By.xpath(eingabefeld_taskErstellenNeuesEventName))
				.sendKeys("Beispielevent");
		// select a issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		Thread.sleep(1000);
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
	}

	@Test
	public void saveEventTaskNoEvent() throws Exception {
		// test case 7
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// based on event
		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
				.click();
		// select a issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		Thread.sleep(1000);
		// save the task without event
		driver.findElement(By.id(button_speichernTask)).click();
	}

	@Test
	public void saveTaskNoSelect() throws Exception {
		// test case 7
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// select a issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		Thread.sleep(1000);
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
	}

	@Test
	public void savaTaskNoIssue() throws Exception {
		// test case 7
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// based on event
		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
				.click();
		// select a event
		driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
		driver.findElement(By.xpath(eingabefeld_taskErstellenNeuesEventName))
				.sendKeys("Beispielevent");
		Thread.sleep(1000);
		// save the task without issue
		driver.findElement(By.id(button_speichernTask)).click();
	}






}
