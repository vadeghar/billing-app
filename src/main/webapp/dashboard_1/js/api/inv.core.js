/*
 *
 *   AMI | Operations Center - Core API
 *   version 1.0
 *
 */

/*
 * Core - Parsing, Converting
 */
function parseTextToNumber(text) {
	var _num = parseFloat(text);
	return (!isNaN(_num) ? _num.toLocaleString() : (text || 'N/A'));
}
function parseTextToEmptyNumber(text) {
	var _num = parseFloat(text);
	return (!isNaN(_num) ? _num.toLocaleString() : (text || ''));
}
function parseTextToNullNumber(text) {
	var _num = parseFloat(text);
	return (!isNaN(_num) && _num > 0 ? _num.toLocaleString() : null);
}
function parseTextToDate(text, format) {
	var _m = moment(text, format);
	return (_m.isValid() ? _m : moment());
}
function parseQueryParameters(params) {
	var _data = {};
	if (params) {
		$.each(params.split('&'), function(i, urlParam) {
			var _paramData = urlParam.split('=');
			_data[_paramData[0]] = $.trim(_paramData[1]);
		});
	}
	return _data;
}
function convertBase64ToArrayBuffer(data) {
	var _binary = window.atob(data);
	var _length = _binary.length;
	var _bytes = new Uint8Array(_length);
	for (var i = 0; i < _length; i++) {
		_bytes[i] = _binary.charCodeAt(i);
	}
	return _bytes;
}



/*
 * Core - Error Handling
 */
function handleSuccess(jqXHR, textStatus) {
	if (textStatus === "success") {
		window.setTimeout(function() {
			toastr.clear();
		}, _AOC_LONG_TIMEOUT);
	}
}
function handleError(jqXHR, textStatus, errorThrown) {
	toastr.clear();
	if (jqXHR.status === 403) {
		toastr.error(jqXHR.responseJSON.message + '<div class="m-t-xs"><button type="button" id="reauthenticate" class="btn btn-danger-inverse btn-xs">Reauthenticate</button></div>', 'Forbidden');
		$('#reauthenticate').on('click', function() {
			location.reload();
		});
	} else if (textStatus === "timeout") {
		toastr.error(_AOC_ERR_TIMEOUT, 'Error');
	} else if (jqXHR.responseJSON) {
		toastr.error(jqXHR.responseJSON.message, 'Error');
	} else {
		toastr.error(_AOC_ERR_COMMON, 'Error');
	}
}



/*
 * Core - Request URL
 */
function prepareURL(url, params) {
	return ($.isEmptyObject(params) ? url : url + '?' + $.param(params));
}



/*
 * Core - Request Data
 */
function simpleRequestDataAll() {
	var _requestData = {};
	var _calendar = $('input[data-search-calendar]');
	if ($.trim(_calendar.val())) {
		if (_calendar.data('include-time')) {
			_requestData['startDate'] = _calendar.data('daterangepicker').startDate.format(_AOC_DATE_FORMAT) + _AOC_TIME00_FORMAT;
			_requestData['endDate'] = _calendar.data('daterangepicker').endDate.format(_AOC_DATE_FORMAT) + _AOC_TIME24_FORMAT;
		} else {
			_requestData['startDate'] = _calendar.data('daterangepicker').startDate.format(_AOC_DATE_FORMAT);
			_requestData['endDate'] = _calendar.data('daterangepicker').endDate.format(_AOC_DATE_FORMAT);
		}
	}
	$('input[data-search-criteria],select[data-search-criteria]').each(function() {
		_requestData[this.id] = $.trim($(this).val());
	});
	return _requestData;
}
function simpleRequestDataWithCalendar(params) {
	var _requestData = parseQueryParameters(params);
	var _calendar = $('input[data-search-calendar]');
	if (_calendar.data('include-time')) {
		_requestData['startDate'] = _calendar.data('daterangepicker').startDate.format(_AOC_DATE_FORMAT) + _AOC_TIME00_FORMAT;
		_requestData['endDate'] = _calendar.data('daterangepicker').endDate.format(_AOC_DATE_FORMAT) + _AOC_TIME24_FORMAT;
	} else {
		_requestData['startDate'] = _calendar.data('daterangepicker').startDate.format(_AOC_DATE_FORMAT);
		_requestData['endDate'] = _calendar.data('daterangepicker').endDate.format(_AOC_DATE_FORMAT);
	}
	return _requestData;
}
function simpleFormRequestDataInputOnly() {
	var _requestData = {};
	$('input[data-search-criteria]').each(function() {
		_requestData[this.id] = $.trim($(this).val());
	});
	return _requestData;
}
function simpleFormRequestData() {
	var _requestData = {};
	$('input[data-search-criteria],select[data-search-criteria]').each(function() {
		_requestData[this.id] = $.trim($(this).val());
	});
	return _requestData;
}



/*
 * Core - Data Calls
 */
function simpleDataBoxCall(url, $box, $content, $value, requestData, responseCallback) {
	var _animateClass = $value.data('animate-class');
	$.ajax({
		url: url,
		headers: _headers,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(requestData),
		beforeSend: function() {
			if (!$content.hasClass('sk-loading')) {
				$content.addClass('sk-loading');
			}
			if (_animateClass) {
				$value.removeClass(_animateClass);
			}
		},
		success: function(data) {
			if (responseCallback) {
				responseCallback($box, data);
			} else {
				$value.text(parseTextToNumber(data));
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			if (responseCallback) {
				responseCallback($box, 'Unavailable');
			} else {
				$value.text('Unavailable');
			}
			handleError(jqXHR, textStatus, errorThrown);
		},
		complete: function() {
			window.setTimeout(function() {
				$content.toggleClass('sk-loading');
				if (_animateClass) {
					$value.addClass(_animateClass);
				}
			}, _AOC_TIMEOUT);
		}
	});
}
function simpleDataCall(url, title, $content, requestData, responseCallback) {
	$.ajax({
		url: url,
		headers: _headers,
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(requestData),
		beforeSend: function() {
			toastr.clear();
			toastr.success(_AOC_INFO_LOAD_IN_PROGRESS, title);
			if (!$content.hasClass('sk-spinner') && !$content.hasClass('sk-spinner-rotating-plane')) {
				$content.addClass('sk-spinner').addClass('sk-spinner-rotating-plane');
			}
		},
		success: function(data) {
			responseCallback(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			responseCallback(null);
			handleError(jqXHR, textStatus, errorThrown);
		},
		complete: function() {
			window.setTimeout(function() {
				$content.toggleClass('sk-spinner').toggleClass('sk-spinner-rotating-plane');
			}, _AOC_TIMEOUT);
		}
	});
}

function simpleGetDataCall(url, title, $content, responseCallback) {
	$.ajax({
		url: url,
		headers: _headers,
		type: 'GET',
		contentType: 'application/json',
		beforeSend: function() {
			toastr.clear();
			toastr.success(_AOC_INFO_LOAD_IN_PROGRESS, title);
			if (!$content.hasClass('sk-loading')) {
				$content.addClass('sk-loading');
			}
		},
		success: function(data) {
			responseCallback(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			responseCallback(null);
			handleError(jqXHR, textStatus, errorThrown);
		},
		complete: function() {
			window.setTimeout(function() {
				$content.toggleClass('sk-loading');
			}, _AOC_TIMEOUT);
		}
	});
}