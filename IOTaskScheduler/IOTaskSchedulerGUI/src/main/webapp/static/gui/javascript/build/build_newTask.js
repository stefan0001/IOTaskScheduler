/**
	Create a new Time Task 
**/
function createTimeTask(radio) {
	var taskForm = document.getElementById("TaskInput");
	for(var i = taskForm.childNodes.length; i > 4; i--) {
		taskForm.removeChild(taskForm.lastChild);
	}
		
	var dateDiv2 = document.createElement("div");
	dateDiv2.setAttribute("class", "input-group date form_date col-md-5 taskeingabe");
	dateDiv2.setAttribute("data-date", "");
	dateDiv2.setAttribute("data-date-format", "dd mm yyyy");
	dateDiv2.setAttribute("data-link-field", "dtp_input2");
	dateDiv2.setAttribute("data-link-format", "yyyy-mm-dd");
	
	var dateInput = document.createElement("input");
	dateInput.setAttribute("class", "form-control");
	dateInput.setAttribute("size", "16");
	dateInput.setAttribute("type", "text");
	dateInput.setAttribute("value", "");
	dateInput.setAttribute("readonly", "true");
	dateInput.setAttribute("style", "background-color:white");
	dateInput.setAttribute("name", "TaskDate");
	dateInput.setAttribute("id", "newTimeTaskDate");
	
	var dateSpan1 = document.createElement("span");
	dateSpan1.setAttribute("class", "input-group-addon");
	dateSpan1.setAttribute("style", "background-color:white");
	var dateSpan2 = document.createElement("span");
	dateSpan2.setAttribute("class", "glyphicon glyphicon-remove");
	var dateSpan3 = document.createElement("span");
	dateSpan3.setAttribute("class", "input-group-addon");
	dateSpan3.setAttribute("style", "background-color:white");
	var dateSpan4 = document.createElement("span");
	dateSpan4.setAttribute("class", "glyphicon glyphicon-calendar");
	
	dateSpan1.appendChild(dateSpan2);
	dateSpan3.appendChild(dateSpan4);
	dateDiv2.appendChild(dateInput);
	dateDiv2.appendChild(dateSpan1);
	dateDiv2.appendChild(dateSpan3);
	var p = document.createElement("p");
	p.setAttribute("id", "newTaskDateInput");
	p.appendChild(dateDiv2);
	taskForm.appendChild(p);
	
	$('.form_date').datetimepicker({
        language:  'de',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
	var timeDiv2 = document.createElement("div");
	timeDiv2.setAttribute("class", "input-group date form_time col-md-5 taskeingabe");
	timeDiv2.setAttribute("data-date", "");
	timeDiv2.setAttribute("data-date-format", "hh:ii");
	timeDiv2.setAttribute("data-link-field", "dtp_input3");
	timeDiv2.setAttribute("data-link-format", "hh:ii");
	
	var timeInput = document.createElement("input");
	timeInput.setAttribute("class", "form-control");
	timeInput.setAttribute("size", "16");
	timeInput.setAttribute("type", "text");
	timeInput.setAttribute("value", "");
	timeInput.setAttribute("readonly", "true");
	timeInput.setAttribute("name", "TaskTime");
	timeInput.setAttribute("style", "background-color:white");
	timeInput.setAttribute("id", "newTimeTaskTime");
	
	var timeSpan1 = document.createElement("span");
	timeSpan1.setAttribute("class", "input-group-addon");
	timeSpan1.setAttribute("style", "background-color:white");
	var timeSpan2 = document.createElement("span");
	timeSpan2.setAttribute("class", "glyphicon glyphicon-remove");
	var timeSpan3 = document.createElement("span");
	timeSpan3.setAttribute("class", "input-group-addon");
	timeSpan3.setAttribute("style", "background-color:white");
	var timeSpan4 = document.createElement("span");
	timeSpan4.setAttribute("class", "glyphicon glyphicon-time");
	
	timeSpan1.appendChild(timeSpan2);
	timeSpan3.appendChild(timeSpan4);
	timeDiv2.appendChild(timeInput);
	timeDiv2.appendChild(timeSpan1);
	timeDiv2.appendChild(timeSpan3);
	var p = document.createElement("p");
	p.setAttribute("id", "newTaskDateInput");
	p.appendChild(timeDiv2);
	taskForm.appendChild(p);

	$('.form_time').datetimepicker({
        language:  'de',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	
	var input2 = createSelect("1", "taskIntervall");
	var option1 = createOption(language.everyHour);
	var option2 = createOption(language.everyDay);
	option2.setAttribute("checked", "true");
	var option3 = createOption(language.everyWeek);
	var option4 = createOption(language.everyTwoWeeks);

	input2.appendChild(option1);
	input2.appendChild(option2);
	input2.appendChild(option3);
	input2.appendChild(option4);
	input2.setAttribute("class", "taskeingabe");
	var p2 = document.createElement("p");
	p2.appendChild(input2);
		
	taskForm.appendChild(p2);
}

/**
	Create a new Event Task 
**/
function createEventTask(radio) {
	var taskForm = document.getElementById("TaskInput");
	if(taskForm.childNodes.length > 4) {
		for(var i = taskForm.childNodes.length; i > 4; i--) {
			taskForm.removeChild(taskForm.lastChild);
		}
	}
	
	if(taskForm.childNodes.length == 4) {
		var input1 = createButton("btn btn-default", "newEvent", "newTaskNewEventButton", language.newEvent, "margin-right:6%; width:21%;", "newTaskNewEvent();", "", "")
		var input2 = createButton("btn btn-default", "selectEvent", "newTaskSelectEventButton", language.selectEvent, "width:21%;", "interaction.getAllEvent();", "modal", "#modalTwo")
		
		var p1 = document.createElement("p");
		p1.appendChild(input1);
		p1.appendChild(input2);
		
		taskForm.appendChild(p1);
	}
}

/**
	Create a new Issue when create a Task
**/
function createNewIssue(showIssue, exist) {
	var button1 = createButton("btn btn-default", "", "stopNewIssueForNewTaskButton", language.cancel, "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveNewIssueForNewTaskButton", language.save, "", showIssue, "", "");
	if(exist == true) {
		buildModalThreeHeader(language.newIssue);
		buildModalThreeFooter(button1, button2);
		var body = document.getElementById("modalThreeBody");
	} else {
		buildModalTwoHeader(language.newIssue);
		buildModalTwoFooter(button1, button2);
		var body = document.getElementById("modalTwoBody");
	}
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	var form = document.createElement("form");
	form.setAttribute("name", "newIssueForTaskFormular");
	var textfield = createTextField("issueName", "", language.name, "newIssueNameForTask", "");
	var input2 = createTextarea("IssueDescription", language.description, "issueDescription", "textarea", "4");
	var select = createSelect("1", "issueType");
	select.setAttribute("id", "newIssueTypForTask");
	select.setAttribute("class", "taskeingabe");
	var option1 = createOption("Bug");
	var option2 = createOption("Improvement");
	var option3 = createOption("Task");
	select.appendChild(option1);
	select.appendChild(option2);
	select.appendChild(option3);
	var p1 = document.createElement("p");
	var p2 = document.createElement("p");
	var p3 = document.createElement("p");
	p1.setAttribute("id", "issueNameParagraph");
	p2.setAttribute("id", "issueDescriptionParagraph");
	p3.setAttribute("id", "issueTypeParagraph");
	
	p1.appendChild(textfield);
	p2.appendChild(input2);
	p3.appendChild(select);
	
	form.appendChild(p1);
	form.appendChild(p2);
	form.appendChild(p3);
	
	body.appendChild(form);
}

/**
 * notice New Issues
 */
function saveNewIssueForTask() {
	var name = document.newIssueForTaskFormular.issueName.value;
	var des = document.newIssueForTaskFormular.issueDescription.value;
	var type = document.newIssueForTaskFormular.issueType.value;
	if(name == "") {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border-color:red");
	} else {
		document.newIssueForTaskFormular.issueName.setAttribute("style", "border-color:black");
	}
	if(des == "") {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border-color:red");
	} else {
		document.newIssueForTaskFormular.issueDescription.setAttribute("style", "border-color:black");
	}
	if(name != "" && des != "") {
		var form = document.getElementById("IssueInput");
		if(form.lastChild.getAttribute("id") == "selectIssuesForTaskTable" || form.lastChild.getAttribute("id") == null) {
			for(var i = form.childNodes.length; i > 2; i--) {
				form.removeChild(form.lastChild);
			}
			var div = document.createElement("div");
			div.setAttribute("class", "panel panel-default");
			div.setAttribute("id", "NewIssuesForTaskTable");
			var table = document.createElement("table");
			table.setAttribute("class", "table");
			var tbody = document.createElement("tbody");
			tbody.setAttribute("id", "newIssuesForTaskTableBody")
			var th = document.createElement("th");
			th.appendChild(document.createTextNode("Issues"));
			th.setAttribute("colspan", "3");
			var tr = document.createElement("tr");
			tr.appendChild(th);
			tbody.appendChild(tr);
			var tr = document.createElement("tr");
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			td1.appendChild(document.createTextNode(name));
			td2.appendChild(document.createTextNode(des));
			td3.appendChild(document.createTextNode(type));
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tbody.appendChild(tr);
			table.appendChild(tbody);
			div.appendChild(table);
			form.appendChild(div);
		} else {
			var body = document.getElementById("newIssuesForTaskTableBody");
			var tr = document.createElement("tr");
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			td1.appendChild(document.createTextNode(name));
			td2.appendChild(document.createTextNode(des));
			td3.appendChild(document.createTextNode(type));
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			body.appendChild(tr);
		}	
		$('#modalTwo').modal('hide');
	}
}

/**
	Create a new Event when create a new Task
**/
function newTaskNewEvent() {
	var issueForm = document.getElementById("TaskInput");
	if(issueForm.childNodes.length > 5) {
		for(var i = issueForm.childNodes.length; i > 5; i--) {
			issueForm.removeChild(issueForm.lastChild);
		}
	}
	if(issueForm.childNodes.length == 5) {	
		var textfield = createTextField("newEventName", "", language.name, "", "");
		var p1 = document.createElement("p");
		p1.setAttribute("id", "newEventNameParagraph");
		p1.appendChild(textfield);
		issueForm.appendChild(p1);
	}
}

/**
 * remove Noticed Issues 
 */
function selectIssueInModal() {
	var issueForm = document.getElementById("IssueInput");
	if(document.getElementById("issueNameParagraph")) {
		for(var i = issueForm.childNodes.length; i > 2; i--) {
			issueForm.removeChild(issueForm.lastChild);
		}
	}
}

/**
 * build Modal to select Issues for new task
 */
function buildSelectIssueModal(data, existTask, timeTask) {
	var button1 = createButton("btn btn-default", "", "stopSelectIssueForTask", language.cancel, "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	if(existTask == true) {
		if(timeTask == true)
			var button2 = createButton("btn btn-primary", "", "stopSelectedIssueForExistTaskButton", language.save, "", "saveSelectedIssuesForExistingTimeTask();", "", "");
		else 
			var button2 = createButton("btn btn-primary", "", "saveSelectedIssueForExistTaskButton", language.save, "", "saveSelectedIssuesForExistingEventTask();", "", "");
	} else if(existTask == false) {
		var button2 = createButton("btn btn-primary", "", "stopSelectIssueForNewTaskButton", language.save, "", "createSelectedIssues();", "", "");
	} else {
		var button2 = createButton("btn btn-primary", "", "saveSelectIssueForNewTaskButton", language.save, "", "saveSelectedIssues();", "", "");
	}
	if(existTask == true) {
		var body = document.getElementById("modalThreeBody");
		buildModalThreeHeader(language.selectIssue);
		buildModalThreeFooter(button1, button2);
		$("#modalThree").modal("show");
	} else {
		var body = document.getElementById("modalTwoBody");
		buildModalTwoHeader(language.selectIssue);
		buildModalTwoFooter(button1, button2);
		$("#modalTwo").modal("show");
	}
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	var div = document.createElement("div");
	div.setAttribute("style", "margin-bottom:20px;");
	var filterInput = createTextField("filterIssueDraftForTask", "margin-right:10px;", "Issue "+language.name, "filterIssueDraftForTask", "");
	var submitButton = createButton("btn btn-default", "filterIssueDraftForTask", "", language.search, "", "filterIssueDrafts("+existTask+");", "", "");
	div.appendChild(filterInput);
	div.appendChild(submitButton);
	body.appendChild(div);
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = document.createElement("table");
	table.setAttribute("style", "table-layout: fixed;");
	table.setAttribute("class", "table");
	var tbody = document.createElement("tbody");
	var row = document.createElement("tr");
	var th1 = document.createElement("th");
	var th2 = document.createElement("th");
	var th3 = document.createElement("th");
	var th4 = document.createElement("th");
	var th5 = document.createElement("th");
	th1.setAttribute("width", "6%");
	th2.setAttribute("width", "20%");
	th3.setAttribute("width", "46%");
	th4.setAttribute("width", "12%");
	th5.setAttribute("width", "16%");
	th1.appendChild(document.createTextNode("#"));
	th2.appendChild(document.createTextNode(language.name));
	th3.appendChild(document.createTextNode(language.description));
	th5.appendChild(document.createTextNode(language.type));
	th4.appendChild(document.createTextNode(language.select));
	row.appendChild(th1);
	row.appendChild(th2);
	row.appendChild(th3);
	row.appendChild(th5);
	row.appendChild(th4);
	tbody.appendChild(row);
	if(existTask == true) {
		var tbodyOfTask = document.getElementById("issuesOfExistingTaskTableBody");
		var issues = new Array();
		for(var j = 0; j < tbodyOfTask.childNodes.length; j++) {
			issues[j] = tbodyOfTask.childNodes[j].firstChild.firstChild.nodeValue;
		}
		console.log(issues);
	}
	for(var i = 0; i < data.content.length; i++) {
		var row = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		td2.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		td3.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		td1.appendChild(document.createTextNode(data.content[i].ID));
		td2.appendChild(document.createTextNode(data.content[i].issueName));
		td3.appendChild(document.createTextNode(data.content[i].issueDescription));
		td5.appendChild(document.createTextNode(data.content[i].issueType));
		var check = createCheckbox(data.content[i].ID+"selectIssuesForTask", "", "selectIssuesForTask", data.content[i].issueName);
		if(existTask == true) {
			if(issues.indexOf(String(data.content[i].ID)) != -1) {
				check.setAttribute("checked", "true");
				check.setAttribute("disabled", "disabled");
			}
		}
		td4.appendChild(check);
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td5);
		row.appendChild(td4);
		tbody.appendChild(row);
	}
	table.appendChild(tbody);
	div.appendChild(table);
	body.appendChild(div);
}

/**
 * build Modal to select Event for new task
 */
function selectEventInModal(data, overview) {
	if(document.getElementById("newEventNameParagraph")) {
		document.getElementById("TaskInput").removeChild(document.getElementById("newEventNameParagraph"));
	}
	if(overview == true) {
		var button1 = createButton("btn btn-primary", "", "closeModalForEventOverviewButton", "OK", "", "", "", "");
		button1.setAttribute("data-dismiss", "modal");
		buildModalOneFooter(button1);
		var body = document.getElementById("modalOneBody");
		buildModalOneHeader(language.eventOverview);
		$("#modalOne").modal("show");
	} else {
		var button1 = createButton("btn btn-default", "", "stopSelectEventForNewTaskButton", language.cancel, "", "", "", "");
		button1.setAttribute("data-dismiss", "modal");
		var button2 = createButton("btn btn-primary", "", "saveSelectEventForNewTaskButton", language.save, "", "saveSelectedEventForEventTask();", "", "");
		buildModalTwoFooter(button1, button2);
		var body = document.getElementById("modalTwoBody");
		buildModalTwoHeader(language.selectEvent);
		$("#modalTwo").modal("show");
	}
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = document.createElement("table");
	table.setAttribute("class", "table");
	table.setAttribute("style", "table-layout: fixed;");
	var tbody = document.createElement("tbody");
	var row = document.createElement("tr");
	var th1 = document.createElement("th");
	var th2 = document.createElement("th");
	var th3 = document.createElement("th");
	if(overview == true) {
		th1.setAttribute("width", "60%");
		th2.setAttribute("width", "30%");
		th3.setAttribute("width", "10%");
		th1.appendChild(document.createTextNode(language.name));
		th2.appendChild(document.createTextNode("URL"));
		th3.appendChild(document.createTextNode(language.trigger));
	} else {
		th1.setAttribute("width", "15%");
		th2.setAttribute("width", "75%");
		th3.setAttribute("width", "10%");
		th1.appendChild(document.createTextNode("#"));
		th2.appendChild(document.createTextNode(language.name));
		th3.appendChild(document.createTextNode(language.select));
	}
	row.appendChild(th1);
	row.appendChild(th2);
	row.appendChild(th3);
	tbody.appendChild(row);
	for(var i = 0; i < data.content.length; i++) {
		var row = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		td2.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		if(overview == true) {
			td1.appendChild(document.createTextNode(data.content[i].name));
			td1.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
			td2.appendChild(document.createTextNode("event/"+data.content[i].ID+"/trigger"));
			var button = createButton("trigger", "", "", "", "", "interaction.triggerEvent("+data.content[i].ID+");", "", "");
			td3.appendChild(button);
		} else {
			td1.appendChild(document.createTextNode(data.content[i].ID));
			td2.appendChild(document.createTextNode(data.content[i].name));
			td3.appendChild(createRadioButton("selectEventForTask", data.content[i].name, data.content[i].ID+"selectEventForTask", ""));
		}
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		tbody.appendChild(row);
	}
	table.appendChild(tbody);
	div.appendChild(table);
	body.appendChild(div);
}

/**
 * build the Modal to create a new task
 */
function emptyNewTaskModal() {
	var body = document.getElementById("modalOneBody");
	buildModalOneHeader(language.newTask);
	var button1 = createButton("btn btn-default", "", "stopNewTaskButton", language.cancel, "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	var button2 = createButton("btn btn-primary", "", "saveNewTaskButton", language.save, "", "createNewTask();", "", "");
	buildModalOneFooter(button1, button2);
	for(var i = body.childNodes.length; i > 0; i--) {
		body.removeChild(body.lastChild);
	}
	
	var form1 = document.createElement("form");
	var form2 = document.createElement("form");
	
	var h1 = document.createElement("h3");
	var h2 = document.createElement("h3");
	
	var p1 = document.createElement("p");
	var p2 = document.createElement("p");
	var p3 = document.createElement("p");
	var p4 = document.createElement("p");
	
	
	form1.setAttribute("action", "index.html");
	form1.setAttribute("method", "post");
	form1.setAttribute("name", "TaskInput");
	form1.setAttribute("id", "TaskInput");
	
	h1.setAttribute("style", "margin-top: 10px;");
	h1.appendChild(document.createTextNode("Task"));
	
	var input1 = createTextField("TaskName", "", "Name", "newTaskName", "");
	p1.appendChild(input1);
	var button1 = createRadioButton("tasktype", "Timetask", "radioNewTimeTask", "createTimeTask(this);");
	var button2 = createRadioButton("tasktype", "Eventask", "radioNewEventTask", "createEventTask(this);");
	var label1 = createLabel("radioNewTimeTask", document.createTextNode(language.timebased));
	var label2 = createLabel("radioNewEventTask", document.createTextNode(language.eventbased));
	p3.appendChild(button1);
	p3.appendChild(label1);
	p3.appendChild(button2);
	p3.appendChild(label2);
	form1.appendChild(h1);
	form1.appendChild(p1);
	form1.appendChild(p2);
	form1.appendChild(p3);
	
	form2.setAttribute("action", "index.html");
	form2.setAttribute("method", "post");
	form2.setAttribute("id", "IssueInput");
	form2.setAttribute("name", "IssueInput");
	
	h2.appendChild(document.createTextNode("Issue"));
	h2.setAttribute("style", "margin-top: 10px;");
	
	var button3 = createButton("btn btn-default", "newIssue", "selectNewIssue", language.newIssue, "margin-right:6%; width:21%;", "createNewIssue('saveNewIssueForTask()');", "modal", "#modalTwo");
	var button4 =createButton("btn btn-default", "selectIssue", "selectSelectIssue", language.selectIssue, "width:21%;", "interaction.getAllIssueDraft();", "modal", "#modalTwo");
	
	p4.appendChild(button3);
	p4.appendChild(button4);
	
	form2.appendChild(h2);
	form2.appendChild(p4);
	
	body.appendChild(form1);
	body.appendChild(form2);
}

/**
 * Notice selected Event for new task
 */
function saveSelectedEventForEventTask() {
	var events = document.getElementsByName("selectEventForTask");
	for(var i = 0; i < events.length; i++) {
		if(events[i].checked == true) {
			var selected = events[i];
			break;
		}
	}
	if(selected == undefined) {
		for(var i = 0; i < events.length; i++) {
			events[i].parentNode.setAttribute("style", "border:solid red 1px;");
		}
	} else {
		var id = selected.parentNode.parentNode.childNodes[0].firstChild.nodeValue;
		var value = selected.value;
		var form = document.getElementById("TaskInput");
		var div = document.createElement("div");
		div.setAttribute("id", "selectEventForTaskTable");
		div.setAttribute("class", "panel panel-default");
		var table = document.createElement("table");
		table.setAttribute("class", "table");
		var tbody = document.createElement("tbody");
		tbody.setAttribute("id", "selectedEventsForTaskTableBody");
		var th = document.createElement("th");
		th.setAttribute("colspan", "2");
		th.appendChild(document.createTextNode("Events"))
		var tr = document.createElement("tr");
		tr.appendChild(th);
		tbody.appendChild(tr);
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		td1.appendChild(document.createTextNode(id));		
		td2.appendChild(document.createTextNode(value));
		tr.appendChild(td1);
		tr.appendChild(td2);
		tbody.appendChild(tr);
		table.appendChild(tbody);
		div.appendChild(table);
		form.appendChild(div);
		$('#modalTwo').modal('hide');
		$('#modalThree').modal('hide');
	}
}

/**
 * Notice Selected Issues for new Task
 */
function saveSelectedIssues() {
	var issues = document.getElementsByName("selectIssuesForTask");
	var a = 0;
	var selected = new Array();
	for(var i = 0; i < issues.length; i++) {
		if(issues[i].checked == true) {
			selected[a] = issues[i];
			a++;
		}
	}
	if(selected.length == 0) {
		for(var i = 0; i < issues.length; i++) {
			issues[i].parentNode.setAttribute("style", "border:solid red 1px");
		}
	} else {
		$('#modalTwo').modal('hide');
		$('#modalThree').modal('hide');
		var form = document.getElementById("IssueInput");
		
		for(var i = form.childNodes.length; i > 2; i--) {
			form.removeChild(form.lastChild);
		}
		
		var div = document.createElement("div");
		div.setAttribute("id", "selectIssuesForTaskTable");
		div.setAttribute("class", "panel panel-default");
		var table = document.createElement("table");
		table.setAttribute("class", "table");
		var tbody = document.createElement("tbody");
		tbody.setAttribute("id", "selectedIssuesForTaskTableBody");
		var th = document.createElement("th");
		th.setAttribute("colspan", "4");
		th.appendChild(document.createTextNode("Issues"))
		var tr = document.createElement("tr");
		tr.appendChild(th);
		tbody.appendChild(tr);
		for(var i = 0; i < selected.length; i++) {
			var tr = document.createElement("tr");
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			var td4 = document.createElement("td");
			var id = selected[i].parentNode.previousSibling.previousSibling.previousSibling.previousSibling.firstChild.nodeValue;
			var des = selected[i].parentNode.previousSibling.previousSibling.firstChild.nodeValue;
			var typ = selected[i].parentNode.previousSibling.firstChild.nodeValue;
			td1.appendChild(document.createTextNode(id));		
			td2.appendChild(document.createTextNode(selected[i].value));
			td3.appendChild(document.createTextNode(des));
			td4.appendChild(document.createTextNode(typ));
			tr.appendChild(td1);
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tbody.appendChild(tr);
		}
		table.appendChild(tbody);
		div.appendChild(table);
		form.appendChild(div);
	}
}

/**
 * Write a New task in Database
 */
function createNewTask() {
	if(document.TaskInput.tasktype[1].checked == true) {
		var type = "Eventask";
	} else if(document.TaskInput.tasktype[0].checked == true) {
		var type = "Timetask";
	}
	if(type == "Timetask") {
		var name = document.TaskInput.TaskName.value;
		if(name == "") {
			document.TaskInput.TaskName.setAttribute("style", "border-color:red");
		} else {
			document.TaskInput.TaskName.setAttribute("style", "border-color:black");
		}
		var date = document.TaskInput.TaskDate.value;
		if(date == "") {
			document.TaskInput.TaskDate.setAttribute("style", "border-color:red");
		} else {
			document.TaskInput.TaskDate.setAttribute("style", "border-color:black");
		}
		var splitDate = date.split(" ");
		var day = splitDate[0];
		var month = splitDate[1];
		var year = splitDate[2];
		var time = document.TaskInput.TaskTime.value;
		if(time == "") {
			document.TaskInput.TaskTime.setAttribute("style", "border-color:red");
		} else {
			document.TaskInput.TaskTime.setAttribute("style", "border-color:black");
		}
		var splitTime = time.split(":");
		var hour = splitTime[0];
		var min = splitTime[1];
		var firstDate = new Date(year, month-1, day, hour, min, 0);
		var firstDateInMilliSeconds = firstDate.getTime();
		var intervall = document.TaskInput.taskIntervall.value;
		if(intervall == "Jede Stunde" || intervall == "hourly") {
			var stdIntervall = 3600;
		} else if(intervall == "Jeden Tag" || intervall == "daily") {
			var stdIntervall = 86400;
		} else if(intervall == "Jede Woche" || intervall == "weekly") {
			var stdIntervall = 604800;
		} else if(intervall == "Zwei Wochen" || intervall == "every two weeks") {
			var stdIntervall = 1209600;
		}
		if(name != "" && time != "" && date != "") {
			if(!document.getElementById("newIssuesForTaskTableBody") && !document.getElementById("selectedIssuesForTaskTableBody")) {
				document.getElementById("selectNewIssue").setAttribute("style", "border-color:red");
				document.getElementById("selectSelectIssue").setAttribute("style", "border-color:red");
			} else {
				var json = JSON.stringify({name: name, firstFireTime: firstDateInMilliSeconds, intervall: stdIntervall, activated: "true"});
				interaction.createNewTimeTask(json);
				$('#modalOne').modal('hide');
			}
		}
	} else if(type == "Eventask") {
		var name = document.TaskInput.TaskName.value;
		if(name == "") {
			document.TaskInput.TaskName.setAttribute("style", "border-color:red");
		} else {
			document.TaskInput.TaskName.setAttribute("style", "border-color:black");
		}
		if(document.getElementById("selectedEventsForTaskTableBody")) {
			var eventId = document.getElementById("selectedEventsForTaskTableBody").childNodes[1].firstChild.firstChild.nodeValue;
			if(name != "") {
				var json = JSON.stringify({name: name});
				$('#modalOne').modal('hide');
				interaction.createNewEventTask(eventId, json);
			}
		} else {
			var eventname = document.TaskInput.newEventName.value;
			if(eventname == "") {
				document.TaskInput.newEventName.setAttribute("style", "border-color:red");
			} else {
				document.TaskInput.newEventName.setAttribute("style", "border-color:black");
			}
			if(name != "" && eventname != "") {
				var json = JSON.stringify({name: eventname});
				$('#modalOne').modal('hide');
				interaction.createNewEvent(json);
			}
		}
	}
	$('#modalTwo').modal('hide');
}

/**
 * Create a Task for a Event
 * @param data the Event
 */
function createTaskForEvent(data) {
	var idAt = data.search(/ID/);
	var id = data.slice(idAt+4, idAt+6);
	var idFinal = id;
	if(id.charAt(1) == ",") {
		idFinal = id.slice(0, 1);
	}
	var name = document.TaskInput.TaskName.value;
	var json = JSON.stringify({name: name});
	interaction.createNewEventTask(idFinal, json);
}

/**
 * Create Issues for a Time Task
 * @param data the Time Task
 */
function createIssueForTimeTask(data) {
	var idAt = data.search(/ID/);
	var slice = data.slice(idAt+4, idAt+6);
	var id = slice;
	if(slice.charAt(1) == ",") {
		id = slice.slice(0, 1);
	}
	var form = document.getElementById("IssueInput");
	var tbody = document.getElementById("newIssuesForTaskTableBody");
	if(document.getElementById("newIssuesForTaskTableBody")) {
		var tbody = document.getElementById("newIssuesForTaskTableBody");
		if(form.lastChild.getAttribute("id") == "NewIssuesForTaskTable") {
			for(var i = 1; i < tbody.childNodes.length; i++) {
				var name = tbody.childNodes[i].childNodes[0].firstChild.nodeValue;
				var des = tbody.childNodes[i].childNodes[1].firstChild.nodeValue;
				var type = tbody.childNodes[i].childNodes[2].firstChild.nodeValue;
				var json = JSON.stringify({issueName: name, issueDescription: des, issueType: type.toUpperCase()});
				interaction.postNewIssueDraftForTimeTask(id, json, false);
			}
		}
	} else if(document.getElementById("selectedIssuesForTaskTableBody")) {
		var tbody = document.getElementById("selectedIssuesForTaskTableBody");
		if(form.lastChild.getAttribute("id") == "selectIssuesForTaskTable") {
			for(var i = 1; i < tbody.childNodes.length; i++) {
				var issueId = tbody.childNodes[i].childNodes[0].firstChild.nodeValue;
				var json = JSON.stringify({ID: issueId});
				interaction.postExistentIssueDraftForTimeTask(id, json, false);
			}
		}
	}
}

/**
 * Create issues for Event Task
 * @param data the Event Task
 */
function createIssueForEventTask(data) {
	var idAt = data.search(/ID/);
	var slice = data.slice(idAt+4, idAt+6);
	var id = slice;
	if(slice.charAt(1) == ",") {
		id = slice.slice(0, 1);
	}
	var form = document.getElementById("IssueInput");
	if(document.getElementById("newIssuesForTaskTableBody")) {
		var tbody = document.getElementById("newIssuesForTaskTableBody");
		if(form.lastChild.getAttribute("id") == "NewIssuesForTaskTable") {
			for(var i = 1; i < tbody.childNodes.length; i++) {
				var name = tbody.childNodes[i].childNodes[0].firstChild.nodeValue;
				var des = tbody.childNodes[i].childNodes[1].firstChild.nodeValue;
				var type = tbody.childNodes[i].childNodes[2].firstChild.nodeValue;
				var json = JSON.stringify({issueName: name, issueDescription: des, issueType: type.toUpperCase()});
				interaction.postNewIssueDraftForEventTask(id, json, false);
			}
		}
	} else if(document.getElementById("selectedIssuesForTaskTableBody")) {
		var tbody = document.getElementById("selectedIssuesForTaskTableBody");
		if(form.lastChild.getAttribute("id") == "selectIssuesForTaskTable") {
			for(var i = 1; i < tbody.childNodes.length; i++) {
				var issueId = tbody.childNodes[i].childNodes[0].firstChild.nodeValue;
				var json = JSON.stringify({ID: issueId});
				interaction.postExistentIssueDraftForEventTask(id, json, false);
			}
		}
	}
}
