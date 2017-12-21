<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

				<!-- Removing google / facebook authentication -->
				<%-- <div class="col-xs-6 col-sm-6 col-md-6">
					<a class="btn btn-lg btn-primary btn-block dashboard"
						href="${pageContext.request.contextPath}/dashboard">Facebook</a>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6">
					<a class="btn btn-lg btn-info btn-block dashboard"
						href="${pageContext.request.contextPath}/dashboard">Google</a>
				</div>
			</div>
			<div class="login-or">
				<hr class="hr-or">
				<span class="span-or">or</span>
			</div> --%>
				<!-- End of Removing google / facebook authentication -->
				<form name='loginForm' action="<c:url value='/login' />"
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
					<div class="checkbox pull-right">
						<label class="login_label_color"> <input type="checkbox">
							Remember me
						</label>
					</div>
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