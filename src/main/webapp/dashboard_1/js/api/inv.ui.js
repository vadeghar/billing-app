/*
 *
 *   AMI | Operations Center - UI API
 *   version 1.0
 *
 */

/*
 * UI - TouchSpin
 */
function initTouchSpin() {
	$('.touchspin3').TouchSpin({
		verticalbuttons: true,
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});
}



/*
 * UI - CheckBoxes
 */
function initCheckboxes() {
	$('.i-checks').iCheck({
		checkboxClass: 'icheckbox_square-green',
		radioClass: 'iradio_square-green',
	});
}
function updateCheckboxes() {
	$('#i-checks-all').on('ifToggled', function(e) {
		var chToggle = $(this).is(':checked') ? "check" : "uncheck";
		$('.i-checks-table:not(#i-checks-all)').iCheck(chToggle);
	});	
}
function clearCheckboxes() {
	$('.i-checks:checked').iCheck('uncheck');
}



/*
 * UI - DateRange
 */
function initDateRangePicker() {
	$('input[data-search-calendar]').daterangepicker({
		alwaysShowCalendars: true,
		showCustomRangeLabel: false,
		opens: 'center',
		startDate: moment(),
		endDate: moment(),
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()]
		}
	}).data('daterangepicker');
	$('input[data-search-calendar] + div.input-group-addon').on('click', function() {
		$('input[data-search-calendar]').focus();
	});
}
function initDateTimePicker() {
	$('input[data-search-time]').datetimepicker({
		sideBySide: true
	});
	$('input[data-search-time] + div.input-group-addon').on('click', function() {
		$('input[data-search-time]').focus();
	});
}
function initDatePicker(dpId, dpDate) {
	$(dpId).daterangepicker({
		singleDatePicker: true,
		showCustomRangeLabel: false,
		startDate: dpDate,
		endDate: dpDate
	}).data('daterangepicker');
	$(dpId + ' + div.input-group-addon').on('click', function() {
		$(dpId).focus();
	});
}
function createOrUpdateDatePicker(dpId, dpDate) {
	if ($(dpId).data('daterangepicker') != null) {
		updateDatePicker(dpId, dpDate);
	} else {
		initDatePicker(dpId, dpDate);
	}
}
function updateDatePicker(dpId, dpDate) {
	$(dpId).data('daterangepicker').setStartDate(dpDate);
	$(dpId).data('daterangepicker').setEndDate(dpDate);
}



/*
 * UI - DataBox
 */
function initDataBox() {
	initDataBoxWithCallback(null);
}
function initDataBoxWithCallback(responseCallback) {
	$('div.aoc-topbox').each(function(i, div) {
		$(div).find('a.aoc-topbox-info:first')
		.each(function(j, a) {
			dataBoxLoad($(div), $(a), responseCallback);
		})
		.on('click', function(e) {
			var _obj = $(e.target).closest('a.aoc-topbox-info');
			dataBoxFilterUpdate(_obj);
			dataBoxLoad($(div), _obj, responseCallback);
		});
	});
}
function dataBoxLoad($box, $obj, responseCallback) {
	var _url = $obj.data('box-url');
	var _params = $obj.data('box-params');
	var _content = $box.find('.aoc-topbox-content:first');
	var _contentValue = $box.find('.aoc-topbox-value:first');
	simpleDataBoxCall(_url, $box, $(_content), $(_contentValue), simpleRequestDataWithCalendar(_params), responseCallback);
}
function dataBoxFilterUpdate($obj) {
	var _params = $obj.data('box-params');
	$('input[data-search-criteria],select[data-search-criteria]').each(function(k, input) {
		$(input).val("");
	});
	$('input[data-search-calendar]').data('daterangepicker').setStartDate(moment());
	$('input[data-search-calendar]').data('daterangepicker').setEndDate(moment());
	$('input[data-search-calendar]').removeClass('animated flash');
	$.each(_params.split('&'), function(i, urlParam) {
		var _paramData = urlParam.split('=');
		$('#' + _paramData[0]).val($.trim(_paramData[1]));
		$('#' + _paramData[0]).removeClass('animated flash');
		window.setTimeout(function() {
			$('#' + _paramData[0]).toggleClass('animated flash');
		}, _AOC_FAST_TIMEOUT);
	});
	window.setTimeout(function() {
		$('input[data-search-calendar]').toggleClass('animated flash');
	}, _AOC_FAST_TIMEOUT);
	$('input[data-search-calendar]').trigger('apply.daterangepicker');
}



/*
 * UI - DataLabel
 */
function initDataLabel() {
	$('div.aoc-toplabel').each(function(i, div) {
		dataLabelLoad($(div));
	});
}
function dataLabelLoad($label) {
	var _url = $label.data('label-url');
	var _params = $label.data('label-params');
	var _contentValue = $label.find('.aoc-toplabel-value:first');
	simpleDataBoxCall(_url, $label, $label, $(_contentValue), parseQueryParameters(_params), null);
}



/*
 * UI - DataWidget
 */
function initDataWidget(conditionCallback) {
	$('div.aoc-widget').each(function(i, div) {
		dataWidgetLoad($(div), conditionCallback);
		$(div).closest('a.aoc-widget-info').on('click', function(e) {
			dataWidgetFilterUpdate($(div));
			dataWidgetLoad($(div), conditionCallback);
		});
	});
}
function dataWidgetLoad($widget, conditionCallback) {
	var _url = $widget.data('widget-url');
	var _params = $widget.data('widget-params');
	var _successClass = $widget.data('widget-success');
	var _failureClass = $widget.data('widget-failure');
	var _animateClass = $widget.data('animate-class');
	var _contentValue = $widget.find('.aoc-widget-value:first');
	simpleDataBoxCall(_url, $widget, $widget, $(_contentValue), parseQueryParameters(_params), function($widget, data) {
		$(_contentValue).text(parseTextToNumber(data));
		dataWidgetResponseCallback($widget, _successClass, _failureClass, _animateClass, conditionCallback(data));
	});
}
function dataWidgetResponseCallback($widget, successClass, failureClass, animateClass, condition) {
	var _class = (condition ? successClass : failureClass); 
	$widget.removeClass(successClass).removeClass(failureClass).addClass(_class);
	if (animateClass) {
		$widget.removeClass(animateClass);
		window.setTimeout(function() {
			$widget.toggleClass(animateClass);
		}, _AOC_FAST_TIMEOUT);
	}
}
function dataWidgetFilterUpdate($widget) {
	var _params = $widget.data('widget-params');
	$('input[data-search-criteria],input[data-search-calendar],select[data-search-criteria]').each(function(k, input) {
		$(input).val('');
	});
	$('input[data-search-calendar]').removeClass('animated flash');
	$.each(_params.split('&'), function(i, urlParam) {
		var _paramData = urlParam.split('=');
		$('#' + _paramData[0]).val($.trim(_paramData[1]));
		$('#' + _paramData[0]).removeClass('animated flash');
		window.setTimeout(function() {
			$('#' + _paramData[0]).toggleClass('animated flash');
		}, _AOC_FAST_TIMEOUT);
	});
	window.setTimeout(function() {
		$('input[data-search-calendar]').toggleClass('animated flash');
	}, _AOC_FAST_TIMEOUT);
	$('input[data-search-calendar]').trigger('apply.daterangepicker');
}



/*
 * UI - Simple DataBox
 */
function updateSimpleDataBoxes(url, title, responseCallback, beforeSendCallback) {
	$.ajax({
		url: url,
		headers: _headers,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(simpleFormRequestData()),
		beforeSend: function() {
			beforeSendCallback();
			clearSimpleDataBoxes();			
			toastr.clear();
			toastr.success(_AOC_INFO_LOAD_IN_PROGRESS, title);
			$('div.aoc-topbox').each(function(i, div) {
				var _content = $(div).find('.aoc-topbox-content:first');
				if (!_content.hasClass('sk-loading')) {
					_content.addClass('sk-loading');
				}
			});
		},
		success: function(data) {
			if (data != null) {
				responseCallback(data);
			}
			$('div.aoc-topbox .aoc-topbox-value').each(function() {
				$(this).removeClass('aoc-value-empty').addClass('aoc-value-na');
				$(this).text(parseTextToEmptyNumber(data[this.id]));
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			restoreSimpleDataBoxes();
			handleError(jqXHR, textStatus, errorThrown);
		},
		complete: function() {
			$('div.aoc-topbox').each(function(i, div) {
				var _content = $(div).find('.aoc-topbox-content:first');
				window.setTimeout(function() {
					_content.toggleClass('sk-loading');
				}, _AOC_FAST_TIMEOUT);
			});
		}
	});
}
function restoreSimpleDataBoxes() {
	$('div.aoc-topbox .aoc-topbox-value').each(function() {
		$(this).removeClass('aoc-value-empty').addClass('aoc-value-na');
		$(this).text('');
	});
}
function clearSimpleDataBoxes() {
	$('div.aoc-topbox .aoc-topbox-value').each(function() {
		$(this).removeClass('aoc-value-na').addClass('aoc-value-empty');
		$(this).text('');
	});
}



/*
 * UI - DataTable
 */
function initDataTableNoControl(table, box, rId, tableColumns) {
	initDataTableWithOptions(table, box, rId, tableColumns, {
		responsive: {
			details: {
				type: 'inline'
			}
		},
		columnDefs: [{
			targets: 'no-sort',
			orderable: false
		}]
	});
}
function initDataTable(table, box, rId, tableColumns) {
	initDataTableWithOptions(table, box, rId, tableColumns, {
		responsive: {
			details: {
				type: 'column',
				target: 1
			}
		},
		columnDefs: [{
			targets: 'no-sort',
			orderable: false
		},
		{
			className: 'control',
			orderable: false,
			targets: 1
		}]
	});
}
function initDataTableWithOptions(table, box, rId, tableColumns, options) {
	var _url = $(table).data('table-url');
	var _title = $(table).data('table-title');
	var _icon = $(table).data('table-icon');
	var _content = $(box).children('.ibox-content');
	var _emptyTable = $.fn.dataTable.defaults.language.emptyTable.replace(/_AOC_ICON_/g, _icon);
	var _opt = $.extend(true, options, {
			language: {
				emptyTable: _emptyTable 
			},
			columns: tableColumns,
			rowId: rId,
			drawCallback: function(settings) {
				initCheckboxes();
			},
			ajax: function (data, callback, settings) {
				var _params = {};
				if (_dt) {
					_params.page = _dt.page.info().page;
				}
				if (data && data.order && data.order.length > 0) {
					_params.sort = data.columns[data.order[0].column].data + ',' + data.order[0].dir;
				}
			}
	});
	var _dt = $(table).DataTable(_opt);
	dataTableRefresh(table);
}
function dataTableRefresh(table) {
	$('input[data-search-criteria],select[data-search-criteria]').change(function() {
		$(table).DataTable().ajax.reload();
	});
	$('input[data-search-calendar]').on('apply.daterangepicker', function(ev, picker) {
		$(table).DataTable().ajax.reload();
	});
}
function dataTableCheckboxRender(data, type, row, meta) {
	return '<input type="checkbox" class="i-checks i-checks-table" value="' + row[meta.settings.rowId] + '">';
}
function dataTableNARender(data, type, row, meta) {
	return (data || 'N/A');
}
function dataTableLabelRender(data, type, row, meta) {
	return '<span class="label aoc-label-' + data + '">' + (data || 'Unknown') + '</span>';
}
function dataTableCalendarRender(data, type, row, meta) {
	return (data ? data + ' <i class="fa fa-calendar"></i>' : '');
}
function dataTableClockRender(data, type, row, meta) {
	return (data ? data + ' <i class="fa fa-clock-o"></i>' : '');
}
function dataTableUserRender(data, type, row, meta) {
	return (data ? data + ' <i class="fa fa-user-circle"></i>' : '');
}



/*
 * UI - Maps
 */
function initMap(mapId) {
	var _map = new google.maps.Map(document.getElementById(mapId), {
        zoom: 12,
        center: {lat:45.5231, lng:-122.6765},
		mapTypeControl: false,
		streetViewControl: true,
        styles: [{"featureType":"water","stylers":[{"saturation":43},{"lightness":-11},{"hue":"#0088ff"}]},{"featureType":"road","elementType":"geometry.fill","stylers":[{"hue":"#ff0000"},{"saturation":-100},{"lightness":99}]},{"featureType":"road","elementType":"geometry.stroke","stylers":[{"color":"#808080"},{"lightness":54}]},{"featureType":"landscape.man_made","elementType":"geometry.fill","stylers":[{"color":"#ece2d9"}]},{"featureType":"poi.park","elementType":"geometry.fill","stylers":[{"color":"#ccdca1"}]},{"featureType":"road","elementType":"labels.text.fill","stylers":[{"color":"#767676"}]},{"featureType":"road","elementType":"labels.text.stroke","stylers":[{"color":"#ffffff"}]},{"featureType":"poi","stylers":[{"visibility":"off"}]},{"featureType":"landscape.natural","elementType":"geometry.fill","stylers":[{"visibility":"on"},{"color":"#b8cb93"}]},{"featureType":"poi.park","stylers":[{"visibility":"on"}]},{"featureType":"poi.sports_complex","stylers":[{"visibility":"on"}]},{"featureType":"poi.medical","stylers":[{"visibility":"on"}]},{"featureType":"poi.business","stylers":[{"visibility":"simplified"}]}]
	});
	return _map;
}



/*
 * UI - Buttons
 */
function initProgressButton(frmId, btnId, requestDataCallback, responseCallback, autoCallback) {
	var _title = $(btnId).data('submit-title');
	var _url = $(btnId).data('submit-url');
	var _input = $('input[data-submit-input]');
	var _content = $('[data-form-content]');
	var _animate = $('[data-form-animate]');
	var _inst = Ladda.create(document.querySelector(btnId));
	$(frmId).on('submit', function(e) {
		e.preventDefault();
		_content.toggleClass('sk-loading');
		_animate.toggleClass('flipInX');
		_input.prop('disabled', true);
		_inst.start();
		toastr.clear();
		toastr.success(_AOC_INFO_IN_PROGRESS, _title);
		$.ajax({
			url: _url,
			headers: _headers,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(requestDataCallback()),
			beforeSend: function() {
				progressButtonClear();
			},
			success: function(data) {
				progressButtonCallback(data);
				responseCallback(data);
				toastr.clear();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				handleError(jqXHR, textStatus, errorThrown);
			},
			complete: function() {
				_content.toggleClass('sk-loading');
				_animate.toggleClass('flipInX');
				_input.prop('disabled', false);
				_input.focus();
				_inst.stop();
			}
		});
	});
	if (autoCallback) {
		if ($.trim(_input.val()) != '') {
			$(btnId).click();
		}
	}
}
function progressButtonClear() {
	$('[data-form-value]').each(function(i, obj) {
		$(obj).text('');
	});
	$('[data-form-label]').each(function(i, obj) {
		$(obj).find('span.label:first').each(function(j, span) {
			$(span).text('');
			$(span).removeClass().toggleClass('label aoc-label');
		});
	});
	$('[data-form-hidden]').each(function(i, obj) {
		$(obj).closest('div').addClass('hidden');
	});
}
function progressButtonCallback(data) {
	$('[data-form-value]').each(function(i, obj) {
		$(obj).text($.trim(data[obj.id]));
	});
	$('[data-form-label]').each(function(i, obj) {
		$(obj).find('span.label:first').each(function(j, span) {
			if ($.trim(data[obj.id])) {
				$(span).text(data[obj.id]);
				$(span).toggleClass('aoc-label-' + data[obj.id]);
			}
		});
	});
	$('[data-form-hidden]').each(function(i, obj) {
		if ($.trim(data[obj.id])) {
			$(obj).closest('div').removeClass('hidden');
		}
	});
}



/*
 * UI - Modal Dialogs
 */
function initModalDialog(modalDialog, table, preload) {
	initModalDialogWithOptions(modalDialog, table, preload);
}
function initSimpleModalDialog(modalDialog) {
	initModalDialogWithOptions(modalDialog, null, false);
}
function initModalDialogWithOptions(modalDialog, table, preload) {
	var _title = $(modalDialog).data('modal-title');
	var _url = $(modalDialog).data('modal-url');
	if (preload) {
		$(modalDialog).on('show.bs.modal', function(e) {
			modalDialogPreloadCallback(modalDialog, table, $(e.relatedTarget).data('row'));
		});
	} else {
		$('a[data-modal-dialog="' + modalDialog + '"]').on('click', function() {
			if (!modalDialogPreValidation()) {
				toastr.clear();
				toastr.error(_AOC_ERR_SELECT_RECORD, 'Error');
				return;
			}
			$(modalDialog).find('[data-modal-focus]').val('');
			$(modalDialog).modal('show');
		});
	}
	$(modalDialog).on('shown.bs.modal', function() {
		toastr.clear();
		$(modalDialog).find('[data-modal-focus]').focus();
	});
	$(modalDialog + ' form').on('submit', function(e) {
		e.preventDefault();
		toastr.clear();
		$.ajax({
			url: _url,
			headers: _headers,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(modalDialogDataCallback(modalDialog, table, preload)),
			beforeSend: function() {
				toastr.clear();
				toastr.info(_AOC_INFO_IN_PROGRESS, _title);
				$(modalDialog).find('.modal-content').toggleClass('sk-loading');
			},
			success: function(data) {
				toastr.success(_AOC_PROCESSED_SUCCESSFULLY, _title);
				$(modalDialog).modal('hide');
				if (table != null) {
					$(table).DataTable().ajax.reload();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				handleError(jqXHR, textStatus, errorThrown);
				$(modalDialog).find('[data-modal-focus]').focus();
			},
			complete: function() {
				$(modalDialog).find('.modal-content').toggleClass('sk-loading');
			}
		});
	});
}
function modalDialogPreValidation() {
	return ($('.i-checks-table:not(#i-checks-all):checked').length > 0);
}
function modalDialogPreloadCallback(modalDialog, table, rowId) {
	var _row = $(table).DataTable().row('#' + rowId).data();
	if (_row) {
		$(modalDialog).find('[data-modal-property]').each(function() {
			$(this).val($.trim(_row[$(this).data('modal-property')]));
		});
		$(modalDialog).find('[data-modal-label]').each(function() {
			var _label = $.trim(_row[$(this).data('modal-label')]);
			if (_label != "") {
				$(this).text(_label);
			} else {
				$(this).text('Unknown');
			}
			$(this).removeClass().addClass('label aoc-label-' + _label);
		});
	}
}
function modalDialogDataCallback(modalDialog, table, preload) {
	var _modalData = {};
	var _requestData = [];
	$(modalDialog).find('input[data-modal-property]').each(function() {
		_modalData[$(this).data('modal-property')] = $.trim($(this).val());
	});
	if (preload || (!preload && table == null)) {
		_requestData.push(_modalData);
	} else {
		$('.i-checks-table:not(#i-checks-all):checked').each(function(i, chk) {
			var _rowId = $(chk).val();
			var _row = $(table).DataTable().row('#' + _rowId).data();
			_requestData.push($.extend({}, _row, _modalData));
		});
	}
	return _requestData;
}



/*
 * UI - Dynamic Dropdown
 */
function initDynamicDropdown(drpId, textProperty, valueProperty, dropdownChangeCallback, dropdownClearCallback) {
	var _title = $(drpId).data('load-title');
	var _url = $(drpId).data('load-url');
	var _form = $(drpId).closest('.ibox-content');
	var _btn = drpId + ' + div.input-group-addon';
	$(_btn).on('click', function() {
		$.ajax({
			url: _url,
			headers: _headers,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(simpleFormRequestDataInputOnly()),
			beforeSend: function() {
				$(drpId).empty();
				$(drpId).prop('disabled', true);
				_form.toggleClass('sk-loading');
				dropdownClearCallback();
				toastr.clear();
				toastr.success(_AOC_INFO_LOAD_IN_PROGRESS, _title);
			},
			success: function(data) {
				if (data != null && data.length > 0) {
					$.each(data, function(i, item) {
						$(drpId).append($('<option>', {
							value: item[valueProperty],
							text: item[textProperty],
							data: item
						}));
					});
					$(drpId).change();
				} else {
					toastr.error(_AOC_INFO_NOT_FOUND, _title);
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				dropdownClearCallback();
				handleError(jqXHR, textStatus, errorThrown);
			},
			complete: function() {
				$(drpId).prop('disabled', false);
				_form.toggleClass('sk-loading');
			}
		});
	});
	$(drpId).change(function() {
		dropdownChangeCallback();
	});
}



/*
 * UI - Export
 */
function initExportLinks() {
	$('a.aoc-export').on('click', function() {
		var _url = $(this).data('export-url');
		var _title = $(this).data('export-title');
		$.ajax({
			url: _url,
			headers: _headers,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(simpleRequestDataAll()),
			beforeSend: function() {
				toastr.clear();
				toastr.success(_AOC_INFO_EXPORTING, _title);
			},
			success: function(json, textStatus, jqXHR) {
				var _data = convertBase64ToArrayBuffer(json.encodedData);
				var _blob = new Blob([_data], {type: json.mediaType})
				if (window.navigator && window.navigator.msSaveOrOpenBlob) {
				    window.navigator.msSaveOrOpenBlob(_blob, json.filename);
				} else {
					var _url = window.URL.createObjectURL(_blob);
					var _a = $('<a class="hidden" href="' + _url + '" download="' + json.filename + '"/>');
					_a[0].click();
					window.URL.revokeObjectURL(_url);
					_a.remove();
				}				
				toastr.clear();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				handleError(jqXHR, textStatus, errorThrown);
			}
		});
	});
}