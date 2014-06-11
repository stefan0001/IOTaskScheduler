/**
 * Build the Modal to edit a Task
 **/
function buildEditTask(data) {
	$("#modalTwo").modal('hide');
	// edit Modal Footer and Header
	buildModalOneHeader(language.editTask);
	
	// edit Modal Body
	var body = document.getElementById("modalOneBody");
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	$('#tasksOfAnIssue').modal('hide');
	$('#showTasksByDate').modal('hide');
	$('#taskUebersicht').modal('hide');
	$('#modalOne').modal('show');
	// Task is a Time Task
	if(data.activated != undefined) {
		buildEditTimeTask(data);
	} else {
		buildEditEventTask(data);
	}
}

function buildEditTimeTask(data) {
	var button1 = createButton("btn btn-default", "", "", language.cancel, "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveEditedTimeTaskButton", language.save, "", "saveEditedTimeTask("+data.ID+")", "", "");
	buildModalOneFooter(button1, button2);
	var body = document.getElementById("modalOneBody");
	var p = document.createElement("p");
	p.setAttribute("id", "editTaskParagraph");
	var ul = document.createElement("ul");
	ul.setAttribute("style", "margin-bottom:20px;");
	ul.setAttribute("class", "nav nav-tabs");
	ul.setAttribute("id", "editTaskTabs");
	var li1 = document.createElement("li");
	var li2 = document.createElement("li");
	li1.setAttribute("class", "active");
	var a1 = document.createElement("a");
	var a2 = document.createElement("a");
	a1.setAttribute("href", "#taskTab");
	a1.setAttribute("data-toggle", "tab");
	a2.setAttribute("href", "#fireTimeTab");
	a2.setAttribute("data-toggle", "tab");
	a1.appendChild(document.createTextNode("Task"));
	a2.appendChild(document.createTextNode("Fire Time"));
	li1.appendChild(a1);
	li2.appendChild(a2);
	ul.appendChild(li1);
	ul.appendChild(li2);
	p.appendChild(ul);
	
	var div = document.createElement("div");
	div.setAttribute("class", "tab-content");
	var div1 = document.createElement("div");
	var div2 = document.createElement("div");
	div1.setAttribute("class", "tab-pane active");
	div1.setAttribute("id", "taskTab");
	div2.setAttribute("class", "tab-pane");
	div2.setAttribute("id", "fireTimeTab");
	div.appendChild(div1);
	div.appendChild(div2);
	p.appendChild(div);
	body.appendChild(p);

	// activate the tabs
	$('#taskTab a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})
	$('#fireTimeTab a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})
	
	var taskTab = document.getElementById("taskTab");
		
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = createTable(3, 2);
	table.childNodes[0].setAttribute("id", "editTaskTableBody");
	table.childNodes[0].childNodes[0].childNodes[0].appendChild(document.createTextNode("ID:"));
	table.childNodes[0].childNodes[0].childNodes[1].appendChild(document.createTextNode(data.ID));
	table.childNodes[0].childNodes[0].childNodes[1].setAttribute("id", "editTaskTableCell1");
	table.childNodes[0].childNodes[1].childNodes[0].appendChild(document.createTextNode("Name:"));
	table.childNodes[0].childNodes[1].childNodes[1].appendChild(createTextField("editTaskName", "", "", "editTaskName", data.name));
	table.childNodes[0].childNodes[1].childNodes[1].setAttribute("id", "editTaskTableCell2");
	table.childNodes[0].childNodes[2].childNodes[0].appendChild(document.createTextNode("aktiviert:"));
	var activatedCheck = createCheckbox("", "", "editTaskActivated", "");
	activatedCheck.setAttribute("id", "editTaskActivated");
	if(data.activated == true) activatedCheck.setAttribute("checked", "true");
	table.childNodes[0].childNodes[2].childNodes[1].appendChild(activatedCheck);
	table.childNodes[0].childNodes[2].childNodes[1].setAttribute("id", "editTaskTableCell3");
	
	div.appendChild(table);
	taskTab.appendChild(div);

	var fireTimeTab = document.getElementById("fireTimeTab");
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = createTable(3, 2);
	
	var date = new Date();
	date.setTime(data.firstFireTime);
	
	table.childNodes[0].childNodes[0].childNodes[0].appendChild(document.createTextNode("Erstes Fire:"));
	table.childNodes[0].childNodes[0].childNodes[1].appendChild(document.createTextNode(date.toLocaleString()));
	table.childNodes[0].childNodes[0].childNodes[1].setAttribute("id", "editTaskTableCellFirstFire");
	table.childNodes[0].childNodes[0].childNodes[1].setAttribute("value", data.firstFireTime);
	table.childNodes[0].childNodes[1].childNodes[0].appendChild(document.createTextNode("Intervall:"));
	var select = createSelect("1", "editTaskIntervall");
	select.setAttribute("id", "editTaskIntervall");
	language.intervall.forEach(
		function(value, index) {
			var option = createOption(language.intervall[index]);
			select.appendChild(option);
		}
	);
	if(data.intervall == intervallToSeconds[0]) {select.childNodes[0].setAttribute("selected", "true")}
	else if(data.intervall == intervallToSeconds[1]) {select.childNodes[1].setAttribute("selected", "true")}
	else if(data.intervall == intervallToSeconds[2]) {select.childNodes[2].setAttribute("selected", "true")}
	else if(data.intervall == intervallToSeconds[3]) {select.childNodes[3].setAttribute("selected", "true")}
	table.childNodes[0].childNodes[1].childNodes[1].appendChild(select);
	table.childNodes[0].childNodes[1].childNodes[1].setAttribute("id", "editTaskTableCellIntervall");
	table.childNodes[0].childNodes[2].childNodes[0].appendChild(document.createTextNode("N\u00e4chstes Fire:"));
	date.setTime(data.nextFireTime);
	table.childNodes[0].childNodes[2].childNodes[1].appendChild(document.createTextNode(date.toLocaleString()));

	div.appendChild(table);
	fireTimeTab.appendChild(div);

	body.appendChild(createButton("btn btn-default", "", "", "Issues", "", "interaction.getIssuesOfTimeTask("+data.ID+")", "", ""));
}

function buildEditEventTask(data) {
	var button1 = createButton("btn btn-default", "", "", language.cancel, "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveEditedEventTaskButton", language.save, "", "saveEditedEventTask("+data.ID+")", "", "");
	buildModalOneFooter(button1, button2);
	var body = document.getElementById("modalOneBody");
	//Task is an Event Task
	var p = document.createElement("p");
	p.setAttribute("id", "editTaskParagraph");
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = createTable(3, 2);
	table.childNodes[0].setAttribute("id", "editTaskTableBody");
	table.childNodes[0].childNodes[0].childNodes[0].appendChild(document.createTextNode("ID:"));
	table.childNodes[0].childNodes[0].childNodes[1].appendChild(document.createTextNode(data.ID));
	table.childNodes[0].childNodes[0].childNodes[1].setAttribute("id", "editTaskTableCell1");
	table.childNodes[0].childNodes[1].childNodes[0].appendChild(document.createTextNode("Name:"));
	var nameInput = createTextField("editTaskName", "", "", "editTaskName", data.name);
	table.childNodes[0].childNodes[1].childNodes[1].appendChild(nameInput);
	table.childNodes[0].childNodes[1].childNodes[1].setAttribute("id", "editTaskTableCell2");
	table.childNodes[0].childNodes[2].childNodes[0].appendChild(document.createTextNode("Event:"));
	table.childNodes[0].childNodes[2].childNodes[1].appendChild(document.createTextNode(data.embedded.event.name));
	table.childNodes[0].childNodes[2].childNodes[1].setAttribute("id", "editTaskTableCell3");

	div.appendChild(table);
	p.appendChild(div);
	body.appendChild(p);
	body.appendChild(createButton("btn btn-default", "", "", "Issues", "", "interaction.getIssuesOfEventTask("+data.ID+")", "", ""));
}

/**
 * Write the updatet Time Task in the Database
 **/
function saveEditedTimeTask(id) {
	var name = document.getElementById("editTaskTableCell2").firstChild.value;
	var activated = document.getElementById("editTaskTableCell3").firstChild.checked;
	var intervallText = document.getElementById("editTaskTableCellIntervall").firstChild.value;
	var firstFire = document.getElementById("editTaskTableCellFirstFire").getAttribute("value");
	var intervallIndex = language.intervall.indexOf(intervallText);
	var intervall = intervallToSeconds[intervallIndex];
	var json = JSON.stringify({name: name, activated: activated, intervall: intervall, firstFireTime: firstFire});
	interaction.updateTimeTask(id, json);
	$('#modalOne').modal('hide');
}

/**
 * Write the updatet Event Task in the Database
 **/
function saveEditedEventTask(id) {
	var name = document.getElementById("editTaskTableCell2").firstChild.value;
	var json = JSON.stringify({name: name});
	interaction.updateEventTask(id, json);
	$('#modalOne').modal('hide');
}

/**
 * Build the Modal to show the Issues of a Time Task
 **/
function showIssuesOfTimeTask(data, id) {
	buildModalTwoHeader(language.issuesOfTask);
	var body = document.getElementById("modalTwoBody");
	var button1 = createButton("btn btn-primary", "", "", "OK", "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	buildModalTwoFooter(button1);
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = createTableWithTableHeaders(1, 4);
	table.childNodes[1].setAttribute("id", "issuesOfExistingTaskTableBody");
	table.childNodes[0].childNodes[0].childNodes[0].appendChild(document.createTextNode("#"));
	table.childNodes[0].childNodes[0].childNodes[1].appendChild(document.createTextNode(language.name));
	table.childNodes[0].childNodes[0].childNodes[2].appendChild(document.createTextNode(language.description));
	table.childNodes[0].childNodes[0].childNodes[3].appendChild(document.createTextNode(language.remove));
	table.childNodes[0].childNodes[0].childNodes[0].setAttribute("width", "6%");
	table.childNodes[0].childNodes[0].childNodes[1].setAttribute("width", "20%");
	table.childNodes[0].childNodes[0].childNodes[2].setAttribute("width", "62%");
	table.childNodes[0].childNodes[0].childNodes[3].setAttribute("width", "12%");
	table.childNodes[0].childNodes[0].childNodes[3].setAttribute("class", "textright");
	for(var i = 0; i < data.content.length; i++) {
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		td4.setAttribute("class", "textright");
		td1.appendChild(document.createTextNode(data.content[i].ID));
		td2.appendChild(document.createTextNode(data.content[i].issueName));
		td3.appendChild(document.createTextNode(data.content[i].issueDescription));
		var button = createButton("remove", "removeTaskIssueConnection", "", "", "", "interaction.removeTimeTaskIssueConnection("+data.content[i].ID+", "+id+")", "", "");
		td4.appendChild(button);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		table.childNodes[1].appendChild(tr);
	}
	div.appendChild(table);
	body.appendChild(div);
	
	var button3 = createButton("btn btn-default", "newIssue", "selectNewIssue", language.newIssue, "margin-right:6%; width:21%;", "createNewIssue('saveNewIssueForExistingTimeTask()', true);", "modal", "#modalThree");
	button3.setAttribute("value", id);
	var button4 = createButton("btn btn-default", "selectIssue", "selectSelectIssue", language.selectIssue, "width:21%;", "interaction.getAllIssueDraft(true, true);", "modal", "#modalThree");
	body.appendChild(button3);
	body.appendChild(button4);
	$('#modalTwo').modal('show');
}

function saveNewIssueForExistingTimeTask() {
	var name = document.newIssueForTaskFormular.issueName.value;
	var des = document.newIssueForTaskFormular.issueDescription.value;
	if(name == "") {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border:solid red 1px;");
	} else {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border:solid red 1px;");
	}
	if(des == "") {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border:solid red 1px;");
	} else {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border:solid red 1px;");
	}
	if(name != "" && des != "") {
		var type = document.newIssueForTaskFormular.issueType.value;
		var json = JSON.stringify({issueName: name, issueDescription: des, issueType: type.toUpperCase()});
		var id = document.getElementById("selectNewIssue").value;
		interaction.postNewIssueDraftForTimeTask(id, json, true);
		$('#modalThree').modal('hide');
	}
}

function saveSelectedIssuesForExistingTimeTask() {
	var issues = document.getElementsByName("selectIssuesForTask");
	var a = 0;
	var selected = new Array();
	for(var i = 0; i < issues.length; i++) {
		if(issues[i].checked == true) {
			selected[a] = issues[i];
			a++;
		}
	}
	var taskId = document.getElementById("selectNewIssue").value;
	for(var i = 0; i < selected.length; i++) {
		var id = selected[i].parentNode.parentNode.childNodes[0].firstChild.nodeValue;
		var json = JSON.stringify({ID: id});
		interaction.postExistentIssueDraftForTimeTask(taskId, json, true);
	}
	$('#modalThree').modal('hide');
}

/**
 * Build the Modal to show the Issues of a Event Task
 **/
function showIssuesOfEventTask(data, id) {
	var body = document.getElementById("modalTwoBody");
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = createTableWithTableHeaders(1, 4);
	table.childNodes[1].setAttribute("id", "issuesOfExistingTaskTableBody");
	table.childNodes[0].childNodes[0].childNodes[0].appendChild(document.createTextNode("#"));
	table.childNodes[0].childNodes[0].childNodes[1].appendChild(document.createTextNode("Name"));
	table.childNodes[0].childNodes[0].childNodes[2].appendChild(document.createTextNode("Beschreibung"));
	table.childNodes[0].childNodes[0].childNodes[3].appendChild(document.createTextNode("Verbindung entfernen"));
	table.childNodes[0].childNodes[0].childNodes[3].setAttribute("width", "22%");
	for(var i = 0; i < data.content.length; i++) {
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		td1.appendChild(document.createTextNode(data.content[i].ID));
		td2.appendChild(document.createTextNode(data.content[i].issueName));
		td3.appendChild(document.createTextNode(data.content[i].issueDescription));
		var button = createButton("remove", "removeTaskIssueConnection", "", "", "", "interaction.removeEventTaskIssueConnection("+data.content[i].ID+", "+id+")", "", "");
		td4.appendChild(button);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		table.childNodes[1].appendChild(tr);
	}
	div.appendChild(table);
	body.appendChild(div);
	
	var button3 = createButton("btn btn-default", "newIssue", "selectNewIssue", "Neues Issue", "margin-right:6%; width:21%;", "createNewIssue('saveNewIssueForExistingEventTask()', true);", "modal", "#modalThree");
	button3.setAttribute("value", id);
	var button4 = createButton("btn btn-default", "selectIssue", "selectSelectIssue", "Issue ausw\u00e4hlen", "width:21%;", "interaction.getAllIssueDraft(true, false);", "modal", "#modalThree");
	body.appendChild(button3);
	body.appendChild(button4);
	$('#modalTwo').modal('show');
}

function saveNewIssueForExistingEventTask() {
	var name = document.newIssueForTaskFormular.issueName.value;
	var des = document.newIssueForTaskFormular.issueDescription.value;
	if(name == "") {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border:solid red 1px;");
	} else {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border:solid red 1px;");
	}
	if(des == "") {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border:solid red 1px;");
	} else {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border:solid red 1px;");
	}
	if(name != "" && des != "") {
		var type = document.newIssueForTaskFormular.issueType.value;
		var json = JSON.stringify({issueName: name, issueDescription: des, issueType: type.toUpperCase()});
		var id = document.getElementById("selectNewIssue").value;
		interaction.postNewIssueDraftForEventTask(id, json, true);
		$('#modalThree').modal('hide');
	}
}

function saveSelectedIssuesForExistingEventTask() {
	var issues = document.getElementsByName("selectIssuesForTask");
	var a = 0;
	var selected = new Array();
	for(var i = 0; i < issues.length; i++) {
		if(issues[i].checked == true) {
			selected[a] = issues[i];
			a++;
		}
	}
	var taskId = document.getElementById("selectNewIssue").value;
	for(var i = 0; i < selected.length; i++) {
		var id = selected[i].parentNode.parentNode.childNodes[0].firstChild.nodeValue;
		var json = JSON.stringify({ID: id});
		interaction.postExistentIssueDraftForEventTask(taskId, json, true);
	}
	$('#modalThree').modal('hide');
}