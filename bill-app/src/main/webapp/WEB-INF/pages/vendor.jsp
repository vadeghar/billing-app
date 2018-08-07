<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style type="text/css">
label {
    margin-bottom: 2px;
    margin-top: 5px;
}
</style>

<div class="form-inline margin-top-30">
<form:form action="${pageContext.request.contextPath}/admin/saveVendor" method="post" modelAttribute="vendor"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<input type="button" class="btn btn-primary add-row" style="margin-top: -40px; float: right;" value="Refresh" onclick="listVendor()" >
		
<div role="tabpanel" class="" id="">
	<div class="">
		
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h3 class="panel-title">Vendor</h3>
				</div>
				<div class="panel-body">
					<div class="row text-control">
					<div class="col-lg-6">
						<div class="col-sm-24 col-md-12 borderOnePx">
							<div class="tabs-wrapper margin-top-10" id="tabsData2" style="">
								<ul class="nav nav-tabs border-bottom5">
									<li class="active"><a href="#tab_1" data-toggle="tab" class="tab_1" onclick="">Personal Information</a> </li>
									<li><a href="#tab_2" data-toggle="tab" class="tab_2" onclick="" >Address Details</a> </li>
									<li><a href="#tab_3" data-toggle="tab" class="tab_3" onclick="">Bank Details</a> </li>
								</ul>
								<div class="tab-content acount-info">
									<div role="tabpanel" class="tab-pane active" id="tab_1">		
										<!-- Tab #1 Content Start -->
										<div class="form-horizontal">
											<div class="form-column">
												<div class="col-sm-4">
													<div class="form-group">
														<form:hidden path="id"/>
														<label for="name" class="rightAlign">Name: <span class="required">*</span></label>
														<form:input path="name" class="form-control"/>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="mobile"  class="rightAlign">Mobile: <span class="required">*</span></label>
														<form:input path="mobile" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="tinNo"  class="rightAlign">TIN No: </label>
														<form:input path="tinNo" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="gstNo"  class="rightAlign">GST No: </label>
														<form:input path="gstNo" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="company"  class="rightAlign">Company: </label>
														<form:input path="company" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="email"  class="rightAlign">Email: </label>
														<form:input path="email" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="phone"  class="rightAlign">Phone: </label>
														<form:input path="phone" class="form-control" cols="25"/>
													</div>
												</div>
												
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="fax"  class="rightAlign">FAX: </label>
														<form:input path="fax" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="website"  class="rightAlign">Website: </label>
														<form:input path="website" class="form-control" cols="25"/>
													</div>
												</div>
											</div>
										<!-- Tab #1 Content End -->		
									</div>					
				</div>
				
									<div role="tabpanel" class="tab-pane" id="tab_2"> 
										<!-- Tab #2 Content Start -->
										<div class="form-horizontal">
											<div class="form-column">
												<div class="col-sm-4">
													<div class="form-group">
														<label for="line1"  class="rightAlign">Line 1: </label>
														<form:input path="line1" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="line2"  class="rightAlign">Line2: </label>
														<form:input path="line2" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="city"  class="rightAlign">City:<span class="required">*</span></label>
														<form:input path="city" class="form-control"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="zip"  class="rightAlign">ZIP: </label>
														<form:input path="zip" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="state"  class="rightAlign">State: </label>
														<form:input path="state" class="form-control" cols="25"/>
													</div>
												</div>
											</div>
										</div>
									<!-- Tab #2 Content End -->						
									</div>
								  
									<div role="tabpanel" class="tab-pane" id="tab_3">	
										<!-- Tab #3 Content Start -->
										<div class="form-horizontal">
											<div class="form-column">
												<div class="col-sm-4">
													<div class="form-group">
														<label for="bankName"  class="rightAlign">Bank: </label>
														<form:input path="bankName" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="bankAccNo"  class="rightAlign">A/C. No: </label>
														<form:input path="bankAccNo" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-sm-4">
													<div class="form-group">
														<label for="bankIfsc"  class="rightAlign">IFSC: </label>
														<form:input path="bankIfsc" class="form-control" cols="25"/>
													</div>
												</div>
											</div>
										</div>
										<!-- Tab #3 Content End -->	
									</div>
								</div>
							</div>
							<div class="">
								<div class="" style="text-align: center; margin-top: 15px;'">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row" onclick="saveVendor();">
										<c:choose>
											<c:when test="${vendor.id gt 0 }">Update</c:when>
											<c:otherwise>Save</c:otherwise>
										</c:choose>
									</button>
									<button id="btnSubmit" type="button" class="btn  btn-danger" onclick="listVendor();">Cancel</button>
								</div>									
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="col-sm-24 col-md-12 borderOnePx">
							<div class="margin-5">
								<c:choose>
									<c:when test="${not empty vendorList}">
									<table class="table table-bordered tb-color darken">								
										<!--Table head-->
										<thead>
											<tr class="text-white">
												<th>ID</th>
									            <th>Name</th>
									            <th>Mobile</th>
									 			<th>Email</th>
											</tr>
										</thead>
										<!--Table head-->
										
										<!--Table body-->
										<tbody>
											<c:forEach var="vendor" items="${vendorList}">
									           <tr>
									               <td>${vendor.id}</td>
									               <td>${vendor.name}</td>
									               <td>${vendor.mobile}</td>
									               <td>${vendor.email}</td>
									               <td>
									               <button type="button" class="" onclick="editVendor('${vendor.id}')">
											          <span class="glyphicon glyphicon-pencil"></span>
											        </button>
											        <button type="button" class="" onclick="deleteVendor('${vendor.id}')">
											          <span class="glyphicon glyphicon glyphicon-remove"></span>
											        </button>
									              </td>
									           </tr>
									         </c:forEach>
										</tbody>
										<!--Table body-->
									</table>
									</c:when>
									<c:otherwise>
										<table class="table table-bordered tb-color darken">
											<tbody>
												<tr style="width: 100%; text-align: center; color: red;"> <td> No Data Found </td> </tr>
											</tbody>
										</table>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">
	</div>
</div>
</form:form>
</div>
<script type="text/javascript">

 $( document ).ready(function() {
    var msg = document.getElementById("message").innerHTML;
    if(msg != '') {
    	var iconType = 'success';
    	if(msg.indexOf('Error') != -1)
    		iconType = "warning";
    	swal(msg, {
		      icon: iconType,
		    });
    }
    

    $(function() {
		$("#myForm").validate({
			rules : {
				name: { 
	                required: true
	            },
	            mobile : { 
	                required: true 
	            },
	            city : { 
	                required: true 
	            }
			},
			messages : {
				name : {
					 required:"Please enter vendor name"
				},
				mobile : {
					 required:"Please enter mobile number"
				},
				bankName :{
					required:"Please enter city name"
				}
			},
			ignore:"",
			errorContainer: $('#errorContainer'),
		    errorLabelContainer: $('#errorContainer ul'),
		    wrapper: 'li'
		});
	});
}); 

function saveVendor() {
	var val = $("#myForm").valid();
	if (val) {
		document.getElementById("selectedId").value = 0;
		document.getElementById("myForm").action = "admin/saveVendor";
		document.getElementById("myForm").submit();
	}
	
}
function listVendor() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "admin/vendorList";
	document.getElementById("myForm").submit();
}

function editVendor(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "admin/vendor";
	document.getElementById("myForm").submit();
}



function deleteVendor(selectedId) {
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this data!",
		  icon: "warning",
		  buttons: true,
		  showCancelButton: true,
		  dangerMode: true,
		}, function() {
			document.getElementById("selectedId").value = selectedId;
			document.getElementById("myForm").action = "admin/deleteVendor";
			document.getElementById("myForm").submit();
		});
}


</script>