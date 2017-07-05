package com.yang.common.tools;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 * @author yanglei 2017年7月4日 下午3:03:21
 */
public class MailThread implements Runnable {

	private MailUtils mailUtil;
	private String subject;
	private String content;

	public MailThread(MailUtils mailUtil, String subject, String content) {
		this.mailUtil = mailUtil;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public void run() {
		System.out.println("======1===="+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		List<String> recipients = new ArrayList<String>();
//		recipients.add("yangl016@ldygo.com");
		List<String> recipients = mailUtil.getRecipients();
		mailUtil.send(recipients, subject, content);
		System.out.println("======2===="+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

}
