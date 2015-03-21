(function($) {
	$.fn.table = function(options) {
		options = options || {};
		new Table(this, options);
	};
	
	function Table(element, options) {
		var mySelf = this;
		mySelf.table = jQuery(element);
		mySelf.options = jQuery.extend({}, Table.DEFAULTS, options || {});
		
		// 给表格元素添加（CSS）类
		mySelf.table.addClass("stylized");
		// 添加行交替色
		mySelf.setRowColor();
		// 设置表格宽度
		mySelf.table.css("width", mySelf.options.tableWidth);
		// 设置行高
		mySelf.table.children("tbody").children("tr").each(function() {
			var tr = jQuery(this);
			tr.css("height", mySelf.options.rowHeight);
			tr.click(function() {
				mySelf.clearHighAll();
				$(this).addClass('high');
			});
		});
		
	}
	
	Table.EF = function() {};
	
	jQuery.extend(Table, {
		DEFAULTS: {
			rowColor:   true,   // 是否显示行交替色
			rowHeight:  35,     // 默认行高
			tableWidth: "100%"  // 默认表格宽
		}
	});
	
	Table.prototype = {
		// 设置行交替色
		setRowColor: function() {
			if (this.options.rowColor) {
				this.table.children("tbody tr:even").css("background-color", "#fbfcff");
			}
		},
		
		clearHighAll : function() {
			this.table.children('tbody').children('tr').each(function() {
				jQuery(this).removeClass('high');
			});
		}
	};
})(jQuery);