package com.yang.ssm.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.common.tools.MailThread;
import com.yang.common.tools.MailUtils;
import com.yang.ssm.BaseTest;

/**
 * 
 * @author yanglei 2017年7月4日 下午3:18:21
 */
public class MailSendTest extends BaseTest {

	@Autowired
	private MailUtils mailUtil;

	@Test
	public void sendSingleTest() {
		mailUtil.send("yangl016@ldygo.com", "测试主题", "测试内容");
	}

	@Test
	public void sendMassTest() {
		// List<String> recipients=new ArrayList<String>();
		// recipients.add("yangl016@ldygo.com");
		List<String> recipients = mailUtil.getRecipients();
		mailUtil.send(recipients, "测试附件", "测试附件发送");
	}

	@Test
	public void mailThreadTest() {
		Thread thread = new Thread(new MailThread(mailUtil, "测试附件", "测试附件发送"));
		thread.start();
	}

	@Test
	public void sendFileTest() {
		List<File> fileList = new ArrayList<File>();
		fileList.add(new File("F:/file/123.txt"));
		fileList.add(new File("F:/file/456.txt"));
		fileList.add(new File("F:/file/456.zip"));

		List<String> recipients = mailUtil.getRecipients();
		mailUtil.send(recipients, "测试附件", "测试附件发送", fileList);
	}

}
