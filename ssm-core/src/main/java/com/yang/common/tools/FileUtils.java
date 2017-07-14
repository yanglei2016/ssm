package com.yang.common.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * @author yanglei
 * 2017年7月5日 下午3:22:36
 */
public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 文件下载
	 * @param file
	 * @param fileName（含扩展名）  空则默认为file.getName()
	 * @param response
	 * @author yanglei
	 * 2017年7月14日 上午11:07:29
	 */
	public static void downLoadFile(File file, String fileName, HttpServletResponse response){
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			if(!file.exists()){
				logger.info("file not found : {}", file.getAbsolutePath());
				throw new FileNotFoundException("file not found : " + file.getAbsolutePath());
			}
			if(StringUtils.isBlank(fileName)){
				fileName = file.getName();
			}
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("gb2312"), "iso-8859-1"));
			
			bis = new BufferedInputStream(new FileInputStream(file));
			os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int n;
			while((n = bis.read(buffer)) != -1){
				os.write(buffer, 0, n);
			}
			os.flush();
		} catch (IOException e) {
			logger.error("文件下载异常", e);
		}finally{
			try {
				os.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
