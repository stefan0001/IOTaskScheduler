/*************************************
 * German language
 *************************************/

var german = {};

german.value = "german";
german.newTask = "Neuer Task";
german.timeTasks = "Zeit Tasks";
german.eventTasks = "Event Tasks";
german.eventOverview = "Event Übersicht";
german.newIssue = "Neues Issue";
german.search = "Suchen";
german.newEvent = "Neues Event";
german.selectEvent = "Event auswählen";
german.timeTaskView = "Zeit Task Übersicht";
german.eventTaskView = "Event Task Übersicht";
german.editTask = "Task bearbeiten";
german.issuesOfTask = "Issues des Task";

german.save = "Speichern";
german.cancel = "Abbrechen";
german.next = "Nächster";
german.prev = "Früher";

german.name = "Name";
german.description = "Beschreibung";
german.type = "Typ";
german.select = "auswählen";
german.trigger = "auslösen";
german.edit = "bearbeiten";
german.deleteString = "löschen";
german.remove = "entfernen";

german.selectIssue = "Issue auswählen";
german.timebased = "zeitbasiert";
german.eventbased = "eventbasiert";

german.allTypes = "Alle Typen";
german.allResolutions = "Alle Resolutionen";

german.everyHour = "Jede Stunde";
german.everyDay = "Jeden Tag";
german.everyWeek = "Jede Woche";
german.everyTwoWeeks = "Alle zwei Wochen";

german.days = ["Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"];
german.month = ["Januar", "Februar", "M\u00e4rz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"];

german.intervall = ["Jede Stunde", "Jeden Tag", "Jede Woche", "Alle zwei Wochen"];

/*************************************
 * English language
 *************************************/

var english = {};

english.value = "english";
english.newTask = "New Task";
english.timeTasks = "Time Tasks";
english.eventTasks = "Event Tasks";
english.eventOverview = "Event Overview";
english.newIssue = "New Issue";
english.search = "Search";
english.newEvent = "New Event";
english.selectEvent = "Select Event";
english.timeTaskView = "Time Task Overview";
english.eventTaskView = "Event Task Overview";
english.editTask = "edit Task";
english.issuesOfTask = "Issues of Task";

english.save = "Save";
english.cancel = "Cancel";
english.next = "Next";
english.prev = "Prev";

english.name = "name";
english.description = "description";
english.type = "type";
english.select = "select";
english.trigger = "trigger";
english.edit = "edit";
english.deleteString = "delete";
english.remove = "remove";

english.selectIssue = "Select Issue";
english.timebased = "time-based";
english.eventbased = "event-based";

english.allTypes = "All Types";
english.allResolutions = "All Resolutions";

english.everyHour = "hourly";
english.everyDay = "daily";
english.everyWeek = "weekly";
english.everyTwoWeeks = "every two weeks";

english.days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
english.month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

english.intervall = ["hourly", "daily", "weekly", "every two weeks"];

// Current language
var language = german;

function getLanguage() {
	return language;
}
changelanguage();

function changelanguage() {
	deleteChildnodes(document.getElementById("newTaskButton"));
	deleteChildnodes(document.getElementById("timeTaskOverviewButton"));
	deleteChildnodes(document.getElementById("eventTaskOverviewButton"));
	deleteChildnodes(document.getElementById("eventOverviewButton"));
	deleteChildnodes(document.getElementById("newIssueButton"));
	deleteChildnodes(document.getElementById("filterIssueView"));
	deleteChildnodes(document.getElementById("filterIssueType").firstChild);
	deleteChildnodes(document.getElementById("filterIssueResolution").firstChild);
	document.getElementById("newTaskButton").appendChild(document.createTextNode(language.newTask));
	document.getElementById("timeTaskOverviewButton").appendChild(document.createTextNode(language.timeTasks));
	document.getElementById("eventTaskOverviewButton").appendChild(document.createTextNode(language.eventTasks));
	document.getElementById("eventOverviewButton").appendChild(document.createTextNode(language.eventOverview));
	document.getElementById("newIssueButton").appendChild(document.createTextNode(language.newIssue));
	document.getElementById("filterIssueView").appendChild(document.createTextNode(language.search));
	document.getElementById("filterIssueType").firstChild.appendChild(document.createTextNode(language.allTypes));
	document.getElementById("filterIssueResolution").firstChild.appendChild(document.createTextNode(language.allResolutions));
	document.getElementById("languageScript").setAttribute("value", language.value);
}





