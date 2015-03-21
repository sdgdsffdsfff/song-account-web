package com.song.account.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.song.account.web.config.ConfigManager;

public class ImgUrlTag extends TagSupport {

	private static final long serialVersionUID = -1504667666641721604L;

	private String imgUrl;

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int doEndTag() {
		ConfigManager cm = ConfigManager.getInstance();
		if (!imgUrl.startsWith("http://")) {
			imgUrl = cm.getResAccountUri() + imgUrl;
		}

		JspWriter out = pageContext.getOut();
		try {
			out.println(imgUrl);
		} catch (IOException e) {
		}
		return EVAL_PAGE;
	}
}
