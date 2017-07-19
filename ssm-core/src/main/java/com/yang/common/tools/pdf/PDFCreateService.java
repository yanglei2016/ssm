package com.yang.common.tools.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.yang.common.tools.pdf.entity.BaseNodeVo;
import com.yang.common.tools.pdf.entity.ImageNodeVo;
import com.yang.common.tools.pdf.entity.PNodeVo;
import com.yang.common.tools.pdf.entity.TableNodeVo;

public class PDFCreateService implements PdfPageEvent {
	private Logger logger = Logger.getLogger(PDFCreateService.class);

	public BaseFont bf;

	private PdfTemplate total;
	
	/**页眉 */  
    public String header = "这是页眉";  
    /**文档字体大小，页脚页眉最好和文本大小一致 */  
    public int presentFontSize = 10;  
    /**利用基础字体生成的字体对象，一般用于生成中文文字  */  
    public Font fontDetail = null;  

	private boolean isNewPage = false;

	public boolean createFile(TemplateVo templateVo, String pathFileName) {
		if (templateVo == null || pathFileName == null) {
			return false;
		}

		List<BaseNodeVo> listNodes = templateVo.getXmlNodeList();

		if (listNodes.size() == 0) {
			logger.error("没有配置全局模版节点！");
			return false;
		}

		if (pathFileName == null || pathFileName.isEmpty()) {
			logger.error("文件名为空！");
			return false;
		}

		File tmpPathFileName = new File(pathFileName);
		String pathName = tmpPathFileName.getParent();
		File directory = new File(pathName);
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				logger.error("目录创建失败：[" + pathName + "]");
			}
		}

		float marginLeft = 36;
		float marginRight = 36;
		float marginTop = 36;
		float marginBottom = 40;
		Document document = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);

		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(pathFileName);
		} catch (FileNotFoundException e) {
			logger.error("打开文件失败！", e);
			return false;
		}

		try {
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			writer.setPageEvent(this);

			document.open();
			// headerTable = HeaderCreate.create(globalModel,
			// document.getPageSize().getWidth());

			for (BaseNodeVo baseNodeVo : listNodes) {
				try {
					if (baseNodeVo instanceof PNodeVo) {
						Paragraph paragraph = PDFHelperUtil.createParagraph((PNodeVo) baseNodeVo,
								templateVo.getGlobalNodeVo());
						if (paragraph == null) {
							continue;
						}

						isNewPage = false;
						document.add(paragraph);
					} else if (baseNodeVo instanceof TableNodeVo) {
						PdfPTable table = PDFHelperUtil.createTable((TableNodeVo) baseNodeVo,
								templateVo.getGlobalNodeVo());
						if (table == null) {
							continue;
						}

						isNewPage = false;
						document.add(table);
					} else if (baseNodeVo instanceof ImageNodeVo) {
						Paragraph paragraph = PDFHelperUtil.createImage((ImageNodeVo) baseNodeVo);
						if (paragraph == null) {
							continue;
						}

						isNewPage = false;
						document.add(paragraph);
					} else if (baseNodeVo instanceof BaseNodeVo) {
						String nodeName = baseNodeVo.getNodeName();
						if (nodeName == null || nodeName.trim().isEmpty()) {
							continue;
						}

						if (nodeName.equalsIgnoreCase("np") && !isNewPage) {
							document.newPage();
						}
					}
				} catch (Exception e) {
					logger.error("异常", e);
					continue;
				}
			}

			document.close();

		} catch (DocumentException e) {
			logger.error("生成文件异常", e);
			return false;
		} catch (Exception e) {
			logger.error("生成文件异常", e);
			return false;
		}

		return true;
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		try {
			isNewPage = true;
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			total = writer.getDirectContent().createTemplate(300, 10);
		} catch (Exception e) {

		}

	}

	@Override
	public void onChapter(PdfWriter arg0, Document arg1, float arg2, Paragraph arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChapterEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document arg1) {
		// 7.最后一步了，就是关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。  
        total.beginText();  
        total.setFontAndSize(bf, 10);// 生成的模版的字体、颜色  
        total.setTextMatrix(0, 0);
        total.showText("" + (writer.getPageNumber()) + "");// 模版显示的内容  
        total.endText();  
        total.closePath();  
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		isNewPage = true;
		// 页数
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 30, 30 });
			table.setTotalWidth(document.getPageSize().getWidth());
			table.setLockedWidth(true);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setBorderWidth(0);
			Font font = new Font(bf, 10, 0);
			Paragraph paragraph = new Paragraph(String.format("%d/", writer.getPageNumber()), font);
			table.addCell(paragraph);

			PdfPCell cell = new PdfPCell(Image.getInstance(total));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderWidth(0);
			table.addCell(cell);
			table.writeSelectedRows(0, -1, 0, 30, writer.getDirectContent());

		} catch (DocumentException e) {
			logger.error("创建页码异常", e);
		}
		
//		try {  
//            if (bf == null) {  
//                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);  
//            }  
//            if (fontDetail == null) {  
//                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);// 数据体字体  
//            }  
//        } catch (DocumentException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    
//        // 1.写入页眉  
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, fontDetail), document.left(), document.top() + 20, 0);  
//           
//        // 2.写入前半部分的 第 X页/共  
//        int pageS = writer.getPageNumber();  
//        String foot1 = "第 " + pageS + " 页 /共";  
//        Phrase footer = new Phrase(foot1, fontDetail);  
//    
//        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len  
//        float len = bf.getWidthPoint(foot1, presentFontSize);  
//    
//        // 4.拿到当前的PdfContentByte  
//        PdfContentByte cb = writer.getDirectContent();  
//         
//        //自己增加的  
//        if(pageS==1){  
//	        Phrase footerLeft = new Phrase("", fontDetail);  
//	        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footerLeft, document.left(), document.bottom() - 20, 0);  
//        }  
//          
//        // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F 再给偏移20F适合人类视觉感受，否则肉眼看上去就太偏左了 ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。  
//        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F, document.bottom() - 20, 0);  
//    
//        // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F + len ， y 轴和之前的保持一致，底边界-20  
//        cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F, document.bottom() - 20); // 调节模版显示的位置  
//		
	}

	@Override
	public void onGenericTag(PdfWriter arg0, Document arg1, Rectangle arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onParagraph(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onParagraphEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSection(PdfWriter arg0, Document arg1, float arg2, int arg3, Paragraph arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSectionEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartPage(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub

	}
}
