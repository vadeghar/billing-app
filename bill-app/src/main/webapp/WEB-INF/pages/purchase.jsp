<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="${pageContext.request.contextPath}/savePurchase" method="post" modelAttribute="purchaseView"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
 <div class="col-lg-6">
 	<div class="column-section mright20">
      <div class="form-horizontal">
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchase.vendor" class="control-label">Vendor:</label>
					<form:hidden path="purchase.id"/>
					<form:select path="purchase.vendorId" class="form-control">
						<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
						<c:forEach var="vendor" items="${purchaseView.vendorList}">
							<form:option value="${vendor.id}"> <c:out value="${vendor.name}"></c:out> </form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchase.vendorMobile" class="control-label">Mobile:</label>
					<form:input type="name" class="form-control" path="purchase.vendorMobile" placeholder="" readonly="true" autocomplete="off"/>
				</div>
			</div>									
			<div class="col-sm-4 nomargin">
				<div class="form-group">
					<label for="purchase.vendorGst" class="control-label">GST No:</label>
					<form:input path="purchase.vendorGst" class="form-control" placeholder="" readonly="true"/>
				</div>
			</div>
		</div>
		
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchase.billDate"  class="control-label">Bill Date: <span class="require">*</span></label>
					<form:input path="purchase.billDate" class="form-control" placeholder="dd/MM/yyyy" autocomplete="off"/>
				</div>
			</div>									
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchase.billNo"  class="control-label">Bill No: </label>
					<form:input path="purchase.billNo" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
			<div class="col-sm-4 nomargin">
				<div class="form-group">
					<label for="purchaseItemDTO.srNo"  class="control-label">SR No: </label>
					<form:input path="purchaseItemDTO.srNo" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
		</div>
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.productId"  class="control-label">Product: </label>
					<form:select path="purchaseItemDTO.productId" class="form-control">
						<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
						<c:forEach var="product" items="${purchaseView.productList}">
							<form:option value="${product.id}"> <c:out value="${product.name}"></c:out> </form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.productItemId"  class="control-label">Size: </label>
					<form:select path="purchaseItemDTO.productItemId" class="form-control">
						<c:choose>
							<c:when test="${purchaseView.purchaseItemDTO.productId gt 0 }">
								<c:forEach var="productItem" items="${purchaseView.productItemsList}">
									<form:option value="${productItem.id}"> <c:out value="${productItem.name}"></c:out> </form:option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
							</c:otherwise>
						</c:choose>
					</form:select>
				</div>
			</div>
			<div class="col-sm-4 nomargin">
				<div class="form-group">
					<label for="purchaseItemDTO.quantity"  class="control-label">Quantity: </label>
					<form:input path="purchaseItemDTO.quantity" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
		</div>
		
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.pricePerUnit"  class="control-label">Price/Unit: </label>
					<form:input path="purchaseItemDTO.pricePerUnit" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.total"  class="control-label">Total: </label>
					<form:input path="purchaseItemDTO.total" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
			<div class="col-sm-4 nomargin">
				<div class="form-group form-group-required">
					<label for="purchaseItemDTO.marginType"  class="control-label">Margin Type: </label><br/>
					<label class="radio-inline">
					  <form:radiobutton path="purchaseItemDTO.marginType" value="%" /> % (Percentage)
					</label>
					<label class="radio-inline">
					  <form:radiobutton path="purchaseItemDTO.marginType" value="RS" />INR (Rupees)
					</label>
				</div>
			</div>
		</div>
		
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.margin"  class="control-label">Margin: </label>
					<form:input path="purchaseItemDTO.margin" class="form-control" placeholder="" readonly="true" autocomplete="off"/>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.salePrice"  class="control-label">Sale Price: </label>
					<form:input path="purchaseItemDTO.salePrice" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.itemCode"  class="control-label">Item Code: </label>
					<form:input path="purchaseItemDTO.itemCode" class="form-control" placeholder="" autocomplete="off"/>
				</div>
			</div>
			
		</div>
		<div class="form-column">
			<div class="col-sm-12">
				<div class="form-group pull-right">
					<!-- <button type="button" class="btn btn-primary" onclick="addPurchaseItem();">Add to list</button> -->
					<button type="button" class="btn btn-primary"  id="savePurhcaseItem">Save</button>
					<button type="button" class="btn btn-danger" onclick="listPurchase();">Cancel</button>
				</div>
			</div>
		</div>
		
	</div>
</div>
 
 </div>
 <div class="col-lg-6">
 	<div class="column-section section-scroll marbottom20">
 		<c:choose>
			<c:when test="${not empty purchaseView.purchase.purchaseItems}">
		        <table class="table table-bordered tb-color darken uh-table price-list">
		            <thead>
		                <tr class="text-white">
		                    <th>#</th>
		                    <th>PRODUCT</th>
		                    <th>SIZE</th>
		                    <th>QTY</th>
		                    <th>PRICE/PC</th>
		                    <th>TOTAL</th>
		                    <th>MARGIN</th>
		                    <th>SALE PRICE/PC</th>
		                    <th>CODE</th>										
		                </tr>								
		            </thead>
		            <tbody>
		            	<c:forEach var="purchaseItem" items="${purchaseView.purchase.purchaseItems}">
				           <tr>
				               <td>${purchaseItem.id}</td>
				               <td>${purchaseItem.productName}</td>
				               <td>${purchaseItem.productItemName}</td>
				               
				               <td>${purchaseItem.quantity}</td>
				               <td>${purchaseItem.pricePerUnit}</td>
				               <td>${purchaseItem.total}</td>
				               <td>${purchaseItem.margin} ( ${purchaseItem.marginType} )</td>
				                <td>${purchaseItem.salePrice}</td>
				               <td>${purchaseItem.itemCode}</td>
				               <td>
				               <c:if test="${purchaseItem.id ne 0}">
							        <button type="button" class="btn btn-danger btn-xs pencil" onclick="editPurchaseItem('${purchaseItem.id}')">
							          <span class="glyphicon glyphicon-trash"></span>
							        </button>
							         <button type="button" class="btn btn-danger btn-xs delete" onclick="deletePurchaseItem('${purchaseItem.id}')">
							          <span class="glyphicon glyphicon-trash"></span>
							        </button>
						        </c:if>
				              </td>
				           </tr>
				         </c:forEach>
		            </tbody>
		         </table>
		       </c:when>
		       <c:otherwise>
					<table class="table table-bordered tb-color darken uh-table">
						<tbody>
							<tr style="width: 100%; text-align: center; color: red;"> <td> No Data Found </td> </tr>
						</tbody>
					</table>
				</c:otherwise>
		     </c:choose>
    	</div>
    	
    	<div class="column-section total_section">
			<div class="form-horizontal">
				<div class="form-column">
					<div class="col-sm-3">
						<div class="form-column">
							<div class="form-group">
								<label for="purchase.billTotal"  class="control-label">Bill Total: </label>
								<form:input path="purchase.billTotal" class="control-label text-block" placeholder=""/>
							</div>							
						</div>
					</div>	
					<div class="col-sm-3">
						<div class="form-group form-group-required">
							<label for="purchase.discountType" class="control-label">Discount:</label><br/>
							<label class="radio-inline">
							  <form:radiobutton path="purchase.discountType" value="%" /> %  
							</label>
							<label class="radio-inline mleft">
							  <form:radiobutton path="purchase.discountType" value="RS" /> INR 
							</label>
						</div>
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="purchase.discount"  class="control-label">Discount(% / Rs): </label>	
							<form:input path="purchase.discount" class="control-label text-block" placeholder="" autocomplete="off"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="purchase.netTotal"  class="control-label">Net Total: </label>
							<form:input path="purchase.netTotal" class="control-label text-block" placeholder="" autocomplete="off"/>
						</div>
					</div>
				</div>
				<%-- <div class="form-column">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="discount"  class="input-inline mleft">Discount(Rs): </label>	
							<form:input path="discount" class="form-control" placeholder="..."/>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="netTotal"  class="control-label">Net Total: </label>
							<form:input path="netTotal" class="form-control" placeholder="..."/>
						</div>
					</div>
				</div>	 --%>
				<div class="form-column">
					<div class="col-sm-12">
						<div class="form-group pull-right">
							<button type="button" class="btn btn-primary" onclick="savePurchase();">Save</button>
							<button type="button" class="btn btn-danger" onclick="navigate('purchaseList')">Cancel</button>
						</div>
					</div>
				</div>						
			</div>	
		</div>
 </div>
<input type="hidden" id="selectedPurchaseItemId" name="selectedPurchaseItemId" value="${selectedPurchaseItemId}">
</form:form>

<script type="text/javascript">

$( document ).ready(function() {
	
	$('#purchase\\.billDate').datepicker({
		autoclose: true
	});	

	
	
	
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

function addPurchaseItem() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/addPurchaseItem";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listPurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/purchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}



function editPurchase(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/purchase";
	document.getElementById("myForm").submit();
}

function editPurchaseItem(purchaseItemId) {
	document.getElementById("selectedPurchaseItemId").value = purchaseItemId;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/loadPurhcaseItem";
	document.getElementById("myForm").submit();
}

function deletePurchaseItem(purchaseId, purchaseItemId, itemCode) {
	if(purchaseId == '') {
		
		purchaseId = $('[name="purchase.id"]').val();
	}
	alert("ID:"+purchaseId);
	if(confirm("Are you sure?")) {
		document.getElementById("selectedId").value =purchaseId+"#"+purchaseItemId+"#"+itemCode;
		document.getElementById("myForm").action = "${pageContext.request.contextPath}/deletePurchaseItem";
		document.getElementById("myForm").submit();
	}
	
	/* 
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			document.getElementById("selectedId").value = purchaseItemId+"|"+itemCode;
			document.getElementById("myForm").action = "/deletePurchaseItem";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		}); */
}




  
$( document ).ready(function() {
	$("#purchaseItemDTO\\.quantity").on('change keyup', function() {
		if($(this).val() != '' && $("#purchaseItemDTO\\.pricePerUnit").val() != '') {
			$("#purchaseItemDTO\\.total").val($(this).val() * $("#purchaseItemDTO\\.pricePerUnit").val());
			$("#purchaseItemDTO\\.total").prop("readonly", true);
		}
	});
	$("#purchaseItemDTO\\.pricePerUnit").on('change keyup', function() {
		if($(this).val() != '' && $("#purchaseItemDTO\\.quantity").val() != '') {
			$("#purchaseItemDTO\\.total").val($(this).val() * $("#purchaseItemDTO\\.quantity").val());
			calculateSalePrice();
			$("#purchaseItemDTO\\.total").prop("readonly", true);
			generateItemCode();
		}
	});
	
	
	$("input[name=purchaseItemDTO\\.marginType]").click(function(e) { 
        if($("input[name=purchaseItemDTO\\.marginType]:checked").val() != "") {  
             $("#purchaseItemDTO\\.margin").removeAttr("readonly");  
             calculateSalePrice();
        } 
	});
	
	$("#purchaseItemDTO\\.margin").on('change keyup', function() {
		calculateSalePrice();
	});
	
	
});

$("#purchase\\.vendorId").on('change', function() {
	var vendorId = $("#purchase\\.vendorId").val();
	if(vendorId != 0) {
		$.ajax({url: "${pageContext.request.contextPath}/ajax/vendor/"+vendorId, 
			success: 
				function(result){
					$("#purchase\\.vendorMobile").val(result.mobile);
					$("#purchase\\.vendorGst").val(result.gstNo);
	    		}
		});
	}else {
		$("#purchase\\.vendorMobile").val('');
		$("#purchase\\.vendorGst").val('');
	}
});


$("#purchaseItemDTO\\.productId").on('change', function() {
	var productId = $('#purchaseItemDTO\\.productId').find(":selected").val(); // $("#purchaseItemDTO\\productId option:selected").val();
	if(productId != 0) {
		$.ajax({
			url: "${pageContext.request.contextPath}/ajax/product/items/"+productId, 
			success: 
				function(result){
					var len = result.length
					var $size = $('#purchaseItemDTO\\.productItemId');
					$size.empty();
					
					$.each( result, function(index, productItem){
						 console.log("Index = " + productItem.id + " value = " + productItem.name); 
						 $size.append("<option value='"+productItem.id+"'>"+productItem.name+"</option>");
						})
						
					/* for( var i = 0; i<len; i++){
						var id = result[i].id;
	                    var name = result[i].name;
	                    $size.append("<option value='"+id+"'>"+name+"</option>");
					} */
					generateItemCode();
	    		}
		});
	}else {
		
	}
});

$('#savePurhcaseItem').on('click', function() {
	$("#selectedPurchaseItemId").val(0);
	document.getElementById("myForm").action = "savePurhcaseItem";
	document.getElementById("myForm").submit();
});

$("#purchaseItemDTO\\.margin").on('change', function() {
	generateItemCode();
});
$("#purchaseItemDTO\\.productItemId").on('change', function() {
	generateItemCode();
});
function generateItemCode() {
	var prod = $('#purchaseItemDTO\\.productId').find(":selected").text();
	var sz = $('#purchaseItemDTO\\.productItemId').find(":selected").val();
	var sp = $("#purchaseItemDTO\\.salePrice").val();
	if(prod != '' && sz != '' && sp != '') {
		var itemCode =  prod.charAt(1); //prod.substring(1, prod.length);
		if(sz == 1)
			itemCode = itemCode + 'A';
		else if(sz == 2)
			itemCode = itemCode + 'B';
		else if(sz == 3)
			itemCode = itemCode + 'C';
		else if(sz == 4)
			itemCode = itemCode + 'D';
		else if(sz == 5)
			itemCode = itemCode + 'E';
		else 
			itemCode = itemCode + 'F';
		
		var postFix = (parseInt(sp)/10).toFixed();
		if(postFix.length < 4) {
			var noOfZeros = 4 - postFix.length;
			var zeros = '';
			for(var i=0; i < noOfZeros; i++) {
				zeros = zeros + '0';
			}
			postFix = zeros+postFix;
		}
		itemCode = itemCode +postFix;
		$("#purchaseItemDTO\\.itemCode").val(itemCode);
	}
}

function calculateSalePrice(){
	if($.isNumeric($("#purchaseItemDTO\\.margin").val())
			&& $.isNumeric($("#purchaseItemDTO\\.pricePerUnit").val())
			&& $("input[name=purchaseItemDTO\\.marginType]:checked").val() != "") {
		
		var ppu = parseFloat($("#purchaseItemDTO\\.pricePerUnit").val());
		var marg = parseFloat($("#purchaseItemDTO\\.margin").val());
		var margType = $("input[name=purchaseItemDTO\\.marginType]:checked").val();
		if(margType == '%'){
			$("#purchaseItemDTO\\.salePrice").val((ppu + (ppu * marg / 100)).toFixed());
		}else {
			$("#purchaseItemDTO\\.salePrice").val( (ppu + marg).toFixed());
		}
		$("#purchaseItemDTO\\.salePrice").prop("readonly", true);
	}else {
		$("#purchaseItemDTO\\.salePrice").val('');
		$("#purchaseItemDTO\\.salePrice").prop("readonly", false);
	}
}

$("#purchase\\.discount").blur(function() {
	if($.isNumeric($("#purchase\\.billTotal").val()) && $.isNumeric($("#purchase\\.discount").val())) {
		var discountType = $("input[name=purchase\\.discountType]:checked").val();
		var discount = parseFloat($("#purchase\\.discount").val());
		var billTotal = parseFloat($("#purchase\\.billTotal").val());
		if(discountType == '%'){
			$("#purchase\\.netTotal").val((billTotal - (billTotal * discount / 100)).toFixed());
		}else {
			$("#purchase\\.netTotal").val((billTotal - discount).toFixed());
		}
		//$("#netTotal").val(billTotal-parseFloat($("#discount").val()));
		$("#purchase\\.discount").prop("readonly", true);
	}
});

$('[name="purchase.discountType"]').click(function() {
	var billTotal = parseFloat($("#purchase\\.billTotal").val());
	$("#purchase\\.netTotal").val(billTotal);
	$("#purchase\\.discount").val('');
});

function savePurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "${pageContext.request.contextPath}/savePurchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

function navigate(actionName) {
	if(actionName != '') {
		document.getElementById("headerForm").action = "${pageContext.request.contextPath}/"+actionName;
		document.getElementById("headerForm").submit();
	}else {
		window.location = 'http://localhost:8090/home';
	}
}
</script>