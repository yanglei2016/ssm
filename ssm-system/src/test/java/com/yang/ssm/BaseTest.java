package com.yang.ssm;

import javax.servlet.ServletRequestEvent;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextListener;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BaseTest {

	@Before
	public void before(){
		RequestContextListener listener = new RequestContextListener(); 
		MockServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest(); 
		listener.requestInitialized(new ServletRequestEvent(context, request)); 
	}
}
