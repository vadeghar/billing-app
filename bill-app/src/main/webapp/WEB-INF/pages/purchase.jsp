<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="${pageContext.request.contextPath}/savePurchase" method="post" modelAttribute="purchase"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
 <div class="col-lg-6">
 	<div class="column-section mright20">
      <div class="form-horizontal">
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="Vendor" class="control-label">Vendor:</label>
					<form:hidden path="id"/>
					<form:select path="vendorId" class="form-control">
						<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
						<c:forEach var="vendor" items="${vendorList}">
							<form:option value="${vendor.id}"> <c:out value="${vendor.name}"></c:out> </form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="vendorMobile" class="control-label">Mobile:</label>
					<form:input type="name" class="form-control" path="vendorMobile" placeholder="" readonly="true" autocomplete="off"/>
				</div>
			</div>									
			<div class="col-sm-4 nomargin">
				<div class="form-group">
					<label for="vendorGst" class="control-label">GST No:</label>
					<form:input path="vendorGst" class="form-control" placeholder="" readonly="true"/>
				</div>
			</div>
		</div>
		
		<div class="form-column">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="billDate"  class="control-label">Bill Date: <span class="require">*</span></label>
					<form:input path="billDate" class="form-control" placeholder="dd/MM/yyyy" autocomplete="off"/>
				</div>
			</div>									
			<div class="col-sm-4">
				<div class="form-group">
					<label for="billNo"  class="control-label">Bill No: </label>
					<form:input path="billNo" class="form-control" placeholder="" autocomplete="off"/>
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
						<c:forEach var="productType" items="${productTypeList}">
							<c:out value="${productType.key}"/>
							<form:option value="${productType.key}"> <c:out value="${productType.value}"></c:out> </form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="purchaseItemDTO.size"  class="control-label">Size: </label>
					<%-- <form:input path="purchaseItemDTO.size" class="form-control" cols="25"/> --%>
					<form:select path="purchaseItemDTO.size" class="form-control">
						<form:option value="0"><c:out value="<-- Select -->"></c:out> </form:option>
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
					<button type="button" class="btn btn-primary" onclick="addPurchaseItem();">Add to list</button>
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
			<c:when test="${not empty purchaseItems}">
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
		            	<c:forEach var="purchaseItem" items="${purchaseItems}">
				           <tr>
				               <td>${purchaseItem.srNo}</td>
				               <td>${purchaseItem.productType}</td>
				               <td>${purchaseItem.sizeName}</td>
				               
				               <td>${purchaseItem.quantity}</td>
				               <td>${purchaseItem.pricePerUnit}</td>
				               <td>${purchaseItem.total}</td>
				               <td>${purchaseItem.margin} ${purchaseItem.marginType}</td>
				                <td>${purchaseItem.salePrice}</td>
				               <td>${purchaseItem.itemCode}</td>
				               <td>
				               <c:if test="${purchase.id ne 0}">
							        <button type="button" class="btn btn-danger btn-xs delete" onclick="deletePurchase('${purchase.id}')">
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
								<label for="billTotal"  class="control-label">Bill Total: </label>
								<form:input path="billTotal" class="control-label text-block" placeholder=""/>
							</div>							
						</div>
					</div>	
					<div class="col-sm-3">
						<div class="form-group form-group-required">
							<label for="discountType" class="control-label">Discount:</label><br/>
							<label class="radio-inline">
							  <form:radiobutton path="discountType" value="%" /> %  
							</label>
							<label class="radio-inline mleft">
							  <form:radiobutton path="discountType" value="RS" /> INR 
							</label>
						</div>
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="discount"  class="control-label">Discount(Rs): </label>	
							<form:input path="discount" class="control-label text-block" placeholder="" autocomplete="off"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="netTotal"  class="control-label">Net Total: </label>
							<form:input path="netTotal" class="control-label text-block" placeholder="" autocomplete="off"/>
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
							<button type="button" class="btn btn-danger" onclick="cancelPurchase();">Cancel</button>
						</div>
					</div>
				</div>						
			</div>	
		</div>
 </div>
<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">
</form:form>

<script type="text/javascript">

$( document ).ready(function() {
	
	$('#billDate').datepicker({
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
	document.getElementById("myForm").action = "/addPurchaseItem";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listPurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/purchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}



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
/* $( function() {
    $("#billDate").datepicker({
    	 maxDate: 0,
    	 dateFormat: 'dd/mm/yy'
    });
}); */
  
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

$("#vendorId").on('change', function() {
	var vendorId = $("#vendorId").val();
	if(vendorId != 0) {
		$.ajax({url: "/ajax/vendor/"+vendorId, 
			success: 
				function(result){
					$("#vendorMobile").val(result.mobile);
					$("#vendorGst").val(result.gstNo);
	    		}
		});
	}else {
		$("#vendorMobile").val('');
		$("#vendorGst").val('');
	}
});


$("#purchaseItemDTO\\.productId").on('change', function() {
	var productId = $('#purchaseItemDTO\\.productId').find(":selected").val(); // $("#purchaseItemDTO\\productId option:selected").val();
	if(productId != 0) {
		$.ajax({
			url: "/ajax/product/"+productId, 
			success: 
				function(result){
					var len = result.length
					var $size = $('#purchaseItemDTO\\.size');
					$size.empty();
					
					$.each( result, function(index,value){
						 console.log("Index = " + index + " value = " + value); 
						 $size.append("<option value='"+index+"'>"+value+"</option>");
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
$("#purchaseItemDTO\\.margin").on('change', function() {
	generateItemCode();
});
$("#purchaseItemDTO\\.size").on('change', function() {
	generateItemCode();
});
function generateItemCode() {
	var prod = $('#purchaseItemDTO\\.productId').find(":selected").text();
	var sz = $('#purchaseItemDTO\\.size').find(":selected").val();
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
		itemCode = itemCode + ((parseInt(sp)/10).toFixed());
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

$("#discount").blur(function() {
	if($.isNumeric($("#billTotal").val()) && $.isNumeric($("#discount").val())) {
		var discountType = $("input[name=discountType]:checked").val();
		var discount = parseFloat($("#discount").val());
		var billTotal = parseFloat($("#billTotal").val());
		if(discountType == '%'){
			$("#netTotal").val((billTotal - (billTotal * discount / 100)).toFixed());
		}else {
			$("#netTotal").val((billTotal - discount).toFixed());
		}
		//$("#netTotal").val(billTotal-parseFloat($("#discount").val()));
		$("#discount").prop("readonly", true);
	}
});

function savePurchase() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/savePurchase";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}
</script>