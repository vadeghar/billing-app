<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div role="tabpanel" class="account-search" id="">
	<!-- Tab #4 Content Start -->
	<div class="account-search collection-status">
		<form:form action="${pageContext.request.contextPath}/admin/user/saveUser" method="post" modelAttribute="user" class="form-inline">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Details</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-24 col-md-14">
							<div class="col-md-12 col-sm-6">
								<form:hidden path="id"/>
								<div class="form-group">
									<%-- <label for="title">Title: <span class="require">*</span></label>
									<form:input path="title" class="form-control"/> --%>
								</div>
							</div>
							<div class="col-md-12 col-sm-6">
								<div class="form-group">
									<%-- <label for="content">Content: <span class="require">*</span></label>
									<form:textarea path="content" class="form-control"/> --%>
								</div>
							</div>
							<div class="row margin-top-20">
								<div class="col-md-12">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row">Save</button>
									<a href="${pageContext.request.contextPath}/admin/useruserList" data-toggle="tab" class="btn btn-warning">Cancel</a>
								</div>									
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>























