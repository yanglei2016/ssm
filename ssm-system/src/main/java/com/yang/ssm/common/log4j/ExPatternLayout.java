package com.yang.ssm.common.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

/**
 * log4j转换线程名为线程ID
 *
 */
public class ExPatternLayout extends PatternLayout {
	 
	 public ExPatternLayout(String pattern) {
	   super(pattern);
	  }
	 
	 public ExPatternLayout() {
	   super();
	  }
	  
	  /**
	   * 重写createPatternParser方法，返回PatternParser的子类
	   */
	  @Override
	  protected PatternParser createPatternParser(String pattern) {
	   return new ExPatternParser(pattern);
	  }
	 }