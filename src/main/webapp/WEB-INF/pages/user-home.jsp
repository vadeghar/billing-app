<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<security:authorize access="isAuthenticated()">
    authenticated as <security:authentication property="principal.username" /> 
</security:authorize>

<security:authentication property="principal.authorities" var="authorities" />
<c:forEach items="${authorities}" var="authority" varStatus="vs">
<p>${authority.authority}</p>
</c:forEach>

<security:authorize access="hasAuthority('ADMIN')">
 Admin Login
</security:authorize>

<security:authorize access="hasAuthority('USER')">
 User Login
</security:authorize>
This is user home page