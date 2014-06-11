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
	var actualStatus = data.issueStatus;
	statussArray.forEach(
		function(value, index) {
			var radio = createRadioButton("editIssueRadioStatus", statussArrayToUpper[index], "editIssueRadioStatus"+value, "");
			if(actualStatus == statussArrayToUpper[index]) radio.setAttribute("checked", "true");
			var label = createLabel("editIssueRadioStatus"+value, document.createTextNode(value));
			table.firstChild.childNodes[2].childNodes[1].appendChild(radio);
			table.firstChild.childNodes[2].childNodes[1].appendChild(label);
		}
	);
	
	table.firstChild.childNodes[3].childNodes[0].appendChild(document.createTextNode("Resolution:"));
	var select = createSelect("1", "editTaskStatusResolution");
	select.setAttribute("id", "editTaskStatusResolution");
	var actualResolution = data.issueResolution;
	resolutionsArray.forEach(
		function(value, index) {
			var option = createOption(value);
			if(actualResolution == resolutionsArrayToUpper[index]) option.setAttribute("selected", "true");
			select.appendChild(option);
		}
	);
	table.firstChild.childNodes[3].childNodes[1].appendChild(select);
	table.firstChild.childNodes[4].childNodes[0].appendChild(document.createTextNode("Typ:"));
	var actualType = data.embedded.issueDraft.issueType;
	typesArray.forEach(
		function(value, index) {
			var radio = createRadioButton("editIssueRadioType", value.toUpperCase(), "editIssueRadioType"+value, "");
			if(actualType == value.toUpperCase()) radio.setAttribute("checked", "true");
			var label = createLabel("editIssueRadioType"+value, document.createTextNode(value));
			table.firstChild.childNodes[4].childNodes[1].appendChild(radio);
			table.firstChild.childNodes[4].childNodes[1].appendChild(label);
		}
	);
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
	var status = document.getElementsByName("editIssueRadioStatus");
	var type = document.getElementsByName("editIssueRadioType");
	console.log(type);
	for(var i = 0; i < type.length; i++) {
		if(type[i].checked == true) {
			var selectedType = type[i].value;
			console.log(selectedType);
			break;
		}
	}
	for(var i = 0; i < status.length; i++) {
		if(status[i].checked == true) {
			var selectedStatus = status[i].value;
			break;
		}
	}
	var json1 = JSON.stringify({issueName: name, issueDescription: des, issueType: selectedType});
	var res = document.getElementById("modalEditIssueColumn8").firstChild.value;
	var resInArray = resolutionsArray.indexOf(res);
	json2 = JSON.stringify({issueStatus: selectedStatus, issueResolution: resolutionsArrayToUpper[resInArray]});
	

	interaction.updateIssuedraft(draftId, json1);
	interaction.updateIssueentity(entityId, json2);
	$('#modalOne').modal('hide');
}