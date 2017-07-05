package com.yang.ssm.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
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
		System.out.println("=====================单发==========begin==========");
		mailUtil.send("yangl016@ldygo.com", "测试主题", "测试内容");
		System.out.println("=====================单发==========end==========");
	}

	@Test
	public void sendMassTest() {
		System.out.println("=====================群发==========begin==========");
		// List<String> recipients=new ArrayList<String>();
		// recipients.add("yangl016@ldygo.com");
		List<String> recipients = mailUtil.getRecipients();
		mailUtil.send(recipients, "测试附件", "测试附件发送");
		System.out.println("=====================群发==========end==========");
	}

	@Test
	public void mailThreadTest() {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("=====================群发==========begin==========");
		Thread thread = new Thread(new MailThread(mailUtil, "测试附件", "测试附件发送"));
		thread.start();
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("=====================群发==========end==========");
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
