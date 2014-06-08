package seleniumTest.webDriver;

import static org.junit.Assert.assertEquals;
import static seleniumTest.webDriver.Selectors.button_createIssueBreak;
import static seleniumTest.webDriver.Selectors.button_createIssueSaveNewIssue;
import static seleniumTest.webDriver.Selectors.button_createIssueSaveSelectIssue;
import static seleniumTest.webDriver.Selectors.button_issueErstellen;
import static seleniumTest.webDriver.Selectors.checkbox_filterIssueTyp;
import static seleniumTest.webDriver.Selectors.checkbox_selectAnIssue;
import static seleniumTest.webDriver.Selectors.dropdown_resolution;
import static seleniumTest.webDriver.Selectors.eingabefeld_createIssueDescription;
import static seleniumTest.webDriver.Selectors.eingabefeld_createIssueName;
import static seleniumTest.webDriver.Selectors.radioButton_createIssueNewIssue;
import static seleniumTest.webDriver.Selectors.radioButton_createIssueSelectIssue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriver_createIssueIT extends AbstractRequest {
	

	@Test
	 public void isUnresolved() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"accordion1\"]/div[1]/a/div/h6")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("editIssue")).click();
		Thread.sleep(1000);
		Select resolution = new Select(driver.findElement(By
				.id(dropdown_resolution)));
		assertEquals(resolution.getFirstSelectedOption().getText(),"Unresolved");
	}



	@Test
	public void createAnIssue() throws Exception {
		// create an new issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueNewIssue)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_createIssueName)).sendKeys(
				"createAnIssueTest");
		driver.findElement(By.id(eingabefeld_createIssueDescription)).sendKeys(
				"RT");
		Thread.sleep(1000);
		// select every type
		Select filterTyp = new Select(driver.findElement(By
				.id(checkbox_filterIssueTyp)));
		filterTyp.selectByVisibleText("Bug");
		filterTyp.selectByVisibleText("Improvement");
		filterTyp.selectByVisibleText("Task");
		// save the issue
		driver.findElement(By.id(button_createIssueSaveNewIssue)).click();
		driver.navigate().refresh();
		Thread.sleep(1000);
	}

	 @Test
	 public void createAnIssueWithoutName() throws Exception{
		// create an new issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueNewIssue)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_createIssueDescription)).sendKeys(
				"RT");
		Thread.sleep(1000);
		// select every type
		Select filterTyp = new Select(driver.findElement(By
				.id(checkbox_filterIssueTyp)));
		filterTyp.selectByVisibleText("Bug");
		filterTyp.selectByVisibleText("Improvement");
		filterTyp.selectByVisibleText("Task");
		// save the issue
		driver.findElement(By.id(button_createIssueSaveNewIssue)).click();
	 }
	
	 @Test
	 public void createAnIssueWithoutDescription() throws Exception{
		// create an new issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueNewIssue)).click();
		// fill name and description
		Thread.sleep(1000);
		driver.findElement(By.id(eingabefeld_createIssueName)).sendKeys(
				"createAnIssueTest");
		Thread.sleep(1000);
		// select every type
		Select filterTyp = new Select(driver.findElement(By
				.id(checkbox_filterIssueTyp)));
		filterTyp.selectByVisibleText("Bug");
		filterTyp.selectByVisibleText("Improvement");
		filterTyp.selectByVisibleText("Task");
		// save the issue
		driver.findElement(By.id(button_createIssueSaveNewIssue)).click();
	 }

	@Test
	public void createIssueSelectIssue() throws Exception {
		// create an issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		//select ssue option
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueSelectIssue)).click();
		//select an issue
		Thread.sleep(1000);
		driver.findElements(By.name(checkbox_selectAnIssue)).get(0).click();
		//save the issue
		driver.findElement(By.id(button_createIssueSaveSelectIssue)).click();
		Thread.sleep(1000);
	}

	@Test
	public void createAnIssueBreak() throws Exception {
		// create an new issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		// break
		Thread.sleep(1000);
		driver.findElement(By.id(button_createIssueBreak)).click();
	}

	@Test
	public void createIssueNewIssueBreak() throws Exception {
		// create an new issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueNewIssue)).click();
		// break
		Thread.sleep(1000);
		driver.findElement(By.id(button_createIssueBreak)).click();
	}

	@Test
	public void createIssueSelectIssueBreak() throws Exception {
		// create an issue
		Thread.sleep(1000);
		driver.findElement(By.id(button_issueErstellen)).click();
		//select an issue
		Thread.sleep(1000);
		driver.findElement(By.id(radioButton_createIssueSelectIssue)).click();
		// break
		Thread.sleep(1000);
		driver.findElement(By.id(button_createIssueBreak)).click();
	}
	

}
