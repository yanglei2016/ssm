package com.yang.common.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

/**
 * 日期工具类
 * @author yanglei
 * 2017年7月4日 上午9:06:14
 */
public class DateUtils {
	
	private Logger logger = Logger.getLogger(getClass());

	/**日期格式 yyyy-MM-dd*/
	public static final String DATE_FMT_D = "yyyy-MM-dd";
	/**日期格式 yyyy-MM-dd HH:mm:ss*/
	public static final String DATE_FMT_DT = "yyyy-MM-dd HH:mm:ss";
	/**日期格式 yyyyMMddHHmmss*/
	public static final String DATE_FMT_DT_SEQ = "yyyyMMddHHmmss";
	
	
	private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    // 获取线程的变量副本，如果不覆盖initialValue，第一次get返回null，故需要初始化一个SimpleDateFormat，并set到threadLocal中
    private static DateFormat getDateFormat(String pattern) {
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }
    
    public static String getCurrentDateTime(String pattern) {
        return getCurrentDateTime(pattern, new Date());
    }
    
    public static String getCurrentDateTime(String pattern, Date date) {
        return getDateFormat(pattern).format(date);
    }
    
    
    public static void main(String[] args) {
    	System.out.println(DateFormatUtils.format(new Date(), DATE_FMT_D));
    	System.out.println(DateFormatUtils.format(new Date(), DATE_FMT_DT));
    	
    	DateFormat dateFormat = new SimpleDateFormat(DATE_FMT_D);
    	System.out.println(dateFormat.format(new Date()));
		System.out.println(getCurrentDateTime(DATE_FMT_D));
		System.out.println(getCurrentDateTime(DATE_FMT_DT));
		System.out.println(getCurrentDateTime(DATE_FMT_DT_SEQ));
	}
}
