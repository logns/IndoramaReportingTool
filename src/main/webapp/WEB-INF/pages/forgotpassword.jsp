<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container_login" onload='document.loginForm.username.focus();'>
	<div class="row">
		<div class="login_main">
			<h3 class="login_label_color">Please Enter email id</h3>
			<div class="row">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<form:form  method="POST" action="forgotpassword" modelAttribute="email" >
				
				<div class="panel-body" cssClass="error">
				<form:errors path="username" element="div" />
				</div>	
					<div class="form-group">
						<label for="inputUsernameEmail" class="login_label_color">Email</label>
						 <form:input type="text" class="form-control"
							id="inputUsernameEmail" path="username" name="username"></form:input>
					</div>
					
					<button type="submit" class="btn btn btn-primary">Submit</button>
					
				</form:form>
			</div>
		</div>
	</div>
</div>