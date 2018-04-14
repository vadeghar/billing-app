<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-inline margin-top-30">
<form:form action="${pageContext.request.contextPath}/saveProduct" method="post" modelAttribute="product"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Refresh" onclick="listProduct()" >
		
<div role="tabpanel" class="" id="">
	<div class="">
		
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h3 class="panel-title">Product</h3>
				</div>
				<div class="panel-body">
					<div class="row text-control">
						<div class="col-sm-24 col-md-12 borderOnePx">
							
							<div class="margin-10 form-content">
							<div class="col-md-8 margin-bottom-15">
								<form:hidden path="id"/>
								<div class="form-group">
									<label for="title" class="rightAlign">Name: <span class="require">*</span></label>
									<form:input path="name" class="form-control"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Mobile: <span class="require">*</span></label>
									<form:input path="mobile" class="form-control" cols="25"/>
								</div>
							</div>	
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">TIN No: </label>
									<form:input path="tinNo" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">GST No: </label>
									<form:input path="gstNo" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Company: </label>
									<form:input path="company" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Email: <span class="require">*</span></label>
									<form:input path="email" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Phone: </label>
									<form:input path="phone" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">FAX: </label>
									<form:input path="fax" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Website: </label>
									<form:input path="website" class="form-control" cols="25"/>
								</div>
							</div>
							
							
							</div>
							<div class="row margin-10">
								<div class="" style="text-align: center;">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row" onclick="saveProduct();">Save</button>
									<button id="btnSubmit" type="button" class="btn btn-primary add-row" onclick="listProduct();">Cancel</button>
									<%-- <a href="${pageContext.request.contextPath}/productList" data-toggle="tab" class="btn btn-warning">Cancel</a> --%>
								</div>									
							</div>
						</div>
						<div class="col-sm-24 col-md-12 borderOnePx">
							<div class="margin-5">
								<c:choose>
									<c:when test="${not empty productList}">
									<table class="table table-bordered tb-color darken">								
										<!--Table head-->
										<thead>
											<tr class="text-white">
												<th>ID</th>
									            <th>Name</th>
									            <th>Mobile</th>
									 			<th>Email</th>
											</tr>
										</thead>
										<!--Table head-->
										
										<!--Table body-->
										<tbody>
											<c:forEach var="product" items="${productList}">
									           <tr>
									               <td>${product.id}</td>
									               <td>${product.name}</td>
									               <td>${product.mobile}</td>
									               <td>${product.email}</td>
									               <td>
									               <button type="button" class="" onclick="editProduct('${product.id}')">
											          <span class="glyphicon glyphicon-pencil"></span>
											        </button>
											        <button type="button" class="" onclick="deleteProduct('${product.id}')">
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

function saveProduct() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/saveProduct";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listProduct() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/product";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>
<%-- <form:hidden path="id" /> --%>


<script type="text/javascript">

function editProduct(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "/product";
	document.getElementById("myForm").submit();
}



function deleteProduct(selectedId) {
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
			document.getElementById("myForm").action = "/deleteProduct";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		});
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deleteProduct";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}


</script>