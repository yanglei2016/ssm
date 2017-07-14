package com.yang.ssm.test.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.common.tools.mq.ActiveMqUtil;
import com.yang.ssm.BaseTest;

public class ActiveMqTest extends BaseTest {

	@Autowired
	private ActiveMqUtil activeMqUtil;
	
	@Test
	public void userTest(){
		activeMqUtil.send("test.mq.yanglei", "12342143测试消息");
	}
	
}
