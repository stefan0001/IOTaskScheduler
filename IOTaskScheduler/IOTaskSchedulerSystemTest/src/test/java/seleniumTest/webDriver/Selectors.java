package seleniumTest.webDriver;

public interface Selectors {

	//overview
	String button_uebersicht = "taskOverviewButton";
	String button_suchen = "filterIssueView";
	String eingabefeld_filterIssueName = "filterIssueName";
	String checkbox_filterIssueTyp = "filterIssueType";
	String checkbox_filterIssueResolution = "filterIssueResolution";
	
	//createTask
	String button_neuerTask = "newTaskButton";
	String button_createTaskBreak = "stopNewTaskButton";
	String button_speichernTask = "saveNewTaskButton";
	String button_taskErstellenEventAuswaehlen = "newTaskSelectEventButton";
	String button_taskErstellenNeuesEvent = "newTaskNewEventButton";
	String button_taskErstellenIssueAuswaehlen = "selectSelectIssue";
	String button_speichernTaskIssueAuswaehlen = "saveSelectIssueForNewTaskButton";
	String button_taskErstellenNeuesIssue = "selectNewIssue";
	String button_speichernTaskNeuesIssue = "saveNewIssueForNewTaskButton";
	
	String eingabe_taskName = "Muster";
	String eingabe_taskBeschreibung = "Beispiel";
	
	String eingabefeld_taskErstellenNeuesEventName = "//*[@id=\"newEventNameParagraph\"]/input";//xpath
	String eingabefeld_taskName = "newTaskName";
	String eingabefeld_datum = "newTimeTaskDate";
	String eingabefeld_uhrzeit = "newTimeTaskTime";
	String eingabefeld_issueNameFuerTask = "newIssueNameForTask";
	String eingabefeld_issueBeschreibungFuerTask = "IssueDescription";


	String radioButton_eventbasiertTaskErstellen = "radioNewEventTask";
	String radioButton_zeitbasiertTaskErstellen = "radioNewTimeTask";
	
	String date = "day";
	
	//createIssue
	String button_issueErstellen = "newIssueButton";
	String button_createIssueBreak = "closeNewIssueentityButton";
	String button_createIssueNewIssueBreak = "closeNewIssueentityButton";
	String button_createIssueSelectIssueBreak = "stopSelectIssueForTask";
	String button_createIssueSaveSelectIssue = "stopSelectIssueForNewTaskButton";
	String button_createIssueSaveNewIssue = "saveNewIssueentityButton";
	
	String radioButton_createIssueSelectIssue = "newIssueSelectSelectIssue";
	String radioButton_createIssueNewIssue = "newIssueSelectCreateNewIssue";
	
	String checkbox_selectAnIssue = "selectIssuesForTask";//name
	
	String eingabefeld_createIssueName = "newIssueName";
	String eingabefeld_createIssueDescription = "newIssueIssueDescription";	
		
	//editTask
	String button_submitActiveTask = "";
	String button_active = "";
	String button_deactive = "";
	String button_fireTime = "";
	String radioButton_eventbasiertUebersicht = "radioEventTasks";
	String radioButton_zeitbasiertUebersicht = "radioTimeTasks";
	String text_fireCounter = "";
	
	//editIssue
	String eingabefeld_issueName = "newIssueName";
	String eingabefeld_issueBeschreibung = "newIssueIssueDescription";
	String className_editIssue = "editIssue";
	String className_removeIssue = "remove";
	String dropdown_resolution = "editTaskStatusResolution";
}
