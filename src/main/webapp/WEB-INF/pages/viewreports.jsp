<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		
			<div  align="center" >
				<!-- <h1 class="page-header">View Reports</h1> -->
				<img
						src="${pageContext.request.contextPath}/static/images/indorama_logo.png"
						width="200" height="50" /> 
			
		</div>
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">DailyLogs View Reports</li>
			</ol>
		</div>
		
<div>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
		<div class="panel panel-default">
			<div class="row">
				<div class="col-sm-6 form-group">
					<h3>View Reports In Excel</h3>
					<h3>
						<a href="/downloadExcel">Download Excel Document</a>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 form-group">
					<h3>View Reports In PDF</h3>
					<h3>
						<a href="/downloadPdf">Download PDF Document</a>
					</h3>
				</div>
			</div>
		</div>
</div>
	</div>
</div>