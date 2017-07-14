package com.yang.ssm.controller.system.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.common.contants.PlatFormConstants;
import com.yang.common.page.PageModel;
import com.yang.common.tools.MD5Util;
import com.yang.common.tools.json.GsonUtils;
import com.yang.ssm.domain.Role;
import com.yang.ssm.domain.User;
import com.yang.ssm.domain.UserRole;
import com.yang.ssm.service.RoleService;
import com.yang.ssm.service.UserService;

@Controller
@RequestMapping("system/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String ROOT_URL = "system/user/";
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("userList.do")
	public String userList(Model model, HttpServletRequest request, String userId){
		String message = "用户列表";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userId", userId);
		parameterMap.put(PageModel.PAGE_PARAMS, PageModel.initPage(request));
		
		List<User> userList = userService.selectUserList(parameterMap);
		
		model.addAttribute(PlatFormConstants.RESULT_LIST, userList);
		model.addAttribute(PlatFormConstants.PARAMETER_MAP, parameterMap);
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		return ROOT_URL + "user_list";
	}

	@RequestMapping("toUserOperation.do")
	public String operationSystemUser(Model model, HttpServletRequest request, String userId, String pageType){
		String returnPage = "";
		if(!PlatFormConstants.PAGE_TYPE_INSERT.equals(pageType)){
			if(StringUtils.isEmpty(userId)){
				throw new RuntimeException("用户userId不能为空");
			}
			User user = userService.selectUser(userId);
			model.addAttribute("user", user);
			
			List<UserRole> userRoleList = userService.selectUserRoleByUserId(userId);
			model.addAttribute("userRoleJson", GsonUtils.toJson(userRoleList));
		}
		if(PlatFormConstants.PAGE_TYPE_DETAIL.equals(pageType) || PlatFormConstants.PAGE_TYPE_DELETE.equals(pageType)){
			returnPage = ROOT_URL + "user_detail";
		}else{
			returnPage = ROOT_URL + "user_save";
		}
		
		List<Role> roleList = roleService.selectRoleList(null);
		model.addAttribute("roleList", roleList);
		
		model.addAttribute("pageType", pageType);
		return returnPage;
	}
	
	@RequestMapping("doUserSave.do")
	public String insertOrUpdateSave(Model model, HttpServletRequest request, User user, String pageType, String roleIds){
		String message = "角色新增/更新";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		if(PlatFormConstants.PAGE_TYPE_UPDATE.equals(pageType)){
			if(StringUtils.isEmpty(user.getUserId())){
				throw new RuntimeException("用户userId不能为空。");
			}
			userService.updateUser(user, roleIds);
		}else if(PlatFormConstants.PAGE_TYPE_INSERT.equals(pageType)){
			User checkUser = userService.selectUser(user.getUserId());
			if(checkUser != null){
				throw new RuntimeException("用户userId重复。");
			}
			user.setPassword(MD5Util.md5(user.getPassword()));
			userService.insertUser(user, roleIds);
		}else{
			throw new RuntimeException("pageType类型错误");
		}
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
		model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作成功");
		model.addAttribute(PlatFormConstants.REDIRECT_URL, "userList.do");
		return PlatFormConstants.OPERATE_RESULT;
	}
	
	@RequestMapping("doUserDelete.do")
	public String deleteSystemUser(Model model, HttpServletRequest request, User user, String pageType){
		String message = "用户删除";
		logger.info(PlatFormConstants.MESSAGE_START, message);
		
		if(PlatFormConstants.PAGE_TYPE_DELETE.equals(pageType)){
			if(StringUtils.isEmpty(user.getUserId())){
				throw new RuntimeException("删除用户userId为空");
			}
			userService.deleteUser(user.getUserId());
		}else{
			throw new RuntimeException("pageType类型错误");
		}
		
		logger.info(PlatFormConstants.MESSAGE_END, message);
		model.addAttribute(PlatFormConstants.RESPONSE_CODE, PlatFormConstants.CODE_SUCC);
		model.addAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作成功");
		model.addAttribute(PlatFormConstants.REDIRECT_URL, "userList.do");
		return PlatFormConstants.OPERATE_RESULT;
	}

	
}
