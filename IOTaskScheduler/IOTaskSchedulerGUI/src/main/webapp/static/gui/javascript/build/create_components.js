function createTable(rows, cols) {
	var table = document.createElement("table");
	table.setAttribute("class", "table");
	var tbody = document.createElement("tbody");
	for(var i = 0; i < rows; i++) {
		var tr = document.createElement("tr");
		tbody.appendChild(tr);
	}
	for(var i = 0; i < rows; i++) {
		for(var a = 0; a < cols; a++) {
			var td = document.createElement("td");
			tbody.childNodes[i].appendChild(td);
		}
	}
	table.appendChild(tbody);
	return table;
}

function createTableWithTableHeaders(rows, cols) {
	var table = document.createElement("table");
	table.setAttribute("class", "table");
	var tbody = document.createElement("tbody");
	var thead = document.createElement("thead");
	for(var i = 0; i < rows; i++) {
		var tr = document.createElement("tr");
		if(i == 0) thead.appendChild(tr);
		else tbody.appendChild(tr);
	}
	for(var i = 0; i < cols; i++) {
		var th = document.createElement("th");
		thead.childNodes[0].appendChild(th);
	}
	for(var i = 1; i < rows; i++) {
		for(var i = 0; i < cols; i++) {
			var td = document.createElement("td");
			tbody.childNodes[i].appendChild(td);
		}
	}
	table.appendChild(thead);
	table.appendChild(tbody);
	return table;
}

function buildModalOneFooter(button1, button2) {
	var footer = document.getElementById("modalOneFooter");
	var length = footer.childNodes.length;
	for(var i = 0; i < length; i++) {
		footer.removeChild(footer.lastChild);
	}
	footer.appendChild(button1);
	if(button2 != undefined) footer.appendChild(button2);
}

function buildModalOneHeader(header) {
	var h3 = document.getElementById("myModalOneLabel");
	for(var i = 0; i < h3.childNodes.length; i++) {
		h3.removeChild(h3.lastChild);
	}
	h3.appendChild(document.createTextNode(header));
}

function buildModalTwoFooter(button1, button2) {
	var footer = document.getElementById("modalTwoFooter");
	var length = footer.childNodes.length;
	for(var i = 0; i < length; i++) {
		footer.removeChild(footer.lastChild);
	}
	footer.appendChild(button1);
	if(button2 != undefined) footer.appendChild(button2);
}

function buildModalTwoHeader(header) {
	var h3 = document.getElementById("myModalTwoLabel");
	for(var i = 0; i < h3.childNodes.length; i++) {
		h3.removeChild(h3.lastChild);
	}
	h3.appendChild(document.createTextNode(header));
}

function buildModalThreeFooter(button1, button2) {
	var footer = document.getElementById("modalThreeFooter");
	var length = footer.childNodes.length;
	for(var i = 0; i < length; i++) {
		footer.removeChild(footer.lastChild);
	}
	footer.appendChild(button1);
	if(button2 != undefined) footer.appendChild(button2);
}

function buildModalThreeHeader(header) {
	var h3 = document.getElementById("myModalThreeLabel");
	for(var i = 0; i < h3.childNodes.length; i++) {
		h3.removeChild(h3.lastChild);
	}
	h3.appendChild(document.createTextNode(header));
}