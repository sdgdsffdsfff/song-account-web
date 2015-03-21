(function($) {
	$.fn.table = function() {
		
		$(this).addClass("layout_table");
		$(this).find("tbody tr:even").addClass("alt");
		$(this).find("tbody tr").click(function() {
			$(this).toggleClass("tr_chouse");
		});
	};
})(jQuery);