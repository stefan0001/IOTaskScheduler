package seleniumTest.webDriver;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;
import static seleniumTest.webDriver.Selectors.*;

public abstract class WebDriver_editTaskIT extends AbstractRequest {
	protected Random name = new Random();
	private boolean execute = true;

	@Test
	public void editTimeTaskName() throws Exception {
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		// clear task name fill a new name
		Thread.sleep(1000);
		driver.findElement(By.id("editTaskName")).clear();
		driver.findElement(By.id("editTaskName")).sendKeys(name.toString());
		// save
		Thread.sleep(1000);
		driver.findElement(By.id("saveEditedTimeTaskButton")).click();
		// compare
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		Thread.sleep(1000);
		assertEquals(
				driver.findElement(
						By.xpath("//*[@id=\"tasksOfDayDiv\"]/div/table/tbody/tr[2]/td[2]"))
						.getText(), name.toString());
	}

	@Test
	public void activateAndDeactivateTimeTask() throws Exception {
		// test case 8,9
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		// activate/deactivate
		boolean active = false;
		if (driver.findElement(By.id("editTaskActivated")).isSelected())
			active = true;
		driver.findElement(By.id("editTaskActivated")).click();
		// save
		Thread.sleep(1000);
		driver.findElement(By.id("saveEditedTimeTaskButton")).click();

		// compare
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(1000);
		assertEquals(driver.findElement(By.id("editTaskActivated"))
				.isSelected(), !active);
	}

	@Test
	public void showFireTime() throws Exception {
		// test case 12
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		// clear task name fill a new name
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"editTaskTabs\"]/li[2]/a"))
				.click();
	}

	@Test
	public void deleteTimeTask() throws Exception {
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// delete the first task
		Thread.sleep(1000);
		driver.findElements(By.className("delete")).get(0).click();
	}

	@Test
	public void editEventTask() throws Exception {
		// based on event
		Thread.sleep(1000);
		driver.findElement(By.id(button_editEventTask)).click();
		// select a task
		Thread.sleep(1000);
		driver.findElements(By.name("editTask")).get(0).click();
		// clear task name fill a new name
		Thread.sleep(1000);
		driver.findElement(By.id("editTaskName")).clear();
		driver.findElement(By.id("editTaskName")).sendKeys("EditedEventTask");
		// save
		Thread.sleep(1000);
		driver.findElement(By.id("saveEditedEventTaskButton")).click();
		// compare
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.id(button_editEventTask)).click();
		// select a issue
		Thread.sleep(1000);
		assertEquals(
				driver.findElement(
						By.xpath("//*[@id=\"modalOneBody\"]/div/table/tbody/tr[2]/td[2]"))
						.getText(), "EditedEventTask");

	}

	@Test
	public void deleteEventTask() throws Exception {
		// based on event
		Thread.sleep(1000);
		driver.findElement(By.id(button_editEventTask)).click();
		Thread.sleep(1000);
		driver.findElements(By.name("deleteTask")).get(0).click();
	}

	public abstract void initializeWebDriver();

	@Before
	public void beforeClass() throws Exception {
		if (execute) {
			// create a task based on time
			driver.findElement(By.id(button_neuerTask)).click();
			// fill name and description
			Thread.sleep(1000);
			driver.findElement(By.id(eingabefeld_taskName)).sendKeys(
					"editIssueTest");
			// based on time
			Thread.sleep(1000);
			driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen))
					.click();
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
			Thread.sleep(1000);
			// save task
			driver.findElement(By.id(button_speichernTask)).click();

			// create a task based on event
			Thread.sleep(1000);
			driver.findElement(By.id(button_neuerTask)).click();
			// fill name and description
			Thread.sleep(1000);
			driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Event");
			// based on event
			driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
					.click();
			// select a event
			driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
			driver.findElement(
					By.xpath(eingabefeld_taskErstellenNeuesEventName))
					.sendKeys("Beispielevent");
			// create a new issue
			driver.findElement(By.id(button_taskErstellenNeuesIssue)).click();
			Thread.sleep(1000);
			driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
					"Muster");
			driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
					.sendKeys("Beispiel");
			driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
			Thread.sleep(1000);
			// save the task
			driver.findElement(By.id(button_speichernTask)).click();

			execute = false;
		}

	}
}
