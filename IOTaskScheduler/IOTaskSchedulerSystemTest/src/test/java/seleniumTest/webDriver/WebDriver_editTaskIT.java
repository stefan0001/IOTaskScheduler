package seleniumTest.webDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public abstract class WebDriver_editTaskIT implements URL, Selectors {
	protected WebDriver driver;

//	@Test
//	public void activeTimeTask() {
//		// test case 8,9
//		driver.findElement(By.id(button_uebersicht)).click();
//		// based on time
//		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
//		// TODO Task auswählen
//		// active task
//		driver.findElements(By.className(checkbox_tasksUebersicht)).get(0)
//				.click();
//		driver.findElement(By.id(button_active)).click();
//		// save change
//		driver.findElement(By.id(button_submitActiveTask)).click();
//	}
//
//	@Test
//	public void activeEventTask() {
//		// test case 8,9
//		driver.findElement(By.id(button_uebersicht)).click();
//		// based on event
//		driver.findElement(By.id(radioButton_eventbasiertUebersicht)).click();
//		// TODO Task auswählen
//		// active task
//		driver.findElements(By.className(checkbox_tasksUebersicht)).get(0)
//				.click();
//		driver.findElement(By.id(button_active)).click();
//		// save change
//		driver.findElement(By.id(button_submitActiveTask)).click();
//	}
//
//	@Test
//	public void deactiveTimeTask() {
//		// test case 8,9
//		driver.findElement(By.id(button_uebersicht)).click();
//		// based on time
//		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
//		// TODO Task auswählen
//		// active task
//		driver.findElements(By.className(checkbox_tasksUebersicht)).get(0)
//				.click();
//		driver.findElement(By.id(button_deactive)).click();
//		// save change
//		driver.findElement(By.id(button_submitActiveTask)).click();
//	}
//
//	@Test
//	public void deactiveEventTask() {
//		// test case 8,9
//		driver.findElement(By.id(button_uebersicht)).click();
//		// based on event
//		driver.findElement(By.id(radioButton_eventbasiertUebersicht)).click();
//		// TODO Task auswählen
//		// active task
//		driver.findElements(By.className(checkbox_tasksUebersicht)).get(0)
//				.click();
//		driver.findElement(By.id(button_deactive)).click();
//		// save change
//		driver.findElement(By.id(button_submitActiveTask)).click();
//	}
//
//	@Test
//	public void showFireTime() {
//		// test case 12
//		driver.findElement(By.id(button_uebersicht)).click();
//		// based on time
//		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
//		// TODO Task auswählen
//		// select a task
//		driver.findElements(By.className(checkbox_tasksUebersicht)).get(0)
//				.click();
//		// show FireTime
//		driver.findElement(By.id(button_fireTime)).click();
//		assertEquals(driver.findElement(By.id(text_fireCounter)).getText(), "0");
//	}
	public abstract void initializeWebDriver();

	@Before
	public void beforeMethod(){
		initializeWebDriver();
		driver.get(url);
	}

	@After
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

//	@BeforeClass
//	public void beforeClass() {
//		beforeMethod();
//		// create a task based on time
//		driver.findElement(By.id(button_neuerTask)).click();
//		// fill name and description
//		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Time");
//		// based on time
//		driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
////		driver.findElement(By.id(eingabefeld_datum)).sendKeys(eingabe_datum);
////		driver.findElement(By.id(eingabefeld_uhrzeit))
////				.sendKeys(eingabe_uhrzeit);
//		// create a new issue
//		driver.findElement(By.id(button_issueAuswaehlen)).click();
//		driver.findElement(By.className(button_neuesIssue)).click();
//		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
//				"Muster Issue1");
//		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask)).sendKeys(
//				"Ein Issue");
//		// save the issue
//		driver.findElement(By.id(button_speichernTask)).click();
//
//		// create a task based on event
//		driver.findElement(By.id(button_neuerTask)).click();
//		// fill name and description
//		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Event");
//		// based on event
//		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
//				.click();
//		// select a event
//		driver.findElement(By.id(button_eventAuswaehlen)).click();
//		driver.findElements(By.className(radioButton_events)).get(0).click();
//		driver.findElement(By.id(button_speichernEvent)).click();
//		// create a new issue
//		driver.findElement(By.id(button_issueAuswaehlen)).click();
//		driver.findElement(By.className(button_neuesIssue)).click();
//		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
//				"Muster Issue2");
//		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask)).sendKeys(
//				"Ein Issue");
//		//select type of this issue 
//		Select issueType = new Select(driver.findElement(By.id(dropdownMenu_issueType)));
//		issueType.selectByVisibleText("Bug");
//		//select status of this issue
//		Select status = new Select(driver.findElement(By.id(dropdownMenu_status)));
//		status.selectByVisibleText("New");
//		// save the task
//		driver.findElement(By.id(button_speichernTask)).click();
//	}

}
