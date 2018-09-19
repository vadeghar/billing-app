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
							<div class="form-group col-sm-3">
								<label for="">Live Price: </label>
								<span id="livePrice"  class="form-control"></span>
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
			      				<table  class="table table-condensed small-text" id="tb">
									<tr class="tr-header">
										<th>Category</th>
										<th>Weight</th>
										<th>Pcs</th>
										<th>Weight/Pc</th>
										<th>Quality</th>
										<th>Rate Cut@</th>
										<th>Rate Cut Date</th>
										<th>MC</th>
										<th>Wastage(%)/Pc</th>
										<th>Tax Rate</th>
										<th><a href="javascript:void(0);" style="font-size:18px;" id="addMore" title="Add More Items"><span class="glyphicon glyphicon-plus"></span></a></th>
									</tr>
									<tr>
										<td><select name="categoryId" id="categoryId_0" class="form-control categoryList"></select></td>
										<td><input type="text" name="totalWieght" id="totalWieght_0" class="form-control"></td>
										<td><input type="text" name="quantity" id="quantity_0" class="form-control"></td>
										<td><input type="text" name="avgWieght" id="avgWieght_0" class="form-control"></td>
										<td><input type="text" name="quality" id="quality_0" class="form-control"></td>
										<td><input type="text" name="rateCutAt" id="rateCutAt_0" class="form-control"></td>
										<td><input type="my-date" name="rateCutDate" id="rateCutDate_0" class="form-control"></td>
										<td><input type="text" name="makingChargePerPc" id="makingChargePerPc_0" class="form-control"></td>
										<td><input type="text" name="wastagePerPc" id="wastagePerPc_0" class="form-control"></td>
										<td><input type="text" name="taxRate" id="taxRate_0" class="form-control"></td>
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
 
 $(document).ready(function () {
	 simpleGetDataCall(SUPPLIER_LIST_URL, "Supplier List", $('#newOrEditPurchaseEntry'), loadSupplierList);
	// simpleGetDataCall(LIVE_RATE, "Updating Live Price", $('#newOrEditPurchaseDetail'), livePriceCallback);
 });
 
 
 function loadSupplierList(data) {
	 if(data) {
		 $('#supplierId') .empty();
		 $.each(data, function( index, supplier ) {
			 $('<option/>', { value : supplier.id }).text(supplier.name).appendTo('#supplierId');
		 });
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
 
	 function livePriceCallback(data) {
		 var liveObject = $.parseJSON(data);
		 var _24Ct = '';
		 var _22Ct = '';
		var dataD = $.parseJSON(liveObject.d);
		 $.each(dataD , function( index, br ) {
			 if(br.Br == 'HYDERABAD')
				 _24Ct = br.Lpr / 10;
			 if(br.Br == 'VISAKHAPATNAM' && br.Pur == "916.0")
				 _22Ct =  br.Lpr / 10; 
		 });
		 $('#livePrice').html(_24Ct+" ::: "+_22Ct);
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
		 	var tRow = $("#tb tr:eq("+maxIndex+")").clone(true);
		 	$.each(tRow.find("td"), function( index, tColumn ) {
				$.each($(tColumn).find("input"), function( index, tColumn ) {
					console.log("ID: "+$(this).attr("id")+" --- "+(rowCount-1));
					//alert($(this).attr("id").indexOf("_"));
					if($(this).attr("id").indexOf("_") != -1){
						var latest = split($(this).attr("id"));
						console.log("latest: "+latest);
					} else {
						var latest = $(this).attr("id");
					}
					$(this).attr("id", latest+"_"+(maxIndex+1));
					//$(this).attr("id").replace(rowCount-1, rowCount);
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