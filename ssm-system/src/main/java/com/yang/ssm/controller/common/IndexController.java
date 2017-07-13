package com.yang.ssm.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.common.contants.PlatFormConstants;
import com.yang.ssm.common.vo.UserVo;
import com.yang.ssm.domain.Menu;
import com.yang.ssm.service.MenuService;

/**
 * 
 * @author yanglei
 * 2017年6月14日 下午4:19:56
 */
@Controller
public class IndexController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index.do")
	public String index(Model model, HttpServletRequest request){
		return "login";
	}
	
	@RequestMapping("/loginSuccess.do")
	public String loginSuccess(Model model, HttpServletRequest request){
		UserVo userVo = (UserVo)request.getSession().getAttribute(PlatFormConstants.USER_INFO);
		List<Menu> leftMenuList = menuService.selectLeftMenuList(userVo.getUserId());
		model.addAttribute("leftMenuList", leftMenuList);
		return "layout/index";
	}
	
	/**
	 * 跳转到欢迎页面
	 * @author yanglei
	 * 2017年6月14日 下午4:28:48
	 */
	@RequestMapping("/welcome.do")
	public String welcome(Model model, HttpServletRequest request){
		return "layout/welcome";
	}
}
