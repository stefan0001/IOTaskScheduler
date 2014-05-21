var server = {};

setupServer();

function setupServer(){
	pathArray = window.location.href.split( '/' );
	protocol = pathArray[0];
	host = pathArray[2];
	app = pathArray[3];
	server.url = protocol + '//' + host + '/' + app +"/";
};


server.fetchAllIssueEntities = function( showData ){
	$.ajax({
	  dataType: "json",
	  url: this.url + "issueentity",
	  data: "",
	  success: showData
	});
};


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

server.getIssueEntityById = function(id, buildEditIssue) {
	$.ajax({
		dataType: "json",
		url: this.url + "issueentity/"+id,
		data: "",
		success: buildEditIssue
	});
}

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

server.putNewIssueDraftForTimeTask = function(taskId, json, existTask) {
	var newUrl = this.url;
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft",
		data: json,
		complete: function(data) {
			var idAt = data.responseText.search(/ID/);
			var slice = data.responseText.slice(idAt+4, idAt+6);
			var id = slice;
			if(slice.charAt(1) == ",") {
				id = slice.slice(0, 1);
			}
			var post = JSON.stringify({ID: id});
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

server.putNewIssueDraftForEventTask = function(taskId, json, existTask) {
	var newUrl = this.url;
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "issuedraft",
		data: json,
		complete: function(data) {
			var idAt = data.responseText.search(/ID/);
			var slice = data.responseText.slice(idAt+4, idAt+6);
			var id = slice;
			if(slice.charAt(1) == ",") {
				id = slice.slice(0, 1);
			}
			var post = JSON.stringify({ID: id});
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

server.fetchAllTimeTask = function() {
	$.ajax({
		dataType: "json",
		url: this.url + "timetask",
		data: "",
		success: function(data) {
			selectTimeTaskView(data);
		}
	});
}

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

server.postExistentIssueDraftForEventTask = function(id, json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "eventtask/"+id+"/issuedraft",
		data: json,
		complete: function(data) {
			server.getIssuesOfEventTask(id);
			display.showResponse("Das Issue wurde an den Task geh\u00e4ngt");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT an den Task geh\u00e4ngt");
		}	
	})
}

server.postExistentIssueDraftForTimeTask = function(id, json) {
	$.ajax({
		type: "POST",
		dataType: "application/json",
		contentType: "application/json",
		url: this.url + "timetask/"+id+"/issuedraft",
		data: json,
		complete: function(data) {
			server.getIssuesOfTimeTask(id);
			display.showResponse("Das Issue wurde an den Task geh\u00e4ngt");
		},
		error: function() {
			display.showResponse("Das Issue wurde NICHT an den Task geh\u00e4ngt");
		}	
	})
}

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

server.filterIssueEntitys = function(json, showData) {
	$.ajax({
		  dataType: "json",
		  url: this.url + "issueentity?filter="+json,
		  data: "",
		  success: showData
		});
}

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