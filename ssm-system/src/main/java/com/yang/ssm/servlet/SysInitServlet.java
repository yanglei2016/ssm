package com.yang.ssm.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yang.common.contants.PlatFormConstants;

/**
 * 系统初始化加载类
 * @author yanglei
 * 2017年7月14日 下午3:42:39
 */
@Component
@WebServlet(name="sysInitServlet", urlPatterns={"/sysInitServlet"}, loadOnStartup=2)
public class SysInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String message = "系统初始化";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		
//		UserService userService = ctx.getBean("userServiceImpl", UserService.class);
//		List<User> userList = userService.selectUserList(null);
//		logger.info(GsonUtils.toJson(userList));
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
	}
	
	
}
