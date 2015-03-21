function Register() {

}

Register.ACCOUT_INFO = '<span class="li-gray-span">英文字符、数字或者“_”组成，长度控制在5-50个字符，不区分大小写</span>';
Register.ACCOUT_INFO2 = '<span class="li-gray-span">检查是否重名中，请稍后...</span>';
Register.NICK_NAME_INFO = '<span class="li-gray-span">长度控制在12个字符以内</span>';
Register.NICK_NAME_INFO2 = '<span class="li-gray-span">检查是否重名中，请稍后...</span>';
Register.PASSWORE_INFO = '<span class="li-gray-span">密码长度6～20位，字母区分大小写</span>';
Register.PASSWORE_FAIL = '<span class="li-fail-span">请输入正确的密码信息</span>';
Register.PASSWORE2_INFO = '<span class="li-gray-span">请再输入一次密码</span>';
Register.PASSWORE2_FAIL = '<span class="li-fail-span">请输入正确的密码信息</span>';
Register.PASSWORE2_FAIL2 = '<span class="li-fail-span">与前一次输入的密码不一致</span>';
Register.AGREEMENT_FAIL = '<span class="li-fail-span">您需要先同意《衣美配服务条款》然后才能完成注册</span>';
Register.OK = '<span class="li-ok-span"></span>';
Register.ACCOUT_FAIL = '<span class="li-fail-span">请输入正确的账号信息</span>';
Register.ACCOUT_FAIL2 = '<span class="li-fail-span">此账户已经被注册了</span>';
Register.NICK_NAME_FAIL = '<span class="li-fail-span">请输入正确的昵称信息</span>';
Register.NICK_NAME_FAIL2 = '<span class="li-fail-span">此昵称已经被注册了</span>';

jQuery.extend(Register, {

	// 判断账号是否已经存在
	isExitAccount : function(account, asyncBol, accountTip) {
		var flag = false;
		//asyncBol = asyncBol == undefined ? true : asyncBol;
		var option = {
			type:"get",
			dataType:'text',
			url: Global.URI + Global.contextPath +"/isExitAccount.html",
			data:{"account":account},
			async:asyncBol,
			success:function(data){
				if (data == "true") {
					flag = true;
					accountTip.empty();	
					accountTip.append(Register.ACCOUT_FAIL2);
				} else {
					accountTip.empty();
					accountTip.append(Register.OK);
				}
			}
		};
		//$.ajaxSettings.async = false;
		$.ajax(option);
		return flag;
	},
	
	// 判断昵称是否存在相同
	isExitNickName : function(nickName, asyncBol, nickNameTip) {
		var flag = false;
		//asyncBol = asyncBol == undefined ? true : asyncBol;
		var option = {
			type:"get",
			dataType:'text',
			url: Global.URI + Global.contextPath +"/isExitNickName.html",
			data:{"nickName":nickName},
			async:asyncBol,
			success:function(data){
				if (data == "true") {
					flag = true;
					nickNameTip.empty();
					nickNameTip.append(Register.NICK_NAME_FAIL2);
				} else {
					nickNameTip.empty();
					nickNameTip.append(Register.OK);
				}
			}
		};
		//$.ajaxSettings.async = false;
		$.ajax(option);
		return flag;
	},
	
	/**
	 * 当账号输入筐获取焦点
	 */
	accountFocus : function(accTxt) {
		accTxt = $(accTxt);
		var accountTip = $('#accountTip');
		accountTip.empty();
		accountTip.append(Register.ACCOUT_INFO);
	},

	/**
	 * 当账号输入筐失去焦点
	 */
	accountBlur : function(accTxt) {
		accTxt = $(accTxt);
		accTxt.val(jQuery.trim(accTxt.val()));
		var accountTip = $('#accountTip');
		accountTip.empty();
		var regExp = /^\w{5,50}$/;
		if (!regExp.test(accTxt.val())) {
			accountTip.append(Register.ACCOUT_FAIL);
			return false;
		}
		
		accountTip.append(Register.ACCOUT_INFO2);
		
		if(Register.isExitAccount(accTxt.val(), false, accountTip)) {
			return false;
		} else {
			return true;
		}
	},
	
	nickNameFocus : function(nicnTxt) {
		var nickNameTip = $('#nickNameTip');
		nickNameTip.empty();
		nickNameTip.append(Register.NICK_NAME_INFO);
	},
	
	nickNameBlur : function(nicnTxt) {
		nicnTxt = $(nicnTxt);
		nicnTxt.val(jQuery.trim(nicnTxt.val()));
		var nickNameTip = $('#nickNameTip');
		nickNameTip.empty();
		if (nicnTxt.val() == "" || nicnTxt.val().length < 1
				|| nicnTxt.val().length > 12) {
			nickNameTip.append(Register.NICK_NAME_FAIL);
			return false;
		}
		
		nickNameTip.append(Register.NICK_NAME_INFO2);
		
		if(Register.isExitNickName(nicnTxt.val(), false, nickNameTip)) {
			return false;
		} else {
			return true;
		}
	},
	
	passwordFocus : function() {
		var passwordTip = $('#passwordTip');
		passwordTip.empty();
		passwordTip.append(Register.PASSWORE_INFO);
	},
	
	passwordBlur : function(pasTxt) {
		pasTxt = $(pasTxt);
		var pas2Txt = $('#password2');
		var passwordTip = $('#passwordTip');
		passwordTip.empty();
		var password2Tip = $('#password2Tip');
		
		if (pasTxt.val().length < 6
				|| pasTxt.val().length > 20) {
			passwordTip.append(Register.PASSWORE_FAIL);
			return false;
		}
		if (pas2Txt.val() != "") {
			if (pas2Txt.val() != pasTxt.val()) {
				password2Tip.empty();
				password2Tip.append(Register.PASSWORE2_FAIL2);
			} else {
				password2Tip.empty();
				password2Tip.append(Register.OK);
			}
		}
		
		passwordTip.append(Register.OK);
		return true;
	},
	
	password2Focus : function() {
		var password2Tip = $('#password2Tip');
		password2Tip.empty();
		password2Tip.append(Register.PASSWORE2_INFO);
	},
	
	password2Blur : function(pas2Txt) {
		pas2Txt = $(pas2Txt);
		var pasTxt = $('#password');
		var password2Tip = $('#password2Tip');
		password2Tip.empty();
		
		if (pas2Txt.val().length < 6
				|| pas2Txt.val().length > 20) {
			password2Tip.append(Register.PASSWORE2_FAIL);
			return false;
		}
		if (pas2Txt.val() != pasTxt.val()) {
			password2Tip.append(Register.PASSWORE2_FAIL2);
			return false;
		}
		
		password2Tip.append(Register.OK);
		return true;
	},
	
	agreementClick : function(agrChb) {
		agrChb = $(agrChb);
		var agreementTip = $('#agreementTip');
		agreementTip.empty();
		if (!agrChb.attr('checked')) {
			agreementTip.append(Register.AGREEMENT_FAIL);
			return false;
		}
		return true;
	}
});

$(document).ready(function() {
	$("#account").focus(function() {
		Register.accountFocus(this);
	}).blur(function() {
		Register.accountBlur(this);
	});
	
	$("#nickName").focus(function() {
		Register.nickNameFocus(this);
	}).blur(function() {
		Register.nickNameBlur(this);
	});
	
	$('#password').focus(function() {
		Register.passwordFocus();
	}).blur(function() {
		Register.passwordBlur(this);
	});
	
	$('#password2').focus(function() {
		Register.password2Focus();
	}).blur(function() {
		Register.password2Blur(this);
	});
	
	$('#agreement').click(function(){
		Register.agreementClick(this);
	});
});

function submitRegisterForm() {
	var flag1 = Register.accountBlur(document.getElementById('account'));
	var flag2 = Register.nickNameBlur(document.getElementById('nickName'));
	var flag3 = Register.passwordBlur(document.getElementById('password'));
	var flag4 = Register.password2Blur(document.getElementById('password2'));
	var flag5 = Register.agreementClick(document.getElementById('agreement'));
	var flag = true;
	if (!flag1 || !flag2 || !flag3 || !flag4 || !flag5) {
		flag = false
	}
	
	if (flag) {
		var registerform = $('#registerform');
		registerform.submit();
	}
}