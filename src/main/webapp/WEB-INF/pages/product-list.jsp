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
		<c:forEach var="product" items="${productList}">
           <tr>
               <td>${product.id}</td>
              <%--  <td>${product.title}</td>
               <td>${product.content}</td> --%>
               <td><a href='${pageContext.request.contextPath}/admin/product/product/${product.id}'>Edit</a>
                   <a href='${pageContext.request.contextPath}/admin/product/product/delete/${product.id}'>Delete</a>
              </td>
           </tr>
         </c:forEach>
	</tbody>
	<!--Table body-->
</table>
<!--Table-->
<!-- Tab #5 Content End -->	
</div>
