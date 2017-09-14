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
					<form:errors path="loadmax" element="div" />
					<form:errors path="loadmin" element="div" />
					<form:errors path="voltmax" element="div" />
					<form:errors path="voltmin" element="div" />
					<form:errors path="frequencymax" element="div" />
					<form:errors path="frequencymin" element="div" />
					<form:errors path="pfmax" element="div" />
					<form:errors path="pfmin" element="div" />
					<form:errors path="powerdip" element="div" />
					<form:errors path="remark" element="div" />
					<form:errors path="machine" element="div" />
					<form:errors path="description" element="div" />
					<form:errors path="timefrom" element="div" />
					<form:errors path="timeto" element="div" />
					<form:errors path="spareparts" element="div" />
					<form:errors path="attendby" element="div" />
					<form:errors path="dates" element="div" />
					<form:errors path="shift" element="div" />
					<form:errors path="realname" element="div" />
					<form:errors path="bu" element="div" />
					<form:errors path="substation" element="div" />

				</div>
				<div class="col-sm-12 panel panel-default">
					<fieldset>
						<div id="register_user" class="col-sm-12 panel panel-default">

							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Date</label> <br />
								
										<div class="input-group date form_datetime"
								id="dtp_input1"
								data-date-format="yyyy-mm-dd  hh:MM a"
								data-link-field="dtp_input1">
								<form:input id="dates" type="text" path="dates"
									placeholder="Select DateTime  Here...."
									class="form-control" value=""  /> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span>
									 <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
									</div>
							<form:input type="hidden" id="dtp_input1" value="" name="dates"
								path="" />
								<br />
							</div>
							</div>
							
							<div class="row">
								<div class="col-sm-3 form-group  ">
								<label>Shift   </label>
								<form:input id="shift" type="label"
										value="${shift}"
										class="form-control text ui-widget-content ui-corner-all"
										path="shift" />
								</div>
								
								<div class="col-sm-3 form-group">
								<label>Plant</label>
								<form:input id="bu" type="label"
										value="${bu1}"
										class="form-control text ui-widget-content ui-corner-all"
										path="bu" />
								</div>
								<div class="col-sm-3 form-group">
								<label>Name </label>
								<form:input id="realname" type="label"
										value="${realname}"
										class="form-control text ui-widget-content ui-corner-all"
										path="realname" />
								</div>
								<div class="col-sm-3 form-group">
								<label>Sub Station</label>
								<form:input id="substation" type="label"
										value="${substation}"
										class="form-control text ui-widget-content ui-corner-all"
										path="substation" />
							</div>
							</div>
							
							
							
							
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Load (MW) : Max</label>
									<form:input id="loadmax" type="text"
										placeholder="Enter Load Max Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="loadmax" />

								</div>
								<div class="col-sm-6 form-group">
									<label>Min</label>
									<form:input id="loadmin" type="text"
										placeholder="Enter Load Min Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="loadmin" />

								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Voltage (KV) : Max</label>
									<form:input id="voltmax" type="text"
										placeholder="Enter Voltage Max Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="voltmax" />

								</div>
								<div class="col-sm-6 form-group">
									<label>Min</label>
									<form:input id="voltmin" type="text"
										placeholder="Enter Voltage Min Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="voltmin" />

								</div>
							</div>
							
										
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Frequency (Hz) : Max</label>
									<form:input id="frequencymax" type="text"
										placeholder="Enter Frequency Max Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="frequencymax" />

								</div>
								<div class="col-sm-6 form-group">
									<label>Min</label>
									<form:input id="frequencymin" type="text"
										placeholder="Enter Frequency Min Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="frequencymin" />

								</div>
							</div>
							
										
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>PF : Max</label>
									<form:input id="pfmax" type="text"
										placeholder="Enter Pf Max Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="pfmax" />

								</div>
								<div class="col-sm-6 form-group">
									<label>Min</label>
									<form:input id="pfmin" type="text"
										placeholder="Enter Pf Min Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="pfmin" />

								</div>
							</div>
							
								<div class="form-group">
									<label>Power DIP</label>
								<form:input id="powerdip" type="text"
										placeholder="Enter Power DIP Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="powerdip" />
								</div>
								<div class="form-group">
									<label>Remark</label>
								<form:input id="remark" type="text"
										placeholder="Enter Remark Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="remark" />
								</div>
								<div class="form-group">
									<label>Machine</label>
								<form:input id="machine" type="text"
										class="form-control text ui-widget-content ui-corner-all"
										path="machine" />
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
								<label>Job Type</label><form:select path="jobtype">
									<option value="NONE">--- Select ---</option>
									<form:options items="${jobtype}"></form:options>
								</form:select>
								
							</div>
							<div class="form-group">
								<label>Record Type</label>
								<form:select path="recordtype">
									<option value="NONE">--- Select ---</option>
									<form:options items="${recordtype}"></form:options>
								</form:select>
							</div>
							<div class="form-group">
								<label>Status</label>
								<form:select path="status">
									<option value="NONE">--- Select ---</option>
									<form:options items="${status}"></form:options>
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