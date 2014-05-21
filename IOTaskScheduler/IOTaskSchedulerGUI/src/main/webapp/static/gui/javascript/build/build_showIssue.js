/**
 * Shows the Issues from the Database
 */
function showIssueFromDatabase(id, status, resolution, tid, name, description, type) {
	if(status == "NEW" || status == "OPEN") {
		var accordion = "accordion1";
	} else if(status == "IN_PROGRESS") {
		var accordion = "accordion2";
	} else if(status == "CLOSED") {
		var accordion = "accordion3"
	}
	
	var div1 = document.createElement("div");
	var div2 = document.createElement("div");
	var div3 = document.createElement("div");
	var div4 = document.createElement("div");
	var button = createButton("editIssue", "", "", "", "", "getToEditIssue("+id+")", "modal", "#modalOne");
	
	div1.setAttribute("class", "panel panel-default");
	div2.setAttribute("class", "panel-heading");
	div2.setAttribute("style", "overflow: auto;");
	div3.setAttribute("id", id);
	div3.setAttribute("class", "panel-collapse collapse");
	div4.setAttribute("class", "panel-body");
	
	var heading = document.createElement("h6");
	heading.setAttribute("class", "panel-title");
	
	var link = document.createElement("a");
	link.setAttribute("data-toggle", "collapse");
	link.setAttribute("data-parent", "#"+accordion);
	link.setAttribute("href", "#"+id);
	link.appendChild(div2);

	div4.appendChild(button);
	if(status == "CLOSED") {
		var deleteButton = createButton("remove", "", "", "", "", "interaction.deleteIssueEntity("+id+")", "", "");
		div4.appendChild(deleteButton);
	}
	div4.appendChild(document.createTextNode(description+' '));
	
	div3.appendChild(div4);
	
	heading.appendChild(document.createTextNode(name));
	
	div2.appendChild(heading);
	
	div1.appendChild(link);
	div1.appendChild(div3);
	
	var acc = document.getElementById(accordion);
	acc.appendChild(div1);
}