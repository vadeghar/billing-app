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
						<a href="#" id="logo">
							<img class="center-block" src="<%=request.getContextPath()%>/img/logo.png">
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
					<!-- <li class="dropdown"><a class="dropdown-link" href="#">Product/Brand</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="addProductBrand" >Add Product/ Brand</a> </li>
							<li><a href="#" id="deletedProducts" >Deleted Products</a> </li>
							<li><a href="#" id="outOfStockProducts" >Out of Stock</a> </li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-link" href="#">Stock</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="addStock" >Add Stock</a> </li>
							<li><a href="#" id="deletedStock" >Deleted Stock</a> </li>
							<li><a href="#" id="lowStock" >Low Stock</a> </li>
							<li><a href="#" id="outOfStockProducts" >Out of Stock</a> </li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-link" href="#">Orders</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="newOrder" >New Order</a> </li>
							<li><a href="#" id="deletedOrders" >Orders</a> </li>
							<li><a href="#" id="cancelledOrders" >Deleted/Cancelled Orders</a> </li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-link" href="#">Suppliers</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="newSupplier" >New Supplier</a> </li>
							<li><a href="#" id="allSuppliers" >Suppliers</a> </li>
							<li><a href="#" id="deletedSuppliers" >Deleted Suppliers</a> </li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-link" href="#">Customers</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="newCustomer" >New Customer</a> </li>
							<li><a href="#" id="allCustomers" >Customers</a> </li>
							<li><a href="#" id="deletedCustomer" >Deleted Customers</a> </li>
						</ul>
					</li>
				
					<li class="dropdown"><a class="dropdown-link" href="#">Sales Orders</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="newSalesOrders" >New SalesOrder</a> </li>
							<li><a href="#" id="allSalesOrders" >Sales Orders</a> </li>
							<li><a href="#" id="deletedSalesOrders" >Deleted Sales Orders</a> </li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-link" href="#">Staff</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
								<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="newStaff" >New Staff</a> </li>
							<li><a href="#" id="allStaff" >Staff</a> </li>
							<li><a href="#" id="deletedStaff" >Deleted Staff</a> </li>
						</ul>
					</li>
				 -->
				
				
				
					<li class="dropdown"><a class="dropdown-link" href="#">Master</a>
						<span class="dropdown-caret dropdown-toggle"
							data-toggle="dropdown"> <b
								class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="userList" >Users</a> </li>
							<li><a href="#" id="customerList" >Customer</a> </li>
							<li><a href="#" id="productList" >Brand & Product</a> </li>
							<li><a href="#"  id="vendorList" title="Platform Migration" >Vendor/Supplier</a></li>
						</ul>
					</li>
					<li class="dropdown"><a class="dropdown-link" href="#">Inventory</a>
						<span class="dropdown-caret dropdown-toggle" data-toggle="dropdown"> 
							<b class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="purchaseList" >Purchases</a> </li>
							<li><a href="#"  id="saleList" title="Platform Migration" >Sales</a></li>
							<li><a href="#" id="purchaseEntry">New Purchases</a> </li>
							<li><a href="#"  id="saleEntry" title="Platform Migration" >New Sale</a></li>
						</ul>
					</li>
					<li class="dropdown"><a class="dropdown-link" href="#">Reports</a>
						<span class="dropdown-caret dropdown-toggle"
							data-toggle="dropdown"> <b
								class="caret glyphicon glyphicon-menu-right"></b>
						</span>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#" id="purchaseList" >Stock Report</a> </li>
							<li><a href="#" id="purchaseList" >Purchase Report</a> </li>
							<li><a href="#"  id="saleEntry" title="Platform Migration" >Sales Report</a></li>
							<li><a href="#"  id="saleEntry" title="Platform Migration" >Profit/Loss Report</a></li>
						</ul>
					</li>

					<li><a href="#"  id="notesList" >Notes</a></li>
					<li><a href="#"  id="contactus" >Contact Us</a> </li>
					<li><a href="<%=request.getContextPath()%>/logout"  id="contactus" >Logout</a> </li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
</form>



<script type="text/javascript">

$('#logo').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}';
	document.getElementById("headerForm").submit();
});

$('#purchaseList').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/purchaseList';
	document.getElementById("headerForm").submit();
});

$('#saleEntry').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/saleEntry';
	document.getElementById("headerForm").submit();
});

$('#repList').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/repList';
	document.getElementById("headerForm").submit();
});

$('#notesList').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/notesList';
	document.getElementById("headerForm").submit();
});

$('#contactus').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/contactus';
	document.getElementById("headerForm").submit();
});

$('#productList').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/productList';
	document.getElementById("headerForm").submit();
});

$('#vendorList').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}/vendorList';
	document.getElementById("headerForm").submit();
});
</script>