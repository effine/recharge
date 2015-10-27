/**
 * @author effine
 * @Date 2015年8月19日  上午10:07:08
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils.alipay;

import java.util.HashMap;
import java.util.Map;

import cn.effine.utils.PropertiesUtils;

/**
 * 自定义处理数据接口
 *
 */
public class DateInterface {
	
		// 商品描述
		String body = null;
		
		//支付类型
		String payment_type = "1";
		//页面跳转同步通知页面路径
		String return_url = PropertiesUtils.getAlipayValue("ali_returnURL");
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数
	
		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1

	/**
	 * 初始化参数列表
	 *
	 * @param out_trade_no
	 *            商户订单号
	 * @param subject
	 *            商品名称
	 * @param total_fee
	 *            商品金额
	 * @return
	 */
	public String intoParameter(String out_trade_no, String subject, String total_fee) {
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", PropertiesUtils.getAlipayValue("notify_url"));
		sParaTemp.put("return_url", PropertiesUtils.getAlipayValue("return_url"));
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", PropertiesUtils.getAlipayValue("show_url"));
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		return AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
	}
}
