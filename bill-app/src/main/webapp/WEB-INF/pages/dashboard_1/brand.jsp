<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
                    	<div class="ibox float-e-margins" id="brandList">
			                <div class="ibox-title"> <h5>New Brand</h5></div>
					        	<div class="ibox-content" id="newOrEditBrand">
		                    		<div class="table-responsive">
		                    			<input type="hidden" id="id">
				                    	<div class="form-group"><label>Brand Name</label> <input type="text" id="brand" placeholder="Brand Name" class="form-control"></div>
				                    	<div class="center-block">
				                    		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button"><strong>Save</strong></button>
				                    		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button"><strong>Cancel</strong></button>
				                    	</div>
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
                         <!-- <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                             <i class="fa fa-wrench"></i>
                         </a>
                         <ul class="dropdown-menu dropdown-user">
                             <li><a href="#">Config option 1</a>
                             </li>
                             <li><a href="#">Config option 2</a>
                             </li>
                         </ul>
                         <a class="close-link">
                             <i class="fa fa-times"></i>
                         </a> -->
                     </div>
                 </div>
                 <div class="ibox-content">
                    <div class="table-responsive">
					<table id="brandListDataTable" class="table table-striped dt-responsive">
						<thead>
							<tr>
								<th  class="all no-sort">ID</th>
								<th class="min-tablet no-sort">Brand Name</th>
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


<script type="text/javascript">
	$(document).ready(function() {
		var tableColumns = [ {
            "data" : "id"
        }, {
            "data" : "name"
        },{"mRender": function ( data, type, row ) {
            return '<button class="glyphicon glyphicon-pencil bg-white" style="margin-right: 10px" onclick="editBrand('+row.id+')"/>' 
            +'<button class="glyphicon glyphicon-trash bg-white" style="margin-right: 10px" onclick="deleteBrand('+row.id+')"/>';}, "orderable": false
        }];
		loadDataTable("${pageContext.request.contextPath}/ajax/brands", $("#brandListDataTable") , tableColumns);
		
		
	});
	
	
	function loadDataTable(url, tId, tableColumns) {
		$(tId).DataTable({
	        "processing" : true,
	        "searching": true,
	        responsive: true,
	        order: [[0, 'desc']],
	        "ajax" : {
	            "url" : url,
	            dataSrc : ''
	        },
	        "columns" : tableColumns
	    });
	}
	
	function editBrand(id) {
		var  _requestData = {
					id: id
				}
		simpleDataCall("${pageContext.request.contextPath}/ajax/brand", "Edit Brand", $('#newOrEditBrand'), _requestData, setBrandDetails)
		alert('ID: '+id+" "+JSON.stringify(_requestData));
	}
	
	function setBrandDetails(brand) {
		
			
	}
	
	function deleteBrand(id){
		alert('ID: '+id);
	}

</script>