package seleniumTest.webDriver;

public class Selectors {
	public static final String date = "/html/body/div[4]/div[3]/table/tbody/tr[2]/td[5]";//change manually
	public static final String day = "//*[@id=\"responsiveCalendar\"]/div[3]/div[13]/a";//change manually

	//overview
	public static final String button_editTimeTask = "timeTaskOverviewButton";
	public static final String button_editEventTask = "eventTaskOverviewButton";
	public static final String button_suchen = "filterIssueView";
	public static final String eingabefeld_filterIssueName = "filterIssueName";
	public static final String checkbox_filterIssueTyp = "filterIssueType";
	public static final String checkbox_filterIssueResolution = "filterIssueResolution";
	
	//createTask
	public static final String button_neuerTask = "newTaskButton";
	public static final String button_createTaskBreak = "stopNewTaskButton";
	public static final String button_speichernTask = "saveNewTaskButton";
	public static final String button_taskErstellenEventAuswaehlen = "newTaskSelectEventButton";
	public static final String button_taskErstellenNeuesEvent = "newTaskNewEventButton";
	public static final String button_taskErstellenIssueAuswaehlen = "selectSelectIssue";
	public static final String button_speichernTaskIssueAuswaehlen = "saveSelectIssueForNewTaskButton";
	public static final String button_taskErstellenNeuesIssue = "selectNewIssue";
	public static final String button_speichernTaskNeuesIssue = "saveNewIssueForNewTaskButton";
	
	public static final String eingabe_taskName = "Muster";
	public static final String eingabe_taskBeschreibung = "Beispiel";
	
	public static final String eingabefeld_taskErstellenNeuesEventName = "//*[@id=\"newEventNameParagraph\"]/input";//xpath
	public static final String eingabefeld_taskName = "newTaskName";
	public static final String eingabefeld_datum = "newTimeTaskDate";
	public static final String eingabefeld_uhrzeit = "newTimeTaskTime";
	public static final String eingabefeld_issueNameFuerTask = "newIssueNameForTask";
	public static final String eingabefeld_issueBeschreibungFuerTask = "IssueDescription";


	public static final String radioButton_eventbasiertTaskErstellen = "radioNewEventTask";
	public static final String radioButton_zeitbasiertTaskErstellen = "radioNewTimeTask";
	
	
	//createIssue
	public static final String button_issueErstellen = "newIssueButton";
	public static final String button_createIssueBreak = "closeNewIssueentityButton";
	public static final String button_createIssueNewIssueBreak = "closeNewIssueentityButton";
	public static final String button_createIssueSelectIssueBreak = "stopSelectIssueForTask";
	public static final String button_createIssueSaveSelectIssue = "stopSelectIssueForNewTaskButton";
	public static final String button_createIssueSaveNewIssue = "saveNewIssueentityButton";
	
	public static final String radioButton_createIssueSelectIssue = "newIssueSelectSelectIssue";
	public static final String radioButton_createIssueNewIssue = "newIssueSelectCreateNewIssue";
	
	public static final String checkbox_selectAnIssue = "selectIssuesForTask";//name
	
	public static final String eingabefeld_createIssueName = "newIssueName";
	public static final String eingabefeld_createIssueDescription = "newIssueIssueDescription";	
		
	//triggerEvent
	public static final String button_eventOverview = "eventOverviewButton";
	
	//editIssue
	public static final String eingabefeld_issueName = "newIssueName";
	public static final String eingabefeld_issueBeschreibung = "newIssueIssueDescription";
	public static final String className_editIssue = "editIssue";
	public static final String className_removeIssue = "remove";
	public static final String dropdown_resolution = "editTaskStatusResolution";
}
