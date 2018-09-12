<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-lg-12 rounded p-5" >
        	<div class="ibox float-e-margins" id="userList">
            	<div class="ibox-title" id="saveOrUpdateTitle"> 
            		<h5>New User</h5>
            		<div class="ibox-tools">
                         <a class="collapse-link" id="collapseLink">
                             <i class="fa fa-chevron-down"></i>
                         </a>
                     </div>
            	</div>
       				<div class="ibox-content" id="newOrEditUser" style="display: none;">
                		<div class="table-responsive">
							<div class="col-sm-12">
									<div class="col-sm-12">
										<div class="form-group col-sm-3">
											<input type="hidden" id="id" data-input>						
											<label for="firstName">First Name: <span class="required">*</span></label>
											<input id="firstName" class="form-control" data-input autocomplete="off"/>
										</div>
										<div class="form-group col-sm-3">
											<label for="lastName" >Last Name: <span class="required">*</span></label>
											<input id="lastName" class="form-control" data-input autocomplete="off"/>
										</div>
										<div class="form-group col-sm-3">
											<label for="email" >Email:</label>
											<input id="email" class="form-control" data-input autocomplete="off" autocomplete="off"/>
										</div>
										<div class="form-group col-sm-3">
											<label for="password" >Password:</label>
											<input type="password" id="password" class="form-control" data-input autocomplete="off"/>
										</div>
										<div class="form-group col-sm-3">
											<label for="mobile">Mobile No:</label>
											<input id="mobile" class="form-control" data-input autocomplete="off"/>
										</div>
										<div class="form-group col-sm-3">
											<label for="userName" >Login User Name:</label>
											<input id="userName" class="form-control" data-input autocomplete="off"/>
										</div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="form-group col-sm-3"><label>Roles</label></div>
							</div>
							<div class="col-sm-12" id="allRolesId">
								
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
            <div class="ibox float-e-margins" id="userList">
                <div class="ibox-title">
                    <h5>Users</h5>
                    <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                     </div>
                 </div>
                 <div class="ibox-content" id="userDataTable">
                    <div class="table-responsive">
                    	<table id="userListDataTable" class="table table-striped dt-responsive">
							<thead>
								<tr>
									<th  class="all no-sort">ID</th>
									<th class="min-tablet no-sort">First Name</th>
									<th class="min-tablet no-sort">Last Name</th>
									<th class="min-tablet no-sort">Email</th>
									<th class="min-tablet no-sort">Mobile</th>
									<th class="min-tablet no-sort">Action</th>
								</tr>
							</thead>
						</table>
                    </div>
                    <div class="modal inmodal" id="confirmDeleteModel" tabindex="-1" user="dialog" aria-hidden="true" style="display: none;">
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
var DATA_LIST_URL = '${pageContext.request.contextPath}/ajax/user/all';
var EDIT_URL = '${pageContext.request.contextPath}/ajax/user';
var SAVE_URL = '${pageContext.request.contextPath}/ajax/user/save';
var DELETE_URL = '${pageContext.request.contextPath}/ajax/user/delete';
var ALL_ROLES = '${pageContext.request.contextPath}/ajax/role/all';

var REQ_FLDS = ['#user'];
var REQ_MSGS = ['User Name is required'];
var DEL_REQ_DATA = {};

var sList = '';
var role = {id:''};
var checkedRoles = [];

function updateRoles() {
	//alert("DFDF");
	checkedRoles = [];
	$('input[type="checkbox"]').each(function () {
		role ={id:''};
		if(this.checked) {
			role.id = $(this).val();
			checkedRoles.push(role);
		}
	    sList += "(" + $(this).val() + "-" + (this.checked ? "checked" : "not checked") + ")";
	});
}
	$('#cancel').on('click', function() {
		resetFields();
		$("#saveOrUpdate strong").text("Save");
		$("#saveOrUpdateTitle h5").text("New User");
	});
	$('#saveOrUpdate').on('click', function() {
		var isValid = validate();
		var _requestData = getSaveRequestData();
		if(isValid) {
			simpleDataCall(SAVE_URL, "Save User", $('#newOrEditUser'), _requestData, saveCallback)
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
		var obj = $.parseJSON(_requestData);
		obj["roles"] = checkedRoles;
		return obj;
	} 
	
	$('#confirmDeleteBtn').on('click', function() {
		simpleDataCall(DELETE_URL, "Delete User", $('#newOrEditUser'), DEL_REQ_DATA, deleteCallback)
	});
	
	$('#confirmNoBtn').on('click', function() {
		DEL_REQ_DATA = {};
	});
	
	function editUser(id) {
		var  _requestData = { id: id }
		simpleDataCall(EDIT_URL, "Edit User", $('#newOrEditUser'), _requestData, editCallback)
	}
	
	function saveCallback() {
		toastr.success('Succesfully Saved');
		resetFields();
		RefreshTable('#userListDataTable', DATA_LIST_URL)
	}
	
	function setDeleteRequest(id) {
		DEL_REQ_DATA = { id: id }
	}
	
	function deleteCallback() {
		$('#confirmNoBtn').click();
		toastr.success('Succesfully Deleted');
		RefreshTable('#userListDataTable', DATA_LIST_URL)
	}
	
	function editCallback(user) {
		$('input:checkbox').removeAttr('checked');
		var permIds = [];
		if(user) {
			$(":input").each(function(i){
				if($(this).data('input') !== undefined) {
					var elementName = $(this).attr('id');
					$('#'+elementName).val(user[elementName]);
				}
			});
			
			$.each(user.roles, function(i, p){
				permIds.push(p.id);
			});
			$('input[type="checkbox"]').each(function () {
				var uiIdCheckbox = $(this);
				var found = 'No';
				if(isExist(permIds, $(uiIdCheckbox).val())){ 
					$(uiIdCheckbox).attr('checked', true);
				}else
					$(uiIdCheckbox).removeAttr('checked');
				
			});
			updateRoles();
			$('#collapseLink').click();
			$("#saveOrUpdate strong").text("Update");
			$("#saveOrUpdateTitle h5").text("Edit User");
			$('#firstName').focus();
		}
	}
	
	function validate() {
		var allValid = true;
		$.each(REQ_FLDS, function( index, fieldName ) {
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
		$("#saveOrUpdateTitle h5").text("New User");
	}
	function InitOverviewDataTable()
	{
		tableColumns = [ 
			{
				"data" : "id"
		    },
		    {
	     		"data" : "firstName"
		    },
		    {
	     		"data" : "lastName"
		    },
		    {
	     		"data" : "email"
		    },
		    {
	     		"data" : "mobile"
		    },
		    {"mRender": function ( data, type, row ) {
		        return '<button class="glyphicon glyphicon-pencil bg-white" style="margin-right: 10px" onclick="editUser('+row.id+')"/>' 
		        +'<button class="glyphicon glyphicon-trash bg-white" style="margin-right: 10px" data-toggle="modal" data-target="#confirmDeleteModel" onclick="setDeleteRequest('+row.id+')"/>';}, "orderable": false
		    }];
		loadDataTable(DATA_LIST_URL, $("#userListDataTable") , tableColumns, $('#userDataTable'));
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
	function loadAllRoles() {
		simpleGetDataCall(ALL_ROLES, "All Roles", $('#allRoles'), addRolesToPage)
	}
	
	function addRolesToPage(data) {
		var aa = '';
		var divBlock = '';
		$.each(data, function( index, role) {
			aa = aa+ '<li>'+
			            '<input type="checkbox" value="'+role.id+'" name="" class="i-checks"  onclick="updateRoles();"/>'+
			            '<span class="m-l-xs">'+role.role+'</span>'+
			         '</li>';
	         var start = '<div class="col-lg-3">'+
				'<div class="ibox-content" style="border: none;">'+
					'<ul class="todo-list m-t">'+
					aa+
					'</ul>'+
				'</div>'+
			'</div>';
			if(data.length > 4) {
				if((index+1)%4 == 0) {
					divBlock = divBlock+start;
					aa= '';
				}
			}else {
				divBlock = start;
			}
		});
		$('#allRolesId').html(divBlock);
	}
	
	function isExist(arr, ele) {
		for(var i=0; i <= arr.length; i++) {
			if(arr[i] == ele) {
				return true;
			}
		}
		return false;
	}
	
	$(document).ready(function () {
	  InitOverviewDataTable();
	  loadAllRoles();
	  $(":input").each(function(){
		  $(this).attr('autocomplete','off');
	  });
	  
	});
</script>