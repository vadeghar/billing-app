<!-- Placed at the end of the document so the pages load faster --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/static/js/moment.js" type="text/javascript" ></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script> 
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug --> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>




<footer class="footer">
  <div class="container">
	<p><strong>Copyright</strong> MyBill &copy; 2017-2018</p>
  </div>
</footer>

<script>
	// set default to straight lines - no curves
	// Chart.defaults.global.elements.line.tension = 0;
	// set default no fill beneath the line
	Chart.defaults.global.elements.line.fill = false;

	// stacked bar with 2 unstacked lines - nope
	var barChartData = {
	  labels: ['Feb 2017', 'Mar 2017', 'Apr 2017', 'May 2017', 'Jun 2017', 'Jul 2017', 'Aug 2017', 'Sep 2017', 'Oct 2017', 'Nov 2017'],
	  datasets: [{
		type: 'bar',
		label: 'Months',
		id: "y-axis-0",
		backgroundColor: "rgba(0,100,200,0.75)",
		data: ['590','698','676','528','731','860','1057','1173','844','0']
	  },{
		type: 'line',
		label: 'KWh',
		id: "y-axis-0",
		backgroundColor: "rgba(51,51,51,0.5)",
		data: [590,698,676,528,731,860,1057,1173,844,0]
	  },{
		label: '°F',
		yAxisID: 'y-axis-1',
		data: ['0', '10', '20', '30', '40', '50', '60', '70', '80', '90']
	  }]
	};

	var ctx = document.getElementById("myChart");
	// allocate and initialize a chart
	var ch = new Chart(ctx, {
	  type: 'bar',
	  data: barChartData,
	  options: {
		title: {
		  display: true,
		  text: "Usage History Graph"
		},
		tooltips: {
		  mode: 'label'
		},
		responsive: true,
		scales: {
		  xAxes: [{
			stacked: true
		  }],
		  yAxes: [{
			stacked: true,
			position: "left",
			id: "y-axis-0",
			scaleLabel: {
				display: true,
				labelString: "KWh (Kilowatt hour)"
			  }
		  }, {
			stacked: false,
			position: "right",
			id: "y-axis-1",
			scaleLabel: {
				display: true,
				labelString: "°F (Fahrenheit )"
			  }
		  }]
		}
	  }
	});
</script>
<script>
$(document).ready(function(){
	var msg = document.getElementById("message").innerHTML;
    if(msg != '') {
    	var iconType = 'success';
    	if(msg.indexOf('Error') != -1)
    		iconType = "warning";
    	swal(msg, {
		      icon: iconType,
		    });
    }
	    
	//Show Targeted Address Details
	$(function() {
	  $('#agreeSelect').change(function(){
		$('.tabs-wrapper').hide();
		$('#' + $(this).val()).show();
	  });
	});
	
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();   
	});
	
	$(".add-row").click(function(){
		var effectiveDate = $("#effectiveDate").val();
		var expirationDate = $("#expirationDate").val();
		var voucherAmount = $("#voucherAmount").val();
		var voucherNumber = $("#voucherNumber").val();
		var statusSelect = $("#statusSelect").val();
		var firstName = $("#firstName").val();
		var lastName = $("#lastName").val();
		var markup = "<tr><td>" + effectiveDate + "</td><td>" + expirationDate + "</td><td>" + voucherAmount + "</td><td>" + voucherNumber + "</td><td>" + firstName + lastName + "</td><td>" + statusSelect + "</td></tr>";
		$("#pledgeTable tbody").append(markup);
	});
    $(".btn-warning").click(function(){
        $(".targetTab").addClass("active");
		$(".targetTab2").removeClass("active");
    });	
	$("#reset").click(function(){
	  document.location.reload(true);
	});	
}); 
</script>
<script>
$(document).ready(function() {
    if (window.location.hash != "") {
        $('a[href="' + window.location.hash + '"]').click()
    }
});
jQuery(window).load(function() {
    function goToByScroll(id) {
        jQuery("html, body").animate({
            scrollTop: jQuery("#" + id).offset().top - 250
        }, 1000);
    }
    if (window.location.hash != '') {
        goToByScroll(window.location.hash.substr(1));
    }
});
</script>
