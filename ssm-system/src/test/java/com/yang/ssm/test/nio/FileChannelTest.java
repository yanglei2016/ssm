package com.yang.ssm.test.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

	public static void main(String[] args){
		String inFilePath = "E:/test/nio/a.txt";
		String outFilePath = "E:/test/nio/b.txt";
		copyFile(inFilePath, outFilePath);
	}
	
	public static void copyFile(String inFilePath, String outFilePath){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			fis = new FileInputStream(new File(inFilePath));
			fos = new FileOutputStream(new File(outFilePath));
			
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = fos.getChannel();
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(2);
			while(true){
				byteBuffer.clear();
				
				int read = inChannel.read(byteBuffer);
				if(read == -1){
					break;
				}
				
				byteBuffer.flip();
				
				outChannel.write(byteBuffer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
