<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper wrapper-content">
	<div class="row">
		 <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>Brands</h5>
                    <div class="ibox-tools">
                         <a class="collapse-link">
                             <i class="fa fa-chevron-up"></i>
                         </a>
                         <!-- <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                             <i class="fa fa-wrench"></i>
                         </a>
                         <ul class="dropdown-menu dropdown-user">
                             <li><a href="#">Config option 1</a>
                             </li>
                             <li><a href="#">Config option 2</a>
                             </li>
                         </ul>
                         <a class="close-link">
                             <i class="fa fa-times"></i>
                         </a> -->
                     </div>
                 </div>
                 <div class="ibox-content">
                    <div class="table-responsive">
					<table id="brandListData" class="table table-striped">
						<thead>
							<tr>
								<th  scope="col">ID</th>
								<th scope="col">Brand Name</th>
							</tr>
						</thead>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		
		var wrapperChildren = $("#side-menu").children();
		for (var i = 0; i < wrapperChildren.length; i++) {
		    $(wrapperChildren[i]).removeClass('active');
		}
		
		
		
		//simpleDataCall("${pageContext.request.contextPath}/ajax/brands", title, $content, requestData, responseCallback)
		$('#brandListData').DataTable();
	});
	
</script>