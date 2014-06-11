var display = {};

display.data = null;

display.displayAllIssueEntities = function( data ){
	display.data = data;
	cleanContainerForGetIssueEntity();
	for( var i = 0; i < data.content.length; i++ ) {
		showIssueFromDatabase(data.content[i].ID, data.content[i].issueStatus, data.content[i].issueResolution, data.content[i].embedded.issueDraft.id, data.content[i].embedded.issueDraft.issueName, data.content[i].embedded.issueDraft.issueDescription, data.content[i].embedded.issueDraft.issueType);
	}
	countIssueContainer();
}

display.getData = function() {
	return this.data;
}

display.setData = function(data) {
	this.data = data;
}

display.showData = function(data) {
	display.data = data;
	display.displayAllIssueEntities(data);
}

display.showResponse = function(text, success) {
	$('#statusmessage').text(text).animate({'margin-bottom':30},200);
	if(success == true) $('#statusmessage').css({'background-color': 'green'});
	else $('#statusmessage').css({'background-color': 'red'});
	setTimeout( function(){
        $('#statusmessage').animate({'margin-bottom':-150},200);
    }, 3*1000);
}

display.buildEditIssue = function(data) {
	buildEditIssue(data);
}



