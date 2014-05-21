package seleniumTest.webDriver;

public interface Selectors {

	//overview and createTask
	String block_todo = "columnNew";
	String block_imProgress = "columnInProgress";
	String block_done = "columnClosed";

	String button_uebersicht = "taskOverviewButton";
	String button_neuerTask = "newTaskButton";
	String button_issueErstellen = "newIssueButton";
	String button_suchen = "filterIssueView";
	String button_createTaskBreak = "stopNewTaskButton";
	String button_speichernTask = "saveNewTaskButton";
	String button_taskErstellenEventAuswaehlen = "newTaskSelectEventButton";
	String button_taskErstellenNeuesEvent = "newTaskNewEventButton";
	String button_taskErstellenIssueAuswaehlen = "selectSelectIssue";
	String button_speichernTaskIssueAuswaehlen = "saveSelectIssueForNewTaskButton";
	String button_speichernTaskNeuesIssue = "saveNewIssueForNewTaskButton";
	String button_taskErstellenNeuesIssue = "selectNewIssue";

	String eingabefeld_filterIssueName = "filterIssueName";
	String eingabefeld_taskErstellenNeuesEventName = "//*[@id=\"newEventNameParagraph\"]/input";
	
	String checkbox_filterIssueTyp = "filterIssueType";
	String checkbox_filterIssueResolution = "filterIssueResolution";
	String eingabefeld_taskName = "newTaskName";
	String eingabefeld_datum = "newTimeTaskDate";
	String eingabefeld_uhrzeit = "newTimeTaskTime";
	String eingabefeld_issueNameFuerTask = "newIssueNameForTask";
	String eingabefeld_issueBeschreibungFuerTask = "IssueDescription";

	String eingabe_taskName = "Muster";
	String eingabe_taskBeschreibung = "Beispiel";

	String radioButton_eventbasiertTaskErstellen = "radioNewEventTask";
	String radioButton_zeitbasiertTaskErstellen = "radioNewTimeTask";
	String radioButton_createIssueSelectIssue = "newIssueSelectSelectIssue";
	String radioButton_createIssueNewIssue = "newIssueSelectCreateNewIssue";
	
	//time
	String date = "day";
	
		
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
	String button_issueBearbeiten = "";
	String dropdownMenu_status = "";
	String dropdownMenu_issueType = "";
	String dropdownMenu_resolution = "";
}
