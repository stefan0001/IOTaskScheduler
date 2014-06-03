/**
	At the Task overview, view the Time Tasks
**/
function selectTimeTaskView(data) {
	var modalBody = document.getElementById("modalOneBody");
	var button1 = createButton("btn btn-primary", "", "closeTimeTaskOverviewModalButton", "OK", "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	buildModalOneFooter(button1);
	buildModalOneHeader("Zeit Task \u00dcbersicht");
	if(modalBody.childNodes.length != 0) {
		for(var i = modalBody.childNodes.length; i > 0 ; i--) {
			modalBody.removeChild(modalBody.lastChild);
		}
	}
	
	var div = document.createElement("div");
	div.setAttribute("class", "responsive-calendar");
	div.setAttribute("id", "responsiveCalendar");
	var div2 = document.createElement("div");
	div2.setAttribute("class", "controls");
	
	var a1 = document.createElement("a");
	a1.setAttribute("class", "pull-left");
	a1.setAttribute("data-go", "prev");
	var div3 = document.createElement("div");
	div3.setAttribute("class", "btn btn-primary");
	div3.appendChild(document.createTextNode("Fr\u00fcher"));
	a1.appendChild(div3);
	
	var h4 = document.createElement("h4");
	var span1 = document.createElement("span");
	span1.setAttribute("data-head-year", "");
	var span2 = document.createElement("span");
	span2.setAttribute("data-head-month", "");
	h4.appendChild(span1);
	h4.appendChild(document.createTextNode(" "));
	h4.appendChild(span2);
	
	var a2 = document.createElement("a");
	a2.setAttribute("class", "pull-right");
	a2.setAttribute("data-go", "next");
	var div4 = document.createElement("div");
	div4.setAttribute("class", "btn btn-primary");
	div4.appendChild(document.createTextNode("N\u00e4chster"));
	a2.appendChild(div4);
	
	div2.appendChild(a1);
	div2.appendChild(h4);
	div2.appendChild(a2);
	
	var hr = document.createElement("hr");
	
	var div5 = document.createElement("div");
	div5.setAttribute("class", "day-headers");
	
	var days = ["Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"];
	for(var i in days) {
		var div6 = document.createElement("div");
		div6.setAttribute("class", "day header");
		div6.appendChild(document.createTextNode(days[i]));
		div5.appendChild(div6);
	}
	var div7 = document.createElement("div");
	div7.setAttribute("class", "days");
	div7.setAttribute("data-group", "days");
	
	div.appendChild(div2);
	div.appendChild(hr);
	div.appendChild(div5);
	div.appendChild(div7);
	
	modalBody.appendChild(div);
	
	var currentDate = new Date();
	var currentYear = currentDate.getFullYear();
	var currentMonth = currentDate.getMonth()+1;
	var currentDate = currentYear+"-"+currentMonth;
	
	var event = {}

	for(var i = 0; i < data.content.length; i++) {
		var stringsec = String(data.content[i].nextFireTime);
		var date = new Date();
		var sec = stringsec.slice(0, stringsec.length-3);
		date.setTime(stringsec);
		var month = date.getMonth()+1;
		if(date.getDate() < 10) {
			var day = "0"+date.getDate();
		} else {
			var day = date.getDate();
		}
		if(month < 10) {
			var monthNew = "0"+month;
		} else {
			var monthNew = month;
		}
		var fullDate = date.getFullYear()+"-"+monthNew+"-"+day;
		if(event[fullDate] != undefined) {
			event[fullDate].number++;
		} else {
			event[fullDate] = {"number": 1, "date": fullDate};
		}
	}
	$(".responsive-calendar").responsiveCalendar({
    	time: currentDate,
        events: event,
        data: data
    });	
}

/**
	Show the Event Tasks in the Task View
**/
function selectEventTaskView(data) {
	var modalBody = document.getElementById("modalOneBody");
	var button1 = createButton("btn btn-primary", "", "closeEventTaskOverviewModalButton", "OK", "", "", "", "");
	button1.setAttribute("data-dismiss", "modal");
	buildModalOneFooter(button1);
	buildModalOneHeader("Event Task \u00dcbersicht");
	if(modalBody.childNodes.length != 0) {
		for(var i = modalBody.childNodes.length; i > 0 ; i--) {
			modalBody.removeChild(modalBody.lastChild);
		}
	}

	var div1 = document.createElement("div");
	var table = document.createElement("table");
	var row1 = document.createElement("tr");
	var column1 = document.createElement("th");
	var column2 = document.createElement("th");
	var column3 = document.createElement("th");
	var column4 = document.createElement("th");
	var column5 = document.createElement("th");
	var tbody = document.createElement("tbody");
	div1.setAttribute("class", "panel panel-default");
	table.setAttribute("class", "table");
	table.setAttribute("style", "table-layout: fixed;");
	column1.setAttribute("width", "10%");
	column2.setAttribute("width", "50%");
	column3.setAttribute("width", "20%");
	column4.setAttribute("width", "10%");
	column5.setAttribute("width", "10%");
	
	column1.appendChild(document.createTextNode("#"));
	column2.appendChild(document.createTextNode("Name"));
	column3.appendChild(document.createTextNode("Event"));
	column4.appendChild(document.createTextNode("bearbeiten"));
	column5.appendChild(document.createTextNode("l\u00f6schen"));
	
	row1.appendChild(column1);
	row1.appendChild(column2);
	row1.appendChild(column3);
	row1.appendChild(column4);
	row1.appendChild(column5);
	
	tbody.appendChild(row1);
	
	for(var i = 0; i < data.content.length; i++) {
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		td2.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		td3.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		td1.appendChild(document.createTextNode(data.content[i].ID));
		td2.appendChild(document.createTextNode(data.content[i].name));
		td3.appendChild(document.createTextNode(data.content[i].embedded.event.name));
		td4.appendChild(createButton("editIssue", "editTask", "", "", "", "interaction.getEventTaskById("+data.content[i].ID+")", "", ""));
		td5.appendChild(createButton("delete", "deleteTask", "", "", "", "deleteEventTask("+data.content[i].ID+")", "", ""));
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tbody.appendChild(tr);
	}
	
	table.appendChild(tbody);

	div1.appendChild(table);
		
	modalBody.appendChild(div1);
}

function showTasksOfDay(selectedDay) {
	if(document.getElementById("tasksOfDayDiv")) {
		var div = document.getElementById("tasksOfDayDiv");
		document.getElementById("responsiveCalendar").removeChild(div);
	}
	var day = selectedDay.split("-");
	var data = display.getData();
	var tasks = new Array();
	var a = 0;
	for(var i = 0; i < data.content.length; i++) {
		var date = new Date();
		var datum = data.content[i].nextFireTime;
		date.setTime(datum);
		var month = date.getMonth()+1;
		var day = date.getDate();
		var year = date.getFullYear();
		if(month < 10) {month = "0"+month};
		if(day < 10) {day = "0"+day};
		var fullDate = year+"-"+month+"-"+day;
		if(selectedDay == fullDate) {
			tasks[a] = data.content[i];
			a++;
		}
	}
	
	var div = document.createElement("div");
	div.setAttribute("class", "panel panel-default");
	var table = document.createElement("table");
	table.setAttribute("style", "table-layout: fixed;");
	table.setAttribute("class", "table");
	var tbody = document.createElement("tbody");

	var tr1 = document.createElement("tr");
	var th1 = document.createElement("th");
	var th2 = document.createElement("th");
	var th3 = document.createElement("th");
	var th4 = document.createElement("th");
	th1.appendChild(document.createTextNode("#"));
	th2.appendChild(document.createTextNode("Name"));
	th3.appendChild(document.createTextNode("bearbeiten"));
	th1.setAttribute("width", "10%");
	th3.setAttribute("width", "15%");
	th4.appendChild(document.createTextNode("l\u00f6schen"));
	th4.setAttribute("width", "15%");
	th3.setAttribute("style", "text-align:right");
	th4.setAttribute("style", "text-align:right");
	tr1.appendChild(th1);
	tr1.appendChild(th2);
	tr1.appendChild(th3);
	tr1.appendChild(th4);
	tbody.appendChild(tr1);

	for(var i = 0; i < tasks.length; i++) {
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		td1.appendChild(document.createTextNode(tasks[i].ID));
		td2.appendChild(document.createTextNode(tasks[i].name));
		td2.setAttribute("style", "overflow: hidden; text-overflow: ellipsis; white-space: nowrap;");
		td3.appendChild(createButton("editIssue", "editTask", "", "", "", "interaction.getTimeTaskById("+tasks[i].ID+")", "", ""));
		td4.appendChild(createButton("delete", "deleteTask", "", "", "", "deleteTimeTask("+tasks[i].ID+", '"+selectedDay+"')", "", ""));
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	div.appendChild(table);
	
	var tableDiv = document.createElement("div");
	tableDiv.setAttribute("id", "tasksOfDayDiv");
	tableDiv.appendChild(div);
	document.getElementById("responsiveCalendar").appendChild(tableDiv);
}

/**
 * Delete a Event Task
 * @param id id of Event Task
 */
function deleteEventTask(id) {
	interaction.deleteEventTask(id);
}

/**
 * Delete a Time Task
 * @param id id of Time Task
 * @param selectedDay 
 */
function deleteTimeTask(id, selectedDay) {
	var div = document.getElementById("tasksOfDayDiv");
	document.getElementById("responsiveCalendar").removeChild(div);
	interaction.deleteTimeTask(id, selectedDay);
}