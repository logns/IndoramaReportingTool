/*

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
              
//              show hide 
              $("[data-id]").click(function(){
            	  var id = $(this).data("id");
                  console.log("data --- id - " + id);
                  var fields = id.split('_');
                var title = jQuery("textarea#title").val();
                  
                  console.log("data --- fields - " + fields);
                  var pre = fields[0];
                  var postId = fields[1];

                  var desc= "desc_"+postId;
                  console.log("data --- desc - " + desc);
                  $("#desc").hide();
                  document.getElementById(desc).style.display='none';
                  
                  console.log("data --- pre - " + pre);
                  console.log("data --- postId - " + postId);
                  var bottom_id = "bottom_"+postId;
                  console.log("data --- bottom_id - " + bottom_id);
                
                  $("#"+bottom_id).toggle();
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
                     
//              buttton  onclick
              $("[data-bid]").click(
            	   function(event) {
            		   var id =$(this).attr('id');
            		   var name =$(this).attr('name');
                       console.log("DATA POSTED name ===="+name);        
                       var fields = name.split('[');
                       var fields1 = fields[0];
                       var fields2 = fields[1];
                       console.log("data --- fields1 - " + fields1);
                       console.log("data --- fields2 - " + fields2);
                        
                       var value = fields2.split(']');
                       var value1 = value[0];
                       var value2 = value[1];
                       console.log("data --- value1 - " + value1);
                       console.log("data --- value2 - " + value2);
                        
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
                          window.location.href = "http://localhost:8080/adddailylog";
                          event.preventDefault();
                      });
              
            
 }); //end of document jQuery