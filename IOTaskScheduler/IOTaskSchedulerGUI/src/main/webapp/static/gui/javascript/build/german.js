var language = {};
language.test = "Test";

load(language);




function load(language) {
	
	document.getElementById("newTaskButton").appendChild(document.createTextNode(language.test));
	console.log(document.getElementById("newTaskButton"));
}