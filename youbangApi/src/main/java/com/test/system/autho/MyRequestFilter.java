//package com.test.system.autho;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.sdc.system.autho.v1.sign.MyAccess;
//import com.sdc.system.autho.v1.sign.MyHttpServletRequestWrapper;
//import com.sdc.utils.ClientConstant;
//
///**
//* description:
//*
//*
//* @author today zhaojintian@ediankai.com
//* @date 2017年3月13日
//* @Company: ediankai
//*/
//public class MyRequestFilter implements Filter{
//
//
//	@Autowired
//	private MyAccess myAccess;
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
//	 */
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//
//		//设置线程全局唯一id
//    	UUID uuid = UUID.randomUUID();
//    	Thread.currentThread().setName(uuid.toString());
//
//		MyHttpServletRequestWrapper requestWrapper = null;
//        if(request instanceof HttpServletRequest) {
//            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);
//        }
//
//        if(requestWrapper == null) {
//            chain.doFilter(request, response);
//        } else {
//            chain.doFilter(requestWrapper, response);
//        }
//
//	}
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.Filter#destroy()
//	 */
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//}
