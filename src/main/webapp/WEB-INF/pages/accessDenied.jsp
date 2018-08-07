
	<div class="middle-box text-center animated fadeInDown">
		<h1>401</h1>
		<h3 class="font-bold">Unauthorized</h3>
		<div class="hr-line-solid"></div>
        <div class="error-desc">
            Sorry, but you are not authorized to access this application.<br/>
            If you have any questions or concerns, please contact system administrators.
            <a href="#" id="logo">
							<img class="center-block" src="<%=request.getContextPath()%>/img/logo.png">
						</a>
			<div class="row m-t-lg">
				<div class="col-xs-4 col-xs-offset-4 animated flash">
					<i class="fa fa-5x fa-warning text-danger"></i>
				</div>
			</div>
        </div>
	</div>
<script type="text/javascript">
$('#logo').on('click', function() {
	document.getElementById("headerForm").action = '${pageContext.request.contextPath}';
	document.getElementById("headerForm").submit();
});
</script>
