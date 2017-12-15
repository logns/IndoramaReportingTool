<%-- <center>
	<img id="kalrav_logo" alt="kalrav_logo"
		src="${pageContext.request.contextPath}/static/images/kalrav_logo.png"
		height="100" width="=100" align="middle">
</center>

 --%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<div class="container">
	<div class="row">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#sidebar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<%-- <img
						src="${pageContext.request.contextPath}/static/images/indorama_logo.png"
						width="250" height="50" /> --%><a class="navbar-brand" href="#"><span>Indorama</span>Admin</a>
					<ul class="user-menu">
						<li class="dropdown pull-right"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"><svg
									class="glyph stroked male-user">
									<use xmlns:xlink="http://www.w3.org/1999/xlink"
										xlink:href="#stroked-male-user"></use></svg> User <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><svg class="glyph stroked male-user">
											<use xmlns:xlink="http://www.w3.org/1999/xlink"
												xlink:href="#stroked-male-user"></use></svg> Profile</a></li>
								<li><a href="#"><svg class="glyph stroked gear">
											<use xmlns:xlink="http://www.w3.org/1999/xlink"
												xlink:href="#stroked-gear"></use></svg> Settings</a></li>
								<li><a href="${pageContext.request.contextPath}/loggedout"><svg class="glyph stroked cancel">
											<use xmlns:xlink="http://www.w3.org/1999/xlink"
												xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
							</ul></li>
					</ul>
				</div>

			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
</div>