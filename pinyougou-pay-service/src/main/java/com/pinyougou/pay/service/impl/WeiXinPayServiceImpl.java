package com.pinyougou.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wxpay.sdk.WXPayUtil;
import com.pinyougou.pay.service.WeiXinPayService;

import util.HttpClient;

@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {

	@Value("${appid}")
	private String appid;
	
	@Value("${partner}")
	private String partner;
	
	@Value("${partnerkey}")
	private String partnerkey;
	
	@Override
	public Map createNative(String out_trade_no, String total_fee) {

		//1.参数封装
		Map param = new HashMap<>();
		param.put("appid", appid );
		param.put("mch_id",partner);
		
		param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
		param.put("body", "品优购");
		param.put("out_trade_no",out_trade_no);//订单号
		param.put("total_fee",total_fee);//金额
		param.put("spbill_create_ip","127.0.0.1");
		param.put("notify_url", "http://www.itcast.cn");
		param.put("trade_type","NATIVE");

		try {
			String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
			System.out.println("请求的参数："+xmlParam);
			
			//2.发送请求
			HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
			httpClient.setHttps(true);
			httpClient.setXmlParam(xmlParam);
			httpClient.post();
		
			//3.获取结果
			String xmlResult = httpClient.getContent();
			System.out.println("微信返回的结果:"+xmlResult);
			
			Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);
			Map map = new HashMap<>();
			//code_url
			map.put("code_url",mapResult.get("code_url"));//生产支付二维码的链接
			map.put("out_trade_no", out_trade_no);
			map.put("total_fee", total_fee);
			
			return map;
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new HashMap<>();
		}
		
	}

}
