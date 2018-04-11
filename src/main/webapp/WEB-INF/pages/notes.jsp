<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${pageContext.request.contextPath}/saveNotes" method="post" modelAttribute="notes" class="form-inline margin-top-30" id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="List Notes" onclick="listNotes()" >
		
<div role="tabpanel" class="account-search" id="">
	<!-- Tab #4 Content Start -->
	<div class="account-search collection-status">
		
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h3 class="panel-title">Details</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-24 col-md-12">
							<div class="">
								<form:hidden path="id"/>
								<div class="form-group">
									<label for="title" class="rightAlign">Title: <span class="require">*</span></label>
									<form:input path="title" class="form-control"/>
								</div>
							</div>
							<div class="">
								<div class="form-group">
									<label for="content"  class="rightAlign">Content: <span class="require">*</span></label>
									<form:textarea path="content" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="row margin-top-20">
								<div class="">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row">Save</button>
									<button id="btnSubmit" type="button" class="btn btn-primary add-row" onclick="listNotes();">Cancel</button>
									<%-- <a href="${pageContext.request.contextPath}/notesList" data-toggle="tab" class="btn btn-warning">Cancel</a> --%>
								</div>									
							</div>
						</div>
					</div>
				</div>
			</div>
		
	</div>
</div>

</form:form>
<script type="text/javascript">

$( document ).ready(function() {
    var msg = document.getElementById("message").innerHTML;
    if(msg != '') {
    	var iconType = 'success';
    	if(msg.indexOf('Error') != -1)
    		iconType = "warning";
    	swal(msg, {
		      icon: iconType,
		    });
    }
});
function listNotes() {
	document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/notesList";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>
