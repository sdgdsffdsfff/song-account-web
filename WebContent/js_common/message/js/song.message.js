(function($) {
	jQuery.fn.message = function(options) {
		options = options || {};
		
		var mes = null
		var layer = null;
		jQuery(this).mouseover(function() {
			clearTimeout(layer);
			close();
			mes = new Message(this, options);
		}).mouseout(function() {
			layer = setTimeout(function () {close();},300);
			if (mes != null) {
				mes.message.mouseover(function() {
					clearTimeout(layer);
				}).mouseout(function() {
					close();
				});
			}
		});
		
		function close() {
			if (mes != null) {
				mes.close();
				mes = null;
			}
		}
	};
	
	function Message(currPoint, options) {
		var mySelf = this;
		mySelf.options = jQuery.extend({}, Message.DEFAULTS, options || {});
		
		// 当前标签宽高
		var h = jQuery(currPoint).height();
		var w = jQuery(currPoint).width();
		// 坐标点
		var curOffset = jQuery(currPoint).offset();
		mySelf.message = jQuery(Message.WRAPPER);
		mySelf.message.addClass("message-css");
		mySelf.message.append(mySelf.options.content);
		jQuery("body").prepend(mySelf.message);
		
		switch(mySelf.options.position) {
			case "right" :
				mySelf.message.css("top", curOffset.top);
				mySelf.message.css("left", curOffset.left + w + 10);
				break;
			case "left" :
				mySelf.message.css("top", curOffset.top);
				mySelf.message.css("left", curOffset.left - mySelf.message.width());
				break;
			case "upper" :
				mySelf.message.css("top", curOffset.top - h - 10);
				mySelf.message.css("left", curOffset.left);
				break;
			case "under" :
				mySelf.message.css("top", curOffset.top + h + 10);
				mySelf.message.css("left", curOffset.left);
				break;
		}
	}
	
	Message.EF = function() {};
	
	jQuery.extend(Message, {
		WRAPPER: "<div></div>",
		
		DEFAULTS: {
			position: "right",       // 提示框出现在上（upper）下（under）左（left）右（right）
			isArrow: true,           // 是否有小箭头
			offset: 15,              // 小箭头偏移量
			mouseover: true,         // 鼠标是否可以悬停在消息框上
			closeButton: false,      // 是否有关闭按钮
			onloadShow: false,       // 加载完成是否显示
			cssStyle: 1,             // 默认为第一套样式
			content: ""              // 内容
		}
	});
	
	Message.prototype = {
		close: function() {
			this.message.detach();
		}
	};
})(jQuery);