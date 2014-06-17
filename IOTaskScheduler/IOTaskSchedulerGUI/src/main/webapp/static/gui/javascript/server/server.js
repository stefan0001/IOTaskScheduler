var server = {};

setupServer();

function setupServer(){
	pathArray = window.location.href.split( '/' );
	protocol = pathArray[0];
	host = pathArray[2];
	app = pathArray[3];
	server.url = protocol + '//' + host + '/' + app +"/";
};

/***************************************************
 * Tasks Start
 ***************************************************/

//Time Tasks
/**
 * Create a Time Task By the passed JSON.
 * then Create the Issues of the Task
 */
server.postNewTimeTask = function(json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask",
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				createIssueForTimeTask(data.responseText);
			} else {
				display.showResponse("Der Time Task konnte nicht erstellt werden", false);
			}
		}
	})
}

/**
 * Get All Time Task an save them into display.data.
 * then show the Tasks 
 */
server.getAllTimeTask = function() {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "timetask",
		data: "",
		success: function(data) {
			display.setData(data);
			selectTimeTaskView(data);
		}
	});
}

/**
 * Get a Time Task by an ID and then show it 
 */
server.getTimeTaskById = function(id) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "timetask/"+id,
		data: "",
		success: function(data) {
			buildEditTask(data);
		}
	});
}

/**
 * Update a Time Task
 */
server.updateTimeTask = function(id, json) {
	$.ajax({
		type: "PUT",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask/"+id,
		data: json,
		complete: function(data) {
			if(data.status == 200) display.showResponse("Der Time Task wurde bearbeitet", true);
			else display.showResponse("Der Time Task konnte nicht bearbeitet werden", false);
		}
	})
}

/**
 * Delete a Time Task and then show it
 */
server.deleteTimeTask = function(id, selectedDay) {
	$.ajax({
		type: "DELETE",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask/"+id,
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				server.getAllTimeTask();
				$('#showTasksByDate').modal('hide');
				display.showResponse("Der Task wurde gel\u00f6scht", true);
			} else display.showResponse("Der Task konnte NICHT gel\u00f6scht werden", false);
		}
	})
}

/**
 * Get Issues of a Time Task and show them
 */
server.getIssuesOfTimeTask = function(id) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "timetask/"+id+"/issuedraft",
		data: "",
		success: function(data) {
			showIssuesOfTimeTask(data, id);
		}
	});
}

/**
 * Remove a Timetask to Issue Connection and show them 
 */
server.removeTimeTaskIssueConnection = function(issueId, taskId) {
	$.ajax({
		type: "DELETE",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask/"+taskId+"/issuedraft/"+issueId,
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				server.getIssuesOfTimeTask(taskId);
				display.showResponse("Die Verbindung wurde gel\u00f6scht", true);
			} else display.showResponse("Die Verbindung konnte nicht gel\u00f6scht werden", false);
		}
	})
}

//Event Tasks
/**
 * Create a Event Task and create the Issues for this Taks
 */
server.postNewEventTask = function(eventId, json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "event/"+eventId+"/eventtask",
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				createIssueForEventTask(data.responseText);
			} else display.showResponse("Der Event Task konnte nicht erstellt werden", false);
		}
	})
}

/**
 * Get All Event Tasks and show them
 */
server.getAllEventTask = function(showTask) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "eventtask",
		data: "",
		success: function(data) {
			selectEventTaskView(data);
		}
	});
}

/**
 * get an Event Task and show it
 */
server.getEventTaskById = function(id) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "eventtask/"+id,
		data: "",
		success: function(data) {
			buildEditTask(data);
		}
	});
}

/**
 * Update a Event Task
 */
server.updateEventTask = function(id, json) {
	$.ajax({
		type: "PUT",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "eventtask/"+id,
		data: json,
		complete: function(data) {
			if(data.status == 200) display.showResponse("Der Event Task wurde bearbeitet", true);
			else display.showResponse("Der Event Task kontne nicht bearbeitet werden", false);
		}
	})
}

/**
 * Delete a Event Task and show the existend
 */
server.deleteEventTask = function(id) {
	$.ajax({
		type: "DELETE",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "eventtask/"+id,
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				interaction.getAllEventTask();
				display.showResponse("Der Task wurde gel\u00f6scht", true);
			} else display.showResponse("Der Task konnte nicht gel\u00f6scht werden", false);
		}
	})
}

/**
 * Get Issues of Event Task and show them
 */
server.getIssuesOfEventTask = function(id) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "eventtask/"+id+"/issuedraft",
		data: "",
		success: function(data) {
			showIssuesOfEventTask(data, id);
		}
	});
}

/**
 * Remove a Event Task to Issue Connection and show the existend
 */
server.removeEventTaskIssueConnection = function(issueId, taskId) {
	$.ajax({
		type: "DELETE",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "eventtask/"+taskId+"/issuedraft/"+issueId,
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				server.getIssuesOfEventTask(taskId);
				display.showResponse("Die Verbindung wurde entfernt", true);
			} else display.showResponse("Die Verbindung konnte nicht entfernt werden", false);
		}
	})
}

/***************************************************
 * Tasks End
 ***************************************************/


/***************************************************
 * Issueentity Start
 ***************************************************/

/**
 * Get All Issueentiktys and show them
 */
server.fetchAllIssueEntities = function( showData ){
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issueentity",
		data: "",
	 	success: showData
	});
};

/**
 * Create a Issueentity by an Issuedraft and show all Issueentitys
 */
server.createIssueEntityFor = function(id) {
	var json = JSON.stringify({issueStatus: "NEW", issueResolution: "UNRESOLVED"});
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft/"+id+"/issueentity",
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				cleanContainerForGetIssueEntity();
				server.fetchAllIssueEntities(display.showData);
				display.showResponse("Das Issue wurde erstellt", true);
			} else display.showResponse("Das Issue konnte nicht erstellt werden", false);
		}
	})
}

/**
 * Update an Issueentity and show all Issueentitys
 */
server.updateIssueentity = function(id, json) {
	$.ajax({
		type: "PUT",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issueentity/"+id,
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				interaction.displayAllIssueEntities();
				display.showResponse("Das Issue wurde bearbeitet", true);
			} else display.showResponse("Das Issue konnte nicht bearbeitet werden", false);
		}
	});
}

/**
 * Get an Issueentity by ID and show it
 */
server.getIssueEntityById = function(id, buildEditIssue) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issueentity/"+id,
		data: "",
		success: buildEditIssue
	});
}

/**
 * Get Issueentitys by an Filter and show it
 */
server.filterIssueEntitys = function(json, showData) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issueentity?filter="+json,
		data: "",
		success: showData
	});
}

/**
 * Delete an Issueentity and show the existend
 */
server.deleteIssueEntity = function(id) {
	$.ajax({
		type: "DELETE",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issueentity/"+id,
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				server.fetchAllIssueEntities(display.showData);
				display.showResponse("Das Issue wurde gel\u00f6scht", true);
			} else display.showResponse("Das Issue konnte nicht gel\u00f6scht werden", false);
		}
	})
}


/***************************************************
 * Issueentity End
 ***************************************************/


/***************************************************
 * Issuedraft Start
 ***************************************************/

/**
 * Get all Issuedraft and return them 
 */
server.fetchAllIssueDrafts = function(  ){
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issuedraft",
		data: "",
		success: function(data) {
			return data;
		}
	});
};

/**
 * Create an Issuedraft and create an Issueentity for it
 */
server.postNewIssueDraft = function(json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft",
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				var json = jQuery.parseJSON(data.responseText);
				interaction.createIssueEntity(json.ID);
			} else display.showResponse("Das Issue konnte nicht erstellt werden", false);
		}
	})
}

/**
 * Update Issuedraft and show the Issues
 */
server.updateIssuedraft = function(id, json) {
	$.ajax({
		type: "PUT",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft/"+id,
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				cleanContainerForGetIssueEntity();
				server.fetchAllIssueEntities(display.showData);
				display.showResponse("Das Issue wurde bearbeitet", true);
			} else display.showResponse("Das Issue konnte nicht bearbeitet werden", false);
		}	
	});
}

/**
 * Create an Issuedraft and attach it to a Time Task
 */
server.putNewIssueDraftForTimeTask = function(taskId, json, existTask) {
	var newUrl = this.url;
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft",
		data: json,
		complete: function(data) {
			console.log(data);
			var responseJson = jQuery.parseJSON(data.responseText);
			var post = JSON.stringify({ID: responseJson.ID});
			$.ajax({
				type: "POST",
				dataType: "application/json",
				contentType: "application/json",
				url: newUrl + "timetask/"+taskId+"/issuedraft",
				data: post,
				complete: function(data) {
					if(existTask == true) {
						if(data.status == 200) {
							server.getIssuesOfTimeTask(taskId);
							display.showResponse("Das Issue wurde an den Time Task angehängt", true);
						} else display.showResponse("Das Issue konnte nicht an den Time Task angehängt werden", false);
					} else {
						if(data.status == 200) display.showResponse("Der Time Task wurde erstellt", true);
						else display.showResponse("Der Time Task konnte nicht erstellt werden", false);
					}
				}
			})
		}
	})
}

/**
 * Create an Issuedraft and attach it to a Event Task
 */
server.putNewIssueDraftForEventTask = function(taskId, json, existTask) {
	var newUrl = this.url;
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft",
		data: json,
		complete: function(data) {
			console.log(data);
			var responseJson = jQuery.parseJSON(data.responseText);
			var post = JSON.stringify({ID: responseJson.ID});
			$.ajax({
				type: "POST",
				dataType: "application/json",
				contentType: "application/json",
				url: newUrl + "eventtask/"+taskId+"/issuedraft",
				data: post,
				complete: function(data) {
					if(existTask == true) {
						if(data.status == 200) {
							server.getIssuesOfEventTask(taskId);
							display.showResponse("Das Issue wurde an den Event Task angehängt", true);
						} else display.showResponse("Das Issue konnte nicht an den Event Task angehängt werden", true);
					} else {
						if(data.status == 200) display.showResponse("Der Event Task wurde erstellt", true);
						else display.showResponse("Der Event Task konnte nicht erstellt werden", false);
					}
				}
			})
		}
	})
}

/**
 * Get All Issuedraft and show them
 */
server.getAllIssueDraft = function(existTask, timeTask) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issuedraft",
		data: "",
		success: function(data) {
			buildSelectIssueModal(data, existTask, timeTask);
		}
	});
}

/**
 * create a Event Task to Issuedraft Connection
 */
server.postExistentIssueDraftForEventTask = function(id, json, existTask) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "eventtask/"+id+"/issuedraft",
		data: json,
		complete: function(data) {
			if(existTask == true) {
				if(data.status == 200) {
					display.showResponse("Das Issue wurde an den Event Task geh\u00e4ngt", true);
					server.getIssuesOfEventTask(id);
				} else display.showResponse("Das Issue konnte nicht an den Event Task geh\u00e4ngt werden", false);
			} else {
				if(data.status == 200) display.showResponse("Der Event Task wurde erstellt", true);
				else display.showResponse("Der Event Task konnte nicht erstellt werden", false);
			}
		}	
	})
}

/**
 * create a Time Task to Issuedraft Connection
 */
server.postExistentIssueDraftForTimeTask = function(id, json, existTask) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask/"+id+"/issuedraft",
		data: json,
		complete: function(data) {
			if(existTask == true) {
				if(data.status == 200) {
					display.showResponse("Das Issue wurde an den Time Task geh\u00e4ngt", true);
					server.getIssuesOfTimeTask(id);
				} else display.showResponse("Das Issue konnte nicht an den Time Task geh\u00e4ngt werden", false);
			} else {
				if(data.status == 200) display.showResponse("Der Time Task wurde erstellt", true);
				else display.showResponse("Der Time Task konnte nicht erstellt werden", false);
			}
		}
	})
}

/**
 * Get Issuedraft by an Filter and show them
 */
server.filterIssueDrafts = function(json, existTask) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "issuedraft?filter="+json,
		data: "",
		success: function(data) {
			buildSelectIssueModal(data, existTask)
		}
	});
}

/***************************************************
 * Issuedraft End
 ***************************************************/


/***************************************************
 * Event Start
 ***************************************************/

/**
 * create a Event and then create a Task for it
 */
server.createNewEvent = function(json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "event",
		data: json,
		complete: function(data) {
			if(data.status == 200) {
				createTaskForEvent(data.responseText);
			} else display.showResponse("Das Event konnte nicht erstellt werden", false);
		}
	})
}

/**
 * Get all Events and show them
 */
server.getAllEvent = function(overview) {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: this.url + "event",
		data: "",
		success: function(data) {
			selectEventInModal(data, overview);
		}
	});
}

/**
 * Trigger an Event and show the Issues
 */
server.triggerEvent = function(id) {
	$.ajax({
		type: "PUT",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "event/"+id+"/trigger",
		data: "",
		complete: function(data) {
			if(data.status == 200) {
				server.fetchAllIssueEntities(display.showData);
				display.showResponse("Das Event wurde ausgel\u00f6st", true);
			} else display.showResponse("Das Event konnte nicht ausgel\u00f6st werden", false);
			
		}
	})
}

/***************************************************
 * Event End
 ***************************************************/