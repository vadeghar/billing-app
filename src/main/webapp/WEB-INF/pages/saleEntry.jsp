<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="${pageContext.request.contextPath}/saveSaleEntry" method="post" id="myForm" modelAttribute="sale">
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<style type="text/css">
.sweet-alert p  {
	display: none;
}
</style>
<div class="col-lg-6">
	<div class="column-section mright20">
		<div class="form-horizontal">
			<div class="form-column">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="itemCode"  class="control-label">Item Code: <span class="require">*</span></label>
						<input type="text" id="itemCode" name="itemCode" class="form-control" autocomplete="off"/>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<div class="col-lg-6">
	<div class="column-section section-scroll marbottom20">
				<table class="table table-bordered tb-color darken uh-table price-list" id="saleEntries">
					<thead>
						<tr class="text-white">
							<th style="width: 5%;">#</th>
							<th style="width: 15%;">Item</th>
				            <th style="width: 40%;">Name</th>
				            <th style="width: 10%;">Rate</th>
				            <th style="width: 20%;">Quantity</th>
				 			<th style="width: 10%;">Total</th>
				 			<th style="width: 5%;"></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
	</div>
	
	<div class="column-section total_section">
			<div class="form-horizontal">
				<div class="form-column">
					<div class="col-sm-3">
							<div class="form-group">
								<label for="invoiceTotal"  class="control-label">Bill Total: </label>
								<form:input path="invoiceTotal" class="control-label text-block" placeholder=""/>
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
							<label for="discount"  class="control-label">Discount(% / Rs): </label>	
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

				<div class="form-column">
					<div class="col-sm-12">
						<div class="form-group pull-right">
							<button type="button" class="btn btn-primary" onclick="" id="generateInvoice">Generate Invoice</button>
						</div>
					</div>
				</div>						
			</div>	
	</div>
</div>

</form:form>

<script type="text/javascript">
var saleEntryViewList = [];
var saleEntryView = {productDescription:"", rate: 0.0, quantity: 0, total:0.0, productItemId:0, itemCode:"", invoiceTotal:0.0, discountType:"", discount:0.0, netTotal:0.0};
$( document ).ready(function() {
	
	$("[id^='quantity']").on('change', function() {
		var idTxt = $(this).attr('id');
		var temp = idTxt.split("-");
		var rowNo = temp[1];
		var ratePerUnit = $('#rate-'+rowNo).html();
		var curQty = $(this).val();
		var total = ratePerUnit * curQty;
		$('#total-'+rowNo).html(total);
		
		//alert($(this).val()+"\n"+$('#rate-'+rowNo).html()+"\n"+total+'\n'+$('#total-'+rowNo).html(total)+"11111111111");
		var allTotal = 0;
		$("[id^='total']").each(function(indx, val) {
			allTotal = parseFloat(allTotal) + parseFloat($(this).html());
		});
		$('#invoiceTotal').val(allTotal);
		if($('#discount').val() == '') {
			$('#netTotal').val(allTotal);
		} else {
			var discountType = $("input[name=discountType]:checked").val();
			var discount = parseFloat($("#discount").val());
			if(discountType == '%'){
				$("#netTotal").val((allTotal - (allTotal * discount / 100)).toFixed());
			}else {
				$("#netTotal").val((allTotal - discount).toFixed());
			}
		}
		$("#discount").prop("readonly", true);
		$("#netTotal").prop("readonly", true);
		$("#invoiceTotal").prop("readonly", true);
	});
});


function updateTotals(rowNo) {
	var ratePerUnit = $('#rate-'+rowNo).html();
	var curQty = $('#quantity-'+rowNo).val();
	
	var total = ratePerUnit * curQty;
	$('#total-'+rowNo).html(total);
	
	//alert($(this).val()+"\n"+$('#rate-'+rowNo).html()+"\n"+total+'\n'+$('#total-'+rowNo).html(total)+"11111111111");
	var allTotal = 0;
	$("[id^='total']").each(function(indx, val) {
		allTotal = parseFloat(allTotal) + parseFloat($(this).html());
	});
	$('#invoiceTotal').val(allTotal);
	if($('#discount').val() == '') {
		$('#netTotal').val(allTotal);
	} else {
		var discountType = $("input[name=discountType]:checked").val();
		var discount = parseFloat($("#discount").val());
		if(discountType == '%'){
			$("#netTotal").val((allTotal - (allTotal * discount / 100)).toFixed());
		}else {
			$("#netTotal").val((allTotal - discount).toFixed());
		}
	}
	$("#discount").prop("readonly", true);
	$("#netTotal").prop("readonly", true);
	$("#invoiceTotal").prop("readonly", true);
	
	var saleEntryView = saleEntryViewList[0];
	saleEntryView.quantity = curQty;
}



$("#itemCode").on('change', function() {
	var itemCode = $("#itemCode").val();
	if(itemCode.length == 6) {
		$.ajax({url: "${pageContext.request.contextPath}/ajax/stock/itemCode/"+itemCode, 
			success: 
				function(result){
				saleEntryViewList.push(result);
				var rows = $('#saleEntries tbody tr').length;
					var tabRow = '<tr valign="middle">'+
													'<td>'+(rows+1)+'</td>'+
													'<td>'+result.itemCode+'</td>'+
													'<td>'+result.productDescription+'</td>'+
													'<td><span id="rate-'+rows+'" >'+result.rate+'</span> </td>'+
													'<td>'+
														'<input  type="number" min="1" max="50" id="quantity-'+rows+'"  class="form-control" autocomplete="off" style="height: 20px; width: 50px; padding-left: 5px; display: inline; " value="1" onchange="updateTotals('+rows+')"/>'+
													'<td><span id="total-'+rows+'" >'+result.rate+'</span></td>'+
													'<td><span id="close-'+rows+'" style="display: inline; cursor: pointer; color: red;">X</span></td>'+
												'</tr>';
					$("#saleEntries > tbody").append(tabRow);
					$("#quantity-"+rows).focus();
					$('#itemCode').val('');
				}
		});
	}
	
});

$('#generateInvoice').click(function() {
	alert("Quantiry: "+saleEntryViewList[0].quantity);
});
</script>