package com.yang.ssm.controller.system.menu;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.common.contants.PlatFormConstants;
import com.yang.common.tools.json.GsonUtils;
import com.yang.ssm.domain.Menu;
import com.yang.ssm.service.MenuService;
import com.yang.ssm.utils.ResponseUtils;

/**
 * 
 * @author yanglei
 * 2017年6月28日 上午10:25:29
 */
@Controller
@RequestMapping("system/menu")
public class MenuController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String ROOT_URL = "system/menu/";
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("menuTree.do")
	public String menuTree(Model model, HttpServletRequest request){
		String message = "跳转菜单管理";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		String treeData = menuService.getTreeData();
		model.addAttribute("treeData", treeData);
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		return ROOT_URL + "menu_tree_list";
	}
	
	@RequestMapping("selectOneMenu.do")
	public void selectOneMenu(HttpServletRequest request, HttpServletResponse response, String menuId){
		String message = "查询菜单";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(menuId)){
			Menu menu = menuService.selectOneMenu(menuId);
			if(menu != null){
				resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
				resultMap.put("menu", menu);
			}else{
				resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_ERROR);
				resultMap.put(PlatFormConstants.RESPONSE_MESSAGE, "根据菜单ID【"+ menuId +"】，未查询到菜单信息！");
			}
		}else{
			resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_ERROR);
			resultMap.put(PlatFormConstants.RESPONSE_MESSAGE, "操作失败，菜单ID为空！");
		}
		ResponseUtils.renderHtmlJson(response, GsonUtils.toJson(resultMap));
		logger.info(PlatFormConstants.MESSAGE_END, message);
	}
	
	@RequestMapping("deleteMenu.do")
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response, String menuIds){
		String message = "删除菜单";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(menuIds)){
			try{
				menuService.batchDeleteMenu(menuIds);
				resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
			}catch(Exception e){
				e.printStackTrace();
				resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_ERROR);
				resultMap.put(PlatFormConstants.RESPONSE_MESSAGE, "根据菜单ID【"+ menuIds +"】删除菜单异常！");
			}
		}else{
			resultMap.put(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_ERROR);
			resultMap.put(PlatFormConstants.RESPONSE_MESSAGE, "操作失败，菜单ID为空！");
		}
		ResponseUtils.renderHtmlJson(response, GsonUtils.toJson(resultMap));
		logger.info(PlatFormConstants.MESSAGE_END, message);
	}
	
	@RequestMapping("toMenuSave.do")
	public String toMenuSave(Model model, HttpServletRequest request, String parentId, String parentMenuName) throws UnsupportedEncodingException{
		String message = "打开新增菜单窗口";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		model.addAttribute("parentId", parentId);
		model.addAttribute("parentMenuName", URLDecoder.decode(parentMenuName, "utf-8"));
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		return ROOT_URL + "menu_save";
	}
	
	@RequestMapping("doMenuSave.do")
	public String doMenuSave(Model model, HttpServletRequest request, Menu menu, String pageType){
		String message = "新增/修改菜单";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		if(menu == null){
			throw new RuntimeException("菜单信息为空！");
		}
		
		if(PlatFormConstants.PAGE_TYPE_INSERT.equals(pageType)){
			Menu parentMenu = menuService.selectOneMenu(menu.getParentId());
			menu.setMenuLevel(parentMenu.getMenuLevel() + 1);
			menuService.insert(menu);
		}else if(PlatFormConstants.PAGE_TYPE_UPDATE.equals(pageType)){
			menuService.update(menu);
		}else{
			throw new RuntimeException("pageType类型错误");
		}
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
		model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作成功");
		model.addAttribute(PlatFormConstants.REDIRECT_URL, "menuTree.do");
		return PlatFormConstants.OPERATE_RESULT;
	}
	
}
