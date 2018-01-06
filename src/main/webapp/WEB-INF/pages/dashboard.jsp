<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Dashboard</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Dashboard</h1>
			</div>
		</div>
		<!--/.row-->

		<!-- errors -->
		<div id="errors">
			<ul id="error_list"></ul>
		</div>

		<!--  -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">

					<!-- panel heading with glyph icon -->
					<div class="panel-heading">
						<svg class="glyph stroked male user">
							<use xlink:href="#stroked-male-user"></use></svg>
					</div>
	
					<div class="panel-body">
						<table id="taskTable" data-toggle="table"
							data-url="static/tables/dashboard.json"
							data-show-refresh="true" data-show-toggle="true"
							data-show-columns="true" data-search="true"
							data-select-item-name="target_date" data-pagination="true"
							data-sort-name="title" data-sort-order="desc">
							<thead>
								<tr>
									<th data-field="checked" data-checkbox="true"></th>
									<th data-field="id" data-sortable="true" data-visible="false">Id</th>
									<th data-field="title" data-sortable="true">title</th>
									</a>
									<th data-field="created_by" data-sortable="true">created_by</th>
									<th data-field="target_date" data-sortable="true">target_date</th>
									<th data-field="status" data-sortable="true">status</th>
								</tr>
							</thead>
						</table>

					</div>


				</div>
			</div>
		</div>
		<!--/.row-->
	</div>

	<ul id="output"></ul>
</div>