var interaction = {};

interaction.displayAllIssueEntities = function() {
	server.fetchAllIssueEntities( display.showData );
}

interaction.postNewIssueDraft = function(json) {
	server.postNewIssueDraft(json);
}

interaction.displayAllIssueDrafts = function() {
	return server.fetchAllIssueDrafts();
}

interaction.createIssueEntity = function(id) {
	server.createIssueEntityFor(id);
}

interaction.updateIssuedraft = function(id, json) {
	server.updateIssuedraft(id, json);
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

interaction.createNewTimeTask = function(json) {
	server.postNewTimeTask(json);
}

interaction.createNewEventTask = function(eventId, json) {
	server.postNewEventTask(eventId, json);
}

interaction.getAllIssueDraft = function(existTask, timeTask) {
	server.getAllIssueDraft(existTask, timeTask);
}

interaction.getAllTimeTask = function() {
	server.getAllTimeTask();
}

interaction.getAllEventTask = function() {
	server.getAllEventTask();
}

interaction.fetchAllTimeTask = function() {
	server.fetchAllTimeTask();
}

interaction.getTimeTaskById = function(id) {
	server.getTimeTaskById(id);
}

interaction.getEventTaskById = function(id) {
	server.getEventTaskById(id);
}

interaction.postNewIssueDraftForTimeTask = function(id, json, existTask) {
	server.putNewIssueDraftForTimeTask(id, json, existTask);
}

interaction.postNewIssueDraftForEventTask = function(id, json, existTask) {
	server.putNewIssueDraftForEventTask(id, json, existTask);
}

interaction.updateTimeTask = function(id, json) {
	server.updateTimeTask(id, json);
}

interaction.updateEventTask = function(id, json) {
	server.updateEventTask(id, json);
}

interaction.createNewEvent = function(json) {
	server.createNewEvent(json);
}

interaction.postExistentIssueDraftForEventTask = function(id, json, existTask) {
	server.postExistentIssueDraftForEventTask(id, json, existTask);
}

interaction.postExistentIssueDraftForTimeTask = function(id, json, existTask) {
	server.postExistentIssueDraftForTimeTask(id, json, existTask);
}

interaction.deleteEventTask = function(id) {
	server.deleteEventTask(id);
}

interaction.deleteTimeTask = function(id, selectedDay) {
	server.deleteTimeTask(id, selectedDay);
}

interaction.filterIssueEntitys = function(json) {
	server.filterIssueEntitys(json, display.showData);
}

interaction.filterIssueDrafts = function(json, existTask) {
	server.filterIssueDrafts(json, existTask);
}

interaction.getIssuesOfTimeTask = function(id) {
	server.getIssuesOfTimeTask(id);
}

interaction.getIssuesOfEventTask = function(id) {
	server.getIssuesOfEventTask(id);
}

interaction.removeTimeTaskIssueConnection = function(issueId, taskId) {
	server.removeTimeTaskIssueConnection(issueId, taskId);
}

interaction.removeEventTaskIssueConnection = function(issueId, taskId) {
	server.removeEventTaskIssueConnection(issueId, taskId);
}

interaction.deleteIssueEntity = function(id) {
	server.deleteIssueEntity(id);
}

interaction.getAllEvent = function(overview) {
	server.getAllEvent(overview);
}

interaction.triggerEvent = function(id) {
	server.triggerEvent(id);
	$("#modalOne").modal("hide");
}