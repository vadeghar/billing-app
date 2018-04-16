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
							<div class="col-md-8">
								<form:hidden path="id"/>
								<div class="form-group">
									<label for="title" class="rightAlign">Brand: </label>
									<form:select  path="brandId" class="statusSelect form-control " >
									<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
										<c:if test="${not empty brandList}">
											<c:forEach var="brand" items="${brandList}">
												<c:choose>
													<c:when test="${brandId == brand.id}">
														<form:option value="${brand.id}" ><c:out value="${brand.name}"/> </form:option>
													</c:when>
													<c:otherwise>
														<form:option value="${brand.id}"><c:out value="${brand.name}"/> </form:option>
													</c:otherwise>
												</c:choose>
												
											</c:forEach>
										</c:if>
									</form:select>
									<button type="button" class="" onclick="showAddBrand()" title="Add new brand">
							          <span class="glyphicon glyphicon-plus"></span>
							        </button>
								</div>
							</div>
							<div class="col-md-8">	
								<div class="form-group">
									<label for="content"  class="rightAlign">Product Name: <span class="require">*</span></label>
									<form:input path="name" class="form-control" cols="25"/>
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
									            <th>Brand</th>
									            <th>Name</th>
									 			<th>Action</th>
											</tr>
										</thead>
										<!--Table head-->
										
										<!--Table body-->
										<tbody>
											<c:forEach var="product" items="${productList}">
									           <tr>
									               <td>${product.id}</td>
									               <td>${product.brandName}</td>
									               <td>${product.name}</td>
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
		<input type="hidden" id="newBrnadName" name="newBrnadName" value="${newBrnadName}">
		
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

function showAddBrand() {
	/* 
	swal("Add new Brand:", {
		  content: "input",
		})
		.then((value) => {
			document.getElementById("newBrnadName").value = value;
			document.getElementById("myForm").action = "/saveBrandInProduct";
			document.getElementById("myForm").submit();
		});
	 */
	
	
	swal("Add new Brand:",{
		  content: "input",
		  buttons: true,
		  dangerMode: true,
		})
		.then((value) => {
		  if (value) {
			    document.getElementById("newBrnadName").value = value;
				document.getElementById("myForm").action = "/saveBrandInProduct";
				document.getElementById("myForm").submit();
		  } else {
		    	//swal("Your imaginary file is safe!");
		  }
		});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

</script>