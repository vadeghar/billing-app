<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="${pageContext.request.contextPath}/admin/product/saveProduct" method="post" id="myForm" modelAttribute="productView">
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${productView.message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Refresh" onclick="listProduct()" >

<div class="col-lg-6">
	<div class="column-section mright20">
		<div class="form-horizontal">
			<div class="form-column">
				<div class="col-sm-4">
					<div class="form-group">
						<form:input type="hidden" path="product.id" />
						<label for=brandId class="control-label">Brand:<span class="required">*</span></label>
						<form:select path="product.brandId" class="form-control">
							<form:option value=""><c:out value="<-- Select -->"></c:out> </form:option>
							<c:forEach var="brand" items="${productView.brandList}">
								<c:choose>
									<c:when test="${product.brandId eq brand.id}">
										<form:option value="${brand.id}" selected="selected"> ${brand.name}  </form:option>
									</c:when>
									<c:otherwise>
										<form:option value="${brand.id}">${brand.name} </form:option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
						<button type="button" class="" onclick="showAddBrand()" title="Add new brand"><span class="glyphicon glyphicon-plus"></span></button>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="name"  class="control-label">Product Name: <span class="required">*</span></label>
						<form:input type="text" path="product.name" class="form-control" autocomplete="off"/>
					</div>
				</div>
			</div>
			<div class="form-column">
				<div class="col-sm-12">
					<div class="form-group pull-right">
						<button type="button" class="btn btn-primary" onclick="saveProduct();">
						<c:choose>
							<c:when test="${ product.id gt 0 }"> Update</c:when>
							<c:otherwise>Save</c:otherwise>
						</c:choose>
						</button>
						<button type="button" class="btn btn-danger" onclick="listProduct();">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-6">
	<div class="column-section section-scroll marbottom20">
		<c:choose>
			<c:when test="${not empty productList}">
				<table
					class="table table-bordered tb-color darken uh-table price-list">
					<thead>
						<tr class="text-white">
							<th>#</th>
				            <th>Brand</th>
				            <th>Name</th>
				 			<th>Action</th>
						</tr>
					</thead>
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
				</table>
			</c:when>
		</c:choose>
	</div>
</div>

<%-- <form:hidden path="product.selectedId"/> --%>

<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">
<input type="hidden" id="newBrnadName" name="newBrnadName" value="${newBrnadName}">
		


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
	$(function() {
			$("#myForm").validate({
				rules : {
					"product.brandId": { 
		                required: true
		            },
		            "product.name": { 
		                required: true
		            }
				},
				messages : {
					"product.brandId" : {
						 required: "Please select brand name"
					},
					"product.name" : {
						 required: "Please select product name"
					}
				},
				errorContainer: $('#errorContainer'),
			    errorLabelContainer: $('#errorContainer ul'),
			    wrapper: 'li'
			});
		});
	
	});
	function validateForm() {
		return true;
	}
	function saveProduct() {
		var val = $("#myForm").valid();
		if (val) {
			document.getElementById("selectedId").value = 0;
			document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/product/saveProduct";
			document.getElementById("myForm").submit();
		}

	}
	function listProduct() {
		document.getElementById("selectedId").value = 0;
		document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/product/productList";
		document.getElementById("myForm").submit();
	}
</script>

<script type="text/javascript">

function editProduct(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/product/productItems";
	document.getElementById("myForm").submit();
}



function deleteProduct(selectedId) {
	
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this imaginary file!",
		  icon: "warning",
		  buttons: true,
		  showCancelButton: true,
		  dangerMode: true,
		}, function() {
			document.getElementById("selectedId").value = selectedId;
			document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/product/deleteProduct";
			document.getElementById("myForm").submit();
		});
		/* .then((willDelete) => {
		  if (willDelete) {
			 	document.getElementById("selectedId").value = selectedId;
				document.getElementById("myForm").action = "deleteProduct";
				document.getElementById("myForm").submit();
		  } else {
		    swal("Your imaginary file is safe!");
		  }
		}); */
	
	
	/* swal({
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
		}); */
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deleteProduct";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}

function showAddBrand() {
	 swal({
		  title: "Add New Brand",
		  text: "Brand Name:",
		  type: "input",
		  showCancelButton: true,
		  closeOnConfirm: false,
		  inputPlaceholder: ""
		}, function (inputValue) {
		  if (inputValue === false) return false;
		  if (inputValue === "") {
		    swal.showInputError("Enter Brand Name");
		    return false
		  }
		  $('#newBrnadName').val(inputValue);
		document.getElementById("myForm").action = "${pageContext.request.contextPath}/admin/product/saveBrandInProduct";
		document.getElementById("myForm").submit();
		});
	 
	 
	/* swal("Add new Brand:",{
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
		    	swal("Your imaginary file is safe!");
		  }
		}); */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

</script> 