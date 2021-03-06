<!DOCTYPE html>
<html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Login</title>
<base href="/">
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="text-center">
	    <h1 class="logo-name">Invoice+</h1>
	</div>
	<tiles:insertAttribute name="body" />
<p class="m-t text-center"> <small>Invoice+ &copy; 2018</small> </p>
 	<!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>