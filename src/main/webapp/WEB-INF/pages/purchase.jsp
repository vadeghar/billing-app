<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-inline margin-top-30">
<form:form action="${pageContext.request.contextPath}/savePurchase" method="post" modelAttribute="purchase"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Refresh" onclick="listPurchase()" >
		
<div role="tabpanel" class="" id="">
	<div class="">
		
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h3 class="panel-title">Purchase</h3>
				</div>
				<div class="panel-body">
					<div class="row text-control">
						<div class="col-sm-24 col-md-12 borderOnePx">
							
							<div class="margin-10 form-content">
							<div class="col-md-8 margin-bottom-15">
								<form:hidden path="id"/>
								<div class="form-group">
									<label for="vendorId" class="rightAlign">Vendor: <span class="require">*</span></label>
									<form:select path="vendorId" class="form-control statusSelect">
										<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
										<c:forEach var="vendor" items="${vendorList}">
											<form:option value="${vendor.id}"> <c:out value="${vendor.name}"></c:out> </form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="vendorMobile"  class="rightAlign">Mobile: </label>
									<form:input path="vendorMobile" class="form-control" cols="25" disabled="true"/>
								</div>
							</div>	
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="vendorGst"  class="rightAlign">GST No: </label>
									<form:input path="vendorGst" class="form-control" cols="25" disabled="true"/>
								</div>
							</div>
							
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="billDate"  class="rightAlign">Bill Date: <span class="require">*</span></label>
									<form:input path="billDate" class="form-control" cols="25"/>
								</div>
							</div>
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="billNo"  class="rightAlign">Bill No: </label>
									<form:input path="billNo" class="form-control" cols="25"/>
								</div>
							</div>
							
							
							
							
							
							
							
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="spurchase.rNo"  class="rightAlign">SR No: </label>
									<form:input path="purchaseItemDTO.srNo" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.productId"  class="rightAlign">Product: </label>
									<form:input path="purchaseItemDTO.productId" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.size"  class="rightAlign">Size: </label>
									<form:input path="purchaseItemDTO.size" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.quantity"  class="rightAlign">Quantity: </label>
									<form:input path="purchaseItemDTO.quantity" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.pricePerUnit"  class="rightAlign">Price/Unit: </label>
									<form:input path="purchaseItemDTO.pricePerUnit" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.total"  class="rightAlign">Total: </label>
									<form:input path="purchaseItemDTO.total" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.marginType"  class="rightAlign">Margin Type: </label>
									<form:radiobutton path="purchaseItemDTO.marginType" value="%" /> Percentage (%)
									<form:radiobutton path="purchaseItemDTO.marginType" value="RS" /> Rupees (Rs)
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.margin"  class="rightAlign">Margin: </label>
									<form:input path="purchaseItemDTO.margin" class="form-control" cols="25" disabled="true"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.salePrice"  class="rightAlign">Sale Price: </label>
									<form:input path="purchaseItemDTO.salePrice" class="form-control" cols="25"/>
								</div>
							</div>
							<div class="col-md-8 margin-bottom-15">	
								<div class="form-group">
									<label for="purchaseItemDTO.itemCode"  class="rightAlign">Item Code: </label>
									<form:input path="purchaseItemDTO.itemCode" class="form-control" cols="25"/>
								</div>
							</div>
							
							
							
							
							
							
							
							
							
							
							
							
														
							</div>
							<div class="row margin-10">
								<div class="" style="text-align: center;">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row" onclick="savePurchase();">Save</button>
									<button id="btnSubmit" type="button" class="btn btn-primary add-row" onclick="listPurchase();">Cancel</button>
									<%-- <a href="${pageContext.request.contextPath}/purchaseList" data-toggle="tab" class="btn btn-warning">Cancel</a> --%>
								</div>									
							</div>
						</div>
						<div class="col-sm-24 col-md-12 borderOnePx">
							<div class="margin-5">
								<c:choose>
									<c:when test="${not empty purchaseItems}">
									<table class="table table-bordered tb-color darken">								
										<!--Table head-->
										<thead>
											<tr class="text-white">
												<th>ID</th>
									            <th>Sr. No</th>
									            <th>productId</th>
									 			<th>size</th>
									 			<th>quantity</th>
									 			<th>pricePerUnit</th>
									 			<th>total</th>
									 			<th>marginType</th>
									 			<th>margin</th>
									 			<th>salePrice</th>
									 			<th>itemCode</th>
									 			<th></th>
											</tr>
										</thead>
										<!--Table head-->
										
										<!--Table body-->
										<tbody>
											<c:forEach var="purchaseItem" items="${purchaseItems}">
									           <tr>
									               <td>${purchaseItem.id}</td>
									               <td>${purchaseItem.srNo}</td>
									               <td>${purchaseItem.productId}</td>
									               <td>${purchaseItem.size}</td>
									               
									               <td>${purchaseItem.quantity}</td>
									               <td>${purchaseItem.pricePerUnit}</td>
									               <td>${purchaseItem.total}</td>
									               <td>${purchaseItem.marginType}</td>
									               
									                <td>${purchaseItem.salePrice}</td>
									               <td>${purchaseItem.itemCode}</td>
									               
									               <td>
									               <button type="button" class="" onclick="editPurchase('${purchase.id}')">
											          <span class="glyphicon glyphicon-pencil"></span>
											        </button>
											        <button type="button" class="" onclick="deletePurchase('${purchase.id}')">
											          <span class="glyphicon glyphicon glyphicon-remove"></span>
											        </button>
									              </td>
									           </tr>
									         </c:forEach>
										</tbody>
										<tfoot>
											<tr>
											
											</tr>
										</tfoot>
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

function savePurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/savePurchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listPurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/purchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>
<%-- <form:hidden path="id" /> --%>


<script type="text/javascript">

function editPurchase(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "/purchase";
	document.getElementById("myForm").submit();
}



function deletePurchase(selectedId) {
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
			document.getElementById("myForm").action = "/deletePurchase";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		});
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deletePurchase";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}
$( function() {
    $("#billDate").datepicker({
    	 maxDate: 0,
    	 dateFormat: 'dd/mm/yy'
    });
});
  
$( document ).ready(function() {
	$("#purchaseItemDTO\\.quantity").on('change keyup', function() {
		if($(this).val() != '' && $("#purchaseItemDTO\\.pricePerUnit").val() != '') {
			$("#purchaseItemDTO\\.total").val($(this).val() * $("#purchaseItemDTO\\.pricePerUnit").val());
			$("#purchaseItemDTO\\.total").prop("disabled", true);
		}
	});
	$("#purchaseItemDTO\\.pricePerUnit").on('change keyup', function() {
		if($(this).val() != '' && $("#purchaseItemDTO\\.quantity").val() != '') {
			$("#purchaseItemDTO\\.total").val($(this).val() * $("#purchaseItemDTO\\.quantity").val());
			$("#purchaseItemDTO\\.total").prop("disabled", true);
		}
	});
	
	$("#purchaseItemDTO\\.marginType").on('change', function() {
		var radioValue = $("input[name='purchaseItemDTO.marginType']:checked").val();
		alert(radioValue);
	})
	
	$.each($("input[name='purchaseItemDTO\\.marginType']:checked"), function(){            
       alert($(this).val());
    });
	
});
</script>