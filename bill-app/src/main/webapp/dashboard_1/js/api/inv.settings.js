/*
 *
 *   AMI | Operations Center - Settings
 *   version 1.0
 *
 */

// HTTP headers
var _headers = {};
var _tc = $("meta[name='_csrf']").attr("content");
var _hc = $("meta[name='_csrf_header']").attr("content");
_headers[_hc] = _tc;

// Date Format
var _AOC_DISPLAY_DATE_FORMAT = 'MM/DD/YYYY';
var _AOC_DATE_FORMAT = 'YYYY-MM-DD';
var _AOC_TIME00_FORMAT = 'T00:00:00';
var _AOC_TIME24_FORMAT = 'T23:59:59';

// Timeout
var _AOC_TIMEOUT = 500;
var _AOC_FAST_TIMEOUT = 100;
var _AOC_LONG_TIMEOUT = 1000;

// Map
var _AOC_MAP_ZOOM = 15;

// Messages
var _AOC_ERR_TIMEOUT = 'Sorry, this request takes too long to process...';
var _AOC_ERR_COMMON = 'Oops! Something went wrong...';
var _AOC_ERR_SELECT_RECORD = 'Please select at least one record.';
var _AOC_INFO_LOADING = 'Loading data...';
var _AOC_INFO_LOAD_IN_PROGRESS = 'Load operation is in progress...';
var _AOC_INFO_IN_PROGRESS = 'Operation is in progress...';
var _AOC_INFO_NOT_FOUND = 'No matching records found...';
var _AOC_INFO_EXPORTING = 'Exporting data...';
var _AOC_PROCESSED_SUCCESSFULLY = 'Changes were successfully saved...';

var $toastlast;

$(document).ready(function() {
	
	// Notification
	toastr.options = {
			maxOpened: 1,
			closeButton: true,
			progressBar: true,
			newestOnTop: true,
			timeOut: 30000,
			hideDuration: 3000,
			showEasing: "swing",
			hideEasing: "linear",
			showMethod: "fadeIn",
			hideMethod: "fadeOut"
	};
	
	// DataTable
	$.fn.DataTable.ext.pager.numbers_length = 5;
	$.extend(true, $.fn.dataTable.defaults, {
		serverSide: false,
		paging: true,
		processing: false,
		lengthChange: false,
		searching: false,
		order: [],
		pageLength: 10,
		pagingType: 'full_numbers',
		language: {
			info: '<strong>Showing</strong> _START_ &ndash; _END_ of _TOTAL_ entries',
			infoEmpty: '<strong>Showing</strong> 0 entries',
			infoFiltered: '',
			emptyTable: '<div class="m-md"><p><i class="fa fa-3x _AOC_ICON_" aria-hidden="true"></i></p><p>No records found</p></div>'
		}
	});
	
	// Tooltip
	$('[data-toggle="tooltip"]').tooltip({
		trigger: 'hover'
	});
	
});
