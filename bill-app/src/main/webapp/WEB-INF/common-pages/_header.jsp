<form id="headerForm" action="#" method="POST">


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="logo-wrapper">
				<a class="navbar-brand" href="#" onclick="navigate('')">My Bill</a>
			</div>
		</div>
		
		
	</div>
</nav>



<div class="container-fluid">
	<div class="welcome-wrapper">
		<!-- <div class="breadcrumb-text">
			<h3>Dashboard</h3>
			<p><a class="navbar-brand" href="#" onclick="navigate('')">Home</a></p>
		</div> -->
		<div style="">
			<a class="menu" href='#' onclick="navigate('vendorList')">Vendor</a> |
			<a class="menu" href='#' onclick="navigate('brandList')">Brand</a> |
			<a class="menu" href='#' onclick="navigate('productList')">Product</a> |
			<a class="menu" href='#' onclick="navigate('purchaseList')">Purchase</a> |
			<a class="menu" href='#' onclick="navigate('notesList')">Notes</a> |
			<a class="menu" href='#' onclick="navigate('contactus')">Contact Us</a>
		</div>
	</div>
</div>
</form>
<script type="text/javascript">

function navigate(actionName) {
	if(actionName != '') {
		document.getElementById("headerForm").action = "/"+actionName;
		document.getElementById("headerForm").submit();
	}else {
		window.location = 'http://localhost:8090/home';
	}
}

</script>