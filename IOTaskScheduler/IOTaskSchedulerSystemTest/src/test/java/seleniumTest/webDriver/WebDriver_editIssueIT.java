package seleniumTest.webDriver;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriver_editIssueIT implements Selectors, URL {
	protected WebDriver driver;
//
//	@Test
//	public void editIssue() {
//		// test case 15
//		// TODO Issue bearbeiten
//	}
//
//	@Test
//	public void editIssueStatus() {
//		// test case 16
//		Select status = new Select(driver.findElement(By.id(dropdownMenu_status)));
//		status.selectByVisibleText("Open");
//		driver.navigate().refresh();
//		assertTrue(driver.findElement(By.id(dropdownMenu_status)).getText().equals("Open"));
//	}
//	
//	@Test
//	public void editResolutionOfClosedIssue(){
//		//test case 17
//		//change status to closed
//		Select status = new Select(driver.findElement(By.id(dropdownMenu_status)));
//		status.selectByVisibleText("Closed");
//		Select resolution = new Select(driver.findElement(By.id(dropdownMenu_resolution)));
//		resolution.selectByVisibleText("Fixed");
//		driver.navigate().refresh();
//		assertTrue(driver.findElement(By.id(dropdownMenu_resolution)).getText().equals("Fixed"));
//	}
//	
//	@Test
//	public void editResolutionOfNoClosedIssue(){
//		//test case 17
//		Select resolution = new Select(driver.findElement(By.id(dropdownMenu_resolution)));
//		resolution.selectByVisibleText("Fixed");
//		//accept alert
//		driver.switchTo().alert().accept();
//	}

	public abstract void initializeWebDriver();
	
	@Before
	public void beforeMethod() {
		initializeWebDriver();
		driver.get(url);
		//TODO issue anzeigen
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
//				driver.findElement(By.id(button_neuerTask)).click();
//				// fill name and description
//				driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Time");
//				// based on time
//				driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
////				driver.findElement(By.id(eingabefeld_datum)).sendKeys(eingabe_datum);
////				driver.findElement(By.id(eingabefeld_uhrzeit))
////						.sendKeys(eingabe_uhrzeit);
//				// create a new issue
//				driver.findElement(By.id(button_issueAuswaehlen)).click();
//				driver.findElement(By.className(button_neuesIssue)).click();
//				driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
//						"Muster Issue1");
//				driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask)).sendKeys(
//						"Ein Issue");
//				// save the issue
//				driver.findElement(By.id(button_speichernTask)).click();
//
//				// create a task based on event
//				driver.findElement(By.id(button_neuerTask)).click();
//				// fill name and description
//				driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Event");
//				// based on event
//				driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
//						.click();
//				// select a event
//				driver.findElement(By.id(button_eventAuswaehlen)).click();
//				driver.findElements(By.className(radioButton_events)).get(0).click();
//				driver.findElement(By.id(button_speichernEvent)).click();
//				// create a new issue
//				driver.findElement(By.id(button_issueAuswaehlen)).click();
//				driver.findElement(By.className(button_neuesIssue)).click();
//				driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
//						"Muster Issue2");
//				driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask)).sendKeys(
//						"Ein Issue");
//				//select type of this issue 
//				Select issueType = new Select(driver.findElement(By.id(dropdownMenu_issueType)));
//				issueType.selectByVisibleText("Bug");
//				//select status of this issue
//				Select status = new Select(driver.findElement(By.id(dropdownMenu_status)));
//				status.selectByVisibleText("New");
//				// save the task
//				driver.findElement(By.id(button_speichernTask)).click();
//	}

}
