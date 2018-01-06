/*



 * 
 * INDORAMA.js
 * @author : 01/04/17 - PJD
 * @author : 19/11/17 - MS
 * 
 * Description : 
 * This contains all the jquery code used for CRUD of Users module , 
 * Assigntask module , Error module.
 * 
 * 
 * CHANGELOG : PJD - 25/07/17 Created Group Module
 * 
 * Notes: 
 * 1. JQuery delegate function is used for dynamically generated html
 * 2. Bootstrap 
 * 
 * ASSIGNTASK MODULE 
 * 1. CRUD ASSIGNTASK
 * 2. TO POPULATE DATA IN AUTOCOMPLETEVIEW USED jquery for ATTENT_BY FIELD
 * 3. TIMEPICKER (timepicker1 & timepicker2) for TIMETO AND TIMEFROM
 * 4. TO DISPLAY STAIC IMAGE FOR ERROR PAGE
 *  
 */
$(document)
    .ready(
        function() {
            // Add background image to login page
//            $(".container_login")
//                .closest('div')
//                .closest('body')
//                .css({
//                    "background": 'url("static/images/stage_backdrop.jpg") no-repeat center fixed',
//                    "-webkit-background-size": "cover",
//                    "-moz-background-size": "cover",
//                    "-o-background-size": "cover",
//                    "background-size": "cover",
//                    "overflow": "auto"
//                });

            /*********************************** userlist table function ***********************************/
            // userlist tables 
            var checkedRows = [];

            // check individual row
            $('#userTable').on('check.bs.table', function(e, row) {
                checkedRows.push({
                    id: row.id,
                    email: row.email
                });
                console.log(JSON.stringify(checkedRows));
            });

            // uncheck individual row
            $('#userTable').on('uncheck.bs.table', function(e, row) {

                $.each(checkedRows, function(index, value) {
                    if (value.id === row.id) {
                        checkedRows.splice(index, 1);
                    }
                });
                console.log(JSON.stringify(checkedRows));
            });

            // remove all checked rows
            $('#userTable').on('uncheck-all.bs.table', function(e) {
                checkedRows.splice(0, checkedRows.length);
                console.log(JSON.stringify(checkedRows));
            });

            // check all rows
            $('#userTable').on('check-all.bs.table', function(e) {
                //Assumption if one or multiple row is checked
                checkedRows.splice(0, checkedRows.length);
                $("#userTable tr:has(:checkbox:checked) td:nth-child(3)").each(function() {
                    checkedRows.push({
                        email: $(this).text()
                    });
                });
                console.log(JSON.stringify(checkedRows));
            });

            // toggle button to disable add/edit button for multiple
            // checkbox select
            $('#userTable tr').find('input:checkbox:first').change(
                function() {

                    // this will contain a reference to the checkbox
                    if (this.checked) {
                        $('#useradd').prop('disabled', true);
                        $('#useredit').prop('disabled', true);
                    } else {
                        $('#useradd').prop('disabled', false);
                        $('#useredit').prop('disabled', false);
                    }
                });

            // check if more than one checkbox checked
            if ($('#userTable tr:has(:checkbox:checked)').length > 1) {
                 $('#useradd').prop('disabled', true);
                 $('#useredit').prop('disabled', true);
            } else {
                 $('#useradd').prop('disabled', false);
                 $('#useredit').prop('disabled', false);
            }
            // Userlist delete function
            $('#userdelete').click(
                function(event) {

                    if ($('#userTable tr:has(:checkbox:checked)').length == 0) {
                    	  $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select one or more user from the list...</a></li>').appendTo('#error_list');
                    } else {

                        var params = {
                            "userIds": JSON.stringify(checkedRows),
                            "userAction": "delete"
                        }
                        $.ajax({
                            url: '/userlist',
                            type: "POST",
                            data: params,
                            success: function(data) {
                                window.location.href = "http://localhost:8080/userlist"
                            }
                        });

                        event.preventDefault();
                    }
                });

            // Userlist add function will call /register page
            $('#useradd').click(
            				function(event){
                    window.location.href = "http://localhost:8080/register";
                    event.preventDefault();
            				});
            // Userlist edit function
            $('#useredit').click(
                function(event) {

                    if ($('#userTable tr:has(:checkbox:checked)').length == 0) {
                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select a user from the list...</a></li>').appendTo('#error_list');
                    } else {
                        console.log(JSON.stringify(checkedRows));
                        var params = {
                            "userIds": JSON.stringify(checkedRows),
                            "userAction": "edit"
                        }
                        var regUser = "";
                        $.ajax({
                            url: '/userlist',
                            type: "POST",
                            data: params,

                            success: function(data) {

                                //get subelements from div('#register_user') from register.jsp
                                regUserElements = $(data).find("#register_user").html();

                                //clear previous added html elements
                                $('#editform').empty();
                              
                                //append html subelements
                                $(regUserElements).appendTo("#editform");
                                
                                //mark username field as read-only
                                $("#username").prop("readonly", true);
                                $('#divPassword').remove();

                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: false,
                                    height: 525,
                                    width: 600,
                                    modal: true,

                                    position: {
                                        my: "center+50",
                                        at: "center+50",
                                        of: "body"
                                        	
                                        	
                                    },
                                    close: function() {
                                        form[0].reset();
//                                        allFields.removeClass("ui-state-error");
                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

                                form = dialog.find("form").on("submit", function(event) {

                                    var isValid = editUser();
                                    if (isValid) {
                                        console.log("VALID - " + isValid);
                                        return;
                                    }
                                    event.preventDefault();

                                });

                                /*** edit user dialog form validation *****/
                                var dialog, form;

                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                                    fname = $("#firstname"),
                                    lname = $("#lastname"),
                                   // username = $("#username"),
                                    address = $("#address"),
                                    city = $("#city"),
                                    state = $("#state"),
                                    zipcode = $("#zipcode"),
                                    company_name = $("#company_name"),
                                    phone = $("#phone"),
                                    allFields = $([]).add(fname).add(lname).add(address).
                                    add(city).add(state).add(zipcode).add(company_name).add(phone),
                                    tips = $(".validateTips");

                                function updateTips(t) {
                                    tips
                                        .text(t)
                                        .addClass("ui-state-highlight");
                                    setTimeout(function() {
                                        tips.removeClass("ui-state-highlight", 1500);
                                    }, 500);
                                }

                                function checkLength(o, n, min, max) {
                                    if (o.val().length > max || o.val().length < min) {
                                        o.addClass("ui-state-error");
                                        updateTips("Length of " + n + " must be between " +
                                            min + " and " + max + ".");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function checkRegexp(o, regexp, n) {
                                    if (!(regexp.test(o.val()))) {
                                        o.addClass("ui-state-error");
                                        updateTips(n);
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function editUser() {
                                    var valid = true;
                                    allFields.removeClass("ui-state-error");

                                    valid = valid && checkLength(fname, "firstname", 3, 80);
                                    valid = valid && checkLength(lname, "lastname", 3, 80);
                                    valid = valid && checkLength(address, "address", 3, 80);
                                    valid = valid && checkLength(city, "city", 3, 80);
                                    valid = valid && checkLength(state, "state", 3, 80);
                                    valid = valid && checkLength(zipcode, "zipcode", 6, 6);
                                    valid = valid && checkLength(company_name, "company", 3, 80);
                                    valid = valid && checkLength(phone, "phone", 10, 10);
                                    valid = valid && checkRegexp(username, emailRegex, "eg. xyz@abc.com");

                                    //valid = valid && checkLength( password, "password", 5, 16 );
                                    //valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
                                    //valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
                                    return valid;
                                }


                            }

                        });
                    }
                    event.preventDefault();
                });
            // Userlist delete function
            $('#usercancel').click(
                function(event) {
                    window.location.href = "http://localhost:8080/dashboard";
                    event.preventDefault();
                });
            
            $('.form_datetime').datetimepicker({
            
                autoclose: 1,
                todayBtn: 1,
                format: "yyyy-mm-dd  hh:ii"
            });
            $('#timepicker1').timepicker();
            $('#timepicker2').timepicker();
      
            function split( val ) {
                return val.split( /,\s*/ );
              }
              function extractLast( term ) {
                return split( term ).pop();
              }
              
              var availableTags = $.parseJSON(
            		    $.ajax(
            		        {
            		           url: "static/tables/realnames.json", 
            		           async: false, 
            		           dataType: 'json'
            		        }
            		    ).responseText
            		);
           
              $( "#attendby" )
                // don't navigate away from the field on tab when selecting an item
                .on( "keydown", function( event ) {
                
                  if ( event.keyCode === $.ui.keyCode.TAB &&
                      $( this ).autocomplete( "instance" ).menu.active ) {
                    event.preventDefault();
                  }
                })
                .autocomplete({
                  minLength: 0,
                  source: function( request, response ) {
                    // delegate back to autocomplete, but extract the last term
                    response( $.ui.autocomplete.filter(
                      availableTags, extractLast( request.term ) ) );
                  },
                  focus: function() {
                    // prevent value inserted on focus
                    return false;
                  },
                  select: function( event, ui ) {
                    var terms = split( this.value );
                    // remove the current input
                    terms.pop();
                    // add the selected item
                    terms.push( ui.item.value );
                    // add placeholder to get the comma-and-space at the end
                    terms.push( "" );
                    this.value = terms.join( ", " );
                    return false;
                  }
                });
            
   
//******************************************Assigntask modules starts*************************************//
//              assigntask list
              var checkedRows = [];

              // check individual row
              $('#taskTable').on('check.bs.table', function(e, row) {
                  checkedRows.push({
                      id: row.id,
                      title: row.title
                  });
                  console.log(JSON.stringify(checkedRows));
              });

              // uncheck individual row
              $('#taskTable').on('uncheck.bs.table', function(e, row) {

                  $.each(checkedRows, function(index, value) {
                      if (value.id === row.id) {
                          checkedRows.splice(index, 1);
                      }
                  });
                  console.log(JSON.stringify(checkedRows));
              });

              // remove all checked rows
              $('#taskTable').on('uncheck-all.bs.table', function(e) {
                  checkedRows.splice(0, checkedRows.length);
                  console.log(JSON.stringify(checkedRows));
              });

              // check all rows
              $('#taskTable').on('check-all.bs.table', function(e) {
                  //Assumption if one or multiple row is checked
                  checkedRows.splice(0, checkedRows.length);
                  $("#taskTable tr:has(:checkbox:checked) td:nth-child(2)").each(function() {
                      checkedRows.push({
                          title: $(this).text()
                      });
                  });
                  console.log(JSON.stringify(checkedRows));
              });

              // toggle button to disable add/edit button for multiple
              // checkbox select
              $('#taskTable tr').find('input:checkbox:first').change(
                  function() {

                      // this will contain a reference to the checkbox
                      if (this.checked) {
                          $('#taskadd').prop('disabled', true);
                          $('#taskedit').prop('disabled', true);
                       
                      } else {
                          $('#taskadd').prop('disabled', false);
                          $('#taskedit').prop('disabled', false);
                      }
                  });

              // check if more than one checkbox checked
              if ($('#taskTable tr:has(:checkbox:checked)').length > 1) {
                   $('#taskadd').prop('disabled', true);
                   $('#taskedit').prop('disabled', true);
              } else {
                   $('#taskadd').prop('disabled', false);
                   $('#taskedit').prop('disabled', false);
              }

//     			Deletion operation      
              $('#taskdelete').click(
                  function(event) {

                      if ($('#taskTable tr:has(:checkbox:checked)').length == 0) {
                      	  $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select one or more user from the list...</a></li>').appendTo('#error_list');
                      } else {

                          var params = {
                              "taskIds": JSON.stringify(checkedRows),
                              "taskAction": "delete"
                          }
                          $.ajax({
                              url: '/assigntasklist',
                              type: "POST",
                              data: params,
                              success: function(data) {
                                  window.location.href = "http://localhost:8080/assigntasklist"
                              }
                          });

                          event.preventDefault();
                      }
                  });

              // ASSIGNTASK_ADD add function will call /register page
              $('#taskadd').click(
                  function(event) {
                      window.location.href = "http://localhost:8080/addtask";
                      event.preventDefault();
                  });


              // ASSIGNTASK_ADD edit function
              $('#taskedit').click(
                  function(event) {

                      if ($('#taskTable tr:has(:checkbox:checked)').length == 0) {
                          $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>'
                        		  + '<strong>Error</strong> Please select a task from the list...</a></li>').appendTo('#error_list');
                      } else {
                          console.log(JSON.stringify(checkedRows));
                          var params = {
                              "taskIds": JSON.stringify(checkedRows),
                              "taskAction": "edit"
                          }
                          var regUser = "";
                          $.ajax({
                              url: '/assigntasklist',
                              type: "POST",
                              data: params,

                              success: function(data) {

                                  //get subelements from div('#register_user') from addtask.jsp
                                  regUserElements = $(data).find("#register_user").html();

                                  //clear previous added html elements
                                  $('#editform').empty();

                                  //append html subelements
                                  $(regUserElements).appendTo("#editform");
                                  
                                  //mark username field as read-only
                                  $("#title").prop("readonly", true);

                                  dialog = $("#dialog-form").dialog({
                                      autoOpen: false,
                                      resizable: false,
                                      height: 525,
                                      width: 600,
                                      modal: true,

                                      position: {
                                          my: "center+50",
                                          at: "center+50",
                                          of: "body"
                                          	
                                          	
                                      },
                                      close: function() {
                                          form[0].reset();
//                                          allFields.removeClass("ui-state-error");
                                      }
                                  });

                                  //default open dialog on ajax success
                                  dialog.dialog("open");

                                  form = dialog.find("form").on("submit", function(event) {

                                      var isValid = editassigndailylog();
                                      if (isValid) {
                                          console.log("VALID - " + isValid);
                                          return;
                                      }
                                      event.preventDefault();

                                  });

                                  /*** edit ASSIGNTASK dialog form validation *****/
                                  var dialog, form;

                                  $('#timepicker1').timepicker();
                                  $('#timepicker2').timepicker();
                                  
                                  $('.form_datetime').datetimepicker({
                                      
                                      autoclose: 1,
                                      todayBtn: 1,
                                      format: "yyyy-mm-dd  hh:ii"
                                  });
                                  $('#timepicker1').timepicker();
                                  $('#timepicker2').timepicker();
                            
                                  function split( val ) {
                                      return val.split( /,\s*/ );
                                    }
                                    function extractLast( term ) {
                                      return split( term ).pop();
                                    }
//                                    FOR AUTOCOMPLETE READING REALNAME
                                    var availableTags = $.parseJSON(
                                  		    $.ajax(
                                  		        {
                                  		           url: "static/tables/realnames.json", 
                                  		           async: false, 
                                  		           dataType: 'json'
                                  		        }
                                  		    ).responseText
                                  		);
//                                    attendby FOR AUTOCOMPLETE READING REALNAME
                                    $( "#attendby" )
                                      // don't navigate away from the field on tab when selecting an item
                                      .on( "keydown", function( event ) {
                                      
                                        if ( event.keyCode === $.ui.keyCode.TAB &&
                                            $( this ).autocomplete( "instance" ).menu.active ) {
                                          event.preventDefault();
                                        }
                                      })
                                      .autocomplete({
                                        minLength: 0,
                                        source: function( request, response ) {
                                          // delegate back to autocomplete, but extract the last term
                                          response( $.ui.autocomplete.filter(
                                            availableTags, extractLast( request.term ) ) );
                                        },
                                        focus: function() {
                                          // prevent value inserted on focus
                                          return false;
                                        },
                                        select: function( event, ui ) {
                                          var terms = split( this.value );
                                          // remove the current input
                                          terms.pop();
                                          // add the selected item
                                          terms.push( ui.item.value );
                                          // add placeholder to get the comma-and-space at the end
                                          terms.push( "" );
                                          this.value = terms.join( ", " );
                                          return false;
                                        }
                                      });
                                  // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                 title = $("#assignTaskDTO.title"),
                                  assigned_to = $("#assignTaskDTO.assigned_to"),
                                  priority = $("#assignTaskDTO.priority"),
                                  done_percentage = $("#assignTaskDTO.done_percentage"),
                                  target_date = $("#assignTaskDTO.target_date"),
                                  shift = $("#dailylogDTO.shift"),
                                  machine = $("#dailylogDTO.machine"),
                                  description = $("#dailylogDTO.description"),
                                  attendby = $("#dailylogDTO.attendby"),
                                  timefrom = $("#dailylogDTO.timefrom"),
                                  timeto = $("#dailylogDTO.timeto"),
                                  spare_parts = $("#dailylogDTO.spare_parts"),
                                  jobtype = $("#dailylogDTO.jobtype"),
                                  recordtype = $("#dailylogDTO.recordtype"),
                                  bu = $("#dailylogDTO.bu"),
                                  status = $("#dailylogDTO.status"),
                                      allFields = $([]).add(title).add(assigned_to).add(priority).
                                      add(done_percentage).add(target_date).add(shift).add(machine).add(description)
                                      .add(attendby).add(timefrom).add(timeto).add(spare_parts).add(jobtype).add(recordtype).add(bu).add(status),
                                      tips = $(".validateTips");

                                  function updateTips(t) {
                                      tips
                                          .text(t)
                                          .addClass("ui-state-highlight");
                                      setTimeout(function() {
                                          tips.removeClass("ui-state-highlight", 1500);
                                      }, 500);
                                  }

                                  function checkLength(o, n, min, max) {
                                      if (o.val().length > max || o.val().length < min) {
                                          o.addClass("ui-state-error");
                                          updateTips("Length of " + n + " must be between " +
                                              min + " and " + max + ".");
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }

                                  function checkRegexp(o, regexp, n) {
                                      if (!(regexp.test(o.val()))) {
                                          o.addClass("ui-state-error");
                                         updateTips(n);
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
//									EDIT ASSIGN_TASK
                                  function editassigndailylog() {
                                      var valid = true;
                                      allFields.removeClass("ui-state-error");

                                      title = $("#assignTaskDTO.title"),
                                      assigned_to = $("#assignTaskDTO.assigned_to"),
                                      priority = $("#assignTaskDTO.priority"),
                                      done_percentage = $("#assignTaskDTO.done_percentage"),
                                      target_date = $("#assignTaskDTO.target_date"),
                                      shift = $("#dailylogDTO.shift"),
                                      machine = $("#dailylogDTO.machine"),
                                      description = $("#dailylogDTO.description"),
                                      attendby = $("#dailylogDTO.attendby"),
                                      timefrom = $("#dailylogDTO.timefrom"),
                                      timeto = $("#dailylogDTO.timeto"),
                                      spare_parts = $("#dailylogDTO.spare_parts"),
                                      jobtype = $("#dailylogDTO.jobtype"),
                                      recordtype = $("#dailylogDTO.recordtype"),
                                      bu = $("#dailylogDTO.bu"),
                                      status = $("#dailylogDTO.status"),
                                      
                                      valid = valid && checkLength(title, "assignTaskDTO.title", 3, 80);
                                      valid = valid && checkLength(assigned_to, "assignTaskDTO.assigned_to", 3, 80);
                                      valid = valid && checkLength(priority, "assignTaskDTO.priority", 3, 80);
                                      valid = valid && checkLength(done_percentage, "assignTaskDTO.done_percentage", 3, 80);
                                      valid = valid && checkLength(target_date, "assignTaskDTO.target_date", 3, 80);
                                      valid = valid && checkLength(shift, "dailylogDTO.shift", 3, 80);
                                      valid = valid && checkLength(machine, "dailylogDTO.machine", 3, 80);
                                      valid = valid && checkLength(description, "dailylogDTO.description", 3, 280);
                                      valid = valid && checkLength(attendby, "dailylogDTO.attendby", 3, 280);
                                      valid = valid && checkLength(timefrom, "dailylogDTO.timefrom", 3, 80);
                                      valid = valid && checkLength(timeto, "dailylogDTO.timeto", 3, 80);
                                      valid = valid && checkLength(spare_parts, "dailylogDTO.spare_parts", 3, 80);
                                      valid = valid && checkLength(jobtype, "dailylogDTO.jobtype", 3, 80);
                                      valid = valid && checkLength(recordtype, "dailylogDTO.recordtype", 3, 80);
                                      valid = valid && checkLength(bu, "dailylogDTO.bu", 3, 80);
                                      valid = valid && checkLength(status, "dailylogDTO.status", 3, 80);
                                      return valid;
                                  }
                              }

                          });
                      }
                      event.preventDefault();
                  });
//              GOING BACK  TO DASHBOARD
              
              $('#taskcancel').click(
                      function(event) {
                          window.location.href = "http://localhost:8080/dashboard";
                          event.preventDefault();
                      });
              // ERROR PAGE Image align js
              $('.imgInner > img').each(function (i) {
            	    var imgWidth = ((0 - parseInt($(this).css('width'))) / 2);
            	    var imgHeight = ((0 - parseInt($(this).css('height'))) / 2);

            	    $(this).css('margin-top', imgHeight);
            	    $(this).css('margin-left', imgWidth);
            	});
              $(document).ready(function() {
            		var showChar = 50;
            		var ellipsestext = "...";
            		var moretext = "more";
            		var lesstext = "less";
            		$('.more').each(function() {
            			var content = $(this).html();

            			if(content.length > showChar) {

            				var c = content.substr(0, showChar);
            				var h = content.substr(showChar-1, content.length - showChar);

            				var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';

            				$(this).html(html);
            			}

            		});

            		$(".morelink").click(function(){
            			if($(this).hasClass("less")) {
            				$(this).removeClass("less");
            				$(this).html(moretext);
            			} else {
            				$(this).addClass("less");
            				$(this).html(lesstext);
            			}
            			$(this).parent().prev().toggle();
            			$(this).prev().toggle();
            			return false;
            		});
            	});
              $(document).ready(function(){
            	    $("[data-id]").click(function(){
            	    	 $(this).addClass("fa fa-caret-down");	 
                         	
            	    });
            	});
             //              show hide 
              $("[data-id]").click(function(){
            	

            	  
            	          	  
            	  var id = $(this).data("id");
            	  if ($("[data-id]").hasClass("fa fa-caret-up")){
            		  $("[data-id]").removeClass("fa fa-caret-up");	 
            		  $("[data-id]").addClass("fa fa-caret-down");	 

                	  console.log("data ---yes it exist - ");	    		
       	    	}else{
       	       	  console.log("data ---no  not it exist - ");	    		
       	       $("[data-id]").addClass("fa fa-caret-up");	 
               	}
            	  
            	  console.log("data --- id - " + id);
                  var fields = id.split('_');
                var title = jQuery("textarea#title").val();
                  
                  console.log("data --- fields - " + fields);
                  var pre = fields[0];
                  var postId = fields[1];

                  var desc= "#desc_"+postId;
                  console.log("data --- desc - " + desc);
                  if($(desc).css('display') == 'none'){ 
                	   $(desc).show('slow'); 
                	} else { 
                	   $(desc).hide('slow'); 
                	}
                  
                  
                  var bottom_id = "bottom_"+postId;
                  console.log("data --- bottom_id - " + bottom_id);
                  var down_id = "down_"+postId;
                  
                  $("#"+bottom_id).toggle();
               
                  var $arrows = $(this).find("img");
                  $(this).parent(".bottom").slideToggle().parent(".box").find(".fa fa-caret-up").removeClass("fa fa-caret-up");
                  /*      if ($("#"+down_id).attr("src").toString().indexOf('https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Down_arrow_download_up_page_add_warning_thumbs_text_search.png') != -1) {

                  this.src = this.src.replace("https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Down_arrow_download_up_page_add_warning_thumbs_text_search.png", "https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Up_arrow_vote_like_upload_thumbs_down_thumb_hand_edit.png");

                  }

                else {

                  this.src = this.src.replace("https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Up_arrow_vote_like_upload_thumbs_down_thumb_hand_edit.png", "https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Down_arrow_download_up_page_add_warning_thumbs_text_search.png");

                  }*/

                  $("#"+down_id).attr('src', 'https://cdn1.iconfinder.com/data/icons/basic-toolbar-icons/20/Up_arrow_vote_like_upload_thumbs_down_thumb_hand_edit.png');
              
                  $.ajax({
                      url: "http://localhost:8080/taskdetailview",
                      data: {
                          assign_task_id: postId,
                          title:title,
                          bottom_id:bottom_id
                      },
                      success: function (data) {
                   /* 	  console.log("response=data =", data);
                          console.log("response=postId =", postId);
                               console.log("response=title =", title);*/
                      }
                  });
//            	  alert(id);
            	});
              $(this).parent(".bottom").slideToggle().parent(".box").find(".fa fa-caret-up").removeClass("fa fa-caret-up");
                 
//              buttton  onclick
              $("[data-bid]").click(
            	   function(event) {
            		   var id =$(this).attr('id');
            		   var name =$(this).attr('name');
                       var fields = name.split('[');
                       var fields2 = fields[1];
                        
                       var value = fields2.split(']');
                       var value1 = value[0];
                       console.log("data --- value1 - " + value1);
                        
                       //get the form data and then serialize that
                  var json = JSON.parse(JSON.stringify(jQuery('#taskform').serializeObject()));
                  var params={
                		  "taskIds":json,
                  			"indexnumber" :value1
                  }
                  
                  $.ajax({
                              url: "/taskdetailview",
                              dataType: "JSON",
                              type: 'POST',
                              contentType: "application/json; charset=utf-8",
                              data: JSON.stringify(params),
                              success: function(data){
                                  console.log("DATA POSTED SUCCESSFULLY"+data);
                              }
                              
                  });

                  event.preventDefault();
            	});
              
             /* $('.top').on('click', function() {
            		$parent_box = $(this).closest('.box');
            		$parent_box.siblings().find('.bottom').slideUp();
            		$parent_box.find('.bottom').slideToggle(1000, 'swing');
            	});*/
              $.fn.serializeObject = function()
              {
                  var o = {};
                  var a = this.serializeArray();
                  $.each(a, function() {
                      if (o[this.name] !== undefined) {
                          if (!o[this.name].push) {
                              o[this.name] = [o[this.name]];
                          }
                          o[this.name].push(this.value || '');
                      } else {
                          o[this.name] = this.value || '';
                      }
                  });
                  return o;
              };
              
              $('#newdailylog').click(
                      function(event) {
                    	  var assign_task_id =$('#assignTaskDTO_id').val();
                    	  var assign_task_title =$('#title').val();

                          console.log("DATA POSTED assign_task_title ===="+assign_task_title);         
                          assign_task_title=assign_task_title.replace(" ","%20");
                          console.log("DATA POSTED assign_task_title after  ===="+assign_task_title);         
                                    
//                         
                         
                          $.ajax({
                              url: "http://localhost:8080/adddailylog",
                              data: {
                            	  assign_task_title: assign_task_title
                              },
                              success: function (data) {
                            	  console.log("DATA POSTED data ===="+data);          
                               window.location.href = "http://localhost:8080/adddailylog?assign_task_title="+assign_task_title;  
                           /* 	  console.log("response=data =", data);
                                  console.log("response=postId =", postId);
                                       console.log("response=title =", title);*/
                              }   
                          });
                          event.preventDefault();
                      });
              
//  			CHECK FOR VALID PASSWORD	          
              function onLoad(){
            	  $("#password").keyup(checkpasswordmatch);
            	  $("#confirmpassword").keyup(checkpasswordmatch);
            	  $("#userdetails").submit(cansubmit);
              }
              function cansubmit(){
            	  var password=$("#password").val();
            	  var confirmpassword=$("#confirmpassword").val();
            	 
            	  if(password != confirmpassword){
            		  alert("Passwords do not match");
            	  return false;
            	  }
	            else{
	            	return true;
	            	 }
              }
              function checkpasswordmatch(){
            	  var password=$("#password").val();
            	  var confirmpassword=$("#confirmpassword").val();
            	  if(password.length >3 || confirmpassword.length > 3){
	            	  if(password == confirmpassword){
	            		 $("#matchpassword").text("Passwords match");
	            		 $("#matchpassword").addClass("valid");
	            		 $("#matchpassword").removeClass("error");
	                     

	 	              }
	            	  else{
	            			 $("#matchpassword").text("Passwords do not match");                     		  
	                		 $("#matchpassword").addClass("error");
	              			 $("#matchpassword").removeClass("valid");
	           		                
	            	  }
            	  }
              	  
              }
              $(document).ready(onLoad);
//              Use RegEx to Test Password Strength and alpha numeric characters
              $(function () {
                  $("#password").bind("keyup", function () {

                     
                	  //TextBox left blank.
                      if ($(this).val().length == 0) {
                          $("#password_strength").html("");
                          return;
                      }
                  //TextBox left blank.
                  if (password.length == 0) {
                      password_strength.innerHTML = "";
                      return;
                  }
           
                  //Regular Expressions.
                  var regex = new Array();
                  regex.push("[A-Z]"); //Uppercase Alphabet.
                  regex.push("[a-z]"); //Lowercase Alphabet.
                  regex.push("[0-9]"); //Digit.
                  regex.push("[$@$!%*#?&]"); //Special Character.
            
                  var passed = 0;
       
                  //Validate for each Regular Expression.
                  for (var i = 0; i < regex.length; i++) {
                      if (new RegExp(regex[i]).test($(this).val())) {
                          passed++;
                      }
                  }
       
       
                  //Validate for length of Password.
                  if (passed > 2 && $(this).val().length > 8) {
                      passed++;
                  }
                  
                  //Display status.
                  var color = "";
                  var strength = "";
                  switch (passed) {
                      case 0:
                      case 1:
                          strength = "Weak";
                          color = "red";
                          break;
                      case 2:
                          strength = "Good";
                          color = "darkorange";
                          break;
                      case 3:
                      case 4:
                          strength = "Strong";
                          color = "green";
                          break;
                      case 5:
                          strength = "Very Strong";
                          color = "darkgreen";
                          break;
                  }
                  $("#password_strength").html(strength);
                  $("#password_strength").css("color", color);
              });
          });
              
 }); //end of document jQuery