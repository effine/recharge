
/**
 * @author effine
 * @Date 2015年8月4日  下午3:30:00
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties文件操作类
 */
public class PropertiesUtils {
	private PropertiesUtils() {
		// 构造方法私有化，外部不能实例化该类 
	}
	
	private static Properties payProperties;
	private static Properties alipayProperties;
	
	
	static {
		payProperties = new Properties();
		alipayProperties = new Properties();
		try {
			// 解析文件pay.properties
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("pay.properties");
			payProperties.load(is);
			is.close();
			
			// 解析文件alipay.properties
			InputStream alipayIs = Thread.currentThread().getContextClassLoader().getResourceAsStream("wechat.properties");
			alipayProperties.load(alipayIs);
			alipayIs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取pay.properties文件属性
	 * 
	 * @param key
	 *            文件key
	 * @return 文件key对应value
	 */
	public static String getPayValue(String key) {
		return payProperties.getProperty(key);
	}
	
	/**
	 * 获取alipay.properties文件属性
	 * 
	 * @param key
	 *            文件key
	 * @return 文件key对应value
	 */
	public static String getAlipayValue(String key) {
		return alipayProperties.getProperty(key);
	}
}