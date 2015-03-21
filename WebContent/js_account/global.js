$.ajaxSetup({cache: false});

$.fn.overflow = function(options) {
	options = options || {};
	var defaults = {length:15};
	options = jQuery.extend({}, defaults, options);
	
	$(this).each(function() {
		var _self = $(this);
		if (_self.text().length > options.length) {
			if (_self.text().substring(_self.text().length - 3, _self.text().length) != '...') {
				_self.attr("title", _self.text());
				var str = jQuery.trim(_self.text()).substring(0, options.length);
				_self.text(str+"...");
			}
		}
	});
};

jQuery.extend(Global, {
	LOADING : '<div class="loading"></div>',
	
	/**
	 * 检查是否登入
	 * @return {TypeName} 
	 */
	checkLogin : function() {
		var userIdUrl = Global.URI + Global.contextPath + "/userIdOnLine.html";
		var options = {
			url : userIdUrl,
			async:false,
			cache : false
		};
		var userObj = jQuery.ajax(options);
		var userId = userObj.responseText;
		if (!userId || userId == 'null' || userId == '') {
			Dgbox.confirm('亲，你还没有登入，请先登入！', function() {document.location.href=Global.URI+Global.contextPath+'/login.html'}, {title : '提示信息'});
			return false;
		}
		return true;
	},
	
	/**
	 * 获取商品单品信息
	 * @param {Object} url
	 */
	getItemBean : function(url) {
		url = jQuery.trim(url);
		if (url == '') {
			return;
		}
		var option = {
			type : 'get',
			dataType : 'json',
			url : Global.URI + Global.contextPath + '/itemBean.html',
			data : {'url' : url},
			async:false,
			success : function(d) {
				if (d.result == 1) {
					Global.setProInfo(d);
				} else {
					Dgbox.alert(d.msg, null, {title:'提示信息'});
				}
			}
		};
		jQuery.ajax(option);
	},
	
	setProInfo : function(d) {
		var productAddForm = $('#productAddForm');
		var proPicture = jQuery('#proPicture', productAddForm);
		proPicture.val(jQuery.trim(d.picUrl));
		var orgPrice = jQuery('#orgPrice', productAddForm);
		orgPrice.val(jQuery.trim(d.price));
		var proUrl = jQuery('#proUrl', productAddForm);
		proUrl.val(jQuery.trim(d.detailUrl));
		var numIid = jQuery('#numIid', productAddForm);
		numIid.val(jQuery.trim(d.numIid));
		var proName = jQuery('#proName', productAddForm);
		proName.val(jQuery.trim(d.proName));
		var flag = d.isExistPro;
		if (flag) {
			Dgbox.alert('该商品信息您已经发布！', null, {title:'提示信息'});
		}
	},
	
	showValidateCode : function(img) {
		img.src = Global.contextPath + "/showValidateCode.html?t="+Math.random();
	}
});

function toTop() {
	var first_top = $("#mainBody").offset().top;
	move_scoll(first_top);
}

function move_scoll(first_top, speed) {
	if (!speed)
		speed = 500;
	$('html, body').stop();
	$('html, body').animate( {
		scrollTop : first_top
	}, speed);
	return false;
}

function addfavorite() {
	title = document.title;
	url = window.location.href;
	if (window.sidebar) {
		window.sidebar.addPanel(title, url, '');
	} else {
		try {
			window.external.AddFavorite(url, title);
		} catch (e) {
			alert("您的浏览器不支持该功能,请使用Ctrl+D收藏本页");
		}
	}
}