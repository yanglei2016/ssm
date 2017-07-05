package com.yang.ssm.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
	
	private String name;
	
	public MyThread(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(name + " said:" + i);
        }
	}
}
