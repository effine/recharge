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
	
		/*------------------------必选-----------------------*/
		//商户订单号
		String out_trade_no = "";
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		//必填
		String WIDsubject = "";
		//付款金额
		String WIDtotal_fee = "";
		//必填
		/*	----------------------可选----------------------	 */ 
		//订单描述
	
		String body = "";
		//商品展示地址
		String show_url = "";
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
		
		
		/*-------------------系统参数----------------------*/
		
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = "";
	
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
	 * @param show_url
	 *            支付宝支付完成调整的URL
	 * @return
	 */
	public String intoParameter(String out_trade_no, String subject, String total_fee, String show_url) {
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		return AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
	}
}
