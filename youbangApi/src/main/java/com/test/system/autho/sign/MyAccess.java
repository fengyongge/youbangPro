//package com.test.system.autho.sign;
//
//import com.sdc.system.autho.v1.sign.redis.SignRedis;
//import com.sdc.utils.ArrayUtil;
//import com.sdc.utils.ClientConstant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
//* description: 签名过滤方法 autho1.0
//*
//*
//* @author today zhaojintian@ediankai.com
//* @date 2017年3月6日
//* @Company: ediankai
//*/
//@Component
//public class MyAccess {
//
//	@Autowired
//	private AccessBean accessBean ;
//	@Autowired
//	private SignRedis signRedis ;
//	@Value("${const.OPENAUTHOTIMEOUT}")
//	private long expireTime;
//
//
//    //登录接口获取 供应商id
//    private Long getSupplierId( String  requestPath,String content ) {
//
//    	Long supplierId = 0l;
//    	//判断是登录接口
//    	if( requestPath.indexOf("staffservice/login") > -1 ){
////    	System.out.println(content);
//    		Pattern p = Pattern.compile("&supplier\\_id\\=[1-9]{1,}");
//    		Matcher m = p.matcher( content );
//
//    		if (m.find()) {
//    			//判断正则表达式是否匹配到
//    			//通过group来获取每个分组的值，group(0)代表正则表达式匹配到的所有内容，1代表第一个分组
//    		    String supplierString = m.group(0).replaceAll("&supplier_id=", "");
//    		    if( supplierString != null && !supplierString.trim().equals("") ) supplierId = Long.parseLong( supplierString );
//
//    		}
//
//    	}
//
//	    return supplierId;
//    }
//
//
//	public boolean  permisssion(MyHttpServletRequestWrapper requestWrapper,Long supplierId) throws IOException, ServletException {
//
//		String requestPath = requestWrapper.getRequestURI().toString();
////    	HttpServletRequest req = (HttpServletRequest) requestWrapper;
//
//		// 得到请求提交方式
//    	String[] allowType = accessBean.getJerseyRequestTypeAllow();
//		String method = requestWrapper.getMethod().toUpperCase();
//
//		StringBuilder content = new StringBuilder();
//		//判断允许的请求类型
//		if( !ArrayUtil.isInArray(method, allowType) ){
//			Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900001_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900001_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//            throw new WebApplicationException(response);
//		}
//
//
//	    //验证请求类型
//	    if ("GET".equals(method)) {
//	    	//GET方式获取参数
//	    	if (requestWrapper.getQueryString() != null)  content.append(requestWrapper.getQueryString());
//
//	    } else {
//	    	//其他方式获取参数
//		    BufferedReader in = new BufferedReader(new InputStreamReader(requestWrapper.getInputStream()));
//		    String org_req_content;
//		    while ( (org_req_content = in.readLine() ) != null) content.append(org_req_content);
//	    }
//	    //参数写入日志
//	    requestWrapper.setAttribute("SDCPARAMCODE",content.toString() );
////		   System.out.println(content + "****************");
//	    //判断参数非空
//	    if (content == null || "".equals((content+"").trim())) {
//	    	Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900003_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900003_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//            throw new WebApplicationException(response);
//	  	}
//
//	    //supplier Id 判断
//	    long loginSupplierId = this.getSupplierId(requestPath, content.toString());
//	    //supplierId写入日志
//	    requestWrapper.setAttribute("SDCSUPPLIERID",supplierId );
//	    if( loginSupplierId > 0 ){
//	         supplierId = loginSupplierId;
//	    }
//	    if( supplierId < 0 ){
//	    	Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900002_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900002_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//            throw new WebApplicationException(response);
//	    }
//	    accessBean.setSuppliers(supplierId);
////		     System.out.println( "******************************************" + supplierId);
//
//	    //参数解析处理
//	  	String[] tmp = content.toString().split("&");
//	  	Map<String, String> params = this.dealParam(tmp);
////		  	try {
////		  		params = this.dealParam(tmp);
////			} catch (Exception e) {
////				// TODO: handle exception
////				e.printStackTrace();
////			}
////		  	System.out.println( "******************************************" +  params.toString() ) ;
//	  	if( params == null ){
//
//	  		Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900004_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900004_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//            throw new WebApplicationException(response);
//	  	}
//        accessBean.setParams(params);
//
//
//        //校验签名
//        String aSign = Access.getSignature( accessBean );
////	         System.out.println(aSign + "*************" + accessBean.getSign());
//        requestWrapper.setAttribute("SDCSIGN",aSign );
////	         && accessBean.getSign().equals(aSign)
//        if( accessBean.getSign() != null && accessBean.getSign().length() > 0  ){
//	         //校验通过
//			return true;
//		}
//
//
//
//        Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900009_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900009_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//        throw new WebApplicationException(response);
//
//    }
//
//
//	//处理解析的参数
//	private Map<String, String> dealParam(String[] tmp) {
//
//		Map<String, String> params = new ConcurrentHashMap<String, String>();
//		//参数检测标志
//		int signP = 0;
//
//		for(String str: tmp){
////			System.out.println(str);
//	         String keyValue;
//	         String[] kValue = new String[2];
//	         StringBuffer sb = null;
//			try {
//				keyValue = URLDecoder.decode(str.toString(), "UTF-8");
//				if ( keyValue == null || keyValue.trim().equals("")) continue;
//
//				String[] kValue1 =  str.split("=");
//		         sb = new StringBuffer();
//		         for(int i=1;i<kValue1.length;i++){
//		   			sb.append(URLDecoder.decode(kValue1[i], "UTF-8"));
//		   		 }
//		         if( kValue1.length == 1 ){
//		      		 kValue[0] = kValue1[0];
//		      		 kValue[1] = "";
//		      	 }else{
//		      		kValue = kValue1;
//
//		      	 }
//		      	 kValue1 = null;
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//
//
//
//
//
//	      	long curTimes =  System.currentTimeMillis();
//
////	      	 if( !kValue[0].equals("sign") && !kValue[0].equals("publicKey") ) params.put(kValue[0].toString(), sb.toString() );
//	      	switch (kValue[0])
//	      	{
//		      	case "publicKey":
//
//		      		//获取公钥
//		      		if(kValue[1] != null && kValue[1].trim().length() >= 16 ){
////		      		if( kValue[1] == null || kValue[1].trim().equals(""))  return null;
//			      		accessBean.setPublicKey( kValue[1] );
//			      		//获取秘钥
//			      		accessBean.setAppSecret();
//			      		String appSecret = accessBean.getAppSecret();
//			      		if( appSecret==null || appSecret.trim().equals("") ) {
//			      			Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900005_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900005_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//			                throw new WebApplicationException(response);
//			      		}
////			      		System.out.println("******************************************" + appSecret);
//		      		}else{
//		      			Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900005_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900005_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//		                throw new WebApplicationException(response);
//		      		}
//		      		signP ++;
//		      	    break;
//		      	case "sign":
//		      		//获取签名
//			     	accessBean.setSign( kValue[1] );
//			     	String signTime = String.valueOf( curTimes );
//			     	if(kValue[1] != null && !kValue[1].trim().equals("")){
//				         if( signRedis.existSignKey(kValue[1]) ){
//				        	//存在访问的签名
//				        	 signRedis.setSignKey(kValue[1],  signTime);
//				        	 Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900006_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900006_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//			                 throw new WebApplicationException(response);
//				         }
//				         //记录签名访问
////				         signRedis.setSignKey(kValue[1], signTime);
//
//			     	}else{
//			     		Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900006_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900006_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//		                throw new WebApplicationException(response);
//			        }
//			     	signP ++;
//			     	break;
////		      	case "supplier_id":
////		      		String supplierString = kValue[1];
////	    		    if( supplierString != null && !supplierString.trim().equals("") ) accessBean.setSuppliers( Long.parseLong( supplierString ) );
////	    		    params.put(kValue[0].toString(), sb.toString() );
////	    		    break;
//		      	case "timestamp":
////		      		long signTimems = signTimes * 1000;
////		      		System.out.println(sb.toString());
//		      		params.put(kValue[0].toString(), sb.toString() );
//		      		if( kValue[1] != null ){
//		      			long requestTime = Long.parseLong( kValue[1] );
////		      			System.out.println( curTimes );
////		      			System.out.println( requestTime );
//		      			if( curTimes - requestTime > expireTime  || curTimes + expireTime < requestTime ){
//		      				// 当前时间 - 请求时间  > 0 并且 小于  expireTime
//		      				Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900007_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900007_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//			                throw new WebApplicationException(response);
//
//		      			}
//		      			accessBean.setTimestamp( requestTime );
//		      		}else{
//		      			Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900007_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900007_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//		                throw new WebApplicationException(response);
//		      		}
//		      		signP ++;
//		      		break;
//		      	default:
//		      		params.put(kValue[0].toString(), sb.toString() );
//	      	}
//
//        }
//
//		if( signP != 3){
//			Response response = Response.ok(com.sdc.utils.Response.info().setCode(ClientConstant.PERMISSION_ERROR_900008_STATUS).setMsg(ClientConstant.PERMISSION_ERROR_900008_STATUS_MSG).toJSON()).status(401).type(MediaType.APPLICATION_JSON).build();
//            throw new WebApplicationException(response);
//		}
//
//		return params;
//
//	}
//
//
//}
