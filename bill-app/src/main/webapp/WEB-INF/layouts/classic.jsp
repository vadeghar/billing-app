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

	<!-- Date picker CSS -->
	<link href="${pageContext.request.contextPath}/assets/css/bootstrap-datepicker3.min.css" rel="stylesheet" />
	
	<!-- SweetAlert CSS-->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.0/sweetalert.css">
	
	<!-- Custom Fonts -->
	<link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,900" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>

	<!-- Fav Icon -->
	<link rel="icon" type="image/png" href="img/favicon.png" />
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style type="text/css">
.sweet-alert h2 {
    color: #575757;
    font-size: 14px;
    text-align: center;
    font-weight: 600;
    text-transform: none;
    position: relative;
    margin: 1px 0;
    padding: 0;
    display: block;
}
</style>
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

	<!-- Date Picker JS-->
	<script src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
	
	<!-- Custom JavaScript -->
	<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
	
	<!-- SweetAlert JS-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.0/sweetalert.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#invoice_billdate input').datepicker({
				autoclose: true
			});	

			$('#priceList').on('click', '.delete', function () {
				var btn = this;

				swal({
					title: "Do you want to delete?",
					text: "Price List from your Dashboard.",
					showCancelButton: true,
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "Yes, Delete",
					cancelButtonText: "Cancel",
					closeOnConfirm: false,
					closeOnCancel: false
				}, function (isConfirm) {
					if (isConfirm) {
						swal("Successfully!", "Removed your Price List", "success");
					} else {
						swal("Cancel", "Go to your Dashboard.", "error");
					}
				});
			});			
		});
	</script>
</body>

</html>