package com.test.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;


public class Response 
{
	
	private String total;
	private String persize;
	private String pagesum;
	/**
	 * Response 消息
	 */
	private String msg;
	
	/**
	 *Response 错误返回码
	 */
	private String code;
	
	/**
	 *Response 数据 
	 */
	private Object data;
	
	/**
	 * 鏈夋晥鏃堕棿鎺у埗
	 */
	private String token;
	
	
	private String index;
	
	//单例
	private static Gson gson = null;
	//静态工厂方法   
    public static Gson getInstance(){  
         if (gson == null) {    
        	 gson = new Gson();  
         }    
        return gson;  
    }  
    
	public String getPagesum() {
		return pagesum;
	}

	public Response setPagesum(String pagesum) {
		this.pagesum = pagesum;
		return this;
	}
	public String getIndex() {
		return index;
	}

	public Response setIndex(String index) {
		this.index = index;
		return this;
		
	}

	public String getTotal() {
		return total;
	}

	public Response setTotal(String total) {
		this.total = total;
		return this;
	}

	public String getPersize() {
		return persize;
	}

	public Response setPersize(String persize) {
		this.persize = persize;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Response setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Response setCode(String code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Response setData(Object data) {
//		Gson g = getInstance();
		this.data = data;
		return this;
	}

	public String getToken() {
		return token;
	}

	public Response setToken(String token) {
		this.token = token;
		return this;
	}

	public static Response info() {
		return new Response();
	}

public String toJSON() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		if(StringUtils.isNotBlank(this.code)){
			sb.append("\"code\":\"").append(code).append("\",");
		}
		if(StringUtils.isNotBlank(this.msg)){
			sb.append("\"msg\":\"").append(msg).append("\",");
		}
		if(StringUtils.isNotBlank(this.token)){
			sb.append("\"token\":\"").append(token).append("\",");
		}
		if(this.data != null){
//			JsonMapper jsonM = JsonMapper.getInstance();
			sb.append("\"data\":").append(data).append(",");
		}
		if(StringUtils.isNotBlank(this.index)){
			sb.append("\"index\":\"").append(index).append("\",");
		}
		if(StringUtils.isNotBlank(this.total)){
			sb.append("\"total\":\"").append(total).append("\",");
		}
		if(StringUtils.isNotBlank(this.persize)){
			sb.append("\"persize\":\"").append(persize).append("\",");
		}
		if(StringUtils.isNotBlank(this.pagesum)){
			sb.append("\"pagesum\":\"").append(pagesum).append("\",");
		}		
		sb.append("}");
		String result = sb.toString().replace(":null",":\"\"");
		
		if(result.lastIndexOf(",")==-1){
			return result;
		}
		return result.substring(0, result.lastIndexOf(",")).concat(result.substring(result.lastIndexOf(",")+1));
	}
	
	
public static void main(String[] args) {
		
		Response re = new Response();
		re.setData("data").setCode("code").setTotal("total");
		
		List<Response> list = new ArrayList<Response>();
		list.add(re);
		list.add(re);
		
		String result =
				
				Response.info().setCode("200").setTotal("23").setData(new Date()).toJSON();
		
	
		
	}
	
	
}
