/**
 * execute when page is loaded
 */
function start() {
	interaction.displayAllIssueEntities();
}

/**
 * Delete all Issues from View
 */
function cleanContainer() {
	var container1 = document.getElementById("accordion1");
	var container2 = document.getElementById("accordion2");
	var container3 = document.getElementById("accordion3");
	
	for(var i = container1.childNodes.length; i > 0; i--) {
		container1.removeChild(container1.lastChild);
	}
	for(var i = container2.childNodes.length; i > 0; i--) {
		container2.removeChild(container2.lastChild);
	}
	for(var i = container3.childNodes.length; i > 0; i--) {
		container3.removeChild(container3.lastChild);
	}
	interaction.displayAllIssueEntities();
}

/**
 * Statistic, how many Issues are in a Container
*/
function countIssueContainer() {
	var countNew = (document.getElementById("accordion1").childNodes.length);
	var countWik = (document.getElementById("accordion2").childNodes.length);
	var countClosed = (document.getElementById("accordion3").childNodes.length);
	
	for(var i = document.getElementById("newBadge").childNodes.length; i > 0; i--) {
		document.getElementById("newBadge").removeChild(document.getElementById("newBadge").lastChild);
	}
	for(var i = document.getElementById("wikBadge").childNodes.length; i > 0; i--) {
		document.getElementById("wikBadge").removeChild(document.getElementById("wikBadge").lastChild);
	}
	for(var i = document.getElementById("closedBadge").childNodes.length; i > 0; i--) {
		document.getElementById("closedBadge").removeChild(document.getElementById("closedBadge").lastChild);
	}
	
	document.getElementById("newBadge").appendChild(document.createTextNode(countNew));
	document.getElementById("wikBadge").appendChild(document.createTextNode(countWik));
	document.getElementById("closedBadge").appendChild(document.createTextNode(countClosed));
}

function cleanContainerForGetIssueEntity() {
	var container1 = document.getElementById("accordion1");
	var container2 = document.getElementById("accordion2");
	var container3 = document.getElementById("accordion3");
	
	for(var i = container1.childNodes.length; i > 0; i--) {
		container1.removeChild(container1.lastChild);
	}
	for(var i = container2.childNodes.length; i > 0; i--) {
		container2.removeChild(container2.lastChild);
	}
	for(var i = container3.childNodes.length; i > 0; i--) {
		container3.removeChild(container3.lastChild);
	}
}