package com.yang.common.tools.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导出EXCEL
 * @author HP
 *
 * @param <T>
 */
public class ExportExcel<T> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Workbook createExcel(String title, String[] headers, Collection<T> dataset) {
        // 声明一个工作薄
        Workbook workbook = new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度
        sheet.setDefaultColumnWidth(18);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
       
        // 生成一个字体
        
        // 生成并设置另一个样式
        


        // 产生表格标题行
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
	            if(fieldName != null && "serialVersionUID".equalsIgnoreCase(fieldName) ){
	            	continue;
	            }
	            
                
	            Cell cell = row.createCell(i);
//                cell.setCellStyle(style2);
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
					Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;    
                    if(null!=value){
                    	textValue = value.toString();
                    }
                        
//                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            XSSFRichTextString richString = new XSSFRichTextString(
                                    textValue);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
        return workbook;
    }
}
