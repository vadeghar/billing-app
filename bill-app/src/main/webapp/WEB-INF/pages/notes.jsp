<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-inline margin-top-30">
<form:form action="${pageContext.request.contextPath}/saveNotes" method="post" modelAttribute="notes"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Refresh" onclick="listNotes()" >
		
<div role="tabpanel" class="" id="">
	<!-- Tab #4 Content Start -->
	<div class="">
		
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h3 class="panel-title">Notes</h3>
				</div>
				<div class="panel-body">
					<div class="row text-control">
						<div class="col-sm-24 col-md-12 borderOnePx">
							
							<div class="margin-10 form-content">
							<div class="col-md-8">
								<form:hidden path="id"/>
								<div class="form-group">
									<label for="title" class="rightAlign">Title: <span class="require">*</span></label>
									<form:input path="title" class="form-control"/>
								</div>
							</div>
							<div class="col-md-8">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Content: <span class="require">*</span></label>
									<form:textarea path="content" class="form-control" cols="25"/>
								</div>
							</div>	
							</div>
							<div class="row margin-10">
								<div class="" style="text-align: center;">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row" onclick="saveNotes();">Save</button>
									<button id="btnSubmit" type="button" class="btn btn-primary add-row" onclick="listNotes();">Cancel</button>
									<%-- <a href="${pageContext.request.contextPath}/notesList" data-toggle="tab" class="btn btn-warning">Cancel</a> --%>
								</div>									
							</div>
						</div>
						<div class="col-sm-24 col-md-12 borderOnePx">
							<div class="margin-5">
								<c:choose>
									<c:when test="${not empty notesList}">
									<table class="table table-bordered tb-color darken">								
										<!--Table head-->
										<thead>
											<tr class="text-white">
												<th>ID</th>
									            <th>Title</th>
									            <th>Content</th>
									 			<th>Action</th>
											</tr>
										</thead>
										<!--Table head-->
										
										<!--Table body-->
										<tbody>
											<c:forEach var="notes" items="${notesList}">
									           <tr>
									               <td>${notes.id}</td>
									               <td>${notes.title}</td>
									               <td>${notes.content}</td>
									               <td>
									               <button type="button" class="" onclick="editNotes('${notes.id}')">
											          <span class="glyphicon glyphicon-pencil"></span>
											        </button>
											        <button type="button" class="" onclick="deleteNotes('${notes.id}')">
											          <span class="glyphicon glyphicon glyphicon-remove"></span>
											        </button>
									              </td>
									           </tr>
									         </c:forEach>
										</tbody>
										<!--Table body-->
									</table>
									</c:when>
									<c:otherwise>
										<table class="table table-bordered tb-color darken">
											<tbody>
												<tr style="width: 100%; text-align: center; color: red;"> <td> No Data Found </td> </tr>
											</tbody>
										</table>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">
	</div>
</div>
</form:form>
</div>
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

function saveNotes() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/saveNotes";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listNotes() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/notes";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>
<%-- <form:hidden path="id" /> --%>


<script type="text/javascript">

function editNotes(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "/notes";
	document.getElementById("myForm").submit();
}



function deleteNotes(selectedId) {
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			document.getElementById("selectedId").value = selectedId;
			document.getElementById("myForm").action = "/deleteNotes";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		});
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deleteNotes";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}


</script>