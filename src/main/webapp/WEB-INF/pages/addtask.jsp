<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">ADD TASK</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Add Task Details</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<form:form method="POST" action="addtask" modelAttribute="atdl">
				<div class="error">
					<table>
						<tr>
							<td>${message}</td>
						</tr>
					</table>

					<form:errors path="assignTaskDTO.title" element="div" />
					<form:errors path="assignTaskDTO.assigned_to" element="div" />
					<form:errors path="assignTaskDTO.priority" element="div" />
					<form:errors path="assignTaskDTO.done_percentage" element="div" />
					<form:errors path="assignTaskDTO.target_date" element="div" />
					<form:errors path="assignTaskDTO.created_by" element="div" />
					
					<form:errors path="dailylogDTO.shift" element="div" />
					<form:errors path="dailylogDTO.machine" element="div" />
					<form:errors path="dailylogDTO.description" element="div" />
					<form:errors path="dailylogDTO.attendby" element="div" />
					<form:errors path="dailylogDTO.timefrom" element="div" />
					<form:errors path="dailylogDTO.timeto" element="div" />
					<form:errors path="dailylogDTO.spare_parts" element="div" />
					<form:errors path="dailylogDTO.jobtype" element="div" />
					<form:errors path="dailylogDTO.recordtype" element="div" />
					<form:errors path="dailylogDTO.bu" element="div" />
					<form:errors path="dailylogDTO.status" element="div" />
				</div>
				<div id="register_user" class="col-sm-12 panel panel-default">

					<fieldset class="the-fieldset">

						<legend class="the-legend">Assign Task Details</legend>

						<div class="form-group">
							<label>Task Title</label>
							<form:textarea id="title" placeholder="Enter Task Title Here.."
								rows="1" path="assignTaskDTO.title"
								class="form-control text ui-widget-content ui-corner-all" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Assigned to</label>
							<form:select path="assignTaskDTO.assigned_to"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="assigned_to" value="NONE">--- Select ---</option>
								<form:options id="assigned_to" items="${usersListAssignto}"></form:options>
							</form:select>
						</div>
					<div class="col-sm-6 form-group">
							<label>Created By</label>
							<form:input id="created_by"
								placeholder="Enter your name Here.." 
								class="form-control text ui-widget-content ui-corner-all"
								path="assignTaskDTO.created_by" />
						</div>
		
						<div class="col-sm-6 form-group">
							<label>Priority</label>
							<form:select path="assignTaskDTO.priority"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="priority" value="NONE">--- Select ---</option>
								<form:options id="priority" items="${priority}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Done Percentage (%)</label>
							<form:select path="assignTaskDTO.done_percentage"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="done_percentage" value="NONE">--- Select
									---</option>
								<form:options id="done_percentage" items="${done_percentage}"></form:options>
							</form:select>
						</div>

						<div class="col-sm-6 form-group">
							<label>Targeted Date</label> <br />
							<div class="input-group date form_datetime col-md-10" id="dates"
								data-date-format="yyyy-mm-dd  hh:MM a" data-link-field="dates">
								<form:input id="dates" type="text"
									path="assignTaskDTO.target_date"
									placeholer="Select DateTime  Here...." class="form-control"
									value="" />
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
						</div>
					</fieldset>
					<br />
					<fieldset class="the-fieldset">
						<legend class="the-legend">Daily Logs Details</legend>
						<div class="col-sm-6 form-group">
							<label>Shift</label>
							<form:select path="dailylogDTO.shift"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="shift" value="NONE">--- Select ---</option>
								<form:options id="shift" items="${shift}"></form:options>
							</form:select>
						</div>
						<br />
						<div class="col-sm-12 form-group">
							<label>Machine</label>
							<form:textarea id="machine"
								placeholder="Enter Machine details Here.." rows="2"
								class="form-control text ui-widget-content ui-corner-all"
								path="dailylogDTO.machine" />
						</div>
						<div class="col-sm-12 form-group">
							<label>Problem Description</label>
							<form:textarea id="description"
								placeholder="Enter Description details Here.." rows="2"
								class="form-control text ui-widget-content ui-corner-all"
								path="dailylogDTO.description" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Time From</label>
							<div class="input-group bootstrap-timepicker timepicker">
								<form:input id="timepicker1" type="text"
									class="form-control input-small" path="dailylogDTO.timefrom" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
						</div>
						<div class="col-sm-6 form-group">
							<label>Time To</label>
							<div class="input-group bootstrap-timepicker timepicker">
								<form:input id="timepicker2" type="text"
									class="form-control input-small" path="dailylogDTO.timeto" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label>Spare Parts</label>
							<form:textarea id="spare_parts"
								placeholder="Enter Spare parts details Here.." rows="1"
								class="form-control text ui-widget-content ui-corner-all"
								path="dailylogDTO.spare_parts" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Attend By</label>
							<%-- 						<form:input  path="dailylogDTO.attendby" id="attendby"	class="form-control text ui-widget-content ui-corner-all" />
	 --%>
							<form:input path="dailylogDTO.attendby" id="attendby"
								class="form-control text ui-widget-content ui-corner-all" />

						</div>
						<div class="col-sm-6 form-group">
							<label>Job Type</label>
							<form:select path="dailylogDTO.jobtype"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="jobtype" value="NONE">--- Select ---</option>
								<form:options id="jobtype" items="${jobtype}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Business Unit</label>
							<form:select path="dailylogDTO.bu"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="bu" value="NONE">--- Select ---</option>
								<form:options id="bu" items="${busList}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Records Type</label>
							<form:select path="dailylogDTO.recordtype"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="recordtype" value="NONE">--- Select ---</option>
								<form:options id="recordtype" items="${recordtype}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Status</label>
							<form:select path="dailylogDTO.status"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="status" value="NONE">--- Select ---</option>
								<form:options id="status" items="${status}"></form:options>
							</form:select>
						</div>
					</fieldset>
					<form:button type="submit" class="btn btn-lg btn-info">Submit</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>