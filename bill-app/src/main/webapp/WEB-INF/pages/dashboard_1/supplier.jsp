<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="brandList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> <h5>New Brand</h5></div>
       				<div class="ibox-content" id="newOrEditBrand">
                		<div class="table-responsive">
                				<fieldset class="col-md-6 p-3" style="border: black 1px solid; ">
                					<legend style="border: none;">General Information</legend>
                					<div class="col-sm-6">
										<div class="form-group">
											<input type="hidden" id="id">						
											<label for="name">Name: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
										<div class="form-group">
											<label for="name" >Class: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<input type="hidden" id="id">						
											<label for="name">Name: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
										<div class="form-group">
											<label for="name" >Class: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
									</div>
                				</fieldset>
                				
                				<fieldset class="col-md-6 p-3" style="border: black 1px solid; ">
                					<legend style="border: none;">General Information</legend>
                					<div class="col-sm-6">
										<div class="form-group">
											<input type="hidden" id="id">						
											<label for="name">Name: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
										<div class="form-group">
											<label for="name" >Class: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<input type="hidden" id="id">						
											<label for="name">Name: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
										<div class="form-group">
											<label for="name" >Class: <span class="required">*</span></label>
											<input id="name" class="form-control"/>
										</div>
									</div>
                				</fieldset>
                			
                		
                		</div>
                	</div>
                </div>
        </div>
		<div class="col-lg-12">
            <div class="ibox float-e-margins" id="brandList">
                <div class="ibox-title">
                    <h5>Brands</h5>
                    <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                     </div>
                 </div>
                 <div class="ibox-content" id="brandDataTable">
                    <div class="table-responsive">
                    	<table id="brandListDataTable" class="table table-striped dt-responsive">
							<thead>
								<tr>
									<th  class="all no-sort">Name</th>
									<th class="min-tablet no-sort">Mobile</th>
									<th class="min-tablet no-sort">Email</th>
									<th class="min-tablet no-sort">City</th>
									<th class="min-tablet no-sort">Action</th>
								</tr>
							</thead>
						</table>
                    
                    
                    </div>
                 </div>
              </div>
         </div>
	</div>
</div>