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
			createIssueForTimeTask(data.responseText);
			display.showResponse("Der Time Task wurde erstellt");
		},
		error: function() {
			display.showResponse("Der Time Task wurde NICHT erstellt");
		}	
	})
}

/**
 * Get All Time Task an save them into display.data.
 * then show the Tasks 
 */
server.getAllTimeTask = function() {
	$.ajax({
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
			display.showResponse("Der Time Task wurde bearbeitet");
		},
		error: function() {
			display.showResponse("Der Time Task wurde NICHT bearbeitet");
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
			server.getAllTimeTask();
			$('#showTasksByDate').modal('hide');
			display.showResponse("Der Task wurde gel\u00f6scht");
		},
		error: function() {
			display.showResponse("Der Task wurde NICHT gel\u00f6scht");
		}	
	})
}

/**
 * Get Issues of a Time Task and show them
 */
server.getIssuesOfTimeTask = function(id) {
	$.ajax({
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
			server.getIssuesOfTimeTask(taskId);
			display.showResponse("Die Verbindung wurde gel\u00f6scht");
		},
		error: function() {
			display.showResponse("Die Verbindung wurde NICHT gel\u00f6scht");
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
			createIssueForEventTask(data.responseText);
			display.showResponse("Der Event Task wurde erstellt");
		},
		error: function() {
			display.showResponse("Der Event Task wurde NICHT erstellt");
		}	
	})
}

/**
 * Get All Event Tasks and show them
 */
server.getAllEventTask = function(showTask) {
	$.ajax({
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
			display.showResponse("Der Event Task wurde bearbeitet");
		},
		error: function() {
			display.showResponse("Der Event Task wurde NICHT bearbeitet");
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
			interaction.getAllEventTask();
			display.showResponse("Der Task wurde gel\u00f6scht");
		},
		error: function() {
			display.showResponse("Der Task wurde NICHT gel\u00f6scht");
		}	
	})
}

/**
 * Get Issues of Event Task and show them
 */
server.getIssuesOfEventTask = function(id) {
	$.ajax({
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
			server.getIssuesOfEventTask(taskId);
			display.showResponse("Die Verbindung wurde gel\u00f6scht");
		},
		error: function() {
			display.showResponse("Die Verbindung wurde NICHT gel\u00f6scht");
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
			cleanContainerForGetIssueEntity();
			server.fetchAllIssueEntities(display.showData);
			display.showResponse("Das Issue wurde erstellt");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT erstellt");
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
		complete: function() {
			interaction.displayAllIssueEntities();
			display.showResponse("Das Issue wurde bearbeitet");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT bearbeitet");
		}	
	});
}

/**
 * Get an Issueentity by ID and show it
 */
server.getIssueEntityById = function(id, buildEditIssue) {
	$.ajax({
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
			server.fetchAllIssueEntities(display.showData);
			display.showResponse("Das Issue wurde gel\u00f6scht");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT gel\u00f6scht");
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
			var json = jQuery.parseJSON(data.responseText);
			interaction.createIssueEntity(json.ID);
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
			cleanContainerForGetIssueEntity();
			server.fetchAllIssueEntities(display.showData);
			display.showResponse("Das Issue wurde bearbeitet");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT bearbeitet");
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
			var responseJson = jQuery.parseJSON(data);
			var post = JSON.stringify({ID: responseJson.ID});
			$.ajax({
				type: "POST",
				dataType: "application/json",
				contentType: "application/json",
				url: newUrl + "timetask/"+taskId+"/issuedraft",
				data: post,
				complete: function(data) {
					if(existTask == true) {
						server.getIssuesOfTimeTask(taskId);
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
			var responseJson = jQuery.parseJSON(data);
			var post = JSON.stringify({ID: responseJson.ID});
			$.ajax({
				type: "POST",
				dataType: "application/json",
				contentType: "application/json",
				url: newUrl + "eventtask/"+taskId+"/issuedraft",
				data: post,
				complete: function(data) {
					if(existTask == true) {
						server.getIssuesOfEventTask(taskId);
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
			if(existTask == "false") {
				server.getIssuesOfEventTask(id);
			}
			display.showResponse("Das Issue wurde an den Task geh\u00e4ngt");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT an den Task geh\u00e4ngt");
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
			if(existTask == "false") {
				server.getIssuesOfTimeTask(id);
			}
			display.showResponse("Das Issue wurde an den Task geh\u00e4ngt");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT an den Task geh\u00e4ngt");
		}	
	})
}

/**
 * Get Issuedraft by an Filter and show them
 */
server.filterIssueDrafts = function(json, existTask) {
	$.ajax({
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
			createTaskForEvent(data.responseText);
			display.showResponse("Das Event wurde erstellt");
		},
		error: function() {
			display.showResponse("Das Event wurde NICHT erstellt");
		}	
	})
}

/**
 * Get all Events and show them
 */
server.getAllEvent = function(overview) {
	$.ajax({
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
		complete: function() {
			server.fetchAllIssueEntities(display.showData);
			display.showResponse("Das Event wurde ausgel\u00f6st");
		},
		error: function() {
			display.showResponse("Das Event wurde NICHT ausgel\u00f6st");
		}	
	})
}

/***************************************************
 * Event End
 ***************************************************/