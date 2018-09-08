<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="permissionsList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> 
            		<h5>New Suppler</h5>
            		<div class="ibox-tools">
                         <a class="collapse-link" id="collapseLink">
                             <i class="fa fa-chevron-down"></i>
                         </a>
                     </div>
            	</div>
       				<div class="ibox-content" id="newOrEditPermissions" style="display: none;">
                		<div class="table-responsive">
									<div class="col-sm-12">
											<div class="form-group col-sm-3">
												<input type="hidden" id="id" data-input>						
												<label for="name">Name: <span class="required">*</span></label>
												<input id="name" class="form-control" data-input/>
											</div>
											<div class="form-group col-sm-3">
												<label for="link" >URL: <span class="required">*</span></label>
												<input id="link" class="form-control" data-input/>
											</div>
									</div>
									<div class="col-sm-12 text-center">
				                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="saveOrUpdate"><strong>Save</strong></button>
				                  		<button class="btn btn-sm btn-primary  m-t-n-xs" type="button" id="cancel"><strong>Cancel</strong></button>
				                  	</div>
	                		</div>
                	</div>
                </div>
        </div>
		<div class="col-lg-12">
            <div class="ibox float-e-margins" id="permissionsList">
                <div class="ibox-title">
                    <h5>Permissionss</h5>
                    <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                     </div>
                 </div>
                 <div class="ibox-content" id="permissionsDataTable">
                    <div class="table-responsive">
                    	<table id="permissionsListDataTable" class="table table-striped dt-responsive">
							<thead>
								<tr>
									<th  class="all no-sort">ID</th>
									<th class="min-tablet no-sort">NAME</th>
									<th class="min-tablet no-sort">URL</th>
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
var DATA_LIST_URL = '${pageContext.request.contextPath}/ajax/permissions/all';
var EDIT_URL = '${pageContext.request.contextPath}/ajax/permissions';
var SAVE_URL = '${pageContext.request.contextPath}/ajax/permissions/save';
var DELETE_URL = '${pageContext.request.contextPath}/ajax/permissions/delete';

var REQ_FLDS = ['#name','#link'];
var REQ_MSGS = ['Permissions Name is required','URL is required'];
var DEL_REQ_DATA = {};
	
	$('#cancel').on('click', function() {
		resetFields();
		$("#saveOrUpdate strong").text("Save");
		$("#saveOrUpdateTitle h5").text("New Permissions");
	});
	$('#saveOrUpdate').on('click', function() {
		var isValid = validate();
		var _requestData = getSaveRequestData();
		if(isValid) {
			simpleDataCall(SAVE_URL, "Save Permissions", $('#newOrEditPermissions'), _requestData, saveCallback)
		}
	});
	
	function getSaveRequestData() {
		var _requestData = '';
		$(":input").each(function(){
			if($(this).data('input') !== undefined) {
				var elementName = $(this).attr('id');
				_requestData = _requestData +'"'+ elementName +'": "'+ $('#'+elementName).val()+'",';
			}
		});
		_requestData = _requestData.replace(/.$/,"");
		_requestData = '{'+_requestData+'}';
		console.log(_requestData);
		var obj = $.parseJSON(_requestData);
		console.log(obj);
		return obj;
	} 
	
	$('#confirmDeleteBtn').on('click', function() {
		simpleDataCall(DELETE_URL, "Delete Permissions", $('#newOrEditPermissions'), DEL_REQ_DATA, deleteCallback)
	});
	
	$('#confirmNoBtn').on('click', function() {
		DEL_REQ_DATA = {};
	});
	
	function editPermissions(id) {
		var  _requestData = { id: id }
		simpleDataCall(EDIT_URL, "Edit Permissions", $('#newOrEditPermissions'), _requestData, editCallback)
	}
	
	function saveCallback() {
		toastr.success('Succesfully Saved');
		resetFields();
		RefreshTable('#permissionsListDataTable', DATA_LIST_URL)
	}
	
	function setDeleteRequest(id) {
		DEL_REQ_DATA = { id: id }
	}
	
	function deleteCallback() {
		$('#confirmNoBtn').click();
		toastr.success('Succesfully Deleted');
		RefreshTable('#permissionsListDataTable', DATA_LIST_URL)
	}
	
	function editCallback(permissions) {
		if(permissions) {
			$(":input").each(function(i){
				if($(this).data('input') !== undefined) {
					var elementName = $(this).attr('id');
					$('#'+elementName).val(permissions[elementName]);
				}
			});
			$('#collapseLink').click();
			$("#saveOrUpdate strong").text("Update");
			$("#saveOrUpdateTitle h5").text("Edit Permissions");
			$('#name').focus();
		}
	}
	
	function validate() {
		var allValid = true;
		$.each(REQ_FLDS, function( index, fieldName ) {
			console.log("Validating : "+fieldName);
			if($(fieldName).val() == '') {
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
		$(":input").each(function(i){
			if($(this).data('input') !== undefined) {
				var elementName = $(this).attr('id');
				$('#'+elementName).val('');
			}
		});
		$('#collapseLink').click();
		$("#saveOrUpdate strong").text("Save");
		$("#saveOrUpdateTitle h5").text("New Permissions");
	}
	function InitOverviewDataTable()
	{
		tableColumns = [ 
			{
				"data" : "id"
		    },
		    {
	     		"data" : "name"
		    }, {
		        "data" : "link"
		    },
		    {"mRender": function ( data, type, row ) {
		        return '<button class="glyphicon glyphicon-pencil bg-white" style="margin-right: 10px" onclick="editPermissions('+row.id+')"/>' 
		        +'<button class="glyphicon glyphicon-trash bg-white" style="margin-right: 10px" data-toggle="modal" data-target="#confirmDeleteModel" onclick="setDeleteRequest('+row.id+')"/>';}, "orderable": false
		    }];
		loadDataTable(DATA_LIST_URL, $("#permissionsListDataTable") , tableColumns, $('#permissionsDataTable'));
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