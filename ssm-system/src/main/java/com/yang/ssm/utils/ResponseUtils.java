package com.yang.ssm.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yang.ssm.common.constants.PlatFormConstants;

/**
 * HttpServletResponse工具类
 * @author HP
 */
public final class ResponseUtils{
	
	public static final Logger log = Logger.getLogger(ResponseUtils.class);
    
	/**
	 * 发送json。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json;charset="+PlatFormConstants.CHARSET_UTF8, text);
	}
	
	/**
	 * 发送内容。使用UTF-8编码。
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 发送文本。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset="+PlatFormConstants.CHARSET_UTF8, text);
	}
	
	/**
	 * 修正chrome和ie8不能解析application/json的问题
	 * @param response
	 * @param text
	 */
	public static void renderHtmlJson(HttpServletResponse response,
			String text) {
		render(response, "text/html;charset="+PlatFormConstants.CHARSET_UTF8, text);
	}

}
