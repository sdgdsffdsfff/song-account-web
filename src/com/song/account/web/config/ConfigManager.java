package com.song.account.web.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 属性管理器
 * 
 * @author 张松
 * 
 */
public class ConfigManager {

	private static Logger logger = Logger.getLogger(ConfigManager.class);

	/** 当前网站版本号 */
	private static final String WEBSITE_VERSION = "website.version";

	/** 账号系统资源文件 */
	private static final String RES_ACCOUNT_URI = "res.account.uri";

	/** 账号系统URI网址 */
	private static final String WEBSITE_ACCOUNT_URI = "website.account.uri";

	/** 搭配网运营平台URI网址 */
	private static final String WEBSITE_OPNDP_URI = "website.opndp.uri";
	
	/** 搭配网URI网站 */
	private static final String WEBSITE_DAPEI_URI = "website.dapei.uri";
	
	/** 属性文件全名 */
	private static final String PFILE = "basic-config-account.properties";

	/** 属性文件的文件对象 */
	private File file;

	/** 属性文件的最后修改日期 */
	private long lastModifiedTime = 0;

	/** 属性文件对应的属性对象 */
	private Properties props;

	/** 本类存在的唯一实例 */
	private static ConfigManager instance = new ConfigManager();

	private ConfigManager() {
		URL s = Thread.currentThread().getContextClassLoader().getResource(
				PFILE);
		logger.info(s.getFile());
		file = new File(s.getFile());
		lastModifiedTime = file.lastModified();
		if (lastModifiedTime == 0) {
			throw new RuntimeException("属性文件不存在！");
		}
		props = new Properties();
		try {
			props.load(new FileInputStream(s.getFile()));
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 静态工厂方法
	 * 
	 * @return
	 */
	public static ConfigManager getInstance() {
		return instance;
	}

	/**
	 * 读取一个特定的属性项
	 * 
	 * @param key
	 * @return
	 */
	public final Object getConfigItem(String key) {
		long newTime = file.lastModified();
		// 检查属性文件是否被其他程序
		// 修改过，如果是，重新读取此文件
		if (newTime == 0) {
			throw new RuntimeException("属性文件被删除");
		} else if (newTime > lastModifiedTime) {
			props.clear();
			URL s = Thread.currentThread().getContextClassLoader().getResource(PFILE);
			try {
				props.load(new FileInputStream(s.getFile()));
			} catch (FileNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			}
		}
		lastModifiedTime = newTime;
		return props.getProperty(key);
	}

	/**
	 * 获取当前网站版本号
	 * @return
	 */
	public String getWebsiteVersion() {
		return (String) this.getConfigItem(WEBSITE_VERSION);
	}

	/**
	 * 获取账号系统资源文件
	 * @return
	 */
	public String getResAccountUri() {
		return (String) this.getConfigItem(RES_ACCOUNT_URI);
	}

	/**
	 * 获取账号系统URI网址
	 * @return
	 */
	public String getWebsiteAccountUri() {
		return (String) this.getConfigItem(WEBSITE_ACCOUNT_URI);
	}

	/**
	 * 获取运营平台URI网址
	 * @return
	 */
	public String getWebsiteOpndpUri() {
		return (String) this.getConfigItem(WEBSITE_OPNDP_URI);
	}
	
	public String getWebsiteDapeiUri() {
		return (String) this.getConfigItem(WEBSITE_DAPEI_URI);
	}
}
