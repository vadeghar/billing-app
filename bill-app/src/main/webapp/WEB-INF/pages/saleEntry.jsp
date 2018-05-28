<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="${pageContext.request.contextPath}/saveSaleEntry" method="post" id="myForm" modelAttribute="sale">
<div id="message"  style="margin-top: -40px; float: left; display: none;"><c:out value="${message}"/></div>
<style type="text/css">
.sweet-alert p  {
	display: none;
}
</style>
<div class="col-lg-6">
	<div class="column-section mright20">
		<div class="form-horizontal">
			<div class="form-column">
				<div class="col-sm-4">
					<div class="form-group">
						<label for="itemCode"  class="control-label">Item Code: <span class="require">*</span></label>
						<input type="text" id="itemCode" name="itemCode" class="form-control" autocomplete="off"/>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<div class="col-lg-6">
	<div class="column-section section-scroll marbottom20">
				<table
					class="table table-bordered tb-color darken uh-table price-list">
					<thead>
						<tr class="text-white">
							<th style="width: 5%;">#</th>
				            <th style="width: 50%;">Product Description</th>
				            <th style="width: 10%;">Rate</th>
				            <th style="width: 20%;">Quantity</th>
				 			<th style="width: 10%;">Total</th>
				 			<th style="width: 5%;"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
	</div>
	
	<div class="column-section total_section">
			<div class="form-horizontal">
				<div class="form-column">
					<div class="col-sm-3">
							<div class="form-group">
								<label for="invoiceTotal"  class="control-label">Bill Total: </label>
								<form:input path="invoiceTotal" class="control-label text-block" placeholder=""/>
							</div>							
					</div>	
					<div class="col-sm-3">
						<div class="form-group form-group-required">
							<label for="discountType" class="control-label">Discount:</label><br/>
							<label class="radio-inline">
							  <form:radiobutton path="discountType" value="%" /> %  
							</label>
							<label class="radio-inline mleft">
							  <form:radiobutton path="discountType" value="RS" /> INR 
							</label>
						</div>
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="discount"  class="control-label">Discount(% / Rs): </label>	
							<form:input path="discount" class="control-label text-block" placeholder="" autocomplete="off"/>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="netTotal"  class="control-label">Net Total: </label>
							<form:input path="netTotal" class="control-label text-block" placeholder="" autocomplete="off"/>
						</div>
					</div>
				</div>

				<div class="form-column">
					<div class="col-sm-12">
						<div class="form-group pull-right">
							<button type="button" class="btn btn-primary" onclick="">Generate Invoice</button>
						</div>
					</div>
				</div>						
			</div>	
	</div>
</div>

</form:form>