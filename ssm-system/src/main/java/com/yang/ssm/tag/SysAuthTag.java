package com.yang.ssm.tag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yang.common.contants.PlatFormConstants;
import com.yang.ssm.common.vo.UserVo;

/**
 * 权限过滤自定义标签
 * @author yanglei
 * 2017年7月3日 下午2:52:59
 */
@Component
public class SysAuthTag extends BodyTagSupport {

	private static final long serialVersionUID = 8892254233207387894L;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String no;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	@Override
	public int doEndTag() throws JspException {
		this.showTagInnerHtml();
		return SKIP_BODY;
	}
	
	public void showTagInnerHtml(){
		if(this.bodyContent != null){
			JspWriter out = this.pageContext.getOut();
			String JspHtmlString = this.bodyContent.getString();
//			System.out.println("JspHtmlString=====:\n"+ JspHtmlString);
			
			boolean showFlag = checkAuth();
			if(!showFlag){
				if(JspHtmlString.contains("<input")){
					JspHtmlString = JspHtmlString.replace("<input", "<input disabled=\"disabled\"");
				}else{
					JspHtmlString="";
				}
			}
			
			try{
				String[] paramArr = JspHtmlString.split("\\$\\{");// 根据${进行拆分
				if(paramArr.length > 1){
					for(String s : paramArr){
						if(s.indexOf("}") != -1){
							String key = "${" + s.substring(0, s.indexOf("}") + 1);
							String value = getStrExp(key);
							if (StringUtils.isEmpty(value)) {
								value="";
							}
							JspHtmlString = JspHtmlString.replace(key, value);
						}
					}
				}
				out.write(JspHtmlString);
			}catch(Exception e){
				logger.error("权限过滤异常：", e);
				try {
					out.write(e.getMessage());
				}catch (Exception ex){}
			}
		}
	}
	
	public boolean checkAuth(){
		boolean authFlag = false;
		Map<String, String> menuIdMap = this.getMenuIdMap();
		if(MapUtils.isNotEmpty(menuIdMap)){
			if(StringUtils.isNotEmpty(menuIdMap.get(no))){
				authFlag = true;
			}
		}
		return authFlag;
	}
	
	private String getStrExp(String express) throws JspException {
		return (String) getExp(express);
	}
	
	private Object getExp(String express) throws JspException {
		if (express == null) {
			return "";
		} else if (!express.startsWith("${")) {
			return express;
		}
		try {
			return ExpressionUtil.evalNotNull("out", "value", express, java.lang.Object.class, this, pageContext);
		} catch (NullAttributeException e) {
			return null;
		} catch (JspException e) {
			throw e;
		}
	}
	
	private Map<String, String> getMenuIdMap(){
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		UserVo userVo = (UserVo)request.getSession().getAttribute(PlatFormConstants.USER_INFO);
		return userVo.getMenuIdMap();
	}
}
