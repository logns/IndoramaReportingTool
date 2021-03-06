<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container_login"
	onload='document.loginForm.username.focus();'>
	<div class="row">
		<div class="login_main">
			<h3 class="login_label_color">Reset Password</h3>
			<div class="row">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<form:form method="POST" action="resetpassword" modelAttribute="pcrs">

					<div class="panel-body" cssClass="error">
						<form:errors path="password" element="div" />
		
						<form:errors path="hash_id" element="div" />
					</div>
						<form:input type="hidden"  path="hash_id" id="hash_id"
									class="form-control text ui-widget-content ui-corner-all" />
				<span id="password_strength"></span>
					<div class="form-group">
						<label>New Password</label>
						<form:input id="password" type="password" onkeyup="CheckPasswordStrength(this.value)"
							placeholder="Enter password Here.." maxlength="15"  
							class="form-control text ui-widget-content ui-corner-all"
							path="password" />

					</div>
					<div class="form-group">
						<label>Confirm Password</label> <input type="password"
							id="confirmpassword" placeholder="Enter confirm password Here.."
							class="form-control text ui-widget-content ui-corner-all"
							name="confirmpassword" />
					</div>
					<div class="row">
						<div id="matchpassword"></div>
					</div>
					<button type="submit" class="btn btn btn-primary" id="resetbutton">Submit</button>

				</form:form>
			</div>
		</div>
	</div>
</div>