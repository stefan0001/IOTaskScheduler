/**
 * Filter the Issue View on start page
 **/
function filterIssues() {
	var name = document.getElementById("filterIssueName").value;
	var type = document.getElementById("filterIssueType").value;
	var res = document.getElementById("filterIssueResolution").value;
	if(name == "") name = null;
	if(type == "ALL") type= null;
	if(res == "ALL") res = null;
	var embedded = JSON.stringify({issueResolution: res, embedded: {issueDraft: {issueName: name, issueType: type}}});
	if(type == null && name == null && res == null) {interaction.displayAllIssueEntities()}
	else {interaction.filterIssueEntitys(embedded)}
}

/**
 * Filter the Issue Drafts when create a new Task
 **/
function filterIssueDrafts(existTask) {
	var name = document.getElementById("filterIssueDraftForTask").value;
	var embedded = JSON.stringify({issueName: name});
	interaction.filterIssueDrafts(embedded, existTask);
}