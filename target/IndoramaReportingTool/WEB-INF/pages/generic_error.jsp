<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

	<c:if test="${not empty errCode}">
		<h1>${errCode} : System Errors</h1>
	</c:if>
	
	<c:if test="${empty errCode}">
		<h1>System Errors</h1>
	</c:if>

	<c:if test="${not empty errMsg}">
		<h4>${errMsg}</h4>
	</c:if>
	
</body>
</html> --%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Indorama Poly</h1>
				<a href="http://localhost:8080/dashboard"> 
				  <div class="imgInner">
        <img  src="indorama_logo.png" alt="Go to Indorama Poly" >
   			 </div>
				</a> <br />
				<p style="color:red font-size:36px;">
				<h3>
					<c:if test="${not empty errMsg}">
						<h4>${errMsg}</h4>
					</c:if>
				</h3>
				</p>
			</div>
		</div>
		
	</div>
</div>