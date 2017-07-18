package com.yang.ssm.test.pdf;

import java.util.HashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.common.tools.pdf.DataProvideVo;
import com.yang.common.tools.pdf.PDFCreateUtils;
import com.yang.common.tools.pdf.PDFCreateService;
import com.yang.common.tools.pdf.TemplateEdit;
import com.yang.common.tools.pdf.TemplateVo;
import com.yang.ssm.BaseTest;

/**
 * 
 * @author yanglei
 * 2017年7月17日 上午10:14:49
 */
public class PDFTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(TemplateEdit.class);
	
	@Test
	public void createPdfTest(){
		DataProvideVo data = getDataProvide();
		
		String templatePath = PDFCreateUtils.getTemplatePath("01");
		TemplateVo templateVo = PDFCreateUtils.loadTemplate(templatePath);
		
		String pathFileName = "F:/work/pdf/01.pdf";
		
		TemplateEdit service = new TemplateEdit();
		service.updateTemplate(templateVo, data);
		
		PDFCreateService pdfCreateService = new PDFCreateService();
		if(pdfCreateService.createFile(templateVo, pathFileName)){
        	logger.info("...........成功...........");
        }else{
        	logger.info("...........失败...........");
        }
		
	}
	
	
	public static DataProvideVo getDataProvide() {
		HashMap<String, Object> allData = new HashMap<String, Object>();
		
		allData.put("contractNo", "LS201132165465");
		allData.put("year", "2017");
		allData.put("month", "07");
		allData.put("date", "17");
		allData.put("borrowRealName", "张三");
		allData.put("identNo", "411528200703170001");
		
		DataProvideVo dataProvideVo = new DataProvideVo();
		dataProvideVo.setAllData(allData);
		return dataProvideVo;
	}
}
