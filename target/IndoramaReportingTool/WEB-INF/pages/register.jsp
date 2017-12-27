<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Registration</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Register</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<form:form method="POST" action="register" modelAttribute="users" id="userdetails" >
				<div class="panel-body" cssClass="error">
					<form:errors path="firstname" element="div" />
					<form:errors path="lastname" element="div" />
					<form:errors path="address" element="div" />
					<form:errors path="city" element="div" />
					<form:errors path="state" element="div" />
					<form:errors path="zipcode" element="div" />
					<form:errors path="phone" element="div" />
					<form:errors path="username" element="div" />
					<form:errors path="birthdate" element="div" />
					<form:errors path="password" element="div" />

				</div>
				<div class="col-sm-12 panel panel-default">
					<fieldset>
						<div id="register_user" class="col-sm-12 panel panel-default">


							<div class="row">
								<div class="col-sm-6 form-group">
									<label>First Name</label>
									<form:input id="firstname" type="text"
										placeholder="Enter First Name Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="firstname" />

								</div>
								<div class="col-sm-6 form-group">
									<label>Last Name</label>
									<form:input id="lastname" type="text"
										placeholder="Enter Last Name Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="lastname" />

								</div>
							</div>
								<div class="row">
								<div class="col-sm-6 form-group">
									<label>Plants</label>
								<form:select path="bu" class="form-control text ui-widget-content ui-corner-all">
									<option value="NONE">--- Select ---</option>
									<form:options items="${busList}"></form:options>
								</form:select>
								</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Roles</label>
								<form:select path="role" class="form-control text ui-widget-content ui-corner-all">
									<option value="NONE">--- Select ---</option>
									<form:options items="${rolesList}"></form:options>
								</form:select>
								</div>
								<div class="col-sm-6 form-group">
								<label>DOB</label>
								<form:input id="birthdate" type="text"
									name="birthdate"
									placeholder="Enter DOB Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="birthdate" />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Contact Number</label>
								<form:input id="phone" type="text"
									placeholder="Enter Phone Number Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="phone" />
								</div>
								<div class="col-sm-6 form-group">
								<label>Email Address</label>
								<form:input id="username" type="text"
									placeholder="Enter Email Address Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="username" />
								</div>
								
								</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Password</label>
								<form:input id="password" type="password"
									placeholder="Enter password Here.."
									class="form-control text ui-widget-content ui-corner-all"
									path="password" />
								</div>
					
								<div class="col-sm-6 form-group">
								<label>Confirm Password</label>
								<input type="password"  id="confirmpassword" onkeyup="CheckPasswordStrength(this.value)"
									placeholder="Enter confirm password Here.."
									class="form-control text ui-widget-content ui-corner-all"
									name="confirmpassword" />
								</div>
								</div>
								
									<div class="row"><div id="matchpassword">			<span id="password_strength"></span></div></div>
							<div class="form-group">
								<label>Address</label>
								<form:textarea id="address" placeholder="Enter Address Here.."
									rows="3"
									class="form-control text ui-widget-content ui-corner-all"
									path="address" />
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>City</label>
									<form:input id="city" type="text"
										placeholder="Enter City Name Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="city" />
								</div>
								<div class="col-sm-4 form-group">
									<label>State</label>
									<form:input id="state" type="text"
										placeholder="Enter State Name Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="state" />
								</div>
								<div class="col-sm-4 form-group">
									<label>Zip</label>
									<form:input id="zipcode" type="text"
										placeholder="Enter Zip Code Here.."
										class="form-control text ui-widget-content ui-corner-all"
										path="zipcode" />
								</div>
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