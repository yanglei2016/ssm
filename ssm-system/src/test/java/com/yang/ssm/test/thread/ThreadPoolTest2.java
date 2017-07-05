package com.yang.ssm.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

	public static void main(String[] args) {
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "   starting...");
		
//		threadPool.schedule(new MyThread(""), 2, TimeUnit.SECONDS);
//		threadPool.shutdown();
		
//		threadPool.scheduleAtFixedRate(new MyThread(""), 2, 3,TimeUnit.SECONDS);
		
		threadPool.scheduleWithFixedDelay(new MyThread(""), 2, 3,TimeUnit.SECONDS);
	}

}
