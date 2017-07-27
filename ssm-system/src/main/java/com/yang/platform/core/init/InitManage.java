package com.yang.platform.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化管理
 * @author yanglei
 * 2017年7月27日 下午2:50:13
 */
public class InitManage {

	private static final Logger logger = LoggerFactory.getLogger(InitManage.class);
	
	public static void printCopyright(){
		logger.info("******************************************************");
		logger.info("*                     杨雷                                                                                   *");
		logger.info("*                Java EE 开发平台                                                                *");
		logger.info("*                v1.0.0 2017-07                      *");
		logger.info("******************************************************");
	}
}
