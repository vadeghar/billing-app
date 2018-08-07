<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="${pageContext.request.contextPath}/admin/saveSaleEntry" method="post" id="myForm" modelAttribute="sale">
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

<div class="col-lg-6 salePart">
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
<div id="invoicePrintData"></div>
<script type="text/javascript">
var saleEntryViewList = [];
var _headers = {};
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
		//$("#discount").prop("readonly", true);
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
	
	var saleEntryView = saleEntryViewList[rowNo];
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
					updateTotals(rows);
				}
		});
	}
	
});

$('#generateInvoice').click(function() {
	//alert("Quantiry: "+saleEntryViewList[0].quantity);
	var saleEntryView = saleEntryViewList[0];
	// Set total and discount details on first saleEntryObject
	saleEntryView.invoiceTotal = $('#invoiceTotal').val();
	var discountType = $("input[name=discountType]:checked").val();
	saleEntryView.discountType = discountType;
	saleEntryView.discount = $('#discount').val();
	saleEntryView.netTotal = $('#netTotal').val();
	simpleDataCall("ajax/sale/saveSale", "Save sale", $(".salePart"), saleEntryViewList, printAfterSave)
});

function printAfterSave(invoicePrintView) {
	if(invoicePrintView != null){
		printInvoice(invoicePrintView, '#invoicePrintData');
		//printInvoice(saleEntryViewList, invoicePrintView);
	}else{
		alert(" Not Saved..");
	}
}

function printInvoice(invoicePrintData, element) {
	Popup($(element).html(), invoicePrintData);	
}


function Popup(divElementData, invoicePrintData) {
	 var mywindow = window.open('', 'Invoice', '_new');
     mywindow.document.write('<html><head><title>Invoice Print</title>');
     /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
		//mywindow.document.write('<style type="text/css">table { border-collapse: collapse; padding-left:10px} tr { border: solid; border-width: 1px 0; padding-left:10px} tr:first-child { border-top: none;} tr:last-child { border-bottom: none; }</style>');
		mywindow.document.write('<style type="text/css">@media print   @page  {  size: 5.5in 8.5in ; size: landscape;  } }</style>');
     mywindow.document.write("</head><body><div class='invoiceData'>");
     
     var tableData = "<table style='width:325px; text-align: center;'>"+
					 "<tr><tr>-----------------------------------------------------------</tr></tr>"+
					 "<tr><td>"+invoicePrintData.invoiceHeader.businessName+"</td></tr>" +
					 "<tr><td>"+invoicePrintData.invoiceHeader.addressLine1+"</td></tr>"+
					 "<tr><td>"+invoicePrintData.invoiceHeader.addressLine2+"</td></tr>"+
					 "<tr><td> Email: "+invoicePrintData.invoiceHeader.email+"</td></tr>"+
					 "<tr><td>-----------------------------------------------------------</td></tr>"+
					 "</table><br/>"+
					 "<table style='width:320px;'><tr><td>Inv. No: "+invoicePrintData.invoiceNumber+"</td><td style='text-align: right;'>Date: "+invoicePrintData.invoiceDate+"</td></tr>";
					 
	 if ( invoicePrintData.invoiceItems.length != 0 ) {					
			tableData += "<table style='width:325px;'>"+
						 "<tr><td colspan='5'>-----------------------------------------------------------</td></tr>"+
						 "<tr><th>S.No</th><th>Desc</th><th>Rate</th><th>Qty</th><th>Cost</th></tr>"+
						 "<tr><td colspan='5'>-----------------------------------------------------------</td></tr>";
							
			for(var i=0; i < invoicePrintData.invoiceItems.length; i++){
				tableData += "<tr><td>"+invoicePrintData.invoiceItems[i].srNo+"</td><td>"+invoicePrintData.invoiceItems[i].desc+"</td><td>"+invoicePrintData.invoiceItems[i].rate+"</td><td style='text-align: center;'>"+invoicePrintData.invoiceItems[i].quantity+"</td><td>"+invoicePrintData.invoiceItems[i].totalItemCost+"</td></tr>";
			}
			tableData += "<tr><td colspan='5'>-----------------------------------------------------------</td></tr>";
			tableData += "<tr><td></td><td></td><td></td><td><b>Total:</b> </td><td>"+invoicePrintData.invoiceItemTotal+"</td></tr>";
			tableData += "<tr><td></td><td></td><td></td><td><b>Discount:</b> </td><td>"+invoicePrintData.invoiceDiscount+"</td></tr>";
			tableData += "<tr><td></td><td></td><td></td><td><b>Net Total:</b> </td><td>"+invoicePrintData.invoiceNetAmount+"</td></tr>";
			tableData += "<tr><td colspan='5'>-----------------------------------------------------------</td></tr>";
			tableData += "<tr><td colspan='5'>Total Purchased: "+invoicePrintData.itemCount+"</td></tr>";
			tableData += "<tr><td colspan='5'>-----------------------------------------------------------</td></tr>";
			tableData += "</table>";
		}
	 
	 mywindow.document.write(tableData);
     mywindow.document.write('</div></body></html>');
    mywindow.print();
    mywindow.close();
     return true;
}

/* function printContent(invoiceTable) {
	return "<html><head><link href='vendor/bootstrap/css/bootstrap.min.css' rel='stylesheet'><script>function step1(){\n" +
			"setTimeout('step2()', 10);}\n" +
			"function step2(){window.print();window.close()}\n" +
			"</scri" + "pt></head><body class='text-center' onload='step1()'>\n" +
			""+invoiceTable+"</body></html>";
}

function printInvoice(saleEntryViewList, invoicePrintView) {
	Pagelink = "about:blank";
	var pwa = window.open(Pagelink, "_new");
	pwa.document.open();
	var invoiceTable = document.getElementById("saleEntries").innerHTML;
	pwa.document.write(printContent(invoiceTable));
	pwa.document.close();
} */

function simpleDataCall(url, title, $content, requestData, responseCallback) {
	$.ajax({
		url: url,
		headers: _headers,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(requestData),
		beforeSend: function() {
			//toastr.clear();
			//toastr.success(_AOC_INFO_LOAD_IN_PROGRESS, title);
			if (!$content.hasClass('sk-loading')) {
				$content.addClass('sk-loading');
			}
		},
		success: function(data) {
			responseCallback(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			responseCallback(null);
			handleError(jqXHR, textStatus, errorThrown);
		},
		complete: function() {
			window.setTimeout(function() {
				$content.toggleClass('sk-loading');
			}, 1000);
		}
	});
}
</script>