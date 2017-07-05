package com.yang.ssm.util;

import org.apache.log4j.Logger;

/**
 * <p>
 * 签名工具类
 * </p>
 * 
 * @author wanghui
 * @version $Id: SignUtil.java, v 0.1 2014-6-17 下午4:26:06 wanghui Exp $
 */
public class SignUtil {
	
	private static Logger logger = Logger.getLogger(Logger.class);
	
	/**
	 * 签名
	 * 
	 * @param content
	 *            签名内容
	 * @param signMsg
	 *            网关返回签名           
	 * @param signType
	 *            MD5/RSA
	 * @param signKey
	 *            密钥
	 * @param charset
	 *            字符集
	 * @return
	 * @throws Exception
	 */
	public static boolean Check_sign(String content,String signMsg,String signType, String signKey,String charset) throws Exception {
		logger.info("*********返回参数****待验签*******：\n"+ content);
		if ("MD5".equalsIgnoreCase(signType)) {
			return MD5.verify(content, signMsg, signKey, charset);
		} else if ("RSA".equalsIgnoreCase(signType)) {
			return RSA.verify(content, signMsg,signKey,charset);
		}
		return false;
	}

	public static String sign(String content, String signType, String signKey,
			String charset) throws Exception {
		logger.info("*********请求参数****待签名*******：\n"+ content);
		if ("MD5".equalsIgnoreCase(signType)) {
			return MD5.sign(content, signKey, charset);
		} else if ("RSA".equalsIgnoreCase(signType)) {
			return RSA.sign(content, signKey, charset);
		}
		return "";
	}

}
