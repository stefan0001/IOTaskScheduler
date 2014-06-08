package seleniumTest.webDriver;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import static seleniumTest.webDriver.Selectors.*;

abstract public class WebDriver_editTimeTaskIssueIT extends AbstractRequest {


	@Before
	public void beforeMethod() throws Exception {

		// create a task based on time
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys(
				"editIssueTest");
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
		// save issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
		Thread.sleep(1000);
		// save task
		driver.findElement(By.id(button_speichernTask)).click();

		// open a time task
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(1000);
		// edit issue
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button"))
				.click();
	}


	@Test
	public void selectTimeTaskIssue() throws Exception {
		// show issue list
		Thread.sleep(1000);
		driver.findElement(By.id("selectSelectIssue")).click();
		// select the first issue
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		driver.findElement(By.id("stopSelectedIssueForExistTaskButton")).click();;
		// ok
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"modalTwoFooter\"]/button"))
				.click();

		// compare
		driver.navigate().refresh();
		// open a time task
		Thread.sleep(1000);
		driver.findElement(By.id(button_editTimeTask)).click();
		// select a day
		Thread.sleep(1000);
		driver.findElement(By.xpath(day)).click();
		// select task to edit
		Thread.sleep(1000);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(1000);
		// edit issue
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button"))
				.click();
		// show issue list
		Thread.sleep(1000);
		driver.findElement(By.id("selectSelectIssue")).click();
		Thread.sleep(1000);
		assertEquals(
				driver.findElement(
						By.id(selectorNumber + "selectIssuesForTask"))
						.isSelected(), true);

	}
	
	 @Test
	 public void createTimeTaskIssue() throws Exception {
		 // click button to create a new issue
		 Thread.sleep(1000);
		 driver.findElement(By.id("selectNewIssue")).click();
		 // fill name and description
		 Thread.sleep(1000);
		 driver.findElement(By.id("newIssueNameForTask")).sendKeys(
		 "editTimeTaskIssueNewIssueName");
		 driver.findElement(By.id("IssueDescription")).sendKeys(
		 "editTimeTaskIssueNewIssueDescription");
		 // save
		 driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();
	 }
	
	 @Test
	 public void deleteTimeTaskIssue() throws Exception {
		 // click button to create a new issue
		 Thread.sleep(1000);
		 driver.findElement(By.id("selectNewIssue")).click();
		 // fill name and description
		 Thread.sleep(1000);
		 driver.findElement(By.id("newIssueNameForTask")).sendKeys(
		 "editTimeTaskIssueNewIssueName");
		 driver.findElement(By.id("IssueDescription")).sendKeys(
		 "editTimeTaskIssueNewIssueDescription");
		 // save
		 driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();
		
		 // remove the first issue
		 Thread.sleep(1000);
		 driver.findElements(By.className("remove")).get(0).click();
	 }

}
