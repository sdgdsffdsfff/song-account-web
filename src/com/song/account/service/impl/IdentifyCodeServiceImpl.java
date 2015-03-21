package com.song.account.service.impl;

import java.util.Calendar;
import java.util.Date;

import com.song.account.dao.IdentifyCodeDao;
import com.song.account.dao.UserDao;
import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.UserF;
import com.song.account.entity.IdentifyCode;
import com.song.account.entity.User;
import com.song.account.service.IdentifyCodeService;
import com.song.account.service.ErrService.IdentifyCodeS;
import com.song.commons.Sequence;
import com.song.commons.mail.MailSenderInfo;
import com.song.commons.mail.SimpleMailSender;
import com.song.commons.service.ServiceException;

public class IdentifyCodeServiceImpl implements IdentifyCodeService {
	
	private UserDao userDao;
	
	private IdentifyCodeDao identifyCodeDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setIdentifyCodeDao(IdentifyCodeDao identifyCodeDao) {
		this.identifyCodeDao = identifyCodeDao;
	}

	/**
	 * 不参与事务回滚
	 */
	@Override
	public boolean verifyEmail(Long userId, String code) {
		User user = userDao.queryById(userId);
		Long icId = user.getEmIcId();
		// 是否存在验证码，为获取验证码抛出异常
		if (icId == null || icId <= 0) {
			throw new ServiceException(IdentifyCodeS.ERR_104_001,
					"链接还未生成，请发送新的验证链接。");
		}
		
		IdentifyCode ic = identifyCodeDao.queryById(icId);
		if (!ic.getVerifyState().equals(IdentifyCode.VerifyState.NO_VERIFY)) {
			throw new ServiceException(IdentifyCodeS.ERR_104_002,
					"链接已经访问，不可重复访问。");
		}
		// 验证码提交时间判断
		Date now = new Date();
		if (now.before(ic.getStartTime()) || now.after(ic.getEndTime())) {
			// 修改验证结果
			identifyCodeDao.updateVerifyResult(icId, IdentifyCode.VerifyState.FAILING, now);
			throw new ServiceException(IdentifyCodeS.ERR_104_003,
					"链接访问已经过期。");
		}
		// 验证码正确性判断
		if (!ic.getCode().equals(code)) {
			// 修改验证结果
			identifyCodeDao.updateVerifyResult(icId, IdentifyCode.VerifyState.FAILING, now);
			throw new ServiceException(IdentifyCodeS.ERR_104_004,
					"邮箱验证码激活链接错误。");
		}
		if (userDao.queryByEnEmail(user.getEmail())!=null) {
			// 修改验证结果
			identifyCodeDao.updateVerifyResult(icId, IdentifyCode.VerifyState.FAILING, now);
			throw new ServiceException(IdentifyCodeS.ERR_104_005,
					"该邮箱已经有人激活，请更换邮箱。");
		}
		
		// 修改验证结果
		identifyCodeDao.updateVerifyResult(icId, IdentifyCode.VerifyState.SUCCESS, now);
		// 激活电子邮箱
		userDao.updateEnEmail(userId, user.getEmail());
		return true;
	}

	@Override
	public void sendEmailIdCode(Long userId, String url) {
		User user = userDao.queryById(userId);
		if (user.getEmail() == null || user.getEmail().equals("")) {
			return;
		}
		if (user.getEmail().equals(user.getEmIcId())) {
			return;
		}
		
		IdentifyCode ic = null;
		if (user.getEmIcId() <= 0) {
			// 创建验证码并发送
			ic = this.createIdCode(userId, "验证电子邮件" + user.getEmail(),
					Account.ACC_USER, UserF.EM_IC_ID);
			userDao.updateEmIcId(userId, ic.getIcId());
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setToAddress(user.getEmail());
			mailInfo.setSubject("衣美配邮箱验证");
			mailInfo.setContent(url + "?code=" + ic.getCode());
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendHtmlMail(mailInfo);
		} else {
			ic = identifyCodeDao.queryById(user.getEmIcId());
			if (ic.getVerifyState().equals(IdentifyCode.VerifyState.NO_VERIFY)) {
				// 发送验证码
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setToAddress(user.getEmail());
				mailInfo.setSubject("衣美配邮箱验证");
				mailInfo.setContent(url + "?code=" + ic.getCode());
				SimpleMailSender sms = new SimpleMailSender();
				sms.sendHtmlMail(mailInfo);
			} else if (ic.getVerifyState().equals(
					IdentifyCode.VerifyState.FAILING)) {
				// 创建验证码并发送
				ic = this.createIdCode(userId, "验证电子邮件" + user.getEmail(),
						Account.ACC_USER, UserF.EM_IC_ID);
				userDao.updateEmIcId(userId, ic.getIcId());
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setToAddress(user.getEmail());
				mailInfo.setSubject("衣美配邮箱验证");
				mailInfo.setContent(url + "?code=" + ic.getCode());
				SimpleMailSender sms = new SimpleMailSender();
				sms.sendHtmlMail(mailInfo);
			}
		}
	}

	private IdentifyCode createIdCode(Long dataId, String textDesc,
			Account table, UserF field) {
		IdentifyCode ic = new IdentifyCode();
		ic.setCode(Sequence.getInstance().getSequence(9));
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DAY_OF_YEAR, 1);
		ic.setStartTime(now);
		ic.setEndTime(c.getTime());
		ic.setDataId(dataId);
		ic.setTableName(table.name());
		ic.setFieldName(field.name());
		ic.setVerifyState(IdentifyCode.VerifyState.NO_VERIFY);
		ic.setTextDesc(textDesc);
		identifyCodeDao.insert(ic);
		return ic;
	}
}
