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
								<input id="name" class="form-control" data-input autocomplete="off"/>
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
				<div class="ibox-content" id="newOrEditPurchaseEntry">
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
										<td><select name="categoryId" id="categoryId[0]" class="form-control">
										  <option value="" selected>Select Category</option>
										    <option value="Engineer">Engineer</option>
										    <option value="Accountant">Accountant</option>
										</select></td>
										<td><input type="text" name="totalWieght" id="totalWieght[0]" class="form-control"></td>
										<td><input type="text" name="quantity" id="quantity[0]" class="form-control"></td>
										<td><input type="text" name="avgWieght" id="avgWieght[0]" class="form-control"></td>
										<td><input type="text" name="quality" id="quality[0]" class="form-control"></td>
										<td><input type="text" name="rateCutAt" id="rateCutAt[0]" class="form-control"></td>
										<td><input type="my-date" name="rateCutDate" id="rateCutDate[0]" class="form-control"></td>
										<td><input type="text" name="makingChargePerPc" id="makingChargePerPc[0]" class="form-control"></td>
										<td><input type="text" name="wastagePerPc" id="wastagePerPc[0]" class="form-control"></td>
										<td><input type="text" name="taxRate" id="taxRate[0]" class="form-control"></td>
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
/*  
 $("input.tr_clone_add").live('click', function() {
	    var $tr    = $(this).closest('.tr_clone');
	    var $clone = $tr.clone();
	    $clone.find(':text').val('');
	    $tr.after($clone);
	});
 
 
  */
 $(function(){
	 	$('#addMore').on('click', function() {
	 		var rowCount = $('#tb tr').length - 1;
		 	var tRow = $("#tb tr:eq("+rowCount+")").clone(true);
		 	$.each(tRow.find("td"), function( index, tColumn ) {
				$.each($(tColumn).find("input"), function( index, tColumn ) {
					var idVal = $(this).attr("id");
					idVal.replace(rowCount-1, rowCount);
					console.log(idVal+" rowCount "+rowCount-1);
				});
				$.each($(tColumn).find("select"), function( index, tColumn ) {
					var idVal = $(this).attr("id");
					idVal.replace(rowCount-1, rowCount);
					console.log("Select "+idVal);
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