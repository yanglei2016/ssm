package com.yang.ssm.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.ssm.BaseTest;
import com.yang.ssm.domain.User;
import com.yang.ssm.service.UserService;

public class SpringTest extends BaseTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void userTest(){
		String id = "SYSAUTO";
		User user = userService.selectUser(id);
		System.out.println("用户信息："+user.toString());
	}
	
	@Test
	public void userCloneTest(){
		User user = new User();
		user.setUserId("1001");
		user.setUserName("zhangsan");
		
	}
	
}
