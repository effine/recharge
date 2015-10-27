/**
 * @author effine
 * @Date 2015年10月27日  上午10:26:11
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量处理
 */
public class Constants {
	// 页面管理
	public static Map<String, String> PAGE_MAP = null;

	static {
		PAGE_MAP = new HashMap<String,String>();
		
		PAGE_MAP.put("show_url", "showUrl");
	}

}
