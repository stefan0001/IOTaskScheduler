package seleniumTest.webDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebDriver_createTaskIT implements Selectors, URL {
	protected WebDriver driver;

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
	 Thread.sleep(500);
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
	 Thread.sleep(500);
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
	 Thread.sleep(500);
	 driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 driver.findElements(By.name("selectIssuesForTask")).get(0).click();
	 driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
	 // break
	 driver.findElement((By.id(button_createTaskBreak))).click();
	 }
	
	 @Test
	 public void saveTimeTaskNothing() throws Exception{
	 // test case 5
	 // create a new task
	 driver.findElement(By.id(button_neuerTask)).click();
	 // save the task
	 Thread.sleep(500);
	 driver.findElement(By.id(button_speichernTask)).click();
	 }
	
	
	 @Test
	 public void saveTimeTaskNoName() throws Exception {
	 // test case 5, 14
	 // create a new task
	 driver.findElement(By.id(button_neuerTask)).click();
	 // based on time
	 Thread.sleep(500);
	 driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
	 Thread.sleep(500);
	 // choose a day
	 driver.findElement(By.id(eingabefeld_datum)).click();
	 Thread.sleep(200);
	 driver.findElements(By.className(date)).get(13).click();
	 // choose a time
	 driver.findElement(By.id(eingabefeld_uhrzeit)).click();
	 Thread.sleep(200);
	 driver.findElement(
	 By.xpath("/html/body/div[5]/div[2]/table/tbody/tr/td/span[8]"))
	 .click();
	 Thread.sleep(200);
	 driver.findElement(
	 By.xpath("/html/body/div[5]/div[1]/table/tbody/tr/td/span[3]"))
	 .click();
	 // create a issue
	 driver.findElement(By.id(button_taskErstellenNeuesIssue)).click();
	 Thread.sleep(500);
	 driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
	 "Muster");
	 driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
	 .sendKeys("Beispiel");
	 driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
	 Thread.sleep(500);
	 // save the task
	 driver.findElement(By.id(button_speichernTask)).click();
	 }
	
	 @Test
	 public void saveTimeTaskNoTime() throws Exception {
	 // test case 5
	 // create a new task
	 driver.findElement(By.id(button_neuerTask)).click();
	 // fill name and description
	 Thread.sleep(500);
	 driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
	 // based on time
	 driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
	 // select a issue
	 Thread.sleep(500);
	 driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 driver.findElements(By.name("selectIssuesForTask")).get(0).click();
	 driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
	 // save the task
	 driver.findElement(By.id(button_speichernTask)).click();
	 }
	
	 @Test
	 public void saveEventTaskValid() throws Exception{
	 // test case 7, 13
	 // create a new task
	 driver.findElement(By.id(button_neuerTask)).click();
	 // fill name and description
	 Thread.sleep(500);
	 driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
	 // based on event
	 driver.findElement(By.id(radioButton_eventbasiertTaskErstellen)).click();
	 // select a event
	 driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
	 driver.findElement(By.xpath(eingabefeld_taskErstellenNeuesEventName)).sendKeys("Beispielevent");
	 // select a issue
	 Thread.sleep(500);
	 driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 driver.findElements(By.name("selectIssuesForTask")).get(0).click();
	 driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 // save the task
	 driver.findElement(By.id(button_speichernTask)).click();
	 }
	
	 @Test
	 public void saveEventTaskNoEvent() throws Exception {
	 // test case 7
	 // create a new task
	 driver.findElement(By.id(button_neuerTask)).click();
	 // fill name and description
	 Thread.sleep(200);
	 driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
	 // based on event
	 driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
	 .click();
	 // select a issue
	 Thread.sleep(500);
	 driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 driver.findElements(By.name("selectIssuesForTask")).get(0).click();
	 driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
	 Thread.sleep(500);
	 // save the task without event
	 driver.findElement(By.id(button_speichernTask)).click();
	 }

	@Test
	public void saveTaskNoSelect() throws Exception {
		// test case 7
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(500);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// select a issue
		Thread.sleep(500);
		driver.findElement(By.id(button_taskErstellenIssueAuswaehlen)).click();
		Thread.sleep(500);
		driver.findElements(By.name("selectIssuesForTask")).get(0).click();
		driver.findElement(By.id(button_speichernTaskIssueAuswaehlen)).click();
		Thread.sleep(500);
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
	}

	 @Test
	 public void savaTaskNoIssue() throws Exception{
	 // test case 7
		// create a new task
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(500);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys("Muster");
		// based on event
		driver.findElement(By.id(radioButton_eventbasiertTaskErstellen))
				.click();
		// select a event
		driver.findElement(By.id(button_taskErstellenNeuesEvent)).click();
		driver.findElement(By.xpath(eingabefeld_taskErstellenNeuesEventName))
				.sendKeys("Beispielevent");
		Thread.sleep(500);
		// save the task without issue
		driver.findElement(By.id(button_speichernTask)).click();
	 }

	public abstract void initializeWebDriver();

	@Before
	public void beforeMethod() {
		initializeWebDriver();
		driver.get(url);
	}

	@After
	public void afterMethod() {
		 driver.close();
		 driver.quit();
	}

}
