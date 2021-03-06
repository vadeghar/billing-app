<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="#" id="myForm"  method="POST">
<input type="button" class="btn btn-primary add-row" style="margin: 5px; float: right;" value="Add Notes" onclick="addNotes()" >

<div role="tabpanel" class="" id="">	
<!-- Tab #5 Content Start -->
<!--Table-->
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
               <button type="button" class="btn btn-default btn-sm" onclick="editNotes('${notes.id}')">
		          <span class="glyphicon glyphicon-pencil"></span>
		        </button>
		        <button type="button" class="btn btn-default btn-sm" onclick="deleteNotes('${notes.id}')">
		          <span class="glyphicon glyphicon glyphicon-remove"></span>
		        </button>
              </td>
           </tr>
         </c:forEach>
	</tbody>
	<!--Table body-->
</table>
<%-- <form:hidden path="id" /> --%>
<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">

<!--Table-->
<!-- Tab #5 Content End -->	
</div>
</form:form>
<script type="text/javascript">

function editNotes(id) {
	document.getElementById("selectedId").value = id;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/notes";
	document.getElementById("myForm").submit();
}

function addNotes() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/notes";
	document.getElementById("myForm").submit();
}

function deleteNotes(id) {
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			document.getElementById("selectedId").value = id;
			document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/deleteNotes";
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