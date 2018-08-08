<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${pageContext.request.contextPath}/admin/purchase/purchase" method="post" modelAttribute="purchase"  id="myForm" >
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<div role="tabpanel" class="" id="">	
<a href="#" id="newPurchase" class="btn glyphicon glyphicon-plus"> New Purchase</a>
<!-- Tab #5 Content Start -->
<!--Table-->
<c:choose>
<c:when test="${not empty PurchaseList}">
	<table class="table table-bordered tb-color darken">								
		<!--Table head-->
		<thead>
			<tr class="text-white">
				<th>ID</th>
	            <th>Bill Date</th>
	            <th>Bill No</th>
	            <th>Vendor</th>
	            
	            <th>Bill Total</th>
	            <th>Discount</th>
	            <th>Net Total</th>
	            
	 			<th>Action</th>
			</tr>
		</thead>
		<!--Table head-->
		
		<!--Table body-->
		<tbody>
			<c:forEach var="purchase" items="${PurchaseList}">
	           <tr>
	               <td>${purchase.id}</td>
	              <td>${purchase.billDate}</td>
	               <td>${purchase.billNo}</td>
	               <td>${purchase.vendorName}</td>
	               <td>${purchase.billTotal}</td>
	               <td>${purchase.discount}</td>
	               <td>${purchase.netTotal}</td>
	               <td>
		                <button type="button" class="" onclick="editPurchase('${purchase.id}')">
				          <span class="glyphicon glyphicon-pencil"></span>
				        </button>
				        <button type="button" class="" onclick="deletePurchase('${purchase.id}')">
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
			<tr style="width: 100%; text-align: center; color: red;"> <td> No Purchases Found </td> </tr>
		</tbody>
	</table>
</c:otherwise>
</c:choose>
<input type="hidden" id="selectedId" name="selectedId" value="${selectedId}">

<!--Table-->
<!-- Tab #5 Content End -->	
</div>
</form:form>

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
});



$("#newPurchase").click(function(){
	$("#selectedId").val(0);
	document.getElementById("myForm").action = "admin/purchase/loadPurchase";
	document.getElementById("myForm").submit();
});


function editPurchase(selectedId) {
	$("#selectedId").val(selectedId);
	document.getElementById("myForm").action = "admin/purchase/loadPurchase";
	document.getElementById("myForm").submit();
}



function deletePurchase(selectedId) {
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  $("#selectedId").val(selectedId);
			document.getElementById("myForm").action = "admin/purchase/deletePurchase";
			document.getElementById("myForm").submit();
		  } else {
		    
		  }
		});
	/* document.getElementById("id").value = id;
	document.getElementById("myForm").action = "/deletePurchase";
	if(confirm("Are you sure want to delete?"))
		document.getElementById("myForm").submit(); */
	
}
</script>