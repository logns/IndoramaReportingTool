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
			<form:form method="POST" action="dailylog" modelAttribute="dailylogs">
				<div class="panel-body" cssClass="error">
					<form:errors path="dates" element="div" />
					<form:errors path="machine" element="div" />
					<form:errors path="description" element="div" />
					<form:errors path="timefrom" element="div" />
					<form:errors path="timeto" element="div" />
					<form:errors path="spareparts" element="div" />
					<form:errors path="attendby" element="div" />
					<form:errors path="shift" element="div" />
					<form:errors path="realname" element="div" />
					<form:errors path="bu" element="div" />
					<form:errors path="jobtype" element="div" />
					<form:errors path="recordtype" element="div" />
					<form:errors path="status" element="div" />
					<form:errors path="dailylog_title" element="div" />
					
				</div>
				<div class="col-sm-12 panel panel-default">
					<fieldset>
						<div id="register_user" class="col-sm-12 panel panel-default">

							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Date</label> <br />
										<div class="input-group date form_datetime col-md-10"
								id="dates"
								data-date-format="yyyy-mm-dd  hh:MM a"
								data-link-field="dates">
								<form:input id="dates" type="text" path="dates"
									placeholder="Select DateTime  Here...."
									class="form-control" value=""  /> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span>
									 <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							
								<br />
							</div>
							</div>
							
							<div class="row">
								<div class="col-sm-3 form-group  ">
								<label>Shift   </label>
							
										<form:select path="shift" class="form-control text ui-widget-content ui-corner-all">
									<option id="shift" value="NONE">--- Select ---</option>
									<form:options  id="shift" items="${shift}"></form:options>
								</form:select>
								</div>
								
								<div class="col-sm-3 form-group">
								<label>Plants</label>
								<form:select  path="bu" class="form-control text ui-widget-content ui-corner-all">
									<option   id="bu"  value="NONE">--- Select ---</option>
									<form:options   id="bu"  items="${busList}"></form:options>
								</form:select>
								</div>
								<div class="col-sm-3 form-group">
								
								<label>Name </label><br />
								<form:select path="realname" class="form-control text ui-widget-content ui-corner-all">
									<option id="realname"  value="NONE">--- Select ---</option>
									<form:options id="realname"  items="${realname}"></form:options>
								</form:select>
								</div>
							</div>
							<div class="form-group">
									<label>Machine</label>
								<form:input id="machine" type="text"
										class="form-control text ui-widget-content ui-corner-all"
										path="machine" />
								</div>
							<div class="form-group">
									<label>DailyLogs Title</label>
								<form:input id="dailylog_title" type="text"
										class="form-control text ui-widget-content ui-corner-all"
										path="dailylog_title" />
								</div>
							<div class="form-group">
								<label>Problem description & action taken</label>
								<form:textarea id="description" placeholder="Enter Problem description & action Here.."
									rows="3"
									class="form-control text ui-widget-content ui-corner-all"
									path="description" />
								</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Time : From</label>
								<form:input id="timefrom" type="text"
									placeholder="Enter Time from Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="timefrom" />
								</div>
								<div class="col-sm-6 form-group">
								<label>To</label>
								<form:input id="timeto" type="text"
									placeholder="Enter Time To Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="timeto" />
								</div>
								</div>
							
							<div class="form-group">
								<label>Spare Part Used</label>
									<form:input id="spareparts" type="text"
										placeholder="Enter Spare Part Used Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="spareparts" />
							</div>
							<div class="form-group">
								<label>Attend By</label>
									<form:input id="attendby" type="text"
										placeholder="Enter Attend By Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="attendby" />
							</div>
							<div class="form-group">
								<label>Job Type</label>
								<form:select path="jobtype" class="form-control text ui-widget-content ui-corner-all">
									<option  id="jobtype"  value="NONE">--- Select ---</option>
									<form:options id="jobtype"  items="${jobtype}"></form:options>
								</form:select>
								
							</div>
							<div class="form-group">
								<label>Record Type</label>
								<form:select path="recordtype" class="form-control text ui-widget-content ui-corner-all">
									<option  id="recordtype"  value="NONE">--- Select ---</option>
									<form:options  id="recordtype"  items="${recordtype}"></form:options>
								</form:select>
							</div>
							<div class="form-group">
								<label>Status</label>
								<form:select path="status" class="form-control text ui-widget-content ui-corner-all">
									<option id="status"  value="NONE">--- Select ---</option>
									<form:options id="status"  items="${status}"></form:options>
								</form:select>
									
							</div>
							<form:button  type="submit"
								class="btn btn-lg btn-info">Submit</form:button>
						</div>
					</fieldset>

				</div>
			</form:form>
		</div>
	</div>
</div>