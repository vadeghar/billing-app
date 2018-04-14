<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-inline margin-top-30">
<form:form action="${pageContext.request.contextPath}/saveVendor" method="post" modelAttribute="vendor"  id="myForm" >
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
						<div class="col-sm-24 col-md-12 borderOnePx">
							
							<div class="tabs-wrapper margin-top-10" id="tabsData2" style="">
								<ul class="nav nav-tabs border-bottom5">
									<li class="active"><a href="#tab_1" data-toggle="tab" class="tab_1">Personal Information</a> </li>
									<li><a href="#tab_2" data-toggle="tab" class="tab_2">Address Details</a> </li>
									<li><a href="#tab_3" data-toggle="tab" class="tab_3">Bank Details</a> </li>
								</ul>
								<div class="tab-content acount-info">
									<div role="tabpanel" class="tab-pane active" id="tab_1">		
										<!-- Tab #1 Content Start -->
											<div class="margin-10 form-content">
												<div class="col-md-8 margin-bottom-15">
													<form:hidden path="id"/>
													<div class="form-group">
														<label for="name" class="rightAlign">Name: <span class="require">*</span></label>
														<form:input path="name" class="form-control"/>
													</div>
												</div>
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="mobile"  class="rightAlign">Mobile: <span class="require">*</span></label>
														<form:input path="mobile" class="form-control" cols="25"/>
													</div>
												</div>	
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="tinNo"  class="rightAlign">TIN No: </label>
														<form:input path="tinNo" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="gstNo"  class="rightAlign">GST No: </label>
														<form:input path="gstNo" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="company"  class="rightAlign">Company: </label>
														<form:input path="company" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="email"  class="rightAlign">Email: <span class="require">*</span></label>
														<form:input path="email" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="phone"  class="rightAlign">Phone: </label>
														<form:input path="phone" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="fax"  class="rightAlign">FAX: </label>
														<form:input path="fax" class="form-control" cols="25"/>
													</div>
												</div>
												
												<div class="col-md-8 margin-bottom-15">	
													<div class="form-group">
														<label for="website"  class="rightAlign">Website: </label>
														<form:input path="website" class="form-control" cols="25"/>
													</div>
												</div>
											</div>
										<!-- Tab #1 Content End -->		
									</div>					
				
									<div role="tabpanel" class="tab-pane" id="tab_2"> 
										<!-- Tab #2 Content Start -->
										<div class="margin-10 form-content">
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="line1"  class="rightAlign">Line 1: </label>
													<form:input path="line1" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="line2"  class="rightAlign">Line2: </label>
													<form:input path="line2" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="city"  class="rightAlign">City: </label>
													<form:input path="city" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="zip"  class="rightAlign">ZIP: </label>
													<form:input path="zip" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="state"  class="rightAlign">State: </label>
													<form:input path="state" class="form-control" cols="25"/>
												</div>
											</div>
											
										</div>
										
										<%-- 
										<div class="myChartDiv">
										  <canvas id="myChart" width="700" height="450"></canvas>
										</div> 
										--%>
										<!-- Tab #2 Content End -->						
									</div>
								  
									<div role="tabpanel" class="tab-pane" id="tab_3">	
										<!-- Tab #3 Content Start -->
										<div class="margin-10 form-content">
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="bankName"  class="rightAlign">Bank: </label>
													<form:input path="bankName" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="bankAccNo"  class="rightAlign">A/C. No: </label>
													<form:input path="bankAccNo" class="form-control" cols="25"/>
												</div>
											</div>
											<div class="col-md-8 margin-bottom-15">	
												<div class="form-group">
													<label for="bankIfsc"  class="rightAlign">IFSC: </label>
													<form:input path="bankIfsc" class="form-control" cols="25"/>
												</div>
											</div>
										</div>
										<!-- 
										Table
										<table class="table table-bordered tb-color darken">
											
											Table head
											<thead>
												<tr class="text-white">
													<th>Date:</th>
													<th>Description:</th>
													<th>Due Date:</th>
													<th>Bill Amount:</th>
													<th>Payment Amount:</th>
												</tr>
											</thead>
											Table head
											
											Table body
											<tbody>
												<tr>
													<td>01/17/18</td>
													<td>Customer Payment</td>
													<td></td>
													<td></td>            
													<td>$55.02</td>
												</tr>
												<tr>
													<td>12/24/17</td>
													<td>Regular Bill</td>
													<td>01/18/18</td>
													<td>$55.02</td>            
													<td></td>
												</tr>		
			
												<tr>
													<td>12/15/17</td>
													<td>Customer Payment</td>
													<td></td>
													<td></td>            
													<td>$32.34</td>
												</tr>
												<tr>
													<td>12/15/17</td>
													<td>Regular Bill</td>
													<td>01/18/18</td>
													<td>$32.34</td>            
													<td></td>
												</tr>
			
												<tr>
													<td>11/14/17</td>
													<td>Customer Payment</td>
													<td></td>
													<td></td>            
													<td>$22.12</td>
												</tr>
												<tr>
													<td>10/20/17</td>
													<td>Regular Bill</td>
													<td>11/14/17</td>
													<td>$22.12</td>            
													<td></td>
												</tr>	
			
												<tr>
													<td>10/20/17</td>
													<td>Customer Payment</td>
													<td></td>
													<td></td>            
													<td>$19.79</td>
												</tr>
												<tr>
													<td>10/13/17</td>
													<td>Regular Bill</td>
													<td>10/20/17</td>
													<td>$19.79</td>            
													<td></td>
												</tr>		
			
												<tr>
													<td>09/21/17</td>
													<td>Customer Payment</td>
													<td></td>
													<td></td>            
													<td>$24.30</td>
												</tr>
												<tr>
													<td>08/22/17</td>
													<td>Regular Bill</td>
													<td>09/21/17</td>
													<td>$19.79</td>            
													<td></td>
												</tr>		
												</tbody>
											Table body
										</table>
										Table
										 -->
										<!-- Tab #3 Content End -->	
									</div>
								</div>
							</div>
							
							
							
							
							
							
							
							
							
							
							
							
							
							<div class="row margin-10">
								<div class="" style="text-align: center;">
									<button id="btnSubmit" type="submit" class="btn btn-primary add-row" onclick="saveVendor();">Save</button>
									<button id="btnSubmit" type="button" class="btn btn-primary add-row" onclick="listVendor();">Cancel</button>
								</div>									
							</div>
						</div>
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
		<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">
	</div>
</div>
</form:form>
</div>
<script type="text/javascript">

/* $( document ).ready(function() {
    var msg = document.getElementById("message").innerHTML;
    if(msg != '') {
    	var iconType = 'success';
    	if(msg.indexOf('Error') != -1)
    		iconType = "warning";
    	swal(msg, {
		      icon: iconType,
		    });
    }
}); */

function saveVendor() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/saveVendor";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
	
}
function listVendor() {
	document.getElementById("selectedId").value = 0;
	document.getElementById("myForm").action = "/vendor";
	//document.getElementById("myForm").method = "get";
	document.getElementById("myForm").submit();
}

</script>
<%-- <form:hidden path="id" /> --%>


<script type="text/javascript">

function editVendor(selectedId) {
	document.getElementById("selectedId").value = selectedId;
	document.getElementById("myForm").action = "/vendor";
	document.getElementById("myForm").submit();
}



function deleteVendor(selectedId) {
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			document.getElementById("selectedId").value = selectedId;
			document.getElementById("myForm").action = "/deleteVendor";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		});
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deleteVendor";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}


</script>