<!-- Group CRUD -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<div style="overflow: scroll;">
	<!-- Breadcrum  -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"> <svg class="glyph social myspace">
                  <use xlink:href="#social-myspace" />
               </svg></a></li>
				<li class="active">Group</li>
			</ol>
		</div>
		<!--/.row-->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Group</h1>
			</div>
		</div>
		<!-- Panel with Add Group -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default add_group">
					<div class="panel-heading">
						<svg class="glyph social myspace">
                     <use xlink:href="#social-myspace" />
                  </svg>
						<button
							class="button_add_group w3-button w3-medium w3-blue w3-padding-small w3-round w3-right">Add
							New Group</button>
					</div>
				
		
					        <c:set var="endDiv" scope="page" value="3"/>
							<c:forEach var="group" items="${mapOfGroup}" varStatus="count">
							
								<c:if test="${count.index % 3 == 0}">
									<div class="row w3-row w3-margin-left row_append_group">
								</c:if>
							
								<!-- start of card_group -->
								<div class="card_group w3-margin w3-col s3 w3-card">
									<header class="w3-container w3-light-grey w3-padding w3-center">
										<span class="w3-margin-left w3-text-grey"
											style="font-size: 150%"><c:out value="${group.key}" /></span>
										<i class="material-icons group_delete close w3-right">close</i>
									</header>
									
									<!-- SUBGROUPS -->
									<div class="subgroup w3-container w3-padding w3-center">
										<c:choose>
											<c:when test="${group.value.size() > '0'}">
												<c:forEach var="subgroup" items="${group.value}">
													<div>
														<span
															class="subgroup_name  w3-margin-left w3-text-dark-grey">${subgroup}</span>
														<i
															class="material-icons subgroup_add w3-text-blue w3-right">add</i>
														<i
															class="material-icons subgroup_remove w3-text-red w3-right">remove</i>
														<hr class="line_break">
													</div>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<button
													class="button_add_subgroup w3-button w3-blue w3-round">Add
													Sub-Group</button>

											</c:otherwise>
										</c:choose>
									</div>
									<div class="w3-container w3-padding">
										<button
											class="button_card_save w3-button w3-green w3-round" style="width:100%">Save</button>
<!-- 										<button
											class="button_card_save w3-button w3-green w3-round  w3-margin-left w3-right">Save</button> -->
										<!-- <button
											class="button_card_cancel w3-button w3-round w3-right ">Cancel</button> -->
									</div>
								</div>
								<!-- end of card_group --> 
									<c:if test="${count.index == endDiv-1}">
										</div>
										<c:set var="endDiv" value="${endDiv + 3}"/>
									</c:if>
							
							</c:forEach>
				
						</div>
					</div>
				</div>
			</div>



<!-- End of Panel -->

<!-- Dialog box -->
<div id="groupDialog">
	<input type="text" id="group_name" />
</div>