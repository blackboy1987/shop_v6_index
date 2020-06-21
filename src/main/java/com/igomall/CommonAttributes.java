
package com.igomall;

/**
 * 公共参数
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public final class CommonAttributes {

	/**
	 * 日期格式配比
	 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/**
	 * shopxx.xml文件路径
	 */
	public static final String 好源XX_XML_PATH = "classpath:shopxx.xml";

	/**
	 * shopxx.properties文件路径
	 */
	public static final String 好源XX_PROPERTIES_PATH = "classpath:shopxx.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}