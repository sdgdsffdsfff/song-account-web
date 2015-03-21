function UserBasicEdit() {

}

jQuery.extend(UserBasicEdit, {
	editUserBasic : function() {
		var userBasicEditForm = $('#userBasicEditForm');
		var errorInfo = jQuery('#errorInfo', userBasicEditForm).css('display', 'none');
		
		// 姓名
		var userName = jQuery('#userName', userBasicEditForm);
		userName.val(jQuery.trim(userName.val()));
		var regExp = /^.+$/;
		if (!regExp.test(userName.val())) {
			errorInfo.css('display', 'block').text('姓名不能为空');
			return;
		}
		
		// QQ号
		var qqId = jQuery('#qqId', userBasicEditForm);
		qqId.val(jQuery.trim(qqId.val()));
		if (!regExp.test(qqId.val())) {
			errorInfo.css('display', 'block').text('QQ号不能为空');
			return;
		}
		
		userBasicEditForm.submit();
	}
})