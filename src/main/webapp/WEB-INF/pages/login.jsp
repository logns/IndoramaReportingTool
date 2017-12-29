<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<div class="container_login" onload='document.loginForm.username.focus();'>
	<div class="row">
		<div class="login_main">
			<h3 class="login_label_color">Please Log In</h3>
			<div class="row">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='loginForm' action="<c:url value='/login?targetUrl=${targetUrl}' />"
					method='POST'>
					<div class="form-group">
						<label for="inputUsernameEmail" class="login_label_color">Username
							or Email</label> <input type="text" class="form-control"
							id="inputUsernameEmail" name="username">
					</div>
					<div class="form-group">
						<a class="pull-right" href="${pageContext.request.contextPath}/forgotpassword">Forgot password?</a> <label
							for="inputPassword" class="login_label_color">Password</label> <input
							type="password" class="form-control" id="inputPassword"
							name="password">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<c:if test="${empty loginUpdate}">
					<div class="checkbox pull-right">
					
						<label class="login_label_color"> <input type="checkbox" name="remember-me">
							Remember me
						</label>
					</div>
					</c:if>
					<button type="submit" class="btn btn btn-primary">Log In</button>
					<div class="form-group">
						<p>
							<a class="pull-right"
								href="${pageContext.request.contextPath}/register">New User?
								Please Register</a>
						</p>

					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>