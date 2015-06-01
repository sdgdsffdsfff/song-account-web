package com.song.account.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import com.song.commons.api.util.GsonUtil;

public class ServletUtil {

	public static void print(HttpServletResponse rsp, Object o, Type t)
			throws IOException {
		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(o, t));
		out.flush();
		out.close();
	}
}
