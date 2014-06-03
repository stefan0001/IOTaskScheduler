package seleniumTest.webDriver;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public abstract class WebDriver_editTaskIT implements URL, Selectors {
	protected WebDriver driver;
	protected Random name = new Random();

	@Test
	public void activateAndDeactivateTimeTask() throws Exception {
		// test case 8,9
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
		// select a day
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"modalOneBody\"]/div/div[3]/div[25]/a"))
				.click();
		// select task to edit
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		// edit
		Thread.sleep(5000);
		driver.findElement(By.id("editTaskButton")).click();
		// activate/deactivate
		String active = "false";
		if (driver.findElement(By.id("editTaskActivated")).isSelected())
			active = "true";
		driver.findElement(By.id("editTaskActivated")).click();
		// OK
		Thread.sleep(500);
		driver.findElement(By.id("changeTaskInputsButton")).click();
		// compare
		Thread.sleep(5000);
		assertFalse(driver.findElement(By.id("editTaskTableCell3")).getText()
				.equals(active));
		// save
		driver.findElement(By.id("saveEditedTaskButton")).click();
	}

	@Test
	public void showFireTime() throws Exception {
		// test case 12
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
		// select a day
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"modalOneBody\"]/div/div[3]/div[25]/a"))
				.click();
		// select task to edit
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"editTaskTabs\"]/li[2]/a"))
				.click();
		Thread.sleep(5000);
	}

	@Test
	public void editTimeTaskIssue() throws Exception {
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
		// select a day
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"modalOneBody\"]/div/div[3]/div[25]/a"))
				.click();
		// select task to edit
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		// show issue
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button"))
				.click();
		Thread.sleep(500);
		driver.findElement(By.id("selectNewIssue")).click();
		Thread.sleep(500);
		driver.findElement(By.id("newIssueNameForTask")).sendKeys("ANewIssue");
		driver.findElement(By.id("IssueDescription")).sendKeys("RT");
		// select a type
		Select filterTyp = new Select(driver.findElement(By
				.id("newIssueTypForTask")));
		filterTyp.selectByVisibleText("Bug");
		filterTyp.selectByVisibleText("Improvement");
		filterTyp.selectByVisibleText("Task");
		// save
		driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();
		// remove a issue
		Thread.sleep(5000);
		driver.findElement(By.className("remove")).click();
		// select a issue
		driver.findElement(By.id("selectSelectIssue")).click();
		Thread.sleep(500);
		driver.findElement(By.name("selectIssuesForTask")).click();
		// save selection
		driver.findElement(By.name("stopSelectedIssueForExistTaskButton"))
				.click();
		Thread.sleep(500);
		// OK
		driver.findElement(By.xpath("//*[@id=\"modalTwoFooter\"]/button"))
				.click();
		// save task
		Thread.sleep(5000);
		driver.findElement(By.id("saveEditedTaskButton")).click();
	}

	@Test
	public void editEventTaskIssue() throws Exception {
		//TODO test laufen lassen
		// based on event
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_eventbasiertUebersicht)).click();
		// select a task
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		// edit
		Thread.sleep(500);
		driver.findElement(By.id("editTaskButton")).click();
		// show issue
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"modalOneBody\"]/button"))
				.click();
		Thread.sleep(500);
		driver.findElement(By.id("selectNewIssue")).click();
		Thread.sleep(500);
		driver.findElement(By.id("newIssueNameForTask")).sendKeys("ANewIssue");
		driver.findElement(By.id("IssueDescription")).sendKeys("RT");
		// select a type
		Select filterTyp = new Select(driver.findElement(By
				.id("newIssueTypForTask")));
		filterTyp.selectByVisibleText("Bug");
		filterTyp.selectByVisibleText("Improvement");
		filterTyp.selectByVisibleText("Task");
		// save
		driver.findElement(By.id("saveNewIssueForNewTaskButton")).click();
		// remove a issue
		Thread.sleep(5000);
		driver.findElement(By.className("remove")).click();
		// select a issue
		driver.findElement(By.id("selectSelectIssue")).click();
		Thread.sleep(500);
		driver.findElement(By.name("selectIssuesForTask")).click();
		// save selection
		driver.findElement(By.name("stopSelectedIssueForExistTaskButton"))
				.click();
		Thread.sleep(500);
		// OK
		driver.findElement(By.xpath("//*[@id=\"modalTwoFooter\"]/button"))
				.click();
		// save task
		Thread.sleep(5000);
		driver.findElement(By.id("saveEditedTaskButton")).click();

	}

	@Test
	public void editEventTask() throws Exception {
		// based on event
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_eventbasiertUebersicht)).click();
		// select a task
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		// edit
		Thread.sleep(500);
		driver.findElement(By.id("editTaskButton")).click();
		// clear task name fill a new name
		Thread.sleep(500);
		driver.findElement(By.id("editTaskName")).clear();
		driver.findElement(By.id("editTaskName")).sendKeys("EditedEventTask");
		// ok
		driver.findElement(By.id("changeTaskInputsButton")).click();
		// save
		Thread.sleep(500);
		driver.findElement(By.id("saveEditedTaskButton")).click();
		// compare
		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(By.id(button_uebersicht)).click();
		// based on event
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_eventbasiertUebersicht)).click();
		// select a issue
		Thread.sleep(500);
		assertEquals(
				driver.findElement(
						By.xpath("//*[@id=\"modalOneBody\"]/p[2]/div/table/tbody/tr[2]/td[2]"))
						.getText(), "EditedTask");

	}

	@Test
	public void editTimeTask() throws Exception {
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
		// select a day
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"modalOneBody\"]/div/div[3]/div[25]/a"))
				.click();
		// select task to edit
		Thread.sleep(500);
		driver.findElements(By.className("editIssue")).get(0).click();
		// edit
		Thread.sleep(500);
		driver.findElement(By.id("editTaskButton")).click();
		// clear task name fill a new name
		Thread.sleep(500);
		driver.findElement(By.id("editTaskName")).clear();
		driver.findElement(By.id("editTaskName")).sendKeys(name.toString());
		// ok
		driver.findElement(By.id("changeTaskInputsButton")).click();
		// save
		Thread.sleep(500);
		driver.findElement(By.id("saveEditedTaskButton")).click();
		// compare
		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(By.id(button_uebersicht)).click();
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertUebersicht)).click();
		// select a day
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"modalOneBody\"]/div/div[3]/div[25]/a"))
				.click();
		Thread.sleep(500);
		assertEquals(
				driver.findElement(
						By.xpath("//*[@id=\"modalTwoBody\"]/div/table/tbody/tr[2]/td[2]"))
						.getText(), name.toString());
	}

	public abstract void initializeWebDriver();

	@Before
	public void beforeMethod() throws Exception {
		initializeWebDriver();
		driver.get(url);
		Thread.sleep(500);
		driver.findElement(By.id(button_uebersicht)).click();
	}

	@After
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

	@BeforeClass
	static public void beforeClass() throws Exception {

		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		// create a task based on time
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(500);
		driver.findElement(By.id(eingabefeld_taskName)).sendKeys(
				"editIssueTest");
		// based on time
		Thread.sleep(500);
		driver.findElement(By.id(radioButton_zeitbasiertTaskErstellen)).click();
		Thread.sleep(500);
		// choose a day
		driver.findElement(By.id(eingabefeld_datum)).click();
		Thread.sleep(500);
		driver.findElements(By.className(date)).get(13).click();
		// choose a time
		driver.findElement(By.id(eingabefeld_uhrzeit)).click();
		Thread.sleep(500);
		driver.findElement(
				By.xpath("/html/body/div[5]/div[2]/table/tbody/tr/td/span[8]"))
				.click();
		Thread.sleep(500);
		driver.findElement(
				By.xpath("/html/body/div[5]/div[1]/table/tbody/tr/td/span[3]"))
				.click();
		// set repeat
		driver.findElement(
				By.xpath("//*[@id=\"TaskInput\"]/p[6]/select/option[2]"))
				.click();
		// create a new issue
		driver.findElement(By.id(button_taskErstellenNeuesIssue)).click();
		Thread.sleep(500);
		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
				"Muster");
		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
				.sendKeys("Beispiel");
		driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
		Thread.sleep(500);

		// create a task based on event
		driver.findElement(By.id(button_neuerTask)).click();
		// fill name and description
		Thread.sleep(500);
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
		Thread.sleep(500);
		driver.findElement(By.id(eingabefeld_issueNameFuerTask)).sendKeys(
				"Muster");
		driver.findElement(By.id(eingabefeld_issueBeschreibungFuerTask))
				.sendKeys("Beispiel");
		driver.findElement(By.id(button_speichernTaskNeuesIssue)).click();
		Thread.sleep(500);
		// save the task
		driver.findElement(By.id(button_speichernTask)).click();
		driver.close();
		driver.quit();
	}
}
