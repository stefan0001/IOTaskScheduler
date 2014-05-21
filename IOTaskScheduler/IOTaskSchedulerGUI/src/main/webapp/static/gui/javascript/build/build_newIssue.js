/**
 * Write the Issue in the Database
 **/
function createIssue() {
	var name = document.newIssueFormular.issueName.value;
	var des = document.newIssueFormular.issueDescription.value;
	var type = document.newIssueFormular.issueType.value.toUpperCase();
	if(name == "") {
		document.newIssueFormular.issueName.setAttribute("style", "border-color:red");
	} else {
		document.newIssueFormular.issueName.setAttribute("style", "border-color:black");
	}
	if(des == "") {
		document.newIssueFormular.issueDescription.setAttribute("style", "border-color:red");
	} else {
		document.newIssueFormular.issueDescription.setAttribute("style", "border-color:black");
	}
	if(name != "" && des != "") {
		var json = JSON.stringify({issueName: name, issueDescription: des, issueType: type});
		$('#modalOne').modal('hide');
		interaction.postNewIssueDraft(json);
	}
}

/**
 * Build the Modal to Create an Issue
 **/
function emptyNewIssueModal() {
	var button1 = createButton("btn btn-default", "", "closeNewIssueentityButton", "Abbrechen", "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveNewIssueentityButton", "Speichern", "", "createIssue();", "", "");
	buildModalOneFooter(button1, button2);
	buildModalOneHeader("Neues Issue");
	var radio1 = createRadioButton("newIssueSelect", "newIssue", "newIssueSelectCreateNewIssue", "newIssueSelectCreateNewIssue();")
	var radio2 = createRadioButton("newIssueSelect", "selectIssue", "newIssueSelectSelectIssue", "interaction.getAllIssueDraft(false);")
	var label1 = createLabel("newIssueSelectCreateNewIssue", document.createTextNode("Neues Issue"));
	var label2 = createLabel("newIssueSelectSelectIssue", document.createTextNode("Issue w\u00e4hlen"));
	var body = document.getElementById("modalOneBody");
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	body.appendChild(radio1);
	body.appendChild(label1);
	body.appendChild(radio2);
	body.appendChild(label2);
}

function newIssueSelectCreateNewIssue() {
	var body = document.getElementById("modalOneBody");
	for(var i = body.childNodes.length; i > 4; i--) {
		body.removeChild(body.lastChild);
	}
	
	var form = document.createElement("form");
	form.setAttribute("name", "newIssueFormular");
	
	var p1 = document.createElement("p");
	var p2 = document.createElement("p");
	var p3 = document.createElement("p");
	
	var input1 = createTextField("issueName", "", "Name", "newIssueName", "");
	var input2 = createTextarea("newIssueIssueDescription", "Beschreibung", "issueDescription", "textarea", "4");
	var input3 = createSelect("1", "issueType");
	input3.setAttribute("class", "taskeingabe");
	input3.setAttribute("id", "newIssueTyp");
	
	var option1 = createOption("Bug");
	var option2 = createOption("Improvement");
	var option3 = createOption("Task");
	
	input3.appendChild(option1);
	input3.appendChild(option2);
	input3.appendChild(option3);
	
	p1.appendChild(input1);
	p2.appendChild(input2);
	p3.appendChild(input3);
	
	form.appendChild(p1);
	form.appendChild(p2);
	form.appendChild(p3);
	
	body.appendChild(form);
}

function createSelectedIssues() {
	var body = document.getElementById("modalOneBody");
	for(var i = body.childNodes.length; i > 4; i--) {
		body.removeChild(body.lastChild);
	}
	var issues = document.getElementsByName("selectIssuesForTask");
	var a = 0;
	var selected = new Array();
	for(var i = 0; i < issues.length; i++) {
		if(issues[i].checked == true) {
			selected[a] = issues[i];
			a++;
		}
	}
	$('#modalTwo').modal('hide');
	$('#modalThree').modal('hide');
	for(var i = 0; i < selected.length; i++) {
		var id = selected[i].parentNode.parentNode.childNodes[0].firstChild.nodeValue;
		interaction.createIssueEntity(id);
	}
	$('#modalOne').modal('hide');
	
}