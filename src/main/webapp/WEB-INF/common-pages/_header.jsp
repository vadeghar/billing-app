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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">My Bill</a>
			</div>
		</div>
	</div>
	<!--/.nav-collapse -->
</nav>
<div class="container-fluid">
	<div class="welcome-wrapper">
		<div class="breadcrumb-text">
			<h3>Dashboard</h3>
			<p><a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a></p>
		</div>
		<div style="float: right;">
			<a class="navbar-brand" href='${pageContext.request.contextPath}/notesList'>Notes</a>
			<a class="navbar-brand" href='${pageContext.request.contextPath}/contactus'>Contact Us</a>
		</div>
			
		<div class="welcome-message">
			<span class="breadcrumb-icon"><img src="${pageContext.request.contextPath}/static/images/grid-icon.png"
				width="90" height="90" /></span>
		</div>
	</div>
</div>
