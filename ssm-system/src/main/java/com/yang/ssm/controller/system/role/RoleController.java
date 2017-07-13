package com.yang.ssm.controller.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.common.contants.PlatFormConstants;
import com.yang.common.page.PageModel;
import com.yang.ssm.domain.Role;
import com.yang.ssm.service.MenuService;
import com.yang.ssm.service.RoleService;

/**
 * 
 * @author yanglei
 * 2017年6月29日 下午4:32:09
 */
@Controller
@RequestMapping("system/role")
public class RoleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String ROOT_URL = "system/role/";
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	/**
	 * 角色列表
	 * @author yanglei
	 * 2017年6月30日 上午9:03:44
	 */
	@RequestMapping("roleList.do")
	public String roleList(Model model, HttpServletRequest request){
		String message = "角色列表";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(PageModel.PAGE_PARAMS, PageModel.initPage(request));
		
		List<Role> roleList = roleService.selectRoleList(parameterMap);
		
		model.addAttribute(PlatFormConstants.RESULT_LIST, roleList);
		model.addAttribute(PlatFormConstants.PARAMETER_MAP, parameterMap);
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		return ROOT_URL + "role_list";
	}
	
	/**
	 * 角色详细/修改/删除跳转
	 * @author yanglei
	 * 2017年6月30日 上午9:03:57
	 */
	@RequestMapping("toRoleOperation.do")
	public String toRoleOperation(Model model, HttpServletRequest request, Integer roleId, String pageType){
		String message = "角色详细/修改/删除跳转";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		String returnPage = "";
		if(!PlatFormConstants.PAGE_TYPE_INSERT.equals(pageType)){
			if(roleId == null){
				throw new RuntimeException("角色roleId不能为空");
			}
			Role role = roleService.selectRoleById(roleId);
			model.addAttribute("role", role);
			
			Map<String, Object> refMap = roleService.getRoleMenuRef(roleId);
			model.addAttribute("refMap", refMap);
		}
		if(PlatFormConstants.PAGE_TYPE_DETAIL.equals(pageType) || PlatFormConstants.PAGE_TYPE_DELETE.equals(pageType)){
			returnPage = ROOT_URL + "/role_detail";
		}else{
			returnPage = ROOT_URL + "/role_save";
		}
		
		//菜单树JSON数据
		String treeData = menuService.getTreeData();
		model.addAttribute("treeData", treeData);
		model.addAttribute("pageType", pageType);
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		return returnPage;
	}
	
	/**
	 * 角色新增
	 * @author yanglei
	 * 2017年6月30日 上午9:05:09
	 */
	@RequestMapping("doRoleSave.do")
	public String doRoleSave(Model model, HttpServletRequest request, Role role, String pageType, String menuIds){
		String message = "角色新增";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		if(PlatFormConstants.PAGE_TYPE_UPDATE.equals(pageType)){
			if(role.getRoleId() == null){
				throw new RuntimeException("角色roleId不能为空。");
			}
			roleService.updateRole(role, menuIds);
		}else if(PlatFormConstants.PAGE_TYPE_INSERT.equals(pageType)){
			roleService.insertRole(role, menuIds);
		}else{
			throw new RuntimeException("pageType类型错误");
		}
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
		model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作成功");
		model.addAttribute(PlatFormConstants.REDIRECT_URL, "roleList.do");
		return PlatFormConstants.OPERATE_RESULT;
	}
	
	/**
	 * 角色删除
	 * @author yanglei
	 * 2017年6月30日 上午9:05:03
	 */
	@RequestMapping("doRoleDelete.do")
	public String doRoleDelete(Model model, HttpServletRequest request, Role role, String pageType){
		String message = "角色删除";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		if(PlatFormConstants.PAGE_TYPE_DELETE.equals(pageType)){
			if(role.getRoleId() == null){
				throw new RuntimeException("删除角色roleId为空。");
			}
			roleService.deleteRole(role.getRoleId());
		}else{
			throw new RuntimeException("pageType类型错误");
		}
		logger.info(PlatFormConstants.MESSAGE_END, message);
		model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
		model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作成功");
		model.addAttribute(PlatFormConstants.REDIRECT_URL, "roleList.do");
		return PlatFormConstants.OPERATE_RESULT;
	}
}
