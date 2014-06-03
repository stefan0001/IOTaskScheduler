/**
 * Gets the selected Issue from Database 
 **/
function getToEditIssue(value) {
	interaction.getIssueEntityById(value);
}

/**
 * Build the Modal to Edit an Issue
 **/
function buildEditIssue(data) {	
	// Build the Header and Footer
	var button1 = createButton("btn btn-default", "", "stopeditIssueButton", "Abbrechen", "", "", "", "")
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveEditedIssue", "Speichern", "", "saveEditedIssue("+data.ID+", "+data.embedded.issueDraft.ID+");", "", "")
	buildModalOneFooter(button1, button2);
	buildModalOneHeader("Issue bearbeiten");
	// Build the Modal Body
	var modal = document.getElementById("modalOneBody");	
	for(var i = modal.childNodes.length; i > 0; i--) {
		modal.removeChild(modal.lastChild);
	}
	var table = createTable(5, 2);
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	div.setAttribute("id", "modalEditIssueTable");
	table.setAttribute("style", "table-layout: fixed;");
	table.firstChild.childNodes[0].childNodes[0].setAttribute("width", "20%");
	table.firstChild.childNodes[0].childNodes[1].setAttribute("id", "modalEditIssueColumn2");
	table.firstChild.childNodes[0].childNodes[1].setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
	table.firstChild.childNodes[1].childNodes[1].setAttribute("id", "modalEditIssueColumn4");
	table.firstChild.childNodes[1].childNodes[1].setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
	table.firstChild.childNodes[2].childNodes[1].setAttribute("id", "modalEditIssueColumn6");
	table.firstChild.childNodes[3].childNodes[1].setAttribute("id", "modalEditIssueColumn8");
	table.firstChild.childNodes[4].childNodes[1].setAttribute("id", "modalEditIssueColumn10");
	
	table.firstChild.childNodes[0].childNodes[0].appendChild(document.createTextNode("Name:"));
	table.firstChild.childNodes[0].childNodes[1].appendChild(createTextField("editIssueModalName", "taskeingabe", "", "editIssueModalName", data.embedded.issueDraft.issueName));
	table.firstChild.childNodes[1].childNodes[0].appendChild(document.createTextNode("Beschreibung:"));
	var issueDesTextField = createTextarea("editIssueModalDes", "", "editIssueModalDes", "textarea", "4");
	issueDesTextField.appendChild(document.createTextNode(data.embedded.issueDraft.issueDescription));
	table.firstChild.childNodes[1].childNodes[1].appendChild(issueDesTextField);
	table.firstChild.childNodes[2].childNodes[0].appendChild(document.createTextNode("Status:"));
	var radio1 = createRadioButton("editIssueRadioStatus", "NEW", "editIssueRadioStatusNew", "");
	var radio2 = createRadioButton("editIssueRadioStatus", "IN_PROGRESS", "editIssueRadioStatusInProgress", "");
	var radio3 = createRadioButton("editIssueRadioStatus", "CLOSED", "editIssueRadioStatusClosed", "");
	var label1 = createLabel("editIssueRadioStatusNew", document.createTextNode("New"));
	var label2 = createLabel("editIssueRadioStatusInProgress", document.createTextNode("In Progress"));
	var label3 = createLabel("editIssueRadioStatusClosed", document.createTextNode("Closed"));
	if(data.issueStatus == "NEW") radio1.setAttribute("checked", "true");
	else if(data.issueStatus == "IN_PROGRESS") radio2.setAttribute("checked", "true");
	else if(data.issueStatus == "CLOSED") radio3.setAttribute("checked", "true");
	table.firstChild.childNodes[2].childNodes[1].appendChild(radio1);
	table.firstChild.childNodes[2].childNodes[1].appendChild(label1);
	table.firstChild.childNodes[2].childNodes[1].appendChild(radio2);
	table.firstChild.childNodes[2].childNodes[1].appendChild(label2);
	table.firstChild.childNodes[2].childNodes[1].appendChild(radio3);
	table.firstChild.childNodes[2].childNodes[1].appendChild(label3);
	table.firstChild.childNodes[3].childNodes[0].appendChild(document.createTextNode("Resolution:"));
	var select = createSelect("1", "editTaskStatusResolution");
	select.setAttribute("id", "editTaskStatusResolution");
	select.appendChild(createOption("Cannot Reproduce"));
	select.appendChild(createOption("Done"));
	select.appendChild(createOption("Duplicate"));
	select.appendChild(createOption("Fixed"));
	select.appendChild(createOption("Wontfix"));
	select.appendChild(createOption("Unresolved"));
	if(data.issueResolution == "CANNOT_REPRODUCE") {
		select.childNodes[0].setAttribute("selected", "true");
	} else if (data.issueResolution == "DONE") {
		select.childNodes[1].setAttribute("selected", "true");
	} else if (data.issueResolution == "DUPLICATE") {
		select.childNodes[2].setAttribute("selected", "true");
	} else if (data.issueResolution == "FIXED") {
		select.childNodes[3].setAttribute("selected", "true");
	} else if (data.issueResolution == "WONTFIX") {
		select.childNodes[4].setAttribute("selected", "true");
	} else if (data.issueResolution == "UNRESOLVED") {
		select.childNodes[5].setAttribute("selected", "true");
	}
	table.firstChild.childNodes[3].childNodes[1].appendChild(select);
	table.firstChild.childNodes[4].childNodes[0].appendChild(document.createTextNode("Typ:"));
	var radio1 = createRadioButton("editIssueRadioType", "BUG", "editIssueRadioTypeBug", "");
	var radio2 = createRadioButton("editIssueRadioType", "IMPROVEMENT", "editIssueRadioTypeImprovement", "");
	var radio3 = createRadioButton("editIssueRadioType", "TASK", "editIssueRadioTypeTask", "");
	var label1 = createLabel("editIssueRadioTypeBug", document.createTextNode("Bug"));
	var label2 = createLabel("editIssueRadioTypeImprovement", document.createTextNode("Improvement"));
	var label3 = createLabel("editIssueRadioTypeTask", document.createTextNode("Task"));
	if(data.embedded.issueDraft.issueType == "BUG") {
		radio1.setAttribute("checked", "true");
	} else if(data.embedded.issueDraft.issueType == "IMPROVEMENT") {
		radio2.setAttribute("checked", "true");
	} else if(data.embedded.issueDraft.issueType == "TASK") {
		radio3.setAttribute("checked", "true");
	}
	table.firstChild.childNodes[4].childNodes[1].appendChild(radio1);
	table.firstChild.childNodes[4].childNodes[1].appendChild(label1);
	table.firstChild.childNodes[4].childNodes[1].appendChild(radio2);
	table.firstChild.childNodes[4].childNodes[1].appendChild(label2);
	table.firstChild.childNodes[4].childNodes[1].appendChild(radio3);
	table.firstChild.childNodes[4].childNodes[1].appendChild(label3);
	var form = document.createElement("form");
	form.setAttribute("name", "editIssueFormular");
	form.appendChild(table);
	div.appendChild(form);
	modal.appendChild(div);	
}

/**
 * Write the new Issue in Database
 **/
function saveEditedIssue(entityId, draftId) {
	var name = document.getElementById("modalEditIssueColumn2").firstChild.value;
	var des = document.getElementById("modalEditIssueColumn4").firstChild.value;
	var status = document.editIssueFormular.editIssueRadioStatus.value;
	var type = document.editIssueFormular.editIssueRadioType.value;

	var json1 = JSON.stringify({issueName: name, issueDescription: des, issueType: type});
	var res = document.getElementById("modalEditIssueColumn8").firstChild.value;
	var upperRes = res.toUpperCase();
	var endRes = upperRes.replace(/\s/, "_");
	json2 = JSON.stringify({issueStatus: status, issueResolution: endRes});
	

	interaction.updateIssuedraft(draftId, json1);
	interaction.updateIssueentity(entityId, json2);
	$('#modalOne').modal('hide');
}