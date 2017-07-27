package com.yang.platform.core.mvc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.yang.platform.core.init.InitManage;

/**
 * Spring MVC的DispatcherServlet
 * @author yanglei
 * 2017年7月27日 下午3:13:01
 */
public class SpringMVCServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1392066633825877487L;

	@Override
	protected WebApplicationContext initWebApplicationContext() {
		WebApplicationContext context = super.initWebApplicationContext();
		InitManage.printCopyright();
		return context;
	}
}
