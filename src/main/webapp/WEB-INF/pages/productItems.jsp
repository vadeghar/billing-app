<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="${pageContext.request.contextPath}/saveProduct" method="post" id="myForm" modelAttribute="productView">
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${productView.message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Back to Products" onclick="listProduct()" >
<style type="text/css">
.sweet-alert p  {
	display: none;
}
.error {
    color: #ff0000;
  }
</style>
<div class="col-lg-6">
	<div class="column-section mright20">
		<div class="form-horizontal">
			<div class="form-column">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="Vendor" class="control-label">Brand:</label>
						<form:input type="hidden" path="product.id" />
						<form:input type="hidden" path="product.brandId" />
						<form:input type="text" path="brandName" class="form-control" autocomplete="off"  readonly="true"/>
						<%-- <c:out value="${productView.brandName }"/> --%>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="content"  class="control-label">Product Name: <span class="require">*</span></label>
						<form:input type="text" path="product.name" class="form-control" autocomplete="off"  readonly="true"/>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="form-horizontal">
			<h4>Product Item</h4>
			<div class="form-column">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="Vendor" class="control-label">Item Name:</label>
						<form:input type="hidden" path="productItems.id" />
						<form:input type="text" path="productItems.name" class="form-control" autocomplete="off"  />
						<%-- <c:out value="${productView.brandName }"/> --%>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<label for="content"  class="control-label">Description</label>
						<form:input type="text" path="productItems.description" class="form-control" autocomplete="off" />
					</div>
				</div>
			</div>
			<div class="form-column">
				<div class="col-sm-12">
					<div class="form-group pull-right">
						<button type="button" class="btn btn-primary"  id="saveProductItem">
						<c:choose>
							<c:when test="${productView.productItems.id gt 0 }"> Update</c:when>
							<c:otherwise>Save</c:otherwise>
						</c:choose>
						</button>
						<button type="button" class="btn btn-danger"  id="cancelProductItem">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-6">
	<div class="column-section section-scroll marbottom20">
				<table
					class="table table-bordered tb-color darken uh-table price-list">
					<thead>
						<tr class="text-white">
							<th>#</th>
				            <th>Name</th>
				            <th>Description</th>
				 			<th></th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
							<c:when test="${ not empty productView.productItemsList }"> 
								<c:forEach var="productItems" items="${productView.productItemsList}">
						           <tr>
						               <td>${productItems.id}</td>
						               <td>${productItems.name}</td>
						               <td>${productItems.description}</td>
						               <td>
						               <button type="button" class="" onclick="editProductItems('${productItems.id}')">
								          <span class="glyphicon glyphicon-pencil"></span>
								        </button>
								        <button type="button" class="" onclick="deleteProductItems('${productItems.id}')">
								          <span class="glyphicon glyphicon glyphicon-remove"></span>
								        </button>
						              </td>
						           </tr>
						         </c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4" style="width: 100%; text-align: center;" > <span  style="color: red;">No Items found.</span> </td>
								</tr>
							</c:otherwise>
					</c:choose>
						
					</tbody>
				</table>
	</div>
</div>


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
			$("form[name='myForm']").validate({
				// Specify validation rules
				rules : {
					brandId : "required",
					name : "required",
				},
				// Specify validation error messages
				messages : {
					brandId : "Please select Brand",
					name : "Please enter Name"
				},
				submitHandler : function(form) {
					return true;
				}
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
			document.getElementById("myForm").action = "saveProduct";
			document.getElementById("myForm").submit();
		}

	}
	function listProduct() {
		document.getElementById("selectedId").value = 0;
		document.getElementById("myForm").action = "productList";
		document.getElementById("myForm").submit();
	}
</script>

<script type="text/javascript">

function editProduct(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "product";
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
			document.getElementById("myForm").action = "deleteProduct";
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
		document.getElementById("myForm").action = "saveBrandInProduct";
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