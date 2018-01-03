<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar collapse in">

	<ul class="nav menu">
		<li class="active"><a
			href="${pageContext.request.contextPath}/dashboard"> <svg
					class="glyph stroked dashboard-dial">
			<use xmlns:xlink="http://www.w3.org/1999/xlink"
						xlink:href="#stroked-dashboard-dial"></use></svg> Dashboard
		</a></li>

		<li><a href="${pageContext.request.contextPath}/userlist"> <svg
					class="glyph stroked male user">
			<use xmlns:xlink="http://www.w3.org/1999/xlink"
						xlink:href="#stroked-male-user"></use></svg> Users
		</a></li>

		<security:authorize access="hasAuthority('ADMIN')">
			<li><a href="${pageContext.request.contextPath}/assigntasklist">
					<svg class="glyph stroked video">
			<use xmlns:xlink="http://www.w3.org/1999/xlink"
<<<<<<< HEAD
							xlink:href="#stroked-video"></use></svg> Assign Task List
			</a></li>
		</security:authorize>
		<%-- 	<li><a href="${pageContext.request.contextPath}/dailylogslist"> <svg
=======
						xlink:href="#stroked-video"></use></svg> Assign Task List
		</a></li>
	 	<li><a href="${pageContext.request.contextPath}/viewreports"> <svg
>>>>>>> f64334a6698a4f9553598eedbab58a5b569fe56a
					class="glyph stroked video">
			<use xmlns:xlink="http://www.w3.org/1999/xlink"
						xlink:href="#stroked-video"></use></svg> View Report
		</a></li>
<<<<<<< HEAD
	 --%>
=======
	 	
>>>>>>> f64334a6698a4f9553598eedbab58a5b569fe56a
		<li><a href="${pageContext.request.contextPath}/userlist"> <svg
					class="glyph stroked gear">
			<use xmlns:xlink="http://www.w3.org/1999/xlink"
						xlink:href="#stroked-gear"></use></svg> Settings
		</a></li>

<%-- 		<security:authentication property="principal.authorities" var="authorities" />
		<c:forEach items="${authorities}" var="authority" varStatus="vs">
			<p>${authority.authority}</p>
		</c:forEach> --%>
</div>