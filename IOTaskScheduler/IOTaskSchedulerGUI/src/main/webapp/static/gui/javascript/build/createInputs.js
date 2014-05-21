function createTextField(name, style, placeholder, id, value) {
	var textfield = document.createElement("input");
	textfield.setAttribute("type", "text");
	textfield.setAttribute("class", "taskeingabe");
	textfield.setAttribute("name", name);
	textfield.setAttribute("style", style);
	textfield.setAttribute("id", id);
	textfield.setAttribute("value", value);
	textfield.setAttribute("placeholder", placeholder);
	
	return textfield;
}

function createDateInput(cssClass, name) {
	var dateInput = document.createElement("input");
	dateInput.setAttribute("type", "date");
	dateInput.setAttribute("class", cssClass);
	dateInput.setAttribute("name", name);
	
	return dateInput;
}

function createCheckbox(id, onchange, name, value) {
	var checkbox = document.createElement("input");
	checkbox.setAttribute("type", "checkbox");
	checkbox.setAttribute("id", id);
	checkbox.setAttribute("onchange", onchange);
	checkbox.setAttribute("name", name);
	checkbox.setAttribute("value", value);
	
	return checkbox;
}

function createLabel(forElem, child) {
	var label = document.createElement("label");
	label.setAttribute("for", forElem);
	label.appendChild(child);
	
	return label;
}

function createButton(cssClass, name, id, value, style, onclick, datatoggle, datatarget) {
	var button = document.createElement("button");
	button.setAttribute("type", "button");
	button.setAttribute("class", cssClass);
	button.setAttribute("name", name);
	button.setAttribute("id", id);
	button.setAttribute("value", value);
	button.setAttribute("style", style);
	button.setAttribute("onclick", onclick);
	button.setAttribute("data-toggle", datatoggle);
	button.setAttribute("data-target", datatarget);
	button.appendChild(document.createTextNode(value));
	
	return button;
}

function createSelect(size, name) {
	var select = document.createElement("select");
	select.setAttribute("size", size);
	select.setAttribute("name", name);
	
	return select;
}

function createOption(child) {
	var option = document.createElement("option");
	option.appendChild(document.createTextNode(child));
	option.setAttribute("value", child);
	return option;
}

function createTextarea(id, placeholder, name, cssClass, rows) {
	var textarea = document.createElement("textarea");
	textarea.setAttribute("id", id);
	textarea.setAttribute("placeholder", placeholder);
	textarea.setAttribute("name", name);
	textarea.setAttribute("class", cssClass);
	textarea.setAttribute("rows", rows);
	
	return textarea;
}

function createRadioButton(name, value, id, onchange) {
	var button = document.createElement("input");
	button.setAttribute("type", "radio");
	button.setAttribute("name", name);
	button.setAttribute("value", value);
	button.setAttribute("id", id);
	button.setAttribute("onchange", onchange);
	
	return button;
}