var interaction = {};

/***************************************************
 * Tasks Start
 ***************************************************/

//Time Tasks

interaction.createNewTimeTask = function(json) {
	server.postNewTimeTask(json);
}

interaction.getAllTimeTask = function() {
	server.getAllTimeTask();
}

interaction.getTimeTaskById = function(id) {
	server.getTimeTaskById(id);
}

interaction.updateTimeTask = function(id, json) {
	server.updateTimeTask(id, json);
}

interaction.deleteTimeTask = function(id, selectedDay) {
	server.deleteTimeTask(id, selectedDay);
}

interaction.getIssuesOfTimeTask = function(id) {
	server.getIssuesOfTimeTask(id);
}

interaction.removeTimeTaskIssueConnection = function(issueId, taskId) {
	server.removeTimeTaskIssueConnection(issueId, taskId);
}

interaction.fetchAllTimeTask = function() {
	server.fetchAllTimeTask();
}

//Event Tasks
interaction.createNewEventTask = function(eventId, json) {
	server.postNewEventTask(eventId, json);
}

interaction.getAllEventTask = function() {
	server.getAllEventTask();
}

interaction.getEventTaskById = function(id) {
	server.getEventTaskById(id);
}

interaction.updateEventTask = function(id, json) {
	server.updateEventTask(id, json);
}

interaction.deleteEventTask = function(id) {
	server.deleteEventTask(id);
}

interaction.getIssuesOfEventTask = function(id) {
	server.getIssuesOfEventTask(id);
}

interaction.removeEventTaskIssueConnection = function(issueId, taskId) {
	server.removeEventTaskIssueConnection(issueId, taskId);
}


/***************************************************
 * Tasks End
 ***************************************************/


/***************************************************
 * Issueentity Start
 ***************************************************/

interaction.displayAllIssueEntities = function() {
	server.fetchAllIssueEntities( display.showData );
}

interaction.createIssueEntity = function(id) {
	server.createIssueEntityFor(id);
}

interaction.updateIssueentity = function(id, json) {
	server.updateIssueentity(id, json);
}

interaction.deleteIssueEntity = function(id) {
	server.deleteIssueEntity(id);
}

interaction.getIssueEntityById = function(id) {
	server.getIssueEntityById(id, display.buildEditIssue);
}

interaction.filterIssueEntitys = function(json) {
	server.filterIssueEntitys(json, display.showData);
}

interaction.deleteIssueEntity = function(id) {
	server.deleteIssueEntity(id);
}

/***************************************************
 * Issueentity End
 ***************************************************/


/***************************************************
 * Issuedraft Start
 ***************************************************/

interaction.postNewIssueDraft = function(json) {
	server.postNewIssueDraft(json);
}

interaction.displayAllIssueDrafts = function() {
	return server.fetchAllIssueDrafts();
}

interaction.updateIssuedraft = function(id, json) {
	server.updateIssuedraft(id, json);
}

interaction.getAllIssueDraft = function(existTask, timeTask) {
	server.getAllIssueDraft(existTask, timeTask);
}

interaction.postNewIssueDraftForTimeTask = function(id, json, existTask) {
	server.putNewIssueDraftForTimeTask(id, json, existTask);
}

interaction.postNewIssueDraftForEventTask = function(id, json, existTask) {
	server.putNewIssueDraftForEventTask(id, json, existTask);
}

interaction.postExistentIssueDraftForEventTask = function(id, json, existTask) {
	server.postExistentIssueDraftForEventTask(id, json, existTask);
}

interaction.postExistentIssueDraftForTimeTask = function(id, json, existTask) {
	server.postExistentIssueDraftForTimeTask(id, json, existTask);
}

interaction.filterIssueDrafts = function(json, existTask) {
	server.filterIssueDrafts(json, existTask);
}

/***************************************************
 * Issuedraft End
 ***************************************************/


/***************************************************
 * Event Start
 ***************************************************/

interaction.createNewEvent = function(json) {
	server.createNewEvent(json);
}

interaction.getAllEvent = function(overview) {
	server.getAllEvent(overview);
}

interaction.triggerEvent = function(id) {
	server.triggerEvent(id);
	$("#modalOne").modal("hide");
}

/***************************************************
 * Event End
 ***************************************************/