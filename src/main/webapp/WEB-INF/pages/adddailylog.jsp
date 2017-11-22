<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Daily Logs</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Daily Logs</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<form:form method="POST" action="adddailylog" modelAttribute="dailylogs">
				<div class="error">

					<form:errors path="done_percentage" element="div" />
					<form:errors path="target_date" element="div" />
					<form:errors path="machine" element="div" />
					<form:errors path="description" element="div" />
					<form:errors path="timefrom" element="div" />
					<form:errors path="timeto" element="div" />
					<form:errors path="spareparts" element="div" />
					<form:errors path="attendby" element="div" />
					<form:errors path="shift" element="div" />
					<form:errors path="bu" element="div" />
					<form:errors path="jobtype" element="div" />
					<form:errors path="recordtype" element="div" />
					<form:errors path="status" element="div" />
					<form:errors path="assign_task_title" element="div" />

				</div>
				<div class="col-sm-12 panel panel-default">
					<fieldset class="the-fieldset">
						<legend class="the-legend">Daily Logs Details</legend>
						<div class="col-sm-6 form-group">
							<label>Task title</label>
							<form:input id="machine" type="label"
								class="form-control text ui-widget-content ui-corner-all"
								path="assign_task_title" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Shift</label>
							<form:select path="shift"
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
								path="machine" />
						</div>
						<div class="col-sm-12 form-group">
							<label>Problem Description</label>
							<form:textarea id="description"
								placeholder="Enter Description details Here.." rows="2"
								class="form-control text ui-widget-content ui-corner-all"
								path="description" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Time From</label>
							<div class="input-group bootstrap-timepicker timepicker">
								<form:input id="timepicker1" type="text"
									class="form-control input-small" path="timefrom" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
						</div>
						<div class="col-sm-6 form-group">
							<label>Time To</label>
							<div class="input-group bootstrap-timepicker timepicker">
								<form:input id="timepicker2" type="text"
									class="form-control input-small" path="timeto" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-time"></i></span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label>Spare Parts</label>
							<form:textarea id="spare_parts"
								placeholder="Enter Spare parts details Here.." rows="1"
								class="form-control text ui-widget-content ui-corner-all"
								path="spareparts" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Attend By</label>
							<form:input path="attendby" id="attendby"
								class="form-control text ui-widget-content ui-corner-all" />

						</div>
						<div class="col-sm-6 form-group">
							<label>Job Type</label>
							<form:select path="jobtype"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="jobtype" value="NONE">--- Select ---</option>
								<form:options id="jobtype" items="${jobtype}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Business Unit</label>
							<form:select path="bu"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="bu" value="NONE">--- Select ---</option>
								<form:options id="bu" items="${busList}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Records Type</label>
							<form:select path="recordtype"
								class="form-control text ui-widget-content ui-corner-all">
								<option id="recordtype" value="NONE">--- Select ---</option>
								<form:options id="recordtype" items="${recordtype}"></form:options>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<label>Status</label>
							<form:select path="status"
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