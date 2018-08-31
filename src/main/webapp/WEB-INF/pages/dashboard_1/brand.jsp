<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="brandList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> <h5>New Brand</h5></div>
       				<div class="ibox-content" id="newOrEditBrand">
                		<div class="table-responsive">
                			<input type="hidden" id="id">
                  			<div class="form-group"><label>Brand Name</label> <input type="text" id="name" required="required" placeholder="Brand Name" class="form-control"></div>
		                  	<div class="center-block">
		                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="saveOrUpdate"><strong>Save</strong></button>
		                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="cancel"><strong>Cancel</strong></button>
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
                     </div>
                 </div>
                 <div class="ibox-content" id="brandDataTable">
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
					<div class="modal inmodal" id="confirmDeleteModel" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                         <div class="modal-dialog">
                             <div class="modal-content animated flipInY">
                                 <div class="modal-body">
                                     <p><strong>Are sure want to delete?</strong></p>
                                 </div>
                                 <div class="modal-footer">
                                     <button type="button" class="btn btn-primary" data-dismiss="modal" id="confirmNoBtn">No</button>
                                     <button type="button" class="btn btn-primary" id="confirmDeleteBtn">Yes</button>
                                 </div>
                             </div>
                         </div>
                     </div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
var tableColumns = [];
var DATA_LIST_URL = '${pageContext.request.contextPath}/ajax/brand/all';
var EDIT_URL = '${pageContext.request.contextPath}/ajax/brand';
var SAVE_URL = '${pageContext.request.contextPath}/ajax/brand/save';
var DELETE_URL = '${pageContext.request.contextPath}/ajax/brand/delete';

var REQ_FLDS = ['#name'];
var REQ_MSGS = ['Name is required'];
var DEL_REQ_DATA = {};
	
	$('#cancel').on('click', function() {
		resetFields();
		$("#saveOrUpdate strong").text("Save");
		$("#saveOrUpdateTitle h5").text("New Brand");
	});
	$('#saveOrUpdate').on('click', function() {
		var isValid = validate();
		if(isValid) {
			var  _requestData = {
					id: $('#id').val(),
					name: $('#name').val().trim()
				}
			simpleDataCall(SAVE_URL, "Save Brand", $('#newOrEditBrand'), _requestData, saveCallback)
		}
	});
	
	$('#confirmDeleteBtn').on('click', function() {
		simpleDataCall(DELETE_URL, "Delete Brand", $('#newOrEditBrand'), DEL_REQ_DATA, deleteCallback)
	});
	
	$('#confirmNoBtn').on('click', function() {
		DEL_REQ_DATA = {};
	});
	
	function editBrand(id) {
		var  _requestData = { id: id }
		simpleDataCall(EDIT_URL, "Edit Brand", $('#newOrEditBrand'), _requestData, editCallback)
	}
	
	function saveCallback() {
		toastr.success('Succesfully Saved');
		resetFields();
		RefreshTable('#brandListDataTable', DATA_LIST_URL)
	}
	
	function setDeleteRequest(id) {
		DEL_REQ_DATA = { id: id }
	}
	
	function deleteCallback() {
		$('#confirmNoBtn').click();
		toastr.success('Succesfully Deleted');
		RefreshTable('#brandListDataTable', DATA_LIST_URL)
	}
	
	function editCallback(brand) {
		if(brand) {
			$('#id').val(brand.id);
			$('#name').val(brand.name);
			$("#saveOrUpdate strong").text("Update");
			$("#saveOrUpdateTitle h5").text("Edit Brand");
			$('#name').focus();
		}
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
	
	function resetFields() {
		$('#id').val('');
		$('#name').val('');
	}
	function InitOverviewDataTable()
	{
		tableColumns = [ {
        				"data" : "id"
				    }, {
				        "data" : "name"
				    },{"mRender": function ( data, type, row ) {
				        return '<button class="glyphicon glyphicon-pencil bg-white" style="margin-right: 10px" onclick="editBrand('+row.id+')"/>' 
				        +'<button class="glyphicon glyphicon-trash bg-white" style="margin-right: 10px" data-toggle="modal" data-target="#confirmDeleteModel" onclick="setDeleteRequest('+row.id+')"/>';}, "orderable": false
				    }];
		loadDataTable(DATA_LIST_URL, $("#brandListDataTable") , tableColumns, $('#brandDataTable'));
	}
	
	function loadDataTable(url, tId, tableColumns, $content) {
		$(tId).DataTable({
	        "processing" : true,
	        "searching": true,
	        responsive: true,
	        order: [[0, 'desc']],
	        "ajax" : {
	            "url" : url,
	            dataSrc : '',
	            /* beforeSend: function() {
	    			toastr.clear();
	    			if (!$content.hasClass('sk-spinner') && !$content.hasClass('sk-spinner-rotating-plane')) {
	    				$content.addClass('sk-spinner').addClass('sk-spinner-rotating-plane');
	    			}
	    		},
	    		complete: function() {
	    			window.setTimeout(function() {
	    				$content.toggleClass('sk-spinner').toggleClass('sk-spinner-rotating-plane');
	    			}, _AOC_TIMEOUT);
	    		} */
	        },
	        "columns" : tableColumns
	    });
	}

	function RefreshTable(tableId, urlData)
	{
	  $.getJSON(urlData, null, function( json )
	  {
	    table = $(tableId).dataTable();
	    oSettings = table.fnSettings();
	    table.fnClearTable(this);
	    for (var i=0; i<json.length; i++)
	    {
	      table.oApi._fnAddData(oSettings, json[i]);
	    }
	    oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
	    table.fnDraw();
	  });
	}

	$(document).ready(function () {
	  InitOverviewDataTable();
	});
</script>