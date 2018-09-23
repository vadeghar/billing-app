<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="purchaseEntryList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> 
            		<h5>New Purchase Entry</h5>
            		<div class="ibox-tools">
                        <a class="collapse-link" id="collapseLink">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
            	</div>
       				<div class="ibox-content" id="newOrEditPurchaseEntry">
                		<div class="table-responsive">
                			<div class="form-group col-sm-3">
								<input type="hidden" id="id" data-input>					
								<label for="name">Supplier: <span class="required">*</span></label>
								<select id="supplierId"  class="form-control"></select>
							</div>
							<div class="form-group col-sm-3">
								<label for="purchaseDate" >Purchase Date: <span class="required">*</span></label>
								<div class="input-group date">
                                    <span class="input-group-addon">
                                    	<i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="my-date" class="form-control" id="purchaseDate">
                                </div>
							</div>
							<div class="col-sm-4">
								<table class="table invoice-total">
	                                <tbody>
	                                <tr>
	                                    <td><strong>Purchase Total :</strong></td>
	                                    <td>Rs. 00.00</td>
	                                </tr>
	                                <tr>
	                                    <td><strong>Payed :</strong></td>
	                                    <td>Rs. 00.00</td>
	                                </tr>
	                                <tr>
	                                    <td><strong>Balance :</strong></td>
	                                    <td>Rs. 00.00</td>
	                                </tr>
	                                </tbody>
	                            </table>
		                    </div>
		                    <div class="col-sm-2">
		                    	<div class="col-sm-12" id="live24CtPrices">
				                    <div class="widget style1 lazur-bg">
				                        <div class="row vertical-align">
				                            <div class="col-xs-3">
				                                <i class="fa fa-inr fa-3x"></i>
				                            </div>
				                            <div class="col-xs-9 ">
				                            	<div class="label label-primary">Gold 999</div>
				                                <h2 class="font-bold" id="live24CtPrice"></h2>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				                <div class="col-sm-12" id="live22Prices">
				                    <div class="widget style1 lazur-bg">
				                        <div class="row vertical-align">
				                            <div class="col-xs-3">
				                                <i class="fa fa-inr fa-3x"></i>
				                            </div>
				                            <div class="col-xs-9 ">
				                            	<div class="label label-primary">Gold 916</div>
				                                <h2 class="font-bold" id="live22CtPrice"></h2>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				                <!-- <div class="col-sm-12" id="liveSilverPrices">
				                    <div class="widget style1 lazur-bg">
				                        <div class="row vertical-align">
				                            <div class="col-xs-3">
				                                <i class="fa fa-inr fa-3x"></i>
				                            </div>
				                            <div class="col-xs-9 ">
				                            	<div class="label label-primary">Silver</div>
				                                <h2 class="font-bold" id="liveSilverPrice">442</h2>
				                            </div>
				                        </div>
				                    </div>
				                </div> -->
		                    
		                    </div>
                		</div>
                		<div class="form-group col-sm-3" >
				        	<div class="center-block">
				            	<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="saveOrUpdate"><strong>Save</strong></button>
				            	<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="cancel"><strong>Cancel</strong></button>
				            </div>
				        </div>
                	</div>
          		</div>
        </div>
  </div>
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
			<div class="ibox float-e-margins" id="purchaseDetails">
			   	<div class="ibox-title" id="saveOrUpdateTitle"> 
			   		<h5>Purchase Details</h5>
			   		<div class="ibox-tools">
			             <a class="collapse-link" id="collapseLink">
			                 <i class="fa fa-chevron-up"></i>
			             </a>
			         </div>
			   	</div>
				<div class="ibox-content" id="newOrEditPurchaseDetail">
					<div class="table-responsive">
			     			<div class="col-lg-12">
			      				<table  class="table table-condensed small-text table100" id="tb">
									<tr class="tr-header">
										<th class="td16" >Category<span class="required">*</span></th>
										<th  class="td8">Weight (in Gms)<span class="required">*</span></th>
										<th class="td8">Pcs<span class="required">*</span></th>
										<th class="td8">Weight/Pc</th>
										<th class="td8">Quality</th>
										<th class="td8">Rate/Gm</th>
										<th class="td12">Rate Date</th>
										<th class="td8">MC/Pc in Rs</th>
										<th class="td6">Wastage/Pc in Gms.</th>
										<th class="td6">Tax Rate/Pc in %</th>
										<th class="td8">Total</th>
										<th class="td4"><a href="javascript:void(0);" style="font-size:18px;" id="addMore" title="Add More Items"><span class="glyphicon glyphicon-plus"></span></a></th>
									</tr>
									<tr>
										<td>
										<input type="hidden" id="purchaseDetailId_0" name="purchaseDetailId">
										<select name="categoryId" id="categoryId_0" class="form-control categoryList"></select></td>
										<td><input type="text" name="totalWieght" id="totalWieght_0" class="form-control" maxlength="7"></td>
										<td><input type="text" name="quantity" id="quantity_0" class="form-control" maxlength="4"></td>
										<td><input type="text" name="avgWieght" id="avgWieght_0" class="form-control" readonly="readonly"></td>
										<td><input type="text" name="quality" id="quality_0" class="form-control" maxlength="3"></td>
										<td><input type="text" name="rateCutAt" id="rateCutAt_0" class="form-control" maxlength="6"></td>
										<td><input type="my-date" name="rateCutDate" id="rateCutDate_0" class="form-control"></td>
										<td><input type="text" name="makingChargePerPc" id="makingChargePerPc_0" class="form-control" maxlength="4"></td>
										<td><input type="text" name="wastagePerPc" id="wastagePerPc_0" class="form-control" maxlength="7"></td>
										<td><input type="text" name="taxRate" id="taxRate_0" class="form-control" maxlength="5"></td>
										<td><input type="text" name="total" id="total_0" class="form-control" readonly="readonly"></td>
										<td><a href='javascript:void(0);'  class='remove'><span class='glyphicon glyphicon-remove'></span></a></td>
									</tr>
								</table>
							</div>
			   			</div>
			 	</div>
			</div>
		</div>
	</div>
</div>
 <script type="text/javascript">
 var SUPPLIER_LIST_URL = '${pageContext.request.contextPath}/ajax/supplier/all';
 var CATEGORY_LIST_URL = '${pageContext.request.contextPath}/ajax/jewelCategory/all';
 var LIVE_RATE = '${pageContext.request.contextPath}/ajax/liveprice';
 var SAVE_URL = '${pageContext.request.contextPath}/ajax/jewelPurchase/save';
 
 
 var DATA_LIST_URL = '${pageContext.request.contextPath}/ajax/jewelCategory/all';
 var EDIT_URL = '${pageContext.request.contextPath}/ajax/jewelCategory';

 var DELETE_URL = '${pageContext.request.contextPath}/ajax/jewelCategory/delete';
 
 var REQ_FLDS = ['#supplierId','#purchaseDate'];
 var REQ_MSGS = ['Selct Supplie name', 'Purchase Date is required'];
 
 var DETAILS_REQ_FLDS = ['categoryId','totalWieght','quantity'];
 var DETAILS_REQ_MSGS = ['Select Category', 'Enter total weight', 'Enter total number of pcs'];
 
 

 
 $('#saveOrUpdate').on('click', function() {
	var isValid = validate();
	if(isValid) {
		var isDetailsValid = validateDetails();
		if(isDetailsValid) {
			alert('Every thing is fine now, can continue with save');
			simpleDataCall(SAVE_URL, "Save Category", $('#newOrEditJewelCategory'), prepareRequestData(), saveCallback); 
		}
		
	}
});

 function prepareRequestData() {
	 var _request = {id: 0, supplierId: 0, purchaseDate: '', _purchaseDetails : []};
	 var  _requestData = {
				id: $('#id').val(),
				supplierId: $('#supplierId').val(),
				purchaseDate: $('#purchaseDate').val(),
				jewelPurchaseDetails: preparePurchaseDetails()
			}
	 return _requestData;
 }
 
 function preparePurchaseDetails() {
	 var _purchaseDetails = [];
	 
	 
	var rows = [];
	$('select[name="categoryId"]').each(function(){
			var ids = $(this).attr('id').split('_');
			rows.push(ids[1]);
	});
	$.each(rows, function(index, val) {
		var _purchaseDetail = {id: 0, categoryId: 0, quantity: 0, quality: 0, totalWieght: 0.0, avgWieght: 0.0, rateCutAt:0.0, 
				 rateCutDate: '28/09/2018', taxRate: 0.0,
				 makingChargePerPc: 0.0, wastagePerPc: 0.0};
		_purchaseDetail.id = $('#purchaseDetailId_'+val).val();
		_purchaseDetail.categoryId=$('#categoryId_'+val).val();
		_purchaseDetail.quantity=$('#quantity_'+val).val();
		_purchaseDetail.quality=$('#quality_'+val).val();
		_purchaseDetail.totalWieght=$('#totalWieght_'+val).val();
		_purchaseDetail.avgWieght=$('#avgWieght_'+val).val();
		_purchaseDetail.rateCutAt=$('#rateCutAt_'+val).val();
		_purchaseDetail.rateCutDate=$('#rateCutDate_'+val).val();
		_purchaseDetail.taxRate=$('#taxRate_'+val).val();
		_purchaseDetail.makingChargePerPc=$('#makingChargePerPc_'+val).val();
		_purchaseDetail.wastagePerPc=$('#wastagePerPc_'+val).val();
		_purchaseDetails.push(_purchaseDetail);
	});
	 return _purchaseDetails;
 }
 
 function saveCallback(data) {
	 console.log("saved: "+JSON.stringify(data));
 }

 function validateDetails() {
	 var allValid = true;
	 var rows = [];
		$('select[name="categoryId"]').each(function(){
				var ids = $(this).attr('id').split('_');
				rows.push(ids[1]);
		});
		$.each(rows, function(index, val) {
			$.each(DETAILS_REQ_FLDS, function( index1, fieldName ) {
				var t = '#'+fieldName+'_'+val;
				if($(t).val().trim() == '') {
					toastr.error('Row '+(index+1)+' '+DETAILS_REQ_MSGS[index1]);
					allValid = false;
					return false;
				}
			});
		});
		if(!allValid)
			return false;
		else
			return true;
		/* var maxIndex = Math.max.apply(Math,rows);
	
	
		var rowCount = $('#tb tr').length - 1;
	 	var tRow = $("#tb tr:eq("+rowCount+")").clone(true);
	 	$.each(tRow.find("td"), function( index, tColumn ) {
			$.each($(tColumn).find("input"), function( index, tColumn ) {
				console.log("ID: "+$(this).attr("id")+" --- "+(rowCount-1));
				var ids = $(this).attr('id').split('_');
				$(this).attr("id", ids[0]+"_"+(maxIndex+1));
			});
			
			$.each($(tColumn).find("select"), function( index, tColumn ) {
				console.log("Select ID: "+$(this).attr("id")+" --- "+(rowCount-1));
				var ids = $(this).attr('id').split('_');
				$(this).attr("id", ids[0]+"_"+(maxIndex+1));
			}); */
 }
 
 function validate() {
		var allValid = true;
		$.each(REQ_FLDS, function( index, fieldName ) {
			if($(fieldName).val().trim() == '') {
				toastr.error(REQ_MSGS[index]);
				allValid = false;
				return false;
			}
		});
		if(!allValid)
			return false;
		else
			return true;
	}

 
 
 
 
 
 
 
 
 
 $(document).ready(function () {
	 simpleGetDataCall(SUPPLIER_LIST_URL, "Supplier List", $('#newOrEditPurchaseEntry'), loadSupplierList);
	 
	 $("select[name='categoryId']").on('change', function() {
	 });
	 $("input[name='totalWieght']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateAvgWeight(attrId[1]);
		 updateRowTotal(attrId[1]);
	 });
	 $("input[name='quantity']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateAvgWeight(attrId[1]);
		 updateRowTotal(attrId[1]);
	 });
	 
	 $("input[name='avgWieght']").on('change', function() {
	 });
	 
	 $("input[name='quality']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateRowTotal(attrId[1]);
	 });
	 
	 $("input[name='rateCutAt']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateRowTotal(attrId[1]);
	 });
	 
	 $("input[name='rateCutDate']").on('change', function() {
	 });
	 
	 $("input[name='makingChargePerPc']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateRowTotal(attrId[1]);
	 });
	 
	 $("input[name='wastagePerPc']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateRowTotal(attrId[1]);
	 });
	 
	 $("input[name='taxRate']").on('change', function() {
		 var attrId = $(this).attr('id').split('_');
		 updateRowTotal(attrId[1]);
	 });
	 $("input[name='total']").on('change', function() {
	 });
	 
	 $("input[name='totalWieght'], input[name='quantity'], input[name='quality'], input[name='rateCutAt'], input[name='makingChargePerPc'], input[name='wastagePerPc'], input[name='taxRate']").keyup(function(){
		    var val = $(this).val();
		    if(isNaN(val)){
		         val = val.replace(/[^0-9\.]/g,'');
		         if(val.split('.').length>2) 
		             val =val.replace(/\.+$/,"");
		    }
		    $(this).val(val); 
		});
	 
 });
 
 function updateAvgWeight(rowNo) {
	 if($('#totalWieght_'+rowNo).val() != '' && $('#quantity_'+rowNo).val() != '')
	 	$('#avgWieght_'+rowNo).val((parseFloat($('#totalWieght_'+rowNo).val()) / parseFloat($('#quantity_'+rowNo).val())).toFixed(3));
 } 
 
 function updateRowTotal(rowNo) {
	 var mcRs=0;
	 var wastageRs=0;
	 var taxToal=0;
	 var totalPrice=0;
	 if($('#rateCutAt_'+rowNo).val() != '' && $('#totalWieght_'+rowNo).val() != '') {
		 totalPrice = parseFloat($('#totalWieght_'+rowNo).val()) * parseFloat($('#rateCutAt_'+rowNo).val())
		 console.log("totalPrice: "+totalPrice);
		 if($('#makingChargePerPc_'+rowNo).val() != '')
		 	mcRs = parseInt($('#quantity_'+rowNo).val()) * parseInt($('#makingChargePerPc_'+rowNo).val());
		 console.log("MC: "+mcRs);
		 if($('#wastagePerPc_'+rowNo).val() != '') { 
			 var wastageGms = parseInt($('#quantity_'+rowNo).val()) * parseFloat($('#wastagePerPc_'+rowNo).val());
		 	wastageRs = wastageGms * parseFloat($('#rateCutAt_'+rowNo).val());
		 }
		 console.log("wastageRs: "+wastageRs);
		 if($('#taxRate_'+rowNo).val() != '')
			 taxToal = (totalPrice * parseInt($('#taxRate_'+rowNo).val())) / 100;
		 console.log("taxToal: "+taxToal);
		 var total = (parseFloat(totalPrice)+parseFloat(mcRs)+parseFloat(wastageRs)+parseFloat(taxToal)).toFixed(2);
		 console.log("totalPrice+mcRs+wastageRs+taxToal : "+(total));
		 $('#total_'+rowNo).val(total);
	 }
 }
 
 function loadSupplierList(data) {
	 if(data) {
		 $('#supplierId') .empty();
		 $.each(data, function( index, supplier ) {
			 $('<option/>', { value : supplier.id }).text(supplier.name).appendTo('#supplierId');
		 });
		 liveGoldPrice();
		 simpleGetDataCall(CATEGORY_LIST_URL, "Supplier List", $('#newOrEditPurchaseDetail'), loadCategoryList);
	 }
 }
 
 function loadCategoryList(data) {
	 if(data) {
		 $('.categoryList') .empty();
		 $.each(data, function( index, category ) {
			 $('<option/>', { value : category.id }).text(category.name).appendTo('.categoryList');
		 });
	 }
 }
 function liveGoldPrice() {
	 simpleGetDataCall(LIVE_RATE, "Updating Live Price", $('#livePrices'), livePriceCallback);
 }
	 function livePriceCallback(data) {
		 var liveObject = $.parseJSON(data);
		 var _24Ct = '';
		 var _22Ct = '';
		var dataD = $.parseJSON(liveObject.d);
		 $.each(dataD , function( index, br ) {
			 if(br.Br == 'HYDERABAD')
				 _24Ct = br.Lpr / 10;
			 if(br.Br == 'VISAKHAPATNAM' && br.Pur == "916.0")
				 _22Ct =  br.Lpr; 
		 });
		 
		 $('#live24CtPrice').html(Math.round(0.0 + _24Ct));
		 $('#live22CtPrice').html(Math.round(0.0 + _22Ct));
	 }
	 
  $(function(){
	 	$('#addMore').on('click', function() {
			var rows = [];
			$('input[name="taxRate"]').each(function(){
					var ids = $(this).attr('id').split('_');
					rows.push(ids[1]);
			});
		
			var maxIndex = Math.max.apply(Math,rows);
		
		
	 		var rowCount = $('#tb tr').length - 1;
		 	var tRow = $("#tb tr:eq("+rowCount+")").clone(true);
		 	$.each(tRow.find("td"), function( index, tColumn ) {
				$.each($(tColumn).find("input"), function( index, tColumn ) {
					console.log("ID: "+$(this).attr("id")+" --- "+(rowCount-1));
					var ids = $(this).attr('id').split('_');
					$(this).attr("id", ids[0]+"_"+(maxIndex+1));
				});
				
				$.each($(tColumn).find("select"), function( index, tColumn ) {
					console.log("Select ID: "+$(this).attr("id")+" --- "+(rowCount-1));
					var ids = $(this).attr('id').split('_');
					$(this).attr("id", ids[0]+"_"+(maxIndex+1));
				});
				
			});
          var data = tRow.appendTo("#tb");
          data.find("input").val('');
	     });
	 	
	     $(document).on('click', '.remove', function() {
	         var trIndex = $(this).closest("tr").index();
	            if(trIndex>1) {
	             $(this).closest("tr").remove();
	           } else {
	             alert("Sorry!! Can't remove first row!");
	           }
	      });
	}); 
 
 </script>