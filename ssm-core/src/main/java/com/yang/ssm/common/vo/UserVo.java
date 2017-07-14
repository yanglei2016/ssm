package com.yang.ssm.common.vo;

import java.util.Map;

/**
 * 登录session用户对象
 * @author yanglei
 * 2017年6月16日 上午9:32:09
 */
public class UserVo {

	private String userId;
	private String userName;
	private Map<String, String> menuIdMap;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Map<String, String> getMenuIdMap() {
		return menuIdMap;
	}
	public void setMenuIdMap(Map<String, String> menuIdMap) {
		this.menuIdMap = menuIdMap;
	}
}
