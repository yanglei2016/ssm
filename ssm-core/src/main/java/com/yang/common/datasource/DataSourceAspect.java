package com.yang.common.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 有{@com.yang.common.datasource.DataSourceType}注解的方法，调用时会切换到指定的数据源
 * @author yanglei
 * 2017年8月23日 下午2:47:15
 */
@Aspect
@Component
public class DataSourceAspect {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	@Around("@annotation(dataSourceType)")
	public Object doAround(ProceedingJoinPoint pjp, DataSourceType dataSourceType){
		Object rtnVal = null;
        boolean selectedDataSource = false;
        try {
            if (null != dataSourceType) {
                selectedDataSource = true;
                if (dataSourceType.slave()) {
                    DynamicDataSource.useSlave();
                } else {
                    DynamicDataSource.useMaster();
                }
            }
            rtnVal = pjp.proceed();
        } catch (Throwable e) {
        	logger.warn("数据源切换错误", e);
            throw new RuntimeException("数据源切换错误", e);
        } finally {
            if (selectedDataSource) {
                DynamicDataSource.reset();
            }
        }
        return rtnVal;
	}
}
