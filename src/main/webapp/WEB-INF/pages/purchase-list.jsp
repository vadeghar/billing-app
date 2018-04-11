<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div role="tabpanel" class="" id="">	
<!-- Tab #5 Content Start -->
<!--Table-->
<table class="table table-bordered tb-color darken">								
	<!--Table head-->
	<thead>
		<tr class="text-white">
			<th>ID</th>
            <!-- <th>Title</th>
            <th>Content</th> -->
 			<th>Action</th>
		</tr>
	</thead>
	<!--Table head-->
	
	<!--Table body-->
	<tbody>
		<c:forEach var="purchase" items="${purchaseList}">
           <tr>
               <td>${purchase.id}</td>
               <%-- <td>${purchase.title}</td>
               <td>${purchase.content}</td> --%>
               <td><a href='${pageContext.request.contextPath}/purchase/${purchase.id}'>Edit</a>
                   <a href='${pageContext.request.contextPath}/purchase/delete/${purchase.id}'>Delete</a>
              </td>
           </tr>
         </c:forEach>
	</tbody>
	<!--Table body-->
</table>
<!--Table-->
<!-- Tab #5 Content End -->	
</div>
