package com.yang.ssm.servlet;

import java.util.List;

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
import com.yang.common.tools.json.GsonUtils;
import com.yang.ssm.domain.User;
import com.yang.ssm.service.UserService;

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
	
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String message = "系统初始化";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		
		userService = ctx.getBean("userServiceImpl", UserService.class);
		List<User> userList = userService.selectUserList(null);
		logger.info(GsonUtils.toJson(userList));
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
	}
	
	
}
