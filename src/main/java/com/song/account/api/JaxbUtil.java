package com.song.account.api;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Jaxb2工具类
 * 
 * @author songzigw
 *
 */
public class JaxbUtil {

	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @param out
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding,
			PrintWriter out) {
		if (encoding == null) {
			encoding = "UTF-8";
		}
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			if (out != null) {
				marshaller.marshal(obj, out);
			}

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		return convertToXml(obj, encoding, null);
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
}
