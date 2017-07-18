package com.yang.common.tools.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
			total = writer.getDirectContent().createTemplate(10, 10);
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
	public void onCloseDocument(PdfWriter arg0, Document arg1) {
		total.beginText();
		total.setFontAndSize(bf, 10);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(arg0.getPageNumber() - 1));
		total.endText();
	}

	@Override
	public void onEndPage(PdfWriter arg0, Document arg1) {
		isNewPage = true;
		// 页数
		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 30, 30 });
			table.setTotalWidth(arg1.getPageSize().getWidth());
			table.setLockedWidth(true);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setBorderWidth(0);
			Font font = new Font(bf, 10, 0);
			Paragraph paragraph = new Paragraph(String.format("%d/", arg0.getPageNumber()), font);
			table.addCell(paragraph);

			PdfPCell cell = new PdfPCell(Image.getInstance(total));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorderWidth(0);
			table.addCell(cell);
			table.writeSelectedRows(0, -1, 0, 30, arg0.getDirectContent());

		} catch (DocumentException e) {
			logger.error("创建页码异常", e);
		}
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
