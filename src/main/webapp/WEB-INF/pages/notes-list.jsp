<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div role="tabpanel" class="" id="tab_5">	
<!-- Tab #5 Content Start -->
<!--Table-->
<table class="table table-bordered tb-color darken">								
	<!--Table head-->
	<thead>
		<tr class="text-white">
			<th>ID</th>
            <th>Title</th>
            <th>Content</th>
 			<th>Action</th>
		</tr>
	</thead>
	<!--Table head-->
	
	<!--Table body-->
	<tbody>
		<c:forEach var="note" items="${notesList}">
           <tr>
               <td>${note.id}</td>
               <td>${note.title}</td>
               <td>${note.content}</td>
               <td><a href='${pageContext.request.contextPath}/notes/${note.id}'>Edit</a>
                   <a href='${pageContext.request.contextPath}/notes/delete/${note.id}'>Delete</a>
              </td>
           </tr>
         </c:forEach>
	</tbody>
	<!--Table body-->
</table>
<!--Table-->
<!-- Tab #5 Content End -->	
</div>
