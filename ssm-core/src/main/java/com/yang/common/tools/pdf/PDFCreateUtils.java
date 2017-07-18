package com.yang.common.tools.pdf;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.common.tools.PropertiesUtils;

/**
 * PDF工具类
 * @author yanglei
 * 2017年7月17日 上午9:52:07
 */
public class PDFCreateUtils {

	private static final Logger logger = LoggerFactory.getLogger(PDFCreateUtils.class);
	
	public static TemplateVo loadTemplate(String templatePath){
		//加载XML模板
        TemplateVo templateVo = TemplateParse.templateParse(templatePath);
        if (templateVo == null){
            logger.error("加载模板文件失败！");
            throw new RuntimeException("加载模板文件失败！");
        }
        try {
        	//克隆模板
			templateVo = (TemplateVo)templateVo.deepClone();
		} catch (Exception e) {
			throw new RuntimeException("克隆模板文件失败！");
		}	
        return templateVo;
	}
	
	public static String getTemplatePath(String templateId){
		String templatePath = PropertiesUtils.getProp("template.path");
        if (templatePath == null || templatePath.isEmpty())
        {
            logger.error("模板路径template.path没有配置.");
            throw new RuntimeException("模板路径template.path没有配置.");
        }
        templatePath = templatePath + File.separator + templateId + ".xml";
        return templatePath;
	}
	
	
}
