package com.yang.ssm.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		// 向线程池里面扔任务
        for (int i = 0; i < 4; i++) {
            threadPool.execute(new MyThread("张三" + i));
        }
        
        threadPool.shutdown();
	}
	
}
