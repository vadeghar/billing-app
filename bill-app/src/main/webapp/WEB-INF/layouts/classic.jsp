<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Home - Dashboard</title>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">

<!-- Responsive CSS -->
<link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,900" rel="stylesheet">

<link href="https://fonts.googleapis.com/css?family=Crete+Round" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

<!-- Fav Icon -->
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon.png" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/sweetalert.min.js"></script>

</head>


<body id="page-top" class="index">

	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="menu" />
	<!-- Content Section -->
	<section class="content-wrapper">
		<div class="fluid-container">
			<div class="content-post">
				<div class="col-lg-12 div-outline">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
	</section>
	<!--/ Content Section -->
	<tiles:insertAttribute name="footer" />
	
	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<!-- Custom JavaScript -->
	<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
</body>
</html>