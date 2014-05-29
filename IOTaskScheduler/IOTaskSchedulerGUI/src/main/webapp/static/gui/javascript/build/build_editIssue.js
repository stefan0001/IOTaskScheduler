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
	
	var button = createButton("editIssue", "editModalIssue", "editModalIssue", "", "", "editModalIssue("+data.ID+", "+data.embedded.issueDraft.ID+");", "", "");
	var pb = document.createElement("p");
	pb.setAttribute("style", "height:22px;")
	pb.appendChild(button);
	
	table.firstChild.childNodes[0].childNodes[0].appendChild(document.createTextNode("Name:"));
	table.firstChild.childNodes[0].childNodes[1].appendChild(document.createTextNode(data.embedded.issueDraft.issueName));
	table.firstChild.childNodes[1].childNodes[0].appendChild(document.createTextNode("Beschreibung:"));
	table.firstChild.childNodes[1].childNodes[1].appendChild(document.createTextNode(data.embedded.issueDraft.issueDescription));
	table.firstChild.childNodes[2].childNodes[0].appendChild(document.createTextNode("Status:"));
	var status = "";
	if(data.issueStatus == "NEW") status = "New";
	else if(data.issueStatus == "IN_PROGRESS") status = "In Progress";
	else if(data.issueStatus == "CLOSED") status = "Closed";
	table.firstChild.childNodes[2].childNodes[1].appendChild(document.createTextNode(status));
	table.firstChild.childNodes[3].childNodes[0].appendChild(document.createTextNode("Resolution:"));
	table.firstChild.childNodes[3].childNodes[1].appendChild(document.createTextNode(data.issueResolution));
	table.firstChild.childNodes[4].childNodes[0].appendChild(document.createTextNode("Typ:"));
	table.firstChild.childNodes[4].childNodes[1].appendChild(document.createTextNode(data.embedded.issueDraft.issueType));
	div.appendChild(table);
	modal.appendChild(pb);
	modal.appendChild(div);	
}

/**
 * Build the Modal when the "edit Issue" Button was clicked
 **/
function editModalIssue(entityId, draftId) {
	var col1 = document.getElementById("modalEditIssueColumn2");
	var col2 = document.getElementById("modalEditIssueColumn4");
	var col3 = document.getElementById("modalEditIssueColumn6");
	var col4 = document.getElementById("modalEditIssueColumn8");
	var col5 = document.getElementById("modalEditIssueColumn10");
	
	var text1 = col1.firstChild.nodeValue;
	var text2 = col2.firstChild.nodeValue;
	var text3 = col3.firstChild.nodeValue;
	var text4 = col4.firstChild.nodeValue;
	var text5 = col5.firstChild.nodeValue;
	
	var input1 = createTextField("editIssueModalName", "taskeingabe", "", "editIssueModalName", text1);	
	var input2 = createTextarea("editIssueModalDes", "", "editIssueModalDes", "", "4");
	input2.setAttribute("class", "textarea");
	input2.appendChild(document.createTextNode(text2));
	
	col1.replaceChild(input1, col1.lastChild);
	col2.replaceChild(input2, col2.lastChild);
	
	var button1 = document.getElementById('editModalIssue');
	var body = document.getElementById("modalOneBody");
	body.removeChild(button1.parentNode);
	
	var radio1 = createRadioButton("editIssueRadioStatus", "new", "editIssueRadioStatusNew", "");
	var radio2 = createRadioButton("editIssueRadioStatus", "inProgress", "editIssueRadioStatusInProgress", "");
	var radio3 = createRadioButton("editIssueRadioStatus", "Closed", "editIssueRadioStatusClosed", "");
	
	var label1 = createLabel("editIssueRadioStatusNew", document.createTextNode("New"));
	var label2 = createLabel("editIssueRadioStatusInProgress", document.createTextNode("In Progress"));
	var label3 = createLabel("editIssueRadioStatusClosed", document.createTextNode("Closed"));
	
	if(text3 == "New") {
		radio1.setAttribute("checked", "true");
	} else if(text3 == "In Progress") {
		radio2.setAttribute("checked", "true");
	} else if(text3 == "Closed") {
		radio3.setAttribute("checked", "true");
	}
	
	col3.removeChild(col3.firstChild);
	col3.appendChild(radio1);
	col3.appendChild(label1);
	col3.appendChild(radio2);
	col3.appendChild(label2);
	col3.appendChild(radio3);
	col3.appendChild(label3);

	var select = createSelect("1", "editTaskStatusResolution");
	select.setAttribute("id", "editTaskStatusResolution");
	select.appendChild(createOption("Cannot Reproduce"));
	select.appendChild(createOption("Done"));
	select.appendChild(createOption("Duplicate"));
	select.appendChild(createOption("Fixed"));
	select.appendChild(createOption("Wontfix"));
	select.appendChild(createOption("Unresolved"));
	if(text4 == "CANNOT_REPRODUCE") {
		select.childNodes[0].setAttribute("selected", "true");
	} else if (text4 == "DONE") {
		select.childNodes[1].setAttribute("selected", "true");
	} else if (text4 == "DUPLICATE") {
		select.childNodes[2].setAttribute("selected", "true");
	} else if (text4 == "FIXED") {
		select.childNodes[3].setAttribute("selected", "true");
	} else if (text4 == "WONTFIX") {
		select.childNodes[4].setAttribute("selected", "true");
	} else if (text4 == "UNRESOLVED") {
		select.childNodes[5].setAttribute("selected", "true");
	}
	col4.replaceChild(select, col4.firstChild);
	
	var radio1 = createRadioButton("editIssueRadioType", "bug", "editIssueRadioTypeBug", "");
	var radio2 = createRadioButton("editIssueRadioType", "inprovement", "editIssueRadioTypeImprovement", "");
	var radio3 = createRadioButton("editIssueRadioType", "task", "editIssueRadioTypeTask", "");
	
	var label1 = createLabel("editIssueRadioTypeBug", document.createTextNode("Bug"));
	var label2 = createLabel("editIssueRadioTypeImprovement", document.createTextNode("Improvement"));
	var label3 = createLabel("editIssueRadioTypeTask", document.createTextNode("Task"));
	
	if(text5 == "BUG") {
		radio1.setAttribute("checked", "true");
	} else if(text5 == "IMPROVEMENT") {
		radio2.setAttribute("checked", "true");
	} else if(text5 == "TASK") {
		radio3.setAttribute("checked", "true");
	}
	
	col5.removeChild(col5.firstChild);
	col5.appendChild(radio1);
	col5.appendChild(label1);
	col5.appendChild(radio2);
	col5.appendChild(label2);
	col5.appendChild(radio3);
	col5.appendChild(label3);
	
	var newButton = createButton("btn btn-primary", "saveIssueChanges", "saveIssueChanges", "OK", "", "saveIssueChanges("+entityId+", "+draftId+")", "", "");
	buildModalOneFooter(newButton);
}

/**
 * update the Modal with the new Inputs
**/
function saveIssueChanges(entityId, draftId) {
	var name = document.getElementById('editIssueModalName').value;
	var des = document.getElementById('editIssueModalDes').value;
	if(name == "") {
		document.getElementById('editIssueModalName').setAttribute("style", "border:solid red 1px");
	} else {
		document.getElementById('editIssueModalName').setAttribute("style", "border:solid black 1px");
	}
	if(des == "") {
		document.getElementById('editIssueModalDes').setAttribute("style", "border:solid red 1px");
	} else {
		document.getElementById('editIssueModalDes').setAttribute("style", "border:solid black 1px");
	}
	if(name != "" && des != "") {	
		var td1 = document.getElementById("modalEditIssueColumn2");
		var td2 = document.getElementById("modalEditIssueColumn4");
		var td3 = document.getElementById("modalEditIssueColumn6");
		var td4 = document.getElementById("modalEditIssueColumn8");
		var td5 = document.getElementById("modalEditIssueColumn10");
		if(document.getElementById("editIssueRadioStatusNew").checked == true) {
			var newStatus = "New";
		} else if(document.getElementById("editIssueRadioStatusInProgress").checked == true) {
			var newStatus = "In Progress";
		} else if(document.getElementById("editIssueRadioStatusClosed").checked == true) {
			var newStatus = "Closed";
		}
		for(var i = td3.childNodes.length; i > 0; i--) {
			td3.removeChild(td3.lastChild);
		}
		td3.appendChild(document.createTextNode(newStatus));
		
		if(document.getElementById("editIssueRadioTypeBug").checked == true) {
			var newType = "BUG";
		} else if(document.getElementById("editIssueRadioTypeImprovement").checked == true) {
			var newType = "IMPROVEMENT";
		} else if(document.getElementById("editIssueRadioTypeTask").checked == true) {
			var newType = "TASK";
		}
		
		for(var i = td5.childNodes.length; i > 0; i--) {
			td5.removeChild(td5.lastChild);
		}
		td5.appendChild(document.createTextNode(newType));
		
		document.getElementById('modalEditIssueColumn2').replaceChild(document.createTextNode(name), document.getElementById('editIssueModalName'));
		document.getElementById('modalEditIssueColumn4').replaceChild(document.createTextNode(des), document.getElementById('editIssueModalDes'));
		
		var resolution = document.getElementById("modalEditIssueColumn8");
		resolution.replaceChild(document.createTextNode(document.getElementById("editTaskStatusResolution").value), resolution.firstChild);
		
		var newButton = createButton("btn btn-primary", "saveEditedIssue", "saveEditedIssue", "Speichern", "",  "saveEditedIssue("+entityId+", "+draftId+");", "", "");
		buildModalOneFooter(newButton);
	
		var editButton = createButton("editIssue", "editModalIssue", "editModalIssue", "", "", "editModalIssue("+entityId+", "+draftId+");", "", "");
		editButton.setAttribute("id", "editModalIssue");
		var pb = document.createElement("p");
		pb.setAttribute("style", "height:22px;");
		pb.appendChild(editButton);
		document.getElementById("modalOneBody").insertBefore(pb, document.getElementById('modalEditIssueTable'));
	}
}

/**
 * Write the new Issue in Database
 **/
function saveEditedIssue(entityId, draftId) {
	var name = document.getElementById("modalEditIssueColumn2").firstChild.nodeValue;
	var des = document.getElementById("modalEditIssueColumn4").firstChild.nodeValue;
	var status = document.getElementById("modalEditIssueColumn6").firstChild.nodeValue;
	var type = document.getElementById("modalEditIssueColumn10").firstChild.nodeValue;
	
	var upperStatus = status.toUpperCase();
	var endStatus = upperStatus.replace(/\s/, "_");
	var json1 = JSON.stringify({issueName: name, issueDescription: des, issueType: type});
	var json2 = JSON.stringify({issueStatus: endStatus, issueResolution: "UNRESOLVED"});
	if(document.getElementById("modalEditIssueColumn8")) {
		var res = document.getElementById("modalEditIssueColumn8").firstChild.nodeValue;
		var upperRes = res.toUpperCase();
		var endRes = upperRes.replace(/\s/, "_");
		json2 = JSON.stringify({issueStatus: endStatus, issueResolution: endRes});
	}
	

	interaction.updateIssuedraft(draftId, json1);
	interaction.updateIssueentity(entityId, json2);
	$('#modalOne').modal('hide');
}