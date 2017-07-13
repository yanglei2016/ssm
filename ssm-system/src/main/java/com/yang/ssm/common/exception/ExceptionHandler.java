package com.yang.ssm.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yang.ssm.common.constants.PlatFormConstants;

/**
 * 异常拦截处理
 * @author yanglei
 * 2017年6月16日 上午8:43:47
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object paramObject, Exception ex) {
		request.setAttribute("ex", ex);
        request.setAttribute(PlatFormConstants.RESPONSE_CODE, "error");
		request.setAttribute(PlatFormConstants.RESPONSE_MESSAGE, "操作失败：" + ex.getMessage());
		request.setAttribute(PlatFormConstants.MESSAGE_EXCEPTION_DETAIL, ex.getMessage());// 异常详情
		
		logger.error("操作异常", ex);
		return new ModelAndView(PlatFormConstants.OPERATE_RESULT);
	}

}
