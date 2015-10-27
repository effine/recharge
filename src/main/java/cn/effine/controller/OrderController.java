
/**
 * @author effine
 * @Date 2015年8月19日  上午10:07:08
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.effine.utils.Constants;
import cn.effine.utils.StringCustomUtils;
import cn.effine.utils.alipay.DateInterface;

@Controller
@RequestMapping("order")
public class OrderController {
	
	/**
	 * 生成订单，调用支付宝支付
	 * 
	 * @param subject
	 *            商品名称
	 * @param total_fee
	 *            商品价格(单位：分)
	 */
	@RequestMapping("alipay")
	public void aliSubmit(HttpServletRequest request, HttpServletResponse response, String subject, String total_fee){
		// 生成商户订单号
		String ordernum = StringCustomUtils.getRandomNum(10);
		
		//对接支付宝
		DateInterface d = new DateInterface();
		//String message = d.intoParameter(orderNum, subject, total_fee, "");
		String message = d.intoParameter(ordernum, subject, total_fee, Constants.PAGE_MAP.get("show_url"));
		System.out.println("调试信息： " + message);
	}
	
	/**
	 * 支付宝支付完成的通知请求(回调方法)进行订单状态修改
	 * 
	 * @return 字符串状态[success|fail]
	 */
	@RequestMapping("notify")
	@ResponseBody
	public String alipayNotify(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> params = new HashMap<String,String>();
		// 获取支付宝POST过来反馈信息
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> itorator = requestParams.keySet().iterator(); itorator.hasNext();) {
			String name = itorator.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}
		
		String out_trade_no = null;	//商户订单号
		String trade_status = null;	//交易状态
		
		try {
			out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
//		if(AlipayNotify.verify(params)){
			if(trade_status.equals("TRADE_SUCCESS")){
					 System.out.println("订单" + out_trade_no + "处理成功");
				}
//		}
		return "fail";	
	}
}


