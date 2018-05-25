<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="headerForm" action="#" method="POST">
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params?'?'+=params:'' }"/>
<!-- Navigation -->
<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom affix">
	<div class="container-fluid">
		<div class="topbar">
			<div class="row">
				<div class="col-lg-4"></div>

				<div class="col-lg-4">
					<div class="stride-logo text-center">
						<a href="index.html">
							<img class="center-block" src="img/logo.png">
						</a>
					</div>
				</div>

				<div class="col-lg-4"></div>
			</div>
		</div>
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu
				<i class="fa fa-bars"></i>
			</button>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-center">
					<li class="dropdown"><a class="dropdown-link" href="#">Master</a>
						<span class="dropdown-caret dropdown-toggle"
							data-toggle="dropdown"> <b
								class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" onclick="navigate('productList')">Product</a> </li>
							<li><a href="#" title="Platform Migration" onclick="navigate('vendorList')">Vendor</a></li>
							<li><a href="page2.html" title="Platform Migration">Page 2</a></li>
						</ul>
					</li>
					<li><a href="#" onclick="navigate('purchaseList')">Purchase</a> </li>
					<li><a href="#" onclick="navigate('salList')">Sales Invoice</a> </li>
					<li><a href="#" onclick="navigate('repList')">Reports</a> </li>
					<li><a href="#" onclick="navigate('notesList')">Notes</a></li>
					<li><a href="#" onclick="navigate('contactus')">Contact Us</a> </li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
</form>



<script type="text/javascript">

function navigate(actionName) {
	if(actionName != '') {
		document.getElementById("headerForm").action = actionName;
		document.getElementById("headerForm").submit();
	}else {
		window.location = 'home';
	}
}

</script>