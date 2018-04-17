<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${pageContext.request.contextPath}/purchase" method="post" modelAttribute="purchase"  id="myForm" >

<div role="tabpanel" class="" id="">	
<a href="#" id="newPurchase" class="btn glyphicon glyphicon-plus"> New Purchase</a>
<!-- Tab #5 Content Start -->
<!--Table-->
<c:choose>
<c:when test="${not empty productList}">
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
			<c:forEach var="purchase" items="${purchaseList}">
	           <tr>
	               <td>${purchase.id}</td>
	              <td>${purchase.billDate}</td>
	               <td>${purchase.billNo}</td>
	               <td>${purchase.vendor.name}</td>
	               <td>${purchase.billTotal}</td>
	               <td>${purchase.discount}</td>
	               <td>${purchase.netTotal}</td>
	               <td><a href='${pageContext.request.contextPath}/purchase/${purchase.id}'>Edit</a>
	                   <a href='${pageContext.request.contextPath}/purchase/delete/${purchase.id}'>Delete</a>
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
$("#newPurchase").click(function(){
	$("#selectedId").val(0);
	document.getElementById("myForm").action = "/purchase";
	document.getElementById("myForm").submit();
});


</script>