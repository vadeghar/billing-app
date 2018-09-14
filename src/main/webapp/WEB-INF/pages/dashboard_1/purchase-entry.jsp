<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="purchaseEntryList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> 
            		<h5>New Purchase Entry</h5>
            		<div class="ibox-tools">
                         <a class="collapse-link" id="collapseLink">
                             <i class="fa fa-chevron-down"></i>
                         </a>
                     </div>
            	</div>
       				<div class="ibox-content" id="newOrEditPurchaseEntry" style="display: none;">
                		<div class="table-responsive">
                			<div class="form-group col-sm-3">
								<input type="hidden" id="id" data-input>						
								<label for="name">Name: <span class="required">*</span></label>
								<input id="name" class="form-control" data-input autocomplete="off"/>
							</div>
							<div class="form-group col-sm-3">
								<label for="mobileNo" >Mobile No: <span class="required">*</span></label>
								<input id="mobileNo" class="form-control" data-input autocomplete="off"/>
							</div>
							<div class="form-group col-sm-3">
								<label for="address" >Address:</label>
								<input id="address" class="form-control" data-input autocomplete="off"/>
							</div>
							<div class="form-group col-sm-3">
								<label for="email" >Email:</label>
								<input id="email" class="form-control" data-input autocomplete="off"/>
							</div>
							<div class="form-group col-sm-3">
								<label for="entryDate" >PurchaseEntry Since:</label>
								<span id="entryDate"> </span>
							</div>
		                  	<div class="center-block">
		                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="saveOrUpdate"><strong>Save</strong></button>
		                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="cancel"><strong>Cancel</strong></button>
		                  	</div>
                		</div>
                	</div>
          		</div>
        </div>
  </div>
 </div>