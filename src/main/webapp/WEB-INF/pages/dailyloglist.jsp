<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">AssignTask</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">AssignTask List</h1>
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
						<table id="userTable" data-toggle="table"
							data-url="static/tables/dailylogs.json" data-show-refresh="true"
							data-show-toggle="true" data-show-columns="true"
							data-search="true" data-select-item-name="assigned_to"
							data-pagination="true" data-sort-name="done_percentage"
							data-sort-order="desc">
							<thead>
								<tr>
									<th data-field="checked" data-checkbox="true"></th> 
									<th data-field="id" data-sortable="true" data-visible="false">ATId</th>
									<th data-field="id" data-sortable="true" data-visible="false">Id</th>
									<th data-field="assign_task_title" data-sortable="true">assign_task_title</th>
									<th data-field="description" data-sortable="true">description</th>
									<th data-field="assigned_to" data-sortable="true">assigned_to</th>
									<th data-field="done_percentage" data-sortable="true">done_percentage</th>
									<th data-field="target_date" data-sortable="true">target_date</th>
									<th data-field="status" data-sortable="true">status</th>
								</tr>
							</thead>
						</table>


						<div class="action_buttons">
							<hr>
							<button id="useradd" type="submit"
								class="btn btn-primary pull-right rbtnMargin">Add</button>
							<button id="userdelete" type="submit"
								class="btn btn-danger pull-right rbtnMargin">Delete</button>
							<button id="useredit" type="submit"
								class="btn btn-warning pull-right rbtnMargin">Edit</button>
							<button id="usercancel" type="reset"
								class="btn btn-default pull-right rbtnMargin">Cancel</button>
						</div>
					</div>


				</div>
			</div>
		</div>
		<!--/.row-->
	</div>

	<ul id="output"></ul>
</div>

<!-- modal form for edit user -->
<div id="dialog-form" title="Edit User">
	<p class="validateTips"></p>

	<form id="editUser" action="/edituser" method="post">
		<div id="editform" class="col-sm-12 panel panel-default"></div>
	</form>
</div>
