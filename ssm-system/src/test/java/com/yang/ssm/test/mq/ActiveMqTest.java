package com.yang.ssm.test.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.ssm.BaseTest;
import com.yang.ssm.utils.ActiveMqUtil;

public class ActiveMqTest extends BaseTest {

	@Autowired
	private ActiveMqUtil activeMqUtil;
	
	@Test
	public void userTest(){
		activeMqUtil.send("test.mq.yanglei", "12342143测试消息");
	}
	
}
