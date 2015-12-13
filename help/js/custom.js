$(document).ready(function() {
	var table = $('#helptable').DataTable( {
		"sDom": 'flrtip',
		scrollX: 400,
		data: dataSet,
		columns: [
			{ title: "Term" },
			{ title: "File" },
			{ title: "Http Query" }
		],
		fixedHeader: {
			header: true
		}
	} );

	$(("<a class='toggle-vis' data-column='2'>Hide/Show Http Query</a>")).insertBefore('.dataTables_scrollHead');

	$('a.toggle-vis').on( 'click', function (e) {
		e.preventDefault();
		// Get the column API object
		var column = table.column( $(this).attr('data-column') );
		// Toggle the visibility
		column.visible( ! column.visible() );
	} );


	$('.wrapper_fileFilter').insertBefore('.dataTables_scrollHead');
	$('a.filterFile').on( 'click', function () {
		filterColumn( $(this).data('filter') );
	} );

	function filterColumn (s) {
		$('#helptable').DataTable().column( 1 ).search(s).draw();
	}

	//$('.wrapper_validate').hide();
	var regexArray = [];
	$(dataSet).each(function(i, element){
		regexArray[i] = element[0];
	});

	$('input[type="text"]').on('input', function() {
		var o = validateSearchTerms($(this).val()),
			target = $('.validateOutput');

		target.empty();
		if(!jQuery.isEmptyObject(o)) {
			$(o).each(function (i, element) {
				target.append('<div class="row"><div class="col-sm-12">'+element+'</div></div>');
			});
		}
	});

	function validateSearchTerms(s) {
		var match = "",
			check = false,
			termsFound = [],
			reg = new RegExp();

		do {
			check = regexArray.some(function (i) {
				reg = new RegExp('\\b' + i + '\\b', 'i');
				var test = reg.test(s);
				if (test) match = i;
				return test;
			});

			//var su = s.match(i);
			s = s.replace(reg, '');

			if (check) {
				termsFound.push(match);
			}
		} while (check);

		return termsFound
	}
} );