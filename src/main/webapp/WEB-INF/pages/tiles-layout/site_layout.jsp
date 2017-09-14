<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- favicon  -->
<link rel="icon" type="image/png" sizes="32x32" href="static/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="static/images/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="static/images/favicon-16x16.png">
<link rel='shortcut icon' type='image/x-icon' href='static/images/favicon.ico'> <!-- IE -->
<link rel='apple-touch-icon' type='image/png' href='static/images/apple-icon-57x57.png'> <!-- iPhone -->
<link rel='apple-touch-icon' type='image/png' sizes='72x72' href='static/images/apple-icon-57x57.png'> <!-- iPad -->
<link rel='apple-touch-icon' type='image/png' sizes='114x114' href='static/images/apple-icon-114x114.png'> <!-- iPhone4 -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 
<!-- W3 School  -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<!-- Bootstrap  -->
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet" href="static/css/styles.css">

<!-- lumino stylesheet in csshome  -->
<link rel="stylesheet" href="static/csshome/bootstrap-table.css">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.css">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.css.map">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/csshome/bootstrap.css">
<link rel="stylesheet" href="static/csshome/bootstrap.css.map">
<link rel="stylesheet" href="static/csshome/bootstrap.min.css"media="screen">
<link rel="stylesheet" href="static/csshome/datepicker.css">
<link rel="stylesheet" href="static/csshome/datepicker3.css">
<link rel="stylesheet" href="static/csshome/styles.css">
<link rel="stylesheet" href="static/csshome/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<!-- jquery -->
<script src="webjars/jquery/2.2.3/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
  
<!-- lumino javascript in js  -->
<script type="text/javascript" src="static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="static/js/bootstrap.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/chart-data.js"></script>
<script type="text/javascript" src="static/js/chart-min.js"></script>
<script type="text/javascript" src="static/js/easypiechart-data.js"></script>
<script type="text/javascript" src="static/js/easypiechart.js"></script>
<script type="text/javascript" src="static/js/html5shiv.min.js"></script>
<script type="text/javascript" src="static/js/kalrav.glyphs.js"></script>
<script type="text/javascript" src="static/js/lumino.glyphs.js"></script>
<script type="text/javascript" src="static/js/respond.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap-datetimepicker.js" ></script>

<!-- adding mustache template engine  -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>

<!-- load kalrav.js after jquery -->
<script type="text/javascript" src="static/js/kalrav.js"></script>

 <!-- glyph icons from https://glyphs.co/docs/getting-started/installation  -->
<script type="text/javascript" src="https://kit.glyphs.co/3ko27b.js"></script>



<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<!-- header -->
	<div id="site_header">
		<tiles:insertAttribute name="header" />
	</div>
	<!-- Menu Page -->
	<div id="site_menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<!-- body -->
	<div id="site_body">
		<tiles:insertAttribute name="body" />
	</div>
	<!-- footer -->
	<div id="site_footer">
		<tiles:insertAttribute name="footer" />
	</div>
	
</body>
</html>