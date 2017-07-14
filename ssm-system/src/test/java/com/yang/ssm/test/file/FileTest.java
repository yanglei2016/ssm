package com.yang.ssm.test.file;

import org.junit.Test;

import com.yang.common.contants.PropertiesContants;
import com.yang.ssm.BaseTest;

/**
 * 
 * @author yanglei
 * 2017年7月14日 上午9:52:56
 */
public class FileTest extends BaseTest {

	@Test
	public void testProperties(){
		System.out.println("dubbo.host=====:"+PropertiesContants.DUBBO_HOST);
	}
}
